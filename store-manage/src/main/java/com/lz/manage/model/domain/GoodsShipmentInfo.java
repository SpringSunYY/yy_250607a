package com.lz.manage.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lz.common.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 商品出货信息对象 tb_goods_shipment_info
 *
 * @author YY
 * @date 2025-06-10
 */
@TableName("tb_goods_shipment_info")
@Data
public class GoodsShipmentInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 出货编号
     */
    @Excel(name = "出货编号")
    @TableId(value = "shipment_id", type = IdType.ASSIGN_ID)
    private Long shipmentId;

    /**
     * 仓库编号
     */
    @Excel(name = "仓库编号")
    private Long storeId;
    @TableField(exist = false)
    @Excel(name = "仓库名称")
    private String storeName;

    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private Long goodsId;
    @TableField(exist = false)
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 出货标题
     */
    @Excel(name = "出货标题")
    private String shipmentTitle;

    /**
     * 出货数量
     */
    @Excel(name = "出货数量")
    private Long quantity;

    /**
     * 出货价格
     */
    @Excel(name = "出货价格")
    private BigDecimal shipmentPrice;

    /**
     * 出货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shipmentDate;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 请求参数
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
