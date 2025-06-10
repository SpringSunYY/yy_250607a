package com.lz.manage.model.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.lz.common.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
/**
 * 仓库库存对象 tb_store_stock_info
 *
 * @author YY
 * @date 2025-06-10
 */
@TableName("tb_store_stock_info")
@Data
public class StoreStockInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 库存编号 */
    @Excel(name = "库存编号")
    @TableId(value = "stock_id", type = IdType.ASSIGN_ID)
    private Long stockId;

    /** 仓库编号 */
    @Excel(name = "仓库编号")
    private Long storeId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;

    /** 库存别名 */
    @Excel(name = "库存别名")
    private String stockName;

    /** 库存 */
    @Excel(name = "库存")
    private Long stock;

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

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;
}
