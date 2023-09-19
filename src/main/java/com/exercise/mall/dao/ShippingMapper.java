package com.exercise.mall.dao;

import com.exercise.mall.pojo.Shipping;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


@Repository
public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);
}