package com.lz.manage.mapper;

import java.util.List;
import com.lz.manage.model.domain.SupplierInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 供货商信息Mapper接口
 * 
 * @author YY
 * @date 2025-06-10
 */
public interface SupplierInfoMapper extends BaseMapper<SupplierInfo>
{
    /**
     * 查询供货商信息
     * 
     * @param supplierId 供货商信息主键
     * @return 供货商信息
     */
    public SupplierInfo selectSupplierInfoBySupplierId(Long supplierId);

    /**
     * 查询供货商信息列表
     * 
     * @param supplierInfo 供货商信息
     * @return 供货商信息集合
     */
    public List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo);

    /**
     * 新增供货商信息
     * 
     * @param supplierInfo 供货商信息
     * @return 结果
     */
    public int insertSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 修改供货商信息
     * 
     * @param supplierInfo 供货商信息
     * @return 结果
     */
    public int updateSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 删除供货商信息
     * 
     * @param supplierId 供货商信息主键
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierId(Long supplierId);

    /**
     * 批量删除供货商信息
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplierInfoBySupplierIds(Long[] supplierIds);
}
