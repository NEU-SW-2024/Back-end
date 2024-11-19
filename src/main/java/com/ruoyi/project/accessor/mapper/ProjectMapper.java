package com.ruoyi.project.accessor.mapper;

import com.ruoyi.project.accessor.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {
    @Select("select * from vue.sys_project where accessor_id=${accessorId}")
    List<Project> selectProjectsByAccessorId(Integer accessorId);
}
