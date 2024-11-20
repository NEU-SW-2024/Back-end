package com.ruoyi.project.analysis.mapper;

import com.ruoyi.project.analysis.domain.ProjectDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectReportMapper {
    List<ProjectDTO> getProjectsByTenantId(@Param("tenantId") Long tenantId);
}
