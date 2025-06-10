package com.lz.manage.service;

import java.util.List;
import com.lz.manage.model.domain.GoodsShipmentInfo;
import com.lz.manage.model.vo.goodsShipmentInfo.GoodsShipmentInfoVo;
import com.lz.manage.model.dto.goodsShipmentInfo.GoodsShipmentInfoQuery;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
/**
 * 商品出货信息Service接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface IGoodsShipmentInfoService extends IService<GoodsShipmentInfo>
{
    //region mybatis代码
    /**
     * 查询商品出货信息
     * 
     * @param shipmentId 商品出货信息主键
     * @return 商品出货信息
     */
    public GoodsShipmentInfo selectGoodsShipmentInfoByShipmentId(Long shipmentId);

    /**
     * 查询商品出货信息列表
     * 
     * @param goodsShipmentInfo 商品出货信息
     * @return 商品出货信息集合
     */
    public List<GoodsShipmentInfo> selectGoodsShipmentInfoList(GoodsShipmentInfo goodsShipmentInfo);

    /**
     * 新增商品出货信息
     * 
     * @param goodsShipmentInfo 商品出货信息
     * @return 结果
     */
    public int insertGoodsShipmentInfo(GoodsShipmentInfo goodsShipmentInfo);

    /**
     * 修改商品出货信息
     * 
     * @param goodsShipmentInfo 商品出货信息
     * @return 结果
     */
    public int updateGoodsShipmentInfo(GoodsShipmentInfo goodsShipmentInfo);

    /**
     * 批量删除商品出货信息
     * 
     * @param shipmentIds 需要删除的商品出货信息主键集合
     * @return 结果
     */
    public int deleteGoodsShipmentInfoByShipmentIds(Long[] shipmentIds);

    /**
     * 删除商品出货信息信息
     * 
     * @param shipmentId 商品出货信息主键
     * @return 结果
     */
    public int deleteGoodsShipmentInfoByShipmentId(Long shipmentId);
    //endregion
    /**
     * 获取查询条件
     *
     * @param goodsShipmentInfoQuery 查询条件对象
     * @return 查询条件
     */
    QueryWrapper<GoodsShipmentInfo> getQueryWrapper(GoodsShipmentInfoQuery goodsShipmentInfoQuery);

    /**
     * 转换vo
     *
     * @param goodsShipmentInfoList GoodsShipmentInfo集合
     * @return GoodsShipmentInfoVO集合
     */
    List<GoodsShipmentInfoVo> convertVoList(List<GoodsShipmentInfo> goodsShipmentInfoList);
}
