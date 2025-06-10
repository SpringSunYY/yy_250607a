package com.lz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.common.utils.DateUtils;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.mapper.StoreInfoMapper;
import com.lz.manage.model.domain.StoreInfo;
import com.lz.manage.model.dto.storeInfo.StoreInfoQuery;
import com.lz.manage.model.vo.storeInfo.StoreInfoVo;
import com.lz.manage.service.IStoreInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 仓库信息Service业务层处理
 *
 * @author YY
 * @date 2025-06-10
 */
@Service
public class StoreInfoServiceImpl extends ServiceImpl<StoreInfoMapper, StoreInfo> implements IStoreInfoService
{
    @Resource
    private StoreInfoMapper storeInfoMapper;

    //region mybatis代码
    /**
     * 查询仓库信息
     *
     * @param storeId 仓库信息主键
     * @return 仓库信息
     */
    @Override
    public StoreInfo selectStoreInfoByStoreId(Long storeId)
    {
        return storeInfoMapper.selectStoreInfoByStoreId(storeId);
    }

    /**
     * 查询仓库信息列表
     *
     * @param storeInfo 仓库信息
     * @return 仓库信息
     */
    @Override
    public List<StoreInfo> selectStoreInfoList(StoreInfo storeInfo)
    {
        return storeInfoMapper.selectStoreInfoList(storeInfo);
    }

    /**
     * 新增仓库信息
     *
     * @param storeInfo 仓库信息
     * @return 结果
     */
    @Override
    public int insertStoreInfo(StoreInfo storeInfo)
    {
        storeInfo.setCreateBy(SecurityUtils.getUsername());
        storeInfo.setCreateTime(DateUtils.getNowDate());
        return storeInfoMapper.insertStoreInfo(storeInfo);
    }

    /**
     * 修改仓库信息
     *
     * @param storeInfo 仓库信息
     * @return 结果
     */
    @Override
    public int updateStoreInfo(StoreInfo storeInfo)
    {
        storeInfo.setUpdateBy(SecurityUtils.getUsername());
        storeInfo.setUpdateTime(DateUtils.getNowDate());
        return storeInfoMapper.updateStoreInfo(storeInfo);
    }

    /**
     * 批量删除仓库信息
     *
     * @param storeIds 需要删除的仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteStoreInfoByStoreIds(Long[] storeIds)
    {
        return storeInfoMapper.deleteStoreInfoByStoreIds(storeIds);
    }

    /**
     * 删除仓库信息信息
     *
     * @param storeId 仓库信息主键
     * @return 结果
     */
    @Override
    public int deleteStoreInfoByStoreId(Long storeId)
    {
        return storeInfoMapper.deleteStoreInfoByStoreId(storeId);
    }
    //endregion
    @Override
    public QueryWrapper<StoreInfo> getQueryWrapper(StoreInfoQuery storeInfoQuery){
        QueryWrapper<StoreInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = storeInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long storeId = storeInfoQuery.getStoreId();
        queryWrapper.eq( StringUtils.isNotNull(storeId),"store_id",storeId);

        String storeName = storeInfoQuery.getStoreName();
        queryWrapper.like(StringUtils.isNotEmpty(storeName) ,"store_name",storeName);

        String status = storeInfoQuery.getStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(status) ,"status",status);

        String createBy = storeInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = storeInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = storeInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = storeInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<StoreInfoVo> convertVoList(List<StoreInfo> storeInfoList) {
        if (StringUtils.isEmpty(storeInfoList)) {
            return Collections.emptyList();
        }
        return storeInfoList.stream().map(StoreInfoVo::objToVo).collect(Collectors.toList());
    }

}
