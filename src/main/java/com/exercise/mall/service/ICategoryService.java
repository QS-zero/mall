package com.exercise.mall.service;

import com.exercise.mall.pojo.Category;
import com.exercise.mall.vo.CategoryVo;
import com.exercise.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

public interface ICategoryService {

    ResponseVo<List<CategoryVo>> selectALl();

    void findSubCategoryId(Integer id, Set<Integer> resultSet);


}
