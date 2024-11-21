package com.ruoyi.project.analysis.controller;

import com.ruoyi.project.analysis.service.ProjectAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit")
public class ProjectAuditController {
    @Autowired
    private ProjectAuditService projectReportStatusService;

    @Autowired
    private ProjectAuditService projectAuditService;

    // 接收前端传回的项目ID并插入到数据库
    @PostMapping("/add/{projectId}")
    public String addProjectReportStatus(@PathVariable Long projectId) {
        try {
            projectReportStatusService.addProjectReportStatus(projectId);
            return "项目报告状态已成功添加。";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败，出现异常：" + e.getMessage();
        }
    }

    // 将项目状态从待审核（1）改为完成（2）
    @PostMapping("/updateToCompleted/{projectId}")
    public String updateStatusToCompleted(@PathVariable Long projectId) {
        try {
            projectAuditService.updateStatusToCompleted(projectId);
            return "项目状态已成功更新为完成（2）。";
        } catch (Exception e) {
            e.printStackTrace();
            return "更新失败，出现异常：" + e.getMessage();
        }
    }

    // 将项目状态从待审核（1）改为待评估（0）
    @PostMapping("/updateToPendingEvaluation/{projectId}")
    public String updateStatusToPendingEvaluation(@PathVariable Long projectId) {
        try {
            projectAuditService.updateStatusToPendingEvaluation(projectId);
            return "项目状态已成功更新为待评估（0）。";
        } catch (Exception e) {
            e.printStackTrace();
            return "更新失败，出现异常：" + e.getMessage();
        }
    }


}
