package com.xiaomi.cs.elasticsearch.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaomi.cs.elasticsearch.entity.KnowledgeLibraryES;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
public interface KnowledgeESLibraryService {

    /**
     * 批量查询知识库
     */
    List<KnowledgeLibraryES> getKnowledges(String question, String keywords , Integer page, Integer pageSize) throws IOException;

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

}
