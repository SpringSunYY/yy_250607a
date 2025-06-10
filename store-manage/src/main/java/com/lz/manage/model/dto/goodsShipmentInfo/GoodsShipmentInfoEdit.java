package com.lz.manage.model.dto.goodsShipmentInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsShipmentInfo;
/**
 * 商品出货信息Vo对象 tb_goods_shipment_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class GoodsShipmentInfoEdit implements Serializable
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

    /** 出货数量 */
    private Long quantity;

    /** 出货价格 */
    private BigDecimal shipmentPrice;

    /** 出货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date shipmentDate;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsShipmentInfoEdit 编辑对象
     * @return GoodsShipmentInfo
     */
    public static GoodsShipmentInfo editToObj(GoodsShipmentInfoEdit goodsShipmentInfoEdit) {
        if (goodsShipmentInfoEdit == null) {
            return null;
        }
        GoodsShipmentInfo goodsShipmentInfo = new GoodsShipmentInfo();
        BeanUtils.copyProperties(goodsShipmentInfoEdit, goodsShipmentInfo);
        return goodsShipmentInfo;
    }
}
