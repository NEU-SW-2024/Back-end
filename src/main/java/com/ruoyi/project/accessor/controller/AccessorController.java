package com.ruoyi.project.accessor.controller;

import com.ruoyi.project.accessor.entity.FeatDAO;
import com.ruoyi.project.accessor.entity.Measure;
import com.ruoyi.project.accessor.entity.Result;
import com.ruoyi.project.accessor.service.AccessorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("/accessor")
public class AccessorController {

    @Resource
    private AccessorService accessorService;

    /**
     * 录入功能点 有多个功能点记录
     */
    @PostMapping("/saveFunc")
    public Result saveFunc(List<FeatDAO> featDAOS){
        return accessorService.saveFunc(featDAOS);
    }

    /**
     * 根据项目ID 获得UPF
     */
    @GetMapping("/getUPF")
    public Result getUPF(@RequestParam("project_id") Integer projectId) {
        return accessorService.getUPF(projectId);
    }

    /**
     * 根据项目名称功能点回显
     */
    @GetMapping("/getFunc")
    public Result getFunc(@RequestParam("project_id") Integer projectId){
        return accessorService.getFunc(projectId);
    }

    /**
     * 保存度量表 传入的是多个度量记录 返回VAF（调整系数）
     */
    @PostMapping("/saveMeasure")
    public Result saveMeasure(List<Measure> measures){
        return accessorService.saveMeasure(measures);
    }

    /**
     * 返回所有的结果
     */
    @GetMapping("/getAll")
    public Result getAll(@RequestParam("projectId") Integer projectId){
        return accessorService.getAll(projectId);
    }

}
