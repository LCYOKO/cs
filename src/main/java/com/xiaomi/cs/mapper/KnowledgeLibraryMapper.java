package com.xiaomi.cs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  22:02
 */
public interface KnowledgeLibraryMapper extends BaseMapper<KnowledgeLibrary> {
    //添加知识库
    @Insert("insert into knowledge_library (question, answer,question_type_id,keywords) values(#{question},#{answer},#{questionType.id},#{keywords})")
	@Options(useGeneratedKeys=true,keyProperty="id")
     void insertKnoeledge(KnowledgeLibrary knowledgeLibrary);
    //更新知识库
     void updateKnoeledge(KnowledgeLibrary knowledgeLibrary);
    //批量查询知识库
     List<KnowledgeLibrary> selectKnoeledges(String question, Integer questionTypeId, Integer pageno, Integer limit);
     //查询总数
    int  selectKnoeledgesCount(KnowledgeLibrary knowledgeLibrary);
}
