package com.lz.manage.model.vo.storeInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.lz.common.annotation.Excel;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.manage.model.domain.StoreInfo;
/**
 * 仓库信息Vo对象 tb_store_info
 *
 * @author YY
 * @date 2025-06-10
 */
@Data
public class StoreInfoVo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @Excel(name = "编号")
    private Long storeId;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String storeName;

    /** 仓库图片 */
    @Excel(name = "仓库图片")
    private String storeImage;

    /** 仓库描述 */
    @Excel(name = "仓库描述")
    private String description;

    /** 仓库状态 */
    @Excel(name = "仓库状态")
    private String status;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateTime;

    /** 备注 */
    @Excel(name = "备注")
    private String remark;


     /**
     * 对象转封装类
     *
     * @param storeInfo StoreInfo实体对象
     * @return StoreInfoVo
     */
    public static StoreInfoVo objToVo(StoreInfo storeInfo) {
        if (storeInfo == null) {
            return null;
        }
        StoreInfoVo storeInfoVo = new StoreInfoVo();
        BeanUtils.copyProperties(storeInfo, storeInfoVo);
        return storeInfoVo;
    }
}
