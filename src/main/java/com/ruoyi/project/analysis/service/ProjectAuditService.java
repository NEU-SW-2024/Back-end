package com.ruoyi.project.analysis.service;

import com.ruoyi.project.analysis.mapper.ProjectAuditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectAuditService {
    @Autowired
    private ProjectAuditMapper projectReportStatusMapper;

    @Autowired
    private ProjectAuditMapper projectAuditMapper;

    // 插入项目ID到报告状态表
    public void addProjectReportStatus(long projectId) {
        projectReportStatusMapper.insertProjectReportStatus(projectId);

    }

    // 将项目状态从待审核（1）改为完成（2）
    public void updateStatusToCompleted(long projectId) {
        projectAuditMapper.updateStatusToCompleted(projectId);
    }

    // 将项目状态从待审核（1）改为待评估（0）
    public void updateStatusToPendingEvaluation(long projectId) {
        projectAuditMapper.updateStatusToPendingEvaluation(projectId);
    }
}
