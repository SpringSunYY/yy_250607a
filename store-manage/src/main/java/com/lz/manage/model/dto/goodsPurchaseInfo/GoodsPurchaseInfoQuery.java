package com.lz.manage.model.dto.goodsPurchaseInfo;

import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.BeanUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
/**
 * 商品进货信息Query对象 tb_goods_purchase_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class GoodsPurchaseInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 进货编号 */
    private Long purchaseId;

    /** 仓库编号 */
    private Long storeId;

    /** 商品编号 */
    private Long goodsId;

    /** 供应商 */
    private Long supplierId;

    /** 进货标题 */
    private String purchaseTitle;

    /** 进货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;

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
     * @param goodsPurchaseInfoQuery 查询对象
     * @return GoodsPurchaseInfo
     */
    public static GoodsPurchaseInfo queryToObj(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery) {
        if (goodsPurchaseInfoQuery == null) {
            return null;
        }
        GoodsPurchaseInfo goodsPurchaseInfo = new GoodsPurchaseInfo();
        BeanUtils.copyProperties(goodsPurchaseInfoQuery, goodsPurchaseInfo);
        return goodsPurchaseInfo;
    }
}
