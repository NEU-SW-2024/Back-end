package com.ruoyi.project.analysis.controller;

import com.ruoyi.project.analysis.domain.ProjectDTO;
import com.ruoyi.project.analysis.service.ProjectReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ProjectReportController {

    private final ProjectReportService projectService;

    @Autowired
    public ProjectReportController(ProjectReportService projectService) {
        this.projectService = projectService;
    }

    /**
     * 根据用户ID获取所有项目及其详细信息
     *
     * @param userId 租户ID
     * @return 项目详情列表
     */
    @GetMapping("/projectInfo/{userId}")
    public ResponseEntity<List<ProjectDTO>> getProjectsByTenantId(@PathVariable Long userId) {
        //加一个权限判断，调用不同方法
        try {
            //List<ProjectDTO> projects = projectService.getProjectsByAccessorId(userId);
            //List<ProjectDTO> projects = projectService.getProjectsByAuditorId(userId);
            List<ProjectDTO> projects = projectService.getProjectsByTenantId(userId);
            return ResponseEntity.ok(projects);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
