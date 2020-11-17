package com.xiaomi.cs.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  20:52
 */
@Data
public class KnowledgeLibrary implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //标准问题
    private String question;
    //标准答案
    private String answer;
    //创建时间
    private Long createTime;
    //更新时间
    private Long updateTime;
    //关键词
    private String  keywords;
    //问题分类
    @TableField(exist=false)
    private QuestionType questionType;
}
