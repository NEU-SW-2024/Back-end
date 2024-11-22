package com.ruoyi.project.system.controller;

import com.ruoyi.project.accessor.service.AccessorService;
import com.ruoyi.project.system.domain.CalculationRequest;
import com.ruoyi.project.system.domain.SysAssessmentDetailResults;
import com.ruoyi.project.system.domain.SysAssessmentResults;
import com.ruoyi.project.system.domain.SysAssessmentStd;
import com.ruoyi.project.system.service.AssessmentStandardService;
import com.ruoyi.project.system.service.ISysAssessmentDetailResultsService;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assessment-detail-results")
public class SysAssessmentDetailResultsController {

    @Autowired
    private ISysAssessmentDetailResultsService assessmentDetailResultsService;

    @Autowired
    private AccessorService accessorService;

    @Autowired
    private AssessmentStandardService assessmentStandardService;

    /**
     * 处理HTTP GET请求，获取所有评估细节结果列表
     * @return 评估细节结果列表
     */
    @GetMapping("/list")
    public AjaxResult getAllAssessmentDetailResults() {
        List<SysAssessmentDetailResults> list = assessmentDetailResultsService.selectAllAssessmentDetailResults();
        return AjaxResult.success(list);
    }

    /**
     * 处理HTTP GET请求，根据ID获取单个评估细节结果
     * @param resId 评估结果ID
     * @return 评估细节结果对象
     */
    @GetMapping("/{resId}")
    public AjaxResult getAssessmentDetailResultById(@PathVariable Long resId) {
        SysAssessmentDetailResults result = assessmentDetailResultsService.selectAssessmentDetailResultsById(resId);
        return AjaxResult.success(result);
    }

    /**
     * 处理HTTP POST请求，创建一个新的评估细节结果
     * @param assessmentDetailResults 要创建的评估细节结果对象
     * @return 插入操作成功与否的状态
     */
    @PostMapping("/create")
    public AjaxResult createAssessmentDetailResult(@RequestBody SysAssessmentDetailResults assessmentDetailResults) {
        boolean result = assessmentDetailResultsService.insertAssessmentDetailResults(assessmentDetailResults);
        if (result) {
            return AjaxResult.success("新增评估细节结果成功！");
        } else {
            return AjaxResult.error("新增评估细节结果失败！");
        }
    }

    /**
     * 处理HTTP PUT请求，更新一个现有的评估细节结果
     * @param assessmentDetailResults 要更新的评估细节结果对象
     * @return 更新操作成功与否的状态
     */
    @PutMapping("/update")
    public boolean updateAssessmentDetailResult(@RequestBody SysAssessmentDetailResults assessmentDetailResults) {
        return assessmentDetailResultsService.updateAssessmentDetailResults(assessmentDetailResults);
    }

    /**
     * 处理HTTP DELETE请求，根据ID删除一个评估细节结果
     * @param resId 要删除的评估细节结果ID
     * @return 删除操作成功与否的状态
     */
    @DeleteMapping("/{resId}")
    public boolean deleteAssessmentDetailResult(@PathVariable Long resId) {
        return assessmentDetailResultsService.deleteAssessmentDetailResultsById(resId);
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

            //工作量AE
            BigDecimal AE = assessmentDetailResultsService.calculateAE(std, DFP);

            /** 人工成本，用于项目开发的直接人力费用 */
            BigDecimal laborCost = assessmentDetailResultsService.calculateLaborCost(std, DFP);

            /** 风险附加成本，根据风险因子计算得出的额外成本 */
            BigDecimal riskCost = assessmentDetailResultsService.calculateRiskCost(std, projectSDC);

            /** 质量附加成本，根据质量因子计算得出的额外成本 */
            BigDecimal qualityCost = assessmentDetailResultsService.calculateQualityCost(std, projectSDC);

            /** 开发服务费用，开发工具和外包服务相关的直接费用 */
            BigDecimal devServiceCost = assessmentDetailResultsService.calculateDevServiceCost(std);

            /** 调整后开发服务费用，考虑优化或额外因素调整的开发费用 */
            BigDecimal adjustedDevServiceCost = assessmentDetailResultsService.calculateadJustedDevServiceCost(std);

            SysAssessmentDetailResults results = new SysAssessmentDetailResults();

            results.setProjectId(Long.valueOf(projectId));
            results.setStdId(Long.valueOf(stdId));
            results.setTotalCost(projectESDC);
            results.setLaborCost(laborCost);
            results.setRiskCost(riskCost);
            results.setQualityCost(qualityCost);
            results.setDevServiceCost(devServiceCost);
            results.setAdjustedDevServiceCost(adjustedDevServiceCost);
            results.setCreatedAt(new Date());
            results.setUpdatedAt(new Date());

            /*            assessmentDetailResultsService.insertAssessmentDetailResults(results);*/

            /*Map<String, Object> data = new HashMap<>();
            data.put("projectSDC", projectSDC);
            data.put("projectESDC", projectESDC);
            data.put("createdAt", results.getCreatedAt());
            data.put("updatedAt", results.getUpdatedAt());*/
            Map<String, Object> data = new HashMap<>();
            data.put("AE", AE);
            data.put("results", results);

            return AjaxResult.success(data);
        } catch (Exception e) {
            return AjaxResult.error("Error during calculation: " + e.getMessage());
        }
    }
}