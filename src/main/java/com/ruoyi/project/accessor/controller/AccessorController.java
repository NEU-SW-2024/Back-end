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
    public AjaxResult saveFunc(@RequestBody ArrayList<FeatDAO> featDAOS){
        System.out.println(featDAOS);
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
    @PostMapping("/saveMeasure")
    @ApiOperation("保存度量表")
    public AjaxResult saveMeasure(@RequestBody List<Measure> measures){
        return accessorService.saveMeasure(measures);
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
    public AjaxResult getProjects() {
        return accessorService.getProjects();
    }

    @GetMapping("/getStatus")
    public AjaxResult getStatus(@RequestParam("projectId") Integer projectId){
        return accessorService.getStatus(projectId);
    }

    @GetMapping("/getProject")
    @ApiOperation("根据ID获取项目")
    public AjaxResult getProject(@RequestParam("projectId") Integer projectId) {
        return accessorService.getProject(projectId);
    }
    //前端通过项目id获取项目名称
    @GetMapping("/getProjectName")
    @ApiOperation("获取项目名称")
    public AjaxResult getProjectName(@RequestParam("projectId") Integer projectId){
        return AjaxResult.success(accessorService.getProjectNameById(projectId));
    }

    @GetMapping("/getPendingProjects")
    @ApiOperation("获取所有待评估项目")
    public AjaxResult getPendingProjects() {
        // 调用服务层方法来获取数据
        return accessorService.getPendingProjects();
    }

    //前端通过项目id获取项目的DFP
    @GetMapping("/getDFP")
    @ApiOperation("获取DFP")
    public AjaxResult getDFP(@RequestParam("projectId") Integer projectId){
        System.out.println("accessorService.getDFP:"+accessorService.getDFP(projectId));
        return AjaxResult.success(accessorService.getDFP(projectId));
    }

}