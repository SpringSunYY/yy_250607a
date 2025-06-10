package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.StoreStockInfoMapper;
import com.lz.manage.model.domain.StoreStockInfo;
import com.lz.manage.service.IStoreStockInfoService;
import com.lz.manage.model.dto.storeStockInfo.StoreStockInfoQuery;
import com.lz.manage.model.vo.storeStockInfo.StoreStockInfoVo;

/**
 * 仓库库存Service业务层处理
 * 
 * @author YY
 * @date 2025-06-10
 */
@Service
public class StoreStockInfoServiceImpl extends ServiceImpl<StoreStockInfoMapper, StoreStockInfo> implements IStoreStockInfoService
{
    @Resource
    private StoreStockInfoMapper storeStockInfoMapper;

    //region mybatis代码
    /**
     * 查询仓库库存
     * 
     * @param stockId 仓库库存主键
     * @return 仓库库存
     */
    @Override
    public StoreStockInfo selectStoreStockInfoByStockId(Long stockId)
    {
        return storeStockInfoMapper.selectStoreStockInfoByStockId(stockId);
    }

    /**
     * 查询仓库库存列表
     * 
     * @param storeStockInfo 仓库库存
     * @return 仓库库存
     */
    @Override
    public List<StoreStockInfo> selectStoreStockInfoList(StoreStockInfo storeStockInfo)
    {
        return storeStockInfoMapper.selectStoreStockInfoList(storeStockInfo);
    }

    /**
     * 新增仓库库存
     * 
     * @param storeStockInfo 仓库库存
     * @return 结果
     */
    @Override
    public int insertStoreStockInfo(StoreStockInfo storeStockInfo)
    {
        storeStockInfo.setCreateTime(DateUtils.getNowDate());
        return storeStockInfoMapper.insertStoreStockInfo(storeStockInfo);
    }

    /**
     * 修改仓库库存
     * 
     * @param storeStockInfo 仓库库存
     * @return 结果
     */
    @Override
    public int updateStoreStockInfo(StoreStockInfo storeStockInfo)
    {
        storeStockInfo.setUpdateTime(DateUtils.getNowDate());
        return storeStockInfoMapper.updateStoreStockInfo(storeStockInfo);
    }

    /**
     * 批量删除仓库库存
     * 
     * @param stockIds 需要删除的仓库库存主键
     * @return 结果
     */
    @Override
    public int deleteStoreStockInfoByStockIds(Long[] stockIds)
    {
        return storeStockInfoMapper.deleteStoreStockInfoByStockIds(stockIds);
    }

    /**
     * 删除仓库库存信息
     * 
     * @param stockId 仓库库存主键
     * @return 结果
     */
    @Override
    public int deleteStoreStockInfoByStockId(Long stockId)
    {
        return storeStockInfoMapper.deleteStoreStockInfoByStockId(stockId);
    }
    //endregion
    @Override
    public QueryWrapper<StoreStockInfo> getQueryWrapper(StoreStockInfoQuery storeStockInfoQuery){
        QueryWrapper<StoreStockInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = storeStockInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long stockId = storeStockInfoQuery.getStockId();
        queryWrapper.eq( StringUtils.isNotNull(stockId),"stock_id",stockId);

        Long storeId = storeStockInfoQuery.getStoreId();
        queryWrapper.eq( StringUtils.isNotNull(storeId),"store_id",storeId);

        Long goodsId = storeStockInfoQuery.getGoodsId();
        queryWrapper.eq( StringUtils.isNotNull(goodsId),"goods_id",goodsId);

        String stockName = storeStockInfoQuery.getStockName();
        queryWrapper.like(StringUtils.isNotEmpty(stockName) ,"stock_name",stockName);

        String createBy = storeStockInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = storeStockInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = storeStockInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = storeStockInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<StoreStockInfoVo> convertVoList(List<StoreStockInfo> storeStockInfoList) {
        if (StringUtils.isEmpty(storeStockInfoList)) {
            return Collections.emptyList();
        }
        return storeStockInfoList.stream().map(StoreStockInfoVo::objToVo).collect(Collectors.toList());
    }

}
