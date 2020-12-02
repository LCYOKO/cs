package com.xiaomi.cs.elasticsearch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author duyan
 * @create 2020-11-14  20:52
 */
@Data
@Document(indexName = "knowledges",type = "knowledge")
public class KnowledgeLibraryES implements Serializable {
    @Field(type = FieldType.Integer)
    private Integer id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String question;
    @Field(type = FieldType.Text)
    private String answer;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String  keywords;
}
