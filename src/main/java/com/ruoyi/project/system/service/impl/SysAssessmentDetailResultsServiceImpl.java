package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.SysAssessmentDetailResults;
import com.ruoyi.project.system.domain.SysAssessmentStd;
import com.ruoyi.project.system.mapper.SysAssessmentDetailResultsMapper;
import com.ruoyi.project.system.service.ISysAssessmentDetailResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SysAssessmentDetailResultsServiceImpl implements ISysAssessmentDetailResultsService {

    @Autowired
    private SysAssessmentDetailResultsMapper assessmentDetailResultsMapper;

    @Override
    public boolean insertAssessmentDetailResults(SysAssessmentDetailResults assessmentDetailResults) {
        int result = assessmentDetailResultsMapper.insertAssessmentDetailResults(assessmentDetailResults);
        return result > 0;
    }

    @Override
    public SysAssessmentDetailResults selectAssessmentDetailResultsById(Long resId) {
        return assessmentDetailResultsMapper.selectAssessmentDetailResultsById(resId);
    }

    @Override
    public List<SysAssessmentDetailResults> selectAllAssessmentDetailResults() {
        return assessmentDetailResultsMapper.selectAllAssessmentDetailResults();
    }

    @Override
    public boolean updateAssessmentDetailResults(SysAssessmentDetailResults assessmentDetailResults) {
        int result = assessmentDetailResultsMapper.updateAssessmentDetailResults(assessmentDetailResults);
        return result > 0;
    }

    @Override
    public boolean deleteAssessmentDetailResultsById(Long resId) {
        int result = assessmentDetailResultsMapper.deleteAssessmentDetailResultsById(resId);
        return result > 0;
    }

    @Override
    public BigDecimal calculateAE(SysAssessmentStd std, float dfp) {
        double AE =(dfp*std.getPdrValue())*std.getSwf() *std.getRdf();
        return BigDecimal.valueOf(AE);
    }

    @Override
    public BigDecimal calculateLaborCost(SysAssessmentStd std, float dfp) {
        double laborCost =(dfp*std.getPdrValue())*std.getSwf() * std.getRdf()/std.getConversionFactor()* std.getCostRate();
        return BigDecimal.valueOf(laborCost);
    }

    @Override
    public BigDecimal calculateRiskCost(SysAssessmentStd std, BigDecimal projectSDC) {
        double riskCost = projectSDC.doubleValue() * (std.getRskFactor()-1);
        return BigDecimal.valueOf(riskCost);
    }

    @Override
    public BigDecimal calculateQualityCost(SysAssessmentStd std, BigDecimal projectSDC) {
        double qualityCost = projectSDC.doubleValue() * (std.getQualityFactor()-1);
        return BigDecimal.valueOf(qualityCost);
    }

    @Override
    public BigDecimal calculateDevServiceCost(SysAssessmentStd std) {
        return BigDecimal.valueOf(std.getDnc());
    }

    @Override
    public BigDecimal calculateadJustedDevServiceCost(SysAssessmentStd std) {
        return BigDecimal.valueOf(std.getDnc() * 1.2);
    }
}