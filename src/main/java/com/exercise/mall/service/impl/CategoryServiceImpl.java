package com.exercise.mall.service.impl;


import com.exercise.mall.consts.MallConst;
import com.exercise.mall.dao.CategoryMapper;
import com.exercise.mall.pojo.Category;
import com.exercise.mall.service.ICategoryService;
import com.exercise.mall.vo.CategoryVo;
import com.exercise.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {
    /**
     * 耗时：http请求 > 磁盘 > 内存
     *      mysql(内网+磁盘)
     */

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public ResponseVo<List<CategoryVo>> selectALl() {

//        List<CategoryVo> categoryVoList = new ArrayList<>();

        List<Category> categories = categoryMapper.selectAll();

        //查出parent_id = 0
//        for (Category category : categories) {
//            if (category.getParentId().equals(MallConst.ROOT_PATENT_ID)) {
//                CategoryVo categoryVo = new CategoryVo();
//                BeanUtils.copyProperties(category, categoryVo);
//                categoryVoList.add(categoryVo);
//            }
//        }

        //lambda + stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(MallConst.ROOT_PATENT_ID))
                .map(this::category2CategoryVo)
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());

        //查询子目录
        findSubCategory(categoryVoList, categories);
        return ResponseVo.success(categoryVoList);
    }

    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {

        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(id, resultSet, categories);
    }

    private void findSubCategoryId(Integer id,
                                   Set<Integer> resultSet,
                                   List<Category> categories){
        for (Category category : categories) {
            if (category.getParentId().equals(id)) {
                resultSet.add(category.getId());
                findSubCategoryId(category.getId(), resultSet, categories);
            }
        }
    }

    private void findSubCategory(List<CategoryVo> categoryVoList,
                                 List<Category> categories){
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoriesList = new ArrayList<>();
            for (Category category : categories) {
                //查到，设置为subCategories，继续查
                if (categoryVo.getId().equals(category.getParentId())) {
                    CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoriesList.add(subCategoryVo);
                }

                subCategoriesList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
                categoryVo.setSubCategory(subCategoriesList);

                //递归查询
                findSubCategory(subCategoriesList, categories);
            }
        }
    }

    private CategoryVo category2CategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }
}
