package com.ruoyi.project.system.controller;

// 导入所需的类
import com.ruoyi.project.system.domain.SysAssessmentResults;
import com.ruoyi.project.system.service.ISysAssessmentResultsService;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 使用RestController注解声明这是一个REST控制器
@RestController
// 使用RequestMapping注解指定这个控制器的基础URL路径
@RequestMapping("/api/assessment-results")
public class SysAssessmentResultsController {

    // 使用Autowired注解自动注入AssessmentResultsService的实例
    @Autowired
    private ISysAssessmentResultsService assessmentResultsService;

    /**
     * 处理HTTP GET请求，获取所有评估结果列表
     * @return 评估结果列表
     */
    @GetMapping("/list")
    public AjaxResult getAllAssessmentResults() {
        // 调用服务层的方法获取评估结果列表
        List<SysAssessmentResults> list = assessmentResultsService.selectAllAssessmentResults();
        return AjaxResult.success(list); // 包装到标准响应中
    }

    /**
     * 处理HTTP GET请求，根据ID获取单个评估结果
     * @param resId 评估结果ID
     * @return 评估结果对象
     */
    @GetMapping("/{resId}")
    public AjaxResult getAssessmentResultById(@PathVariable Long resId) {
        // 调用服务层的方法根据ID获取评估结果
        SysAssessmentResults result = assessmentResultsService.selectAssessmentResultsById(resId);
        return AjaxResult.success(result); // 包装到标准响应中
    }

    /**
     * 处理HTTP POST请求，创建一个新的评估结果
     * @param assessmentResults 要创建的评估结果对象
     * @return 插入操作成功与否的状态
     */
    @PostMapping("/create")
    public AjaxResult createAssessmentResult(@RequestBody SysAssessmentResults assessmentResults) {
        // 调用服务层的方法插入新的评估结果
        boolean result = assessmentResultsService.insertAssessmentResults(assessmentResults);
        if (result) {
            return AjaxResult.success("新增评估结果成功！");
        } else {
            return AjaxResult.error("新增评估结果失败！");
        }
    }

    /**
     * 处理HTTP PUT请求，更新一个现有的评估结果
     * @param assessmentResults 要更新的评估结果对象
     * @return 更新操作成功与否的状态
     */
    @PutMapping("/update")
    public boolean updateAssessmentResult(@RequestBody SysAssessmentResults assessmentResults) {
        // 调用服务层的方法更新评估结果
        return assessmentResultsService.updateAssessmentResults(assessmentResults);
    }

    /**
     * 处理HTTP DELETE请求，根据ID删除一个评估结果
     * @param resId 要删除的评估结果ID
     * @return 删除操作成功与否的状态
     */
    @DeleteMapping("/{resId}")
    public boolean deleteAssessmentResult(@PathVariable Long resId) {
        // 调用服务层的方法根据ID删除评估结果
        return assessmentResultsService.deleteAssessmentResultsById(resId);
    }
}