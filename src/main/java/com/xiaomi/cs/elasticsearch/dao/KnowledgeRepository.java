package com.xiaomi.cs.elasticsearch.dao;

import com.xiaomi.cs.elasticsearch.entity.KnowledgeLibraryES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.io.IOException;
import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
public interface KnowledgeRepository  extends ElasticsearchRepository<KnowledgeLibraryES,Integer> {

    /**
     * 批量查询知识库
     */
    Page<KnowledgeLibraryES> findByQuestionOrKeywords(String question, String keywords, Pageable page) throws IOException;


}
