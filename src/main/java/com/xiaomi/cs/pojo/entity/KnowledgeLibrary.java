package com.xiaomi.cs.pojo.entity;

import lombok.Data;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  20:52
 */
@Data
public class KnowledgeLibrary {
    private Integer id;
    //标准问题
    private String question;
    //标准答案
    private String answer;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
    //问题分类
    private QuestionType questionType;
    //关键词
    private List<SimilarQuestion> similarQuesion;
}
