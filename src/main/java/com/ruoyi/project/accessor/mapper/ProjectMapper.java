package com.ruoyi.project.accessor.mapper;

import com.ruoyi.project.accessor.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("select * from vue.sys_project where accessor_id=${accessorId}")
    List<Project> selectProjectsByAccessorId(Integer accessorId);

    /**
     * 根据项目ID获取项目名称
     */
    @Select("SELECT name FROM vue.sys_project WHERE project_id = #{projectId}")
    String selectProjectNameById(Integer projectId);
}
