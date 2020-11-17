package com.xiaomi.cs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public void addKnowledge(KnowledgeLibrary knowledgeLibrary) {
        try {
            this.baseMapper.insertKnoeledge(knowledgeLibrary);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void modifyKnowledge(KnowledgeLibrary knowledgeLibrary) {

    }

    @Override
    public void delKnowledge(Integer id) {

    }

    @Override
    public PageSerializable<KnowledgeLibrary> getKnowledges(String question, Integer questionTypeId, Integer pageno, Integer limit) {
        PageSerializable<KnowledgeLibrary>result = null;
        try {
            QueryWrapper<KnowledgeLibrary> wrapper = new QueryWrapper<>();
            if ("".equals(question)&&question != null ) {
                wrapper.eq("question", question);
            }
            if(questionTypeId!=null){
                wrapper.eq("question_type_id", questionTypeId);
            }
            int count = this.baseMapper.selectCount(wrapper);
            if(count!=0){
                List<KnowledgeLibrary> list = (List<KnowledgeLibrary>) this.baseMapper.selectPage(new Page<>(pageno, limit), wrapper);
                List<KnowledgeLibrary> list2 =this.baseMapper.selectKnoeledges(question,questionTypeId,pageno,limit);
                result.setTotal(count);
                result.setList(list);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public KnowledgeLibrary getKnowledgeById(Integer id) {
        KnowledgeLibrary l = this.baseMapper.selectById(id);
        System.out.println(l.toString());
        return l;
    }
}
