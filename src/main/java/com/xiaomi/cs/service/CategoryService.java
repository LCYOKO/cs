package com.xiaomi.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.cs.pojo.entity.Category;

import java.util.List;

/**
 * @author l
 * @create 2020-11-15-17:24
 */
public interface CategoryService extends IService<Category> {

    List<Category> selAll();
    Category  selCategoryById(int id);
    int delCategoryById(int id);
    int addCategory(Category category);
}
