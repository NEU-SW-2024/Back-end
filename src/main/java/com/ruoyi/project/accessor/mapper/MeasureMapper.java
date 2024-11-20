package com.ruoyi.project.accessor.mapper;

import com.ruoyi.project.accessor.domain.Measure;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeasureMapper {
    void insert(Measure measure);

    @Select("select * from vue.tb_measure where project_id=#{projectId}")
    List<Measure> selectByProjectId(Integer projectId);
}
