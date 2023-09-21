package com.exercise.mall.dao;

import com.exercise.mall.pojo.OrderItem;
import com.exercise.mall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;


@Repository
public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    int deleteByIdKeyAndUid(@Param("uid") Integer uid,
                            @Param("shippingId") Integer shippingId);

    List<Shipping> selectByUid(Integer uid);

    Shipping selectByUidAndShipping(@Param("uid") Integer uid,
                                    @Param("shippingId") Integer shippingId);

    List<Shipping> selectByShippingIdSet(@Param("shippingIdSet") Set shippingIdSet);

}