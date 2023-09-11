package com.exercise.mall.service;

import com.exercise.mall.pojo.Category;
import com.exercise.mall.vo.CategoryVo;
import com.exercise.mall.vo.ResponseVo;

import java.util.List;

public interface ICategoryService {

    ResponseVo<List<CategoryVo>> selectALl();


}
