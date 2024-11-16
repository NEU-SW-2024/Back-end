package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SysProject;

/**
 * 项目 服务层
 *
 * @author ruoyi
 */
public interface ISysProjectService
{
    /**
     * 查询项目信息
     *
     * @param projectId 项目ID
     * @return 项目信息
     */
    public SysProject selectProjectById(Long projectId);

    /**
     * 查询项目列表
     *
     * @param project 项目信息
     * @return 项目集合
     */
    public List<SysProject> selectProjectList(SysProject project);

    /**
     * 新增项目
     *
     * @param project 项目信息
     * @return 结果
     */
    public int insertProject(SysProject project);

    /**
     * 修改项目
     *
     * @param project 项目信息
     * @return 结果
     */
    public int updateProject(SysProject project);

    /**
     * 删除项目信息
     *
     * @param projectId 项目ID
     * @return 结果
     */
    public int deleteProjectById(Long projectId);

    /**
     * 批量删除项目信息
     *
     * @param projectIds 需要删除的项目ID
     * @return 结果
     */
    public int deleteProjectByIds(Long[] projectIds);
}