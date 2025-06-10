package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.GoodsShipmentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 商品出货信息Mapper接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface GoodsShipmentInfoMapper extends BaseMapper<GoodsShipmentInfo>
{
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
     * 删除商品出货信息
     * 
     * @param shipmentId 商品出货信息主键
     * @return 结果
     */
    public int deleteGoodsShipmentInfoByShipmentId(Long shipmentId);

    /**
     * 批量删除商品出货信息
     * 
     * @param shipmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsShipmentInfoByShipmentIds(Long[] shipmentIds);
}
