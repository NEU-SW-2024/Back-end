package com.ruoyi.project.accessor.service;

import com.ruoyi.project.accessor.entity.FeatDAO;
import com.ruoyi.project.accessor.entity.Measure;
import com.ruoyi.project.accessor.entity.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccessorService {
    Result saveFunc(List<FeatDAO> featDAOS);

    Result getUPF(Integer projectId);

    Result getFunc(Integer projectId);

    Result saveMeasure(List<Measure> measures);

    Result getAll(Integer projectId);
}
