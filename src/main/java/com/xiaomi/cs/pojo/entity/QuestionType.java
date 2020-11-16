package com.xiaomi.cs.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author duyan
 * @create 2020-11-14  20:56
 */
@Data
public class QuestionType {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String type;
}
