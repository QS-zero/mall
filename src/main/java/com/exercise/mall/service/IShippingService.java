package com.exercise.mall.service;


import com.exercise.mall.form.ShoppingForm;
import com.exercise.mall.pojo.Shipping;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.omg.CORBA.INTERNAL;

import java.util.Map;

public interface IShippingService {

    ResponseVo<Map<String, Integer>> add(Integer uid, ShoppingForm form);

    ResponseVo delete(Integer uid, Integer shippingId);

    ResponseVo update(Integer uid, Integer shippingId ,ShoppingForm form);

    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);
}
