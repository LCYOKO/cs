package com.xiaomi.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.cs.mapper.KnowledgeLibraryMapper;
import com.xiaomi.cs.mapper.QuestionTypeMapper;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.PageSerializable;
import com.xiaomi.cs.pojo.entity.QuestionType;
import com.xiaomi.cs.service.KnowledgeLibraryService;
import com.xiaomi.cs.service.QuestionTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
@Service
public class QuestionTypeServiceImpl extends ServiceImpl<QuestionTypeMapper, QuestionType> implements QuestionTypeService {


    @Override
    public void addQuestionType(String val) {
         this.baseMapper.insertQuestionType(val);
    }

    @Override
    public List<QuestionType> getQuestionTypes() {
         return this.getBaseMapper().selectQuestionType(0);
    }

    @Override
    public Integer getQuestionTypeByVal(String val) {
        return this.baseMapper.selectQuestionTypeByVal(val);
    }
}
