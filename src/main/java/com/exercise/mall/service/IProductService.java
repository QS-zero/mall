package com.exercise.mall.service;


import com.exercise.mall.vo.ProductDetailVo;
import com.exercise.mall.vo.ProductVo;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface IProductService {

    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);
}
