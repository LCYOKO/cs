package com.xiaomi.cs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

@RequestMapping("/question")
@RestController
public class KnowledgeLibraryController {
    @Autowired
    private KnowledgeLibraryService knowledgeLibraryService;

    @PostMapping("/add")
    public CommonResponse addKnowledgeLibrary(KnowledgeLibrary knowledgeLibrary) {

        try {
            int res= knowledgeLibraryService.addKnowledge(knowledgeLibrary);
            if(res>0){
                return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);
            }
            if(res<0){
                return new CommonResponse(ResponseConstants.FAIL_CODE,"问题已存在",null);
            }
            return  new CommonResponse(ResponseConstants.FAIL_CODE,ResponseConstants.FAIL_MSG,null);
        } catch (Exception e) {
           return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }


    }

    @GetMapping("/getAll")
    public CommonResponse findKnowledgeLibraryByIds( @RequestParam(required = false) String question,
                                                   @RequestParam(required = false) Integer questionTypeId,
                                                  @RequestParam(required = false,defaultValue = "1") Integer page,
                                                  @RequestParam(required = false,defaultValue = "10")Integer limit, HttpServletRequest request) {

               IPage res=null;
        try {
              res=knowledgeLibraryService.getKnowledges(question,questionTypeId,page,limit);

        } catch (Exception e) {
           return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
        }
        return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, res);

    }

    @PostMapping("/update")
    public CommonResponse  updateKnowledge(KnowledgeLibrary knowledgeLibrary){
        int  res = knowledgeLibraryService.updateKnowledge(knowledgeLibrary);
        if(res>0){
            return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);
        }
        return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
    }

    @GetMapping("/delete")
    public CommonResponse deleteKnowledge(int id){
        int  res = knowledgeLibraryService.delKnowledge(id);
        if(res>0){
            return new CommonResponse(ResponseConstants.SUCCESS_CODE, ResponseConstants.SUCCESS_MSG, null);
        }
        return new CommonResponse(ResponseConstants.FAIL_CODE, ResponseConstants.FAIL_MSG, null);
    }

}
