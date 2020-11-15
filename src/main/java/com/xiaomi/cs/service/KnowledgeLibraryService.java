package com.xiaomi.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  21:11
 */
public interface KnowledgeLibraryService  extends IService<KnowledgeLibrary> {
    //添加知识库
    public void addKnowledge(KnowledgeLibrary knowledgeLibrary);
    //更新知识库
    public void modifyKnowledge(KnowledgeLibrary knowledgeLibrary);
    //删除知识库
    public void delKnowledge(Integer id);
    //批量查询知识库
    public List<KnowledgeLibrary> getKnowledges(KnowledgeLibrary knowledgeLibrary);
    //根据id查询知识库
    public KnowledgeLibrary getKnowledgeById(Integer id);



}
