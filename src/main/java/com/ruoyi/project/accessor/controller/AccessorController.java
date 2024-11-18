package com.ruoyi.project.accessor.controller;

import com.ruoyi.project.accessor.domain.FeatDAO;
import com.ruoyi.project.accessor.domain.Measure;
import com.ruoyi.project.accessor.domain.Result;
import com.ruoyi.project.accessor.service.AccessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dev-api/accessor")
@Api("评估师")
public class AccessorController {

    @Resource
    private AccessorService accessorService;

    /**
     * 录入功能点 有多个功能点记录
     */
    @PostMapping("/saveFunc")
    @ApiOperation("保存功能点")
    public Result saveFunc(@RequestBody ArrayList<FeatDAO> featDAOS){
        return accessorService.saveFunc(featDAOS);
    }

    /**
     * 根据项目ID 获得UPF
     */
    @GetMapping("/getUPF")
    @ApiOperation("计算UPF")
    public Result getUPF(@RequestParam("project_id") Integer projectId) {
        return accessorService.getUPF(projectId);
    }

    /**
     * 根据项目名称功能点回显
     */
    @GetMapping("/getFunc")
    @ApiOperation("功能点回显")
    public Result getFunc(@RequestParam("project_id") Integer projectId){
        return accessorService.getFunc(projectId);
    }

    /**
     * 保存度量表 传入的是多个度量记录 返回VAF（调整系数）
     */
    @PostMapping("/saveMeasure/{cf}")
    @ApiOperation("保存度量表")
    public Result saveMeasure(@PathVariable("cf") Float cf, @RequestBody List<Measure> measures){
        return accessorService.saveMeasure(cf, measures);
    }

    /**
     * 返回所有的结果
     */
    @GetMapping("/getAll")
    @ApiOperation("返回所有结果")
    public Result getAll(@RequestParam("projectId") Integer projectId){
        return accessorService.getAll(projectId);
    }

}
