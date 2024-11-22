package com.ruoyi.project.analysis.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.analysis.domain.ProjectDTO;
import com.ruoyi.project.analysis.service.ProjectReportService;
import com.sun.jna.platform.win32.WinBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.utils.SecurityUtils.hasRole;

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
     * @return 项目详情列表
     */
    @GetMapping("/projectInfo")
    public ResponseEntity<List<ProjectDTO>> getProjectsByTenantId() {
        //加一个权限判断，调用不同方法
        try {
            List<ProjectDTO> projects = new ArrayList<>();
            projects = projectService.getProjectsByAuditorId(SecurityUtils.getUserId());
            for(ProjectDTO project : projects){
                System.out.println(project);
            }
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
