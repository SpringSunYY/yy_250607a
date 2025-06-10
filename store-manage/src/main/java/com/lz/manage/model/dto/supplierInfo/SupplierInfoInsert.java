package com.lz.manage.model.dto.supplierInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.SupplierInfo;
/**
 * 供货商信息Vo对象 tb_supplier_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class SupplierInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 供应商编号 */
    private Long supplierId;

    /** 供应商名称 */
    private String supplierName;

    /** 手机号码 */
    private String phone;

    /** 地址 */
    private String address;

    /** 图片 */
    private String supplierImage;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param supplierInfoInsert 插入对象
     * @return SupplierInfoInsert
     */
    public static SupplierInfo insertToObj(SupplierInfoInsert supplierInfoInsert) {
        if (supplierInfoInsert == null) {
            return null;
        }
        SupplierInfo supplierInfo = new SupplierInfo();
        BeanUtils.copyProperties(supplierInfoInsert, supplierInfo);
        return supplierInfo;
    }
}
