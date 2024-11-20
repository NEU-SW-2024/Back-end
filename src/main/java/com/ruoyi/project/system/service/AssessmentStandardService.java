package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysAssessmentStd;
import java.math.BigDecimal;
import java.util.List;

public interface AssessmentStandardService {
    List<SysAssessmentStd> selectStandardList(SysAssessmentStd std);
    SysAssessmentStd selectStandardById(Integer stdId);
    int insertStandard(SysAssessmentStd std);
    int updateStandard(SysAssessmentStd std);
    int deleteStandardById(Long stdId);

    // 添加计算项目开发服务费用的方法
    BigDecimal calculateProjectSDC(SysAssessmentStd std, float DFP);

    // 添加计算项目调整后的开发服务费用的方法
    BigDecimal calculateProjectESDC(SysAssessmentStd std, BigDecimal projectSDC);
}