package com.ruoyi.project.accessor.service;

import com.ruoyi.project.accessor.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccessorService {
    Result saveFunc(List<FeatDAO> featDAOS);

    Result getUPF(Integer projectId);

    Result getFunc(Integer projectId);

    Result saveMeasure(Float cf, List<Measure> measures);

    Result getAll(Integer projectId);

//    Result updateFunc(List<FeatDAO> featDAOS);
//
//    Result deleteFunc(DeleteFuncRequest request);

//    Result deleteMeasure(String measureName);
//
//    Result updateMeasures(SaveMeasureRequest saveMeasureRequest);
//
//    Feat getFeatBySoreId(FeatDAO featDAO);
}