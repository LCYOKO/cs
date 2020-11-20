package com.xiaomi.cs.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.xiaomi.cs.pojo.entity.KnowledgeLibrary;
import com.xiaomi.cs.pojo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author duyan
 * @create 2020-11-14  22:02
 */
public interface KnowledgeLibraryMapper extends BaseMapper<KnowledgeLibrary> {



     IPage<KnowledgeLibrary> selAllKnowledge(IPage<KnowledgeLibrary> page,@Param(Constants.WRAPPER) QueryWrapper<KnowledgeLibrary> wrapper);



}
