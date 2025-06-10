package com.lz.manage.model.dto.storeStockInfo;

import java.util.Map;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.StoreStockInfo;
/**
 * 仓库库存Query对象 tb_store_stock_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class StoreStockInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 库存编号 */
    private Long stockId;

    /** 仓库编号 */
    private Long storeId;

    /** 商品编号 */
    private Long goodsId;

    /** 库存别名 */
    private String stockName;

    /** 创建人 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private Map<String, Object> params;

    /**
     * 对象转封装类
     *
     * @param storeStockInfoQuery 查询对象
     * @return StoreStockInfo
     */
    public static StoreStockInfo queryToObj(StoreStockInfoQuery storeStockInfoQuery) {
        if (storeStockInfoQuery == null) {
            return null;
        }
        StoreStockInfo storeStockInfo = new StoreStockInfo();
        BeanUtils.copyProperties(storeStockInfoQuery, storeStockInfo);
        return storeStockInfo;
    }
}
