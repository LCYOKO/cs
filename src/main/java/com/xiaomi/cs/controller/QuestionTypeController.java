package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.common.ResponseConstants;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.PageSerializable;
import com.xiaomi.cs.pojo.entity.QuestionType;
import com.xiaomi.cs.service.KnowledgeLibraryService;
import com.xiaomi.cs.service.QuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author l
 * @create 2020-11-05-10:59
 */
@RestController
@RequestMapping("/questionType")
public class QuestionTypeController {
    @Autowired
    private QuestionTypeService questionTypeService;

    @PostMapping("/add")
    public CommonResponse addQuestionType(@RequestParam String questionType) {
        try {
            if(questionTypeService.getQuestionTypeByVal(questionType)!=null){
                return new CommonResponse(ResponseConstants.ADD_TPYE_FAIL, ResponseConstants.ADD_TPYE_FAIL_MSG, null);
            }
            questionTypeService.addQuestionType(questionType);
            return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);
        } catch (Exception e) {
            System.out.println(e);
            return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
    }

    @GetMapping("/getAll")
    public CommonResponse findQuestionTypes() {
        try {
            List<QuestionType> result=questionTypeService.getQuestionTypes();
            return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, result);
        } catch (Exception e) {
            return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
    }

}
