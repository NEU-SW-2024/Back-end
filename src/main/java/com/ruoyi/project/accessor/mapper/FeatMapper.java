package com.ruoyi.project.accessor.mapper;

import com.ruoyi.project.accessor.domain.Feat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FeatMapper{
    void insert(Feat feat);

    @Select("select score_id from vue.tb_feat where project_id=#{projectId}")
    List<Integer> selectList(Integer projectId);

    @Select("select * from vue.tb_feat where project_id=#{projectId}")
    List<Feat> selectByProjectId(Integer projectId);
}