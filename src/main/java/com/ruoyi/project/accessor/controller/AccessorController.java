package com.ruoyi.project.accessor.controller;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.accessor.domain.*;
import com.ruoyi.project.accessor.service.AccessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accessor")
@Api("评估师")
public class AccessorController {

    @Resource
    private AccessorService accessorService;

    /**
     * 录入功能点 有多个功能点记录
     */
    @PostMapping("/saveFunc")
    @ApiOperation("保存功能点")
    public AjaxResult saveFunc(@RequestBody ArrayList<FeatDAO> featDAOS){
        return accessorService.saveFunc(featDAOS);
    }

    /**
     * 根据项目ID 获得UPF
     */
    @GetMapping("/getUPF")
    @ApiOperation("计算UPF")
    public AjaxResult getUPF(@RequestParam("project_id") Integer projectId) {
        return accessorService.getUPF(projectId);
    }

    /**
     * 根据项目名称功能点回显
     */
    @GetMapping("/getFunc")
    @ApiOperation("功能点回显")
    public AjaxResult getFunc(@RequestParam("project_id") Integer projectId){
        return accessorService.getFunc(projectId);
    }

    /**
     * 保存度量表 传入的是多个度量记录 返回VAF（调整系数）
     */
    @PostMapping("/saveMeasure/{cf}")
    @ApiOperation("保存度量表")
    public AjaxResult saveMeasure(@PathVariable("cf") Float cf, @RequestBody List<Measure> measures){
        return accessorService.saveMeasure(cf, measures);
    }

    /**
     * 返回所有的结果
     */
    @GetMapping("/getAll")
    @ApiOperation("返回所有结果")
    public AjaxResult getAll(@RequestParam("projectId") Integer projectId){
        return accessorService.getAll(projectId);
    }

    @GetMapping("/getProjects")
    @ApiOperation("根据评估师ID获取评估师对应的所有项目")
    public AjaxResult getProjects(@RequestParam("accessorId") Integer accessorId) {
        System.out.println(accessorService.getProjects(accessorId));
        return accessorService.getProjects(accessorId);
    }

    @GetMapping("/getAssessment")
    @ApiOperation("给子系统三的接口")
    public AjaxResult getAssessment(@RequestParam("projectId") Integer projectId){
        return accessorService.getAssessment(projectId);
    }

    @GetMapping("/getAllAssessments")
    @ApiOperation("获取所有的assessment")
    public AjaxResult getAllAssessments() {
        return accessorService.getAllAssessments();
    }

    @GetMapping("/getStatus")
    @ApiOperation("获取项目状态")
    public AjaxResult getStatus(@RequestParam("projectId") Integer projectId){
        return accessorService.getStatus(projectId);
    }

    @GetMapping("/getProjectById")
    @ApiOperation("根据ID获取单个项目的信息")
    public AjaxResult getProjectId(@RequestParam("projectId") Integer projectId){
        return accessorService.getProjectById(projectId);
    }

}
