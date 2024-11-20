package com.ruoyi.project.system.controller;


import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.accessor.service.AccessorService;
import com.ruoyi.project.system.domain.CalculationRequest;
import com.ruoyi.project.system.domain.SysAssessmentResults;
import com.ruoyi.project.system.domain.SysAssessmentStd;
import com.ruoyi.project.system.service.AssessmentStandardService;
import com.ruoyi.project.system.service.ISysAssessmentResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/assessment-standards")
public class AssessmentStandardController {

    @Autowired
    private AssessmentStandardService assessmentStandardService;

    @Autowired
    private ISysAssessmentResultsService sysAssessmentResultsService;

    @Autowired
    private AccessorService accessorService;

    @GetMapping("/list")
    public AjaxResult getAllStandards(SysAssessmentStd std) {
        List<SysAssessmentStd> list = assessmentStandardService.selectStandardList(std);
        return AjaxResult.success(list); // 包装到标准响应中
    }


    @GetMapping("/detail/{stdId}")
    public AjaxResult getStandardById(@PathVariable Long stdId) {
        SysAssessmentStd standard = assessmentStandardService.selectStandardById(Math.toIntExact(stdId));
        return AjaxResult.success(standard); // 包装到标准响应中
    }


    @PostMapping("/create")
    public AjaxResult createStandard(@RequestBody SysAssessmentStd std) {
        int result = assessmentStandardService.insertStandard(std);
        if (result > 0) {
            return AjaxResult.success("新增标准成功！");
        } else {
            return AjaxResult.error("新增标准失败！");
        }
    }


    @PutMapping("/update")
    public int updateStandard(@RequestBody SysAssessmentStd std) {
        return assessmentStandardService.updateStandard(std);
    }

    @DeleteMapping("/{stdId}")
    public int deleteStandard(@PathVariable Long stdId) {
        return assessmentStandardService.deleteStandardById(stdId);
    }

    // 处理HTTP POST请求，根据给定的标准和项目输入计算评估结果
    @PostMapping("/calculate")
    public AjaxResult calculateAssessmentResults(@RequestBody CalculationRequest request) {
        try {
            Integer projectId = Math.toIntExact(request.getProjectId());
            Integer stdId = Math.toIntExact(request.getStdId());

            float DFP = accessorService.getDFP(projectId);
            SysAssessmentStd std = assessmentStandardService.selectStandardById(stdId);
            BigDecimal projectSDC = assessmentStandardService.calculateProjectSDC(std, DFP);
            BigDecimal projectESDC = assessmentStandardService.calculateProjectESDC(std, projectSDC);

            SysAssessmentResults results = new SysAssessmentResults();
            results.setProjectId(Long.valueOf(projectId));
            results.setStdId(Long.valueOf(stdId));
            results.setProjectSDC(projectSDC);
            results.setProjectESDC(projectESDC);
            results.setCreatedAt(new Date());
            results.setUpdatedAt(new Date());

//            sysAssessmentResultsService.insertAssessmentResults(results);

            Map<String, Object> data = new HashMap<>();
            data.put("projectSDC", projectSDC);
            data.put("projectESDC", projectESDC);
            data.put("createdAt", results.getCreatedAt());
            data.put("updatedAt", results.getUpdatedAt());

            return AjaxResult.success(data);
        } catch (Exception e) {
            return AjaxResult.error("Error during calculation: " + e.getMessage());
        }
    }

}
