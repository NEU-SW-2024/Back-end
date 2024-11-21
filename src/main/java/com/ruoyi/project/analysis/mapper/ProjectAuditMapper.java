package com.ruoyi.project.analysis.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectAuditMapper {
    void insertProjectReportStatus(long projectId);
    // 更新项目状态为完成（从待审核1改为2完成）
    void updateStatusToCompleted(long projectId);

    // 更新项目状态为待评估（从待审核1改为0待评估）
    void updateStatusToPendingEvaluation(long projectId);
}
