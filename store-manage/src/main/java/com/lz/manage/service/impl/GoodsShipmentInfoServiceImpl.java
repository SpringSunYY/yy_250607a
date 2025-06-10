package com.lz.manage.service.impl;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.lz.common.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.utils.DateUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lz.manage.mapper.GoodsShipmentInfoMapper;
import com.lz.manage.model.domain.GoodsShipmentInfo;
import com.lz.manage.service.IGoodsShipmentInfoService;
import com.lz.manage.model.dto.goodsShipmentInfo.GoodsShipmentInfoQuery;
import com.lz.manage.model.vo.goodsShipmentInfo.GoodsShipmentInfoVo;

/**
 * 商品出货信息Service业务层处理
 * 
 * @author YY
 * @date 2025-06-10
 */
@Service
public class GoodsShipmentInfoServiceImpl extends ServiceImpl<GoodsShipmentInfoMapper, GoodsShipmentInfo> implements IGoodsShipmentInfoService
{
    @Resource
    private GoodsShipmentInfoMapper goodsShipmentInfoMapper;

    //region mybatis代码
    /**
     * 查询商品出货信息
     * 
     * @param shipmentId 商品出货信息主键
     * @return 商品出货信息
     */
    @Override
    public GoodsShipmentInfo selectGoodsShipmentInfoByShipmentId(Long shipmentId)
    {
        return goodsShipmentInfoMapper.selectGoodsShipmentInfoByShipmentId(shipmentId);
    }

    /**
     * 查询商品出货信息列表
     * 
     * @param goodsShipmentInfo 商品出货信息
     * @return 商品出货信息
     */
    @Override
    public List<GoodsShipmentInfo> selectGoodsShipmentInfoList(GoodsShipmentInfo goodsShipmentInfo)
    {
        return goodsShipmentInfoMapper.selectGoodsShipmentInfoList(goodsShipmentInfo);
    }

    /**
     * 新增商品出货信息
     * 
     * @param goodsShipmentInfo 商品出货信息
     * @return 结果
     */
    @Override
    public int insertGoodsShipmentInfo(GoodsShipmentInfo goodsShipmentInfo)
    {
        goodsShipmentInfo.setCreateTime(DateUtils.getNowDate());
        return goodsShipmentInfoMapper.insertGoodsShipmentInfo(goodsShipmentInfo);
    }

    /**
     * 修改商品出货信息
     * 
     * @param goodsShipmentInfo 商品出货信息
     * @return 结果
     */
    @Override
    public int updateGoodsShipmentInfo(GoodsShipmentInfo goodsShipmentInfo)
    {
        goodsShipmentInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsShipmentInfoMapper.updateGoodsShipmentInfo(goodsShipmentInfo);
    }

    /**
     * 批量删除商品出货信息
     * 
     * @param shipmentIds 需要删除的商品出货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsShipmentInfoByShipmentIds(Long[] shipmentIds)
    {
        return goodsShipmentInfoMapper.deleteGoodsShipmentInfoByShipmentIds(shipmentIds);
    }

    /**
     * 删除商品出货信息信息
     * 
     * @param shipmentId 商品出货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsShipmentInfoByShipmentId(Long shipmentId)
    {
        return goodsShipmentInfoMapper.deleteGoodsShipmentInfoByShipmentId(shipmentId);
    }
    //endregion
    @Override
    public QueryWrapper<GoodsShipmentInfo> getQueryWrapper(GoodsShipmentInfoQuery goodsShipmentInfoQuery){
        QueryWrapper<GoodsShipmentInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsShipmentInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long shipmentId = goodsShipmentInfoQuery.getShipmentId();
        queryWrapper.eq( StringUtils.isNotNull(shipmentId),"shipment_id",shipmentId);

        Long storeId = goodsShipmentInfoQuery.getStoreId();
        queryWrapper.eq( StringUtils.isNotNull(storeId),"store_id",storeId);

        Long goodsId = goodsShipmentInfoQuery.getGoodsId();
        queryWrapper.eq( StringUtils.isNotNull(goodsId),"goods_id",goodsId);

        String shipmentTitle = goodsShipmentInfoQuery.getShipmentTitle();
        queryWrapper.like(StringUtils.isNotEmpty(shipmentTitle) ,"shipment_title",shipmentTitle);

        Date shipmentDate = goodsShipmentInfoQuery.getShipmentDate();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginShipmentDate"))&&StringUtils.isNotNull(params.get("endShipmentDate")),"shipment_date",params.get("beginShipmentDate"),params.get("endShipmentDate"));

        String createBy = goodsShipmentInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy) ,"create_by",createBy);

        Date createTime = goodsShipmentInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime"))&&StringUtils.isNotNull(params.get("endCreateTime")),"create_time",params.get("beginCreateTime"),params.get("endCreateTime"));

        String updateBy = goodsShipmentInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy) ,"update_by",updateBy);

        Date updateTime = goodsShipmentInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime"))&&StringUtils.isNotNull(params.get("endUpdateTime")),"update_time",params.get("beginUpdateTime"),params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsShipmentInfoVo> convertVoList(List<GoodsShipmentInfo> goodsShipmentInfoList) {
        if (StringUtils.isEmpty(goodsShipmentInfoList)) {
            return Collections.emptyList();
        }
        return goodsShipmentInfoList.stream().map(GoodsShipmentInfoVo::objToVo).collect(Collectors.toList());
    }

}
