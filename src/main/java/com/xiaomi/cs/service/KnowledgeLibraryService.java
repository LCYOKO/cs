package com.xiaomi.cs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.PageSerializable;
import com.xiaomi.cs.pojo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
public interface KnowledgeLibraryService  extends IService<KnowledgeLibrary> {
    /**
     * 添加知识库
     */
     int addKnowledge(KnowledgeLibrary knowledgeLibrary);

    /**
     *  更新知识库
     */
     int updateKnowledge(KnowledgeLibrary knowledgeLibrary);


    /**
     * 删除知识库
     */
     int delKnowledge(Integer id);

    /**
     * 批量查询知识库
     */
     IPage<KnowledgeLibrary> getKnowledges(String question, Integer questionTypeId, Integer page, Integer pageSize);

    /**
     * 根据id查询知识库
     */
     KnowledgeLibrary getKnowledgeById(Integer id);



}
