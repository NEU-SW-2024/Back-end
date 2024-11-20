package com.ruoyi.project.accessor.mapper;


import com.ruoyi.project.accessor.domain.MeasureRes;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeasureResMapper {
    void insert(MeasureRes measureRes);

    @Select("select * from vue.tb_measure_res where project_id=#{projectId}")
    MeasureRes selectByProjectId(Integer projectId);

    @Select("select status from vue.tb_measure_res where project_id=#{projectId};")
    Integer selectStatusByProjectId(Integer projectId);

    @Delete("delete from vue.tb_measure_res where project_id=#{projectId}")
    void deleteByProjectId(Integer projectId);
}
