package com.xiaomi.cs.elasticsearch.service.impl;

import com.xiaomi.cs.elasticsearch.dao.KnowledgeRepository;
import com.xiaomi.cs.elasticsearch.entity.KnowledgeLibraryES;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.elasticsearch.service.KnowledgeESLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
@Service
public class KnowledgeESLibraryServiceImpl implements KnowledgeESLibraryService {
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Override
    public List<KnowledgeLibraryES> getKnowledges(String question, String keywords, Integer pageNo, Integer pageSize) throws IOException {
        try {
            Page<KnowledgeLibraryES> searchResponse = knowledgeRepository.findByQuestionOrKeywords(question, keywords, PageRequest.of(pageNo, pageSize));
            if (searchResponse != null && searchResponse.getContent() !=null) {
                List<KnowledgeLibraryES> es = searchResponse.getContent();
                return es;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public int addKnowledge(KnowledgeLibrary knowledgeLibrary) {
        try {
            KnowledgeLibraryES es = new KnowledgeLibraryES();
            es.setAnswer(knowledgeLibrary.getAnswer());
            es.setQuestion(knowledgeLibrary.getQuestion());
            es.setId(knowledgeLibrary.getId());
            es.setKeywords(knowledgeLibrary.getKeywords());
            knowledgeRepository.save(es);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

    }

    @Override
    public int updateKnowledge(KnowledgeLibrary knowledgeLibrary) {
        try {
            KnowledgeLibraryES es = new KnowledgeLibraryES();
            es.setAnswer(knowledgeLibrary.getAnswer());
            es.setQuestion(knowledgeLibrary.getQuestion());
            es.setId(knowledgeLibrary.getId());
            es.setKeywords(knowledgeLibrary.getKeywords());
            knowledgeRepository.save(es);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }

    }


    @Override
    public int delKnowledge(Integer id) {
        try {
            KnowledgeLibraryES es = new KnowledgeLibraryES();
            es.setId(id);
            knowledgeRepository.delete(es);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

}
