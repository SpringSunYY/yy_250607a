package com.lz.manage.model.vo.goodsPurchaseInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.common.annotation.Excel;
import com.lz.manage.model.domain.GoodsPurchaseInfo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品进货信息Vo对象 tb_goods_purchase_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class GoodsPurchaseInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 进货编号
     */
    @Excel(name = "进货编号")
    private Long purchaseId;

    /**
     * 仓库编号
     */
    @Excel(name = "仓库编号")
    private Long storeId;
    @Excel(name = "仓库")
    private String storeName;

    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private Long goodsId;
    @Excel(name = "商品名称")
    private String goodsName;

    /**
     * 供应商
     */
    @Excel(name = "供应商")
    private Long supplierId;
    @Excel(name = "供应商")
    private String supplierName;

    /**
     * 进货标题
     */
    @Excel(name = "进货标题")
    private String purchaseTitle;

    /**
     * 进货价格
     */
    @Excel(name = "进货价格")
    private BigDecimal purchasePrice;

    /**
     * 进货数量
     */
    @Excel(name = "进货数量")
    private Long quantity;

    /**
     * 进货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "进货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

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
     * 对象转封装类
     *
     * @param goodsPurchaseInfo GoodsPurchaseInfo实体对象
     * @return GoodsPurchaseInfoVo
     */
    public static GoodsPurchaseInfoVo objToVo(GoodsPurchaseInfo goodsPurchaseInfo) {
        if (goodsPurchaseInfo == null) {
            return null;
        }
        GoodsPurchaseInfoVo goodsPurchaseInfoVo = new GoodsPurchaseInfoVo();
        BeanUtils.copyProperties(goodsPurchaseInfo, goodsPurchaseInfoVo);
        return goodsPurchaseInfoVo;
    }
}
