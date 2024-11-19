
package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.SysAssessmentStd;
import com.ruoyi.project.system.mapper.AssessmentStandardMapper;
import com.ruoyi.project.system.service.AssessmentStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public SysAssessmentStd selectStandardById(Long stdId) {
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
}