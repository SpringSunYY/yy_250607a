package com.lz.manage.model.dto.goodsShipmentInfo;

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
import com.lz.manage.model.domain.GoodsShipmentInfo;
/**
 * 商品出货信息Query对象 tb_goods_shipment_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class GoodsShipmentInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 出货编号 */
    private Long shipmentId;

    /** 仓库编号 */
    private Long storeId;

    /** 商品编号 */
    private Long goodsId;

    /** 出货标题 */
    private String shipmentTitle;

    /** 出货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date shipmentDate;

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
     * @param goodsShipmentInfoQuery 查询对象
     * @return GoodsShipmentInfo
     */
    public static GoodsShipmentInfo queryToObj(GoodsShipmentInfoQuery goodsShipmentInfoQuery) {
        if (goodsShipmentInfoQuery == null) {
            return null;
        }
        GoodsShipmentInfo goodsShipmentInfo = new GoodsShipmentInfo();
        BeanUtils.copyProperties(goodsShipmentInfoQuery, goodsShipmentInfo);
        return goodsShipmentInfo;
    }
}
