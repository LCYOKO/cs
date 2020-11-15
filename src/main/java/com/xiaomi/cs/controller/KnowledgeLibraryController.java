package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.common.ResponseConstants;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.PageSerializable;
import com.xiaomi.cs.pojo.entity.QuestionType;
import com.xiaomi.cs.service.KnowledgeLibraryService;
import com.xiaomi.cs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author l
 * @create 2020-11-05-10:59
 */
@RestController
@RequestMapping("/question")
public class KnowledgeLibraryController {
    @Autowired
    private KnowledgeLibraryService knowledgeLibraryService;

    @PostMapping("/addQuestion ")
    public CommonResponse addKowldgeLibrary(@RequestBody KnowledgeLibrary knowledgeLibrary) {

        try {
            knowledgeLibraryService.addKnowledge(knowledgeLibrary);
        } catch (Exception e) {
            new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
        return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);

    }

    @GetMapping("/getAll")
    public CommonResponse findKowldgeLibraryByIds( @RequestParam(required = false) String question,
                                                   @RequestParam(required = false) Integer questionTypeId,
                                                  @RequestParam(required = false,defaultValue = "1") Integer page,
                                                  @RequestParam(required = false,defaultValue = "10")Integer limit, HttpServletRequest request) {

        try {
            PageSerializable<KnowledgeLibrary> result=knowledgeLibraryService.getKnowledges("11",questionTypeId,page,limit);
        } catch (Exception e) {
            new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
        return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);

    }

}
