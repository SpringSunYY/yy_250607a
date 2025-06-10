package com.lz.manage.model.vo.goodsShipmentInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.annotation.Excel;
import com.lz.manage.model.domain.GoodsShipmentInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 商品出货信息Vo对象 tb_goods_shipment_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class GoodsShipmentInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 出货编号 */
    @Excel(name = "出货编号")
    private Long shipmentId;

    /** 仓库编号 */
    @Excel(name = "仓库编号")
    private Long storeId;
    @Excel(name = "仓库名称")
    private String storeName;

    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private Long goodsId;
    @Excel(name = "商品名称")
    private String goodsName;

    /** 出货标题 */
    @Excel(name = "出货标题")
    private String shipmentTitle;

    /** 出货数量 */
    @Excel(name = "出货数量")
    private Long quantity;

    /** 出货价格 */
    @Excel(name = "出货价格")
    private BigDecimal shipmentPrice;

    /** 出货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipmentDate;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


     /**
     * 对象转封装类
     *
     * @param goodsShipmentInfo GoodsShipmentInfo实体对象
     * @return GoodsShipmentInfoVo
     */
    public static GoodsShipmentInfoVo objToVo(GoodsShipmentInfo goodsShipmentInfo) {
        if (goodsShipmentInfo == null) {
            return null;
        }
        GoodsShipmentInfoVo goodsShipmentInfoVo = new GoodsShipmentInfoVo();
        BeanUtils.copyProperties(goodsShipmentInfo, goodsShipmentInfoVo);
        return goodsShipmentInfoVo;
    }
}
