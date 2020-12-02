package com.xiaomi.cs;


import com.xiaomi.cs.elasticsearch.entity.KnowledgeLibraryES;
import com.xiaomi.cs.elasticsearch.service.KnowledgeESLibraryService;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class EsApplicationTests {
    @Autowired
    private KnowledgeESLibraryService knowledgeESLibraryService;

    @Test
    void testSelect() throws IOException {

    }

    @Test
    void testSave() throws IOException {
        KnowledgeLibrary es = new KnowledgeLibrary();
        es.setId(3);
        es.setQuestion("订单在哪里查呢");
        es.setAnswer("订单这里");
        es.setKeywords("11,222");
        knowledgeESLibraryService.addKnowledge(es);
    }

    @Test
    void testdelet() throws IOException {
        knowledgeESLibraryService.delKnowledge(3);
    }

    @Test
    void testfind() throws IOException {
        List<KnowledgeLibraryES> p = knowledgeESLibraryService.getKnowledges("物流信息查询", "物流信息查询", 0, 10);
        System.out.println(p.size());
    }

    @Test
    void testupdate() throws IOException {
        KnowledgeLibrary es = new KnowledgeLibrary();
        es.setId(3);
        es.setQuestion("我的物流信息在哪里");
        es.setAnswer("物流");
        es.setKeywords("11,222");
        knowledgeESLibraryService.updateKnowledge(es);
    }
}
