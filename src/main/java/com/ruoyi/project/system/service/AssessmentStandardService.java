package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysAssessmentStd;
import java.util.List;

public interface AssessmentStandardService {
    List<SysAssessmentStd> selectStandardList(SysAssessmentStd std);
    SysAssessmentStd selectStandardById(Long stdId);
    int insertStandard(SysAssessmentStd std);
    int updateStandard(SysAssessmentStd std);
    int deleteStandardById(Long stdId);
}