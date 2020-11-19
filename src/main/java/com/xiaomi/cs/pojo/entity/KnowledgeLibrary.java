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

    private String question;

    private String answer;

    private String createTime;

    private String updateTime;

    private String  keywords;

    private Integer questionTypeId;
    @TableField(exist = false)
    private QuestionType questionType;
}
