package com.xiaomi.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.cs.mapper.KnowledgeLibraryMapper;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.PageSerializable;
import com.xiaomi.cs.pojo.entity.User;
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
    public int addKnowledge(KnowledgeLibrary knowledgeLibrary) {

            QueryWrapper wrapper = new QueryWrapper<KnowledgeLibrary>();
            wrapper.eq("question",knowledgeLibrary);
            if(this.baseMapper.selectOne(wrapper)!=null){
                return -1;
            }
           return this.baseMapper.insert(knowledgeLibrary);

    }

    @Override
    public int updateKnowledge(KnowledgeLibrary knowledgeLibrary) {
        return this.baseMapper.updateById(knowledgeLibrary);
    }


    @Override
    public int delKnowledge(Integer id) {
     return this.baseMapper.deleteById(id);
    }

    @Override
    public IPage<KnowledgeLibrary> getKnowledges(String question, Integer questionTypeId, Integer pageNo, Integer pageSize) {
        IPage<KnowledgeLibrary> res=null;
        try {
            QueryWrapper<KnowledgeLibrary> wrapper = new QueryWrapper<>();
            if (!"".equals(question)&&question != null ) {
                wrapper.like("question", question);
            }
            if(questionTypeId!=null){
                wrapper.eq("question_type_id", questionTypeId);
            }
            wrapper.orderByAsc("id");
                res=this.baseMapper.selAllKnowledge(new Page<KnowledgeLibrary>(pageNo,pageSize),wrapper);

        } catch (Exception e) {
            System.out.println(e);
        }
        return res;
    }


    @Override
    public KnowledgeLibrary getKnowledgeById(Integer id) {
        KnowledgeLibrary l = this.baseMapper.selectById(id);
        return l;
    }
}
