package com.xiaomi.cs;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.cs.mapper.KnowledgeLibraryMapper;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CsApplicationTests {
  @Autowired
    private KnowledgeLibraryMapper mapper;
    @Test
    void contextLoads() {
        QueryWrapper<KnowledgeLibrary> wrapper = new QueryWrapper<>();
        wrapper.eq("question_type_id",9);
        wrapper.like("question","");
         System.out.println(wrapper.getCustomSqlSegment());
        IPage<KnowledgeLibrary> page = mapper.selAllKnowledge(new Page<KnowledgeLibrary>(1, 10), wrapper);
        System.out.println(page.getRecords());
    }

}
