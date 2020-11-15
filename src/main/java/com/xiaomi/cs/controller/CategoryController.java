package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.pojo.entity.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author l
 * @create 2020-11-15-17:42
 */
@RestController("/category")
public class CategoryController {

    @PostMapping("/addCategory")
    public CommonResponse  addCategory(Category category){
        return null;
    }

//    @GetMapping
//    public CommonResponse getCategory(int id){
//        return null;
//    }
//    @GetMapping
//    public CommonResponse getAllCategory(){
//        return  null;
//    }
//
//    @GetMapping
//    public CommonResponse delCategoryById(int id){
//        return null;
//    }
}
