package com.exercise.mall.service;


import com.exercise.mall.vo.OrderVo;
import com.exercise.mall.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

public interface IOrderService {

    ResponseVo<OrderVo> create(Integer uid, Integer shippingId);

    ResponseVo<PageInfo> list(Integer uid, Integer pangNum, Integer pageSize);

    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);
}
