package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.StoreInfo;
import com.lz.manage.model.vo.storeInfo.StoreInfoVo;
import com.lz.manage.model.dto.storeInfo.StoreInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 仓库信息Service接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface IStoreInfoService extends IService<StoreInfo>
{
    //region mybatis代码
    /**
     * 查询仓库信息
     * 
     * @param storeId 仓库信息主键
     * @return 仓库信息
     */
    public StoreInfo selectStoreInfoByStoreId(Long storeId);

    /**
     * 查询仓库信息列表
     * 
     * @param storeInfo 仓库信息
     * @return 仓库信息集合
     */
    public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo);

    /**
     * 新增仓库信息
     * 
     * @param storeInfo 仓库信息
     * @return 结果
     */
    public int insertStoreInfo(StoreInfo storeInfo);

    /**
     * 修改仓库信息
     * 
     * @param storeInfo 仓库信息
     * @return 结果
     */
    public int updateStoreInfo(StoreInfo storeInfo);

    /**
     * 批量删除仓库信息
     * 
     * @param storeIds 需要删除的仓库信息主键集合
     * @return 结果
     */
    public int deleteStoreInfoByStoreIds(Long[] storeIds);

    /**
     * 删除仓库信息信息
     * 
     * @param storeId 仓库信息主键
     * @return 结果
     */
    public int deleteStoreInfoByStoreId(Long storeId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param storeInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<StoreInfo> getQueryWrapper(StoreInfoQuery storeInfoQuery);

    /**
     * 转换vo
     *
     * @param storeInfoList StoreInfo集合
     * @return StoreInfoVO集合
     */
    List<StoreInfoVo> convertVoList(List<StoreInfo> storeInfoList);
}
