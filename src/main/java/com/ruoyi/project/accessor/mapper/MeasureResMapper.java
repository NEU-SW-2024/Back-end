package com.ruoyi.project.accessor.mapper;


import com.ruoyi.project.accessor.domain.MeasureRes;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeasureResMapper {
    void insert(MeasureRes measureRes);

    @Select("select * from vue.tb_measure_res where project_id=#{projectId}")
    List<MeasureRes> selectByProjectId(Integer projectId);

    @Select("select status from vue.tb_measure_res where project_id=#{projectId};")
    Integer selectStatusByProjectId(Integer projectId);

    /**
     * 获取vue.tb_measure_res表中的所有数据
     */
    @Select("SELECT project_id AS projectId, UPF, VAF, DFP, GSC, status, cf, S FROM vue.tb_measure_res")
    List<MeasureRes> selectAll();

    @Select("select DFP from vue.tb_measure_res where project_id=#{projectId};")
    Float selectDFP(Integer projectId);
}
