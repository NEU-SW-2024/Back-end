package com.ruoyi.project.analysis.service;

import com.ruoyi.project.analysis.domain.ProjectDTO;
import com.ruoyi.project.analysis.mapper.ProjectReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectReportService {

    private final ProjectReportMapper projectMapper;

    @Autowired
    public ProjectReportService(ProjectReportMapper projectReportMapper) {
        this.projectMapper = projectReportMapper;
    }

    /**
     * 根据租户ID获取所有项目及其详细信息
     *
     * @param tenantId 租户ID
     * @return 项目详情列表
     */
    public List<ProjectDTO> getProjectsByTenantId(Long tenantId) {
        if (tenantId == null || tenantId <= 0) {
            throw new IllegalArgumentException("租户ID不能为空或无效！");
        }

        List<ProjectDTO> projects = projectMapper.getProjectsByTenantId(tenantId);

        if (projects == null || projects.isEmpty()) {
            throw new RuntimeException("未找到租户ID为 " + tenantId + " 的项目！");
        }

        return projects;
    }
}
