package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.common.ResponseConstants;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.QuestionType;
import com.xiaomi.cs.service.KnowledgeLibraryService;
import com.xiaomi.cs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author l
 * @create 2020-11-05-10:59
 */
@RestController
@RequestMapping("/knowledge")
public class KnowledgeLibraryController {
    @Autowired
    private KnowledgeLibraryService knowledgeLibraryService;
    @GetMapping("/add")
    public CommonResponse addKowldgeLibrary(KnowledgeLibrary knowledgeLibrary, HttpServletRequest request) {

        try {
            knowledgeLibrary.setQuestion("11");
            knowledgeLibrary.setAnswer("11");
            QuestionType q= new QuestionType();
            q.setId(1);
            knowledgeLibrary.setQuestionType(q);
            knowledgeLibraryService.addKnowledge(knowledgeLibrary);
        } catch (Exception e) {
            new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
        return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);

    }
    @GetMapping("/find")
    public CommonResponse findKowldgeLibraryByIds(KnowledgeLibrary knowledgeLibrary, HttpServletRequest request) {

        try {

            KnowledgeLibrary k=knowledgeLibraryService.getKnowledgeById(1);
        } catch (Exception e) {
            new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
        return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);

    }

}
