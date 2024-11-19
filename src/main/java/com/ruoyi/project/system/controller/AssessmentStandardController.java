package com.ruoyi.project.system.controller;


import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysAssessmentStd;
import com.ruoyi.project.system.service.AssessmentStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/assessment-standards")
public class AssessmentStandardController {

    @Autowired
    private AssessmentStandardService assessmentStandardService;

    @GetMapping("/list")
    public AjaxResult getAllStandards(SysAssessmentStd std) {
        List<SysAssessmentStd> list = assessmentStandardService.selectStandardList(std);
        return AjaxResult.success(list); // 包装到标准响应中
    }


    @GetMapping("/detail/{stdId}")
    public AjaxResult getStandardById(@PathVariable Long stdId) {
        SysAssessmentStd standard = assessmentStandardService.selectStandardById(stdId);
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
}
