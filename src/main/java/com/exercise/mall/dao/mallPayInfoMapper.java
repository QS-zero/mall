package com.exercise.mall.dao;

import com.exercise.mall.pojo.mallPayInfo;

public interface mallPayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(mallPayInfo record);

    int insertSelective(mallPayInfo record);

    mallPayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(mallPayInfo record);

    int updateByPrimaryKey(mallPayInfo record);
}