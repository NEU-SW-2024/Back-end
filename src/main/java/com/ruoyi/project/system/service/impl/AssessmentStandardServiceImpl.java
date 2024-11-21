
package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.SysAssessmentStd;
import com.ruoyi.project.system.mapper.AssessmentStandardMapper;
import com.ruoyi.project.system.service.AssessmentStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AssessmentStandardServiceImpl implements AssessmentStandardService {

    @Autowired
    private AssessmentStandardMapper assessmentStandardMapper;

    @Override
    public List<SysAssessmentStd> selectStandardList(SysAssessmentStd std) {
        return assessmentStandardMapper.selectStandardList(std);
    }

    @Override
    public SysAssessmentStd selectStandardById(Integer stdId) {
        return assessmentStandardMapper.selectStandardById(stdId);
    }

    @Override
    public int insertStandard(SysAssessmentStd std) {
        return assessmentStandardMapper.insertStandard(std);
    }

    @Override
    public int updateStandard(SysAssessmentStd std) {
        return assessmentStandardMapper.updateStandard(std);
    }

    @Override
    public int deleteStandardById(Long stdId) {
        return assessmentStandardMapper.deleteStandardById(stdId);
    }

    // 根据给定的标准和项目输入计算项目的开发服务费用
    public BigDecimal calculateProjectSDC(SysAssessmentStd std, float DFP) {
        // 根据提供的公式计算项目SDC
        double projectSDC = DFP * std.getPdrValue() * std.getSwf() * std.getRdf()/std.getConversionFactor() * std.getCostRate() + std.getDnc();
        // 返回计算结果，转换为BigDecimal以保持精度
        return BigDecimal.valueOf(projectSDC);
    }

    // 根据给定的标准和项目SDC计算项目调整后的开发服务费用
    public BigDecimal calculateProjectESDC(SysAssessmentStd std, BigDecimal projectSDC) {
        // 根据提供的公式计算项目ESDC
        double projectESDC = projectSDC.doubleValue() * std.getRskFactor() * std.getQualityFactor();
        // 返回计算结果，转换为BigDecimal以保持精度
        return BigDecimal.valueOf(projectESDC);
    }

}