package com.lz.manage.model.vo.storeStockInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.StoreStockInfo;
/**
 * 仓库库存Vo对象 tb_store_stock_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class StoreStockInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 库存编号 */
    @Excel(name = "库存编号")
    private Long stockId;

    /** 仓库编号 */
    @Excel(name = "仓库编号")
    private Long storeId;
    @Excel(name = "仓库")
    private String storeName;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private Long goodsId;
    @Excel(name = "商品名称")
    private String goodsName;

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


     /**
     * 对象转封装类
     *
     * @param storeStockInfo StoreStockInfo实体对象
     * @return StoreStockInfoVo
     */
    public static StoreStockInfoVo objToVo(StoreStockInfo storeStockInfo) {
        if (storeStockInfo == null) {
            return null;
        }
        StoreStockInfoVo storeStockInfoVo = new StoreStockInfoVo();
        BeanUtils.copyProperties(storeStockInfo, storeStockInfoVo);
        return storeStockInfoVo;
    }
}
