package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.common.ResponseConstants;
import com.xiaomi.cs.elasticsearch.entity.KnowledgeLibraryES;
import com.xiaomi.cs.elasticsearch.service.KnowledgeESLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author duyan
 * @create 2020-12-02-13:50
 */

@RequestMapping("/questionES")
@RestController
public class KnowledgeESController {
    @Autowired
    private KnowledgeESLibraryService knowledgeESLibraryService;

    @GetMapping("/gets")
    public CommonResponse addKnowledgeLibrary(String  content) {

        try {
            List<KnowledgeLibraryES> result = knowledgeESLibraryService.getKnowledges(content, content, 0, 10);
            return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, result);
        } catch (Exception e) {
            return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
    }
}
