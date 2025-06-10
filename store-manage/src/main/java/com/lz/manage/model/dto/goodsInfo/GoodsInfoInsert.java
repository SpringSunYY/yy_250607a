package com.lz.manage.model.dto.goodsInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.GoodsInfo;
/**
 * 商品信息Vo对象 tb_goods_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class GoodsInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 商品编号 */
    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 商品图片 */
    private String goodsImage;

    /** 商品描述 */
    private String description;

    /** 商品状态 */
    private String status;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param goodsInfoInsert 插入对象
     * @return GoodsInfoInsert
     */
    public static GoodsInfo insertToObj(GoodsInfoInsert goodsInfoInsert) {
        if (goodsInfoInsert == null) {
            return null;
        }
        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.copyProperties(goodsInfoInsert, goodsInfo);
        return goodsInfo;
    }
}
