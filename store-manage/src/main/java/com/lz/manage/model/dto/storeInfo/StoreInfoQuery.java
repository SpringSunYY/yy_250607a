package com.lz.manage.model.dto.storeInfo;

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
import com.lz.manage.model.domain.StoreInfo;
/**
 * 仓库信息Query对象 tb_store_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class StoreInfoQuery implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long storeId;

    /** 仓库名称 */
    private String storeName;

    /** 仓库状态 */
    private String status;

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
     * @param storeInfoQuery 查询对象
     * @return StoreInfo
     */
    public static StoreInfo queryToObj(StoreInfoQuery storeInfoQuery) {
        if (storeInfoQuery == null) {
            return null;
        }
        StoreInfo storeInfo = new StoreInfo();
        BeanUtils.copyProperties(storeInfoQuery, storeInfo);
        return storeInfo;
    }
}
