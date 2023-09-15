package com.exercise.mall.service.impl;

import com.exercise.mall.dao.ProductMapper;
import com.exercise.mall.enums.ProductStatusEnum;
import com.exercise.mall.enums.ResponseEnum;
import com.exercise.mall.form.CartAddForm;
import com.exercise.mall.form.CartUpdateForm;
import com.exercise.mall.pojo.Cart;
import com.exercise.mall.pojo.Product;
import com.exercise.mall.service.ICartService;
import com.exercise.mall.vo.CartProductVo;
import com.exercise.mall.vo.CartVo;
import com.exercise.mall.vo.ResponseVo;
import com.google.gson.Gson;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CartServerImpl implements ICartService {

    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    @Override
    public ResponseVo<CartVo> add(Integer uid, CartAddForm form) {
        Integer quantity = 1;

        Product product = productMapper.selectByPrimaryKey(form.getProductId());

        //判断商品是否存在
        if (product == null){
            return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);
        }

        //商品是否正常在售
        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())){
            return ResponseVo.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE);
        }

        //商品库存是否充足
        if (product.getStock() <= 0) {
            ResponseVo.error(ResponseEnum.PRODUCT_STOCK_ERROR);
        }

        //写入到Redis
        //key:Cart_Uid  eg. key:cart_1
        // H:redis的Key HK:哈希的KEY HV:哈希的value(hash套hash???)
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        Cart cart;
        String value = opsForHash.get(redisKey, String.valueOf(product.getId()));
        if (StringUtils.isEmpty(value)){
            //没有该商品,新增
            cart = new Cart(product.getId(), quantity, form.getSelected());
        }else {
            //已经有数量,累加
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + quantity);
        }

        opsForHash.put(redisKey,
                String.valueOf(product.getId()),
                gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {

        boolean selectAll = true;

        Integer cartTotalQuantity = 0;

        BigDecimal cartTotalPrice = BigDecimal.ZERO;

        //创建redis的hash结构
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        //获取redis的key
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //通过key得到该redis的数据
        Map<String, String> entries = opsForHash.entries(redisKey);

        CartVo cartVo = new CartVo();
        List<CartProductVo> cartProductVoList = new ArrayList<>();

//        Set<Map.Entry<String, String>> cartEntrySet = entries.entrySet();
//
//        List<Product> products = productMapper.selectByProductId(cartEntrySet);
//        for (Product product : products) {
//
//        }

        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Integer productId = Integer.valueOf(entry.getKey());
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            //TODO 需要优化,使用MySQL的in
            //优化的话做子查询,子查询条件为product的内容
            //用redis购物车里面的数据在子条件里面查询
            Product product = productMapper.selectByPrimaryKey(productId);
            if (product != null) {
                CartProductVo cartProductVo = new CartProductVo(productId,
                        cart.getQuantity(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getMainImage(),
                        product.getPrice(),
                        product.getStatus(),
                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        product.getStock(),
                        cart.getProductSelected());
                cartProductVoList.add(cartProductVo);
                if (!cart.getProductSelected()) {
                    selectAll = false;
                }

                //计算总价(只计算选中的)
                if (cart.getProductSelected()) {
                    cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                }
            }

            cartTotalQuantity += cart.getQuantity();

        }
        cartVo.setSelectedAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateForm form) {

        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)){
            //没有该商品,数据有问题,报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }

        //已经有数量,更改内容
        Cart cart = gson.fromJson(value, Cart.class);
        if (form.getQuantity() != null && form.getQuantity() >= 0){
            cart.setQuantity(form.getQuantity());
        }
        if (form.getSelected() != null) {
            cart.setProductSelected(form.getSelected());
        }

        opsForHash.put(redisKey,
                String.valueOf(productId),
                gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid, Integer productId) {

        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)){
            //没有该商品,数据有问题,报错
            return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        }

        //有商品,直接删除
        opsForHash.delete(redisKey,
                String.valueOf(productId));

        return list(uid);
    }
}
