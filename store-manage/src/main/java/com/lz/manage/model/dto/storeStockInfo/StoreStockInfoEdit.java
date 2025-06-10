package com.lz.manage.model.dto.storeStockInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.StoreStockInfo;
/**
 * 仓库库存Vo对象 tb_store_stock_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class StoreStockInfoEdit implements Serializable
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

    /** 库存 */
    private Long stock;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param storeStockInfoEdit 编辑对象
     * @return StoreStockInfo
     */
    public static StoreStockInfo editToObj(StoreStockInfoEdit storeStockInfoEdit) {
        if (storeStockInfoEdit == null) {
            return null;
        }
        StoreStockInfo storeStockInfo = new StoreStockInfo();
        BeanUtils.copyProperties(storeStockInfoEdit, storeStockInfo);
        return storeStockInfo;
    }
}
