package com.xiaomi.cs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.cs.mapper.CategoryMapper;
import com.xiaomi.cs.pojo.entity.Category;
import com.xiaomi.cs.service.CategoryService;

import java.util.List;

/**
 * @author l
 * @create 2020-11-15-17:32
 */
public class CategortServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
    @Override
    public List<Category> selAll() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public Category selCategoryById(int id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int delCategoryById(int id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int addCategory(Category category) {
        return this.baseMapper.insert(category);
    }
}
