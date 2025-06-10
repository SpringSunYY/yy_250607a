package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.StoreInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 仓库信息Mapper接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface StoreInfoMapper extends BaseMapper<StoreInfo>
{
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
     * 删除仓库信息
     * 
     * @param storeId 仓库信息主键
     * @return 结果
     */
    public int deleteStoreInfoByStoreId(Long storeId);

    /**
     * 批量删除仓库信息
     * 
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreInfoByStoreIds(Long[] storeIds);
}
