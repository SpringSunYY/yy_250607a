package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.StoreStockInfo;
import com.lz.manage.model.vo.storeStockInfo.StoreStockInfoVo;
import com.lz.manage.model.dto.storeStockInfo.StoreStockInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 仓库库存Service接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface IStoreStockInfoService extends IService<StoreStockInfo>
{
    //region mybatis代码
    /**
     * 查询仓库库存
     * 
     * @param stockId 仓库库存主键
     * @return 仓库库存
     */
    public StoreStockInfo selectStoreStockInfoByStockId(Long stockId);

    /**
     * 查询仓库库存列表
     * 
     * @param storeStockInfo 仓库库存
     * @return 仓库库存集合
     */
    public List<StoreStockInfo> selectStoreStockInfoList(StoreStockInfo storeStockInfo);

    /**
     * 新增仓库库存
     * 
     * @param storeStockInfo 仓库库存
     * @return 结果
     */
    public int insertStoreStockInfo(StoreStockInfo storeStockInfo);

    /**
     * 修改仓库库存
     * 
     * @param storeStockInfo 仓库库存
     * @return 结果
     */
    public int updateStoreStockInfo(StoreStockInfo storeStockInfo);

    /**
     * 批量删除仓库库存
     * 
     * @param stockIds 需要删除的仓库库存主键集合
     * @return 结果
     */
    public int deleteStoreStockInfoByStockIds(Long[] stockIds);

    /**
     * 删除仓库库存信息
     * 
     * @param stockId 仓库库存主键
     * @return 结果
     */
    public int deleteStoreStockInfoByStockId(Long stockId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param storeStockInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<StoreStockInfo> getQueryWrapper(StoreStockInfoQuery storeStockInfoQuery);

    /**
     * 转换vo
     *
     * @param storeStockInfoList StoreStockInfo集合
     * @return StoreStockInfoVO集合
     */
    List<StoreStockInfoVo> convertVoList(List<StoreStockInfo> storeStockInfoList);
}
