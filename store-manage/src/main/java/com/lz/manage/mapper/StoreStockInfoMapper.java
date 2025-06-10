package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.StoreStockInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 仓库库存Mapper接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface StoreStockInfoMapper extends BaseMapper<StoreStockInfo>
{
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
     * 删除仓库库存
     * 
     * @param stockId 仓库库存主键
     * @return 结果
     */
    public int deleteStoreStockInfoByStockId(Long stockId);

    /**
     * 批量删除仓库库存
     * 
     * @param stockIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreStockInfoByStockIds(Long[] stockIds);
}
