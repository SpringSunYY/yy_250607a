package com.lz.manage.model.dto.storeInfo;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import com.lz.manage.model.domain.StoreInfo;
/**
 * 仓库信息Vo对象 tb_store_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class StoreInfoInsert implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long storeId;

    /** 仓库名称 */
    private String storeName;

    /** 仓库图片 */
    private String storeImage;

    /** 仓库描述 */
    private String description;

    /** 仓库状态 */
    private String status;

    /** 备注 */
    private String remark;

    /**
     * 对象转封装类
     *
     * @param storeInfoInsert 插入对象
     * @return StoreInfoInsert
     */
    public static StoreInfo insertToObj(StoreInfoInsert storeInfoInsert) {
        if (storeInfoInsert == null) {
            return null;
        }
        StoreInfo storeInfo = new StoreInfo();
        BeanUtils.copyProperties(storeInfoInsert, storeInfo);
        return storeInfo;
    }
}
