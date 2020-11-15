package com.xiaomi.cs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.cs.mapper.KnowledgeLibraryMapper;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.service.KnowledgeLibraryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
@Service
public class KnowledgeLibraryServiceImpl extends ServiceImpl<KnowledgeLibraryMapper, KnowledgeLibrary> implements KnowledgeLibraryService {
    @Override
    public void addKnowledge(KnowledgeLibrary knowledgeLibrary) {
        int id=this.baseMapper.insertKnoeledge(knowledgeLibrary);
        System.out.println(id);
      /*
       int id = knowledgeLibraryMapper.insertKnoeledge(knowledgeLibrary);
      if (id > 0) {
            List<SimilarQuestion> similarQuestions = knowledgeLibrary.getSimilarQuesion();
            if (similarQuestions != null) {
                for (SimilarQuestion similarQuestion : similarQuestions) {
                    similarQuestion.setKnowledgeId(id);
                    similarQuestionMapper.insertSlimilarQuestion(similarQuestion);
                }
            }
        }*/
    }

    @Override
    public void modifyKnowledge(KnowledgeLibrary knowledgeLibrary) {

    }

    @Override
    public void delKnowledge(Integer id) {

    }

    @Override
    public List<KnowledgeLibrary> getKnowledges(KnowledgeLibrary knowledgeLibrary) {

        return null;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public KnowledgeLibrary getKnowledgeById(Integer id) {
        KnowledgeLibrary  l=this.baseMapper.selectById(id);
        System.out.println(l.toString());
        return l;
    }
}
