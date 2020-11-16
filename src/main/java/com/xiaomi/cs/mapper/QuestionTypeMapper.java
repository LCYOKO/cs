package com.xiaomi.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.QuestionType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  22:02
 */
public interface QuestionTypeMapper extends BaseMapper<QuestionType> {
    //添加知识库
    @Insert("insert into quesion_type (type) values(#{val})")
	@Options(useGeneratedKeys=true)
     void insertQuestionType(String val);
    //批量查询知识库
    @Select("select * from  quesion_type where  id>#{id}")
    List<QuestionType> selectQuestionType(Integer id);

    //批量查询知识库
    @Select("select id from  quesion_type where  type=#{val}")
    Integer selectQuestionTypeByVal(String val);

}
