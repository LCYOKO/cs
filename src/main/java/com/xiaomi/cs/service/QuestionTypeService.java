package com.xiaomi.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.PageSerializable;
import com.xiaomi.cs.pojo.entity.QuestionType;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
public interface QuestionTypeService extends IService<QuestionType> {
    //添加问题分类
    public void addQuestionType(String val);
    //批量查询问题分类
    public List<QuestionType> getQuestionTypes();
    //内容查询查询问题分类
    public Integer  getQuestionTypeByVal(String val);
}
