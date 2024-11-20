package com.ruoyi.project.accessor.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.accessor.domain.*;
import com.ruoyi.project.accessor.service.AccessorService;
import com.ruoyi.project.system.controller.SysProfileController;
import com.ruoyi.project.system.domain.SysProject;
import com.ruoyi.project.system.mapper.SysProjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dev-api/accessor")
@Api("评估师")
public class AccessorController {

    @Resource
    private AccessorService accessorService;
    @Autowired
    private SysProfileController sysProfileController;
    @Autowired
    private SysProjectMapper sysProjectMapper;

//    /**
//     * 批量更新功能点
//     */
//    @PostMapping("/updateFunc")
//    public Result updateFunc(@RequestBody List<FeatDAO> featDAOS){
//        return accessorService.updateFunc(featDAOS);
//    }
//
//    /**
//     * 删除功能点（非批量）
//     * @param deleteRequest
//     * @return
//     */
//    @PostMapping("/deleteFunc")
//    public Result deleteFunc(@RequestBody DeleteFuncRequest deleteRequest){
//        return accessorService.deleteFunc(deleteRequest);
//    }

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
        System.out.println("accessorId:"+accessorId);
        System.out.println(sysProjectMapper.selectProjectList(null));
        List <SysProject>sysProjectMapperList = sysProjectMapper.selectProjectList(null);
        //筛选所有accessorId为accessorId的项目参考下面这行
//        return list.stream().filter(u -> u.getCreateBy().equals(SecurityUtils.getUsername()) || u.getUserName().equals(SecurityUtils.getUsername())).collect(Collectors.toList());
        List<SysProject> sysProjectList = sysProjectMapperList.stream().filter(u -> u.getAccessorId().equals(SecurityUtils.getUserId())).collect(Collectors.toList());
        return AjaxResult.success(sysProjectList);
}

@GetMapping("/getStatus")
public AjaxResult getStatus(@RequestParam("projectId") Integer projectId){
return accessorService.getStatus(projectId);
}
}