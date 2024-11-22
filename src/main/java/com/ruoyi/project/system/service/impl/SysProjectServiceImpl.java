package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.domain.SysProject;
import com.ruoyi.project.system.mapper.SysProjectMapper;
import com.ruoyi.project.system.service.ISysProjectService;

/**
 * 项目 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysProjectServiceImpl implements ISysProjectService
{
    @Autowired
    private SysProjectMapper projectMapper;

    /**
     * 查询项目信息
     *
     * @param projectId 项目ID
     * @return 项目信息
     */
    @Override
    public SysProject selectProjectById(Long projectId)
    {
        return projectMapper.selectProjectById(projectId);
    }

    

    /**
     * 查询项目列表
     *
     * @param project 项目信息
     * @return 项目集合
     */
    @Override
    public List<SysProject> selectProjectList(SysProject project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int insertProject(SysProject project)
    {
        return projectMapper.insertProject(project);
    }

    /**
     * 修改项目
     *
     * @param project 项目信息
     * @return 结果
     */
    @Override
    public int updateProject(SysProject project)
    {
        return projectMapper.updateProject(project);
    }

    /**
     * 删除项目对象
     *
     * @param projectId 项目ID
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long projectId)
    {
        return projectMapper.deleteProjectById(projectId);
    }

    /**
     * 批量删除项目信息
     *
     * @param projectIds 需要删除的项目ID
     * @return 结果
     */
    @Override
    public int deleteProjectByIds(Long[] projectIds)
    {
        return projectMapper.deleteProjectByIds(projectIds);
    }
}