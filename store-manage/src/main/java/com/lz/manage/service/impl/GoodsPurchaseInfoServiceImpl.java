package com.lz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.common.exception.ServiceException;
import com.lz.common.utils.DateUtils;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.mapper.GoodsPurchaseInfoMapper;
import com.lz.manage.model.domain.*;
import com.lz.manage.model.dto.goodsPurchaseInfo.GoodsPurchaseInfoQuery;
import com.lz.manage.model.enums.CommonStatusEnum;
import com.lz.manage.model.vo.goodsPurchaseInfo.GoodsPurchaseInfoVo;
import com.lz.manage.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品进货信息Service业务层处理
 *
 * @author YY
 * @date 2025-06-10
 */
@Service
public class GoodsPurchaseInfoServiceImpl extends ServiceImpl<GoodsPurchaseInfoMapper, GoodsPurchaseInfo> implements IGoodsPurchaseInfoService {
    @Resource
    private GoodsPurchaseInfoMapper goodsPurchaseInfoMapper;

    @Resource
    private IGoodsInfoService goodsInfoService;

    @Resource
    private IStoreInfoService storeInfoService;

    @Resource
    private ISupplierInfoService supplierInfoService;

    @Resource
    private IStoreStockInfoService storeStockInfoService;

    @Resource
    private TransactionTemplate transactionTemplate;

    //region mybatis代码

    /**
     * 查询商品进货信息
     *
     * @param purchaseId 商品进货信息主键
     * @return 商品进货信息
     */
    @Override
    public GoodsPurchaseInfo selectGoodsPurchaseInfoByPurchaseId(Long purchaseId) {
        return goodsPurchaseInfoMapper.selectGoodsPurchaseInfoByPurchaseId(purchaseId);
    }

    /**
     * 查询商品进货信息列表
     *
     * @param goodsPurchaseInfo 商品进货信息
     * @return 商品进货信息
     */
    @Override
    public List<GoodsPurchaseInfo> selectGoodsPurchaseInfoList(GoodsPurchaseInfo goodsPurchaseInfo) {
        List<GoodsPurchaseInfo> goodsPurchaseInfos = goodsPurchaseInfoMapper.selectGoodsPurchaseInfoList(goodsPurchaseInfo);
        for (GoodsPurchaseInfo info : goodsPurchaseInfos) {
            StoreInfo storeInfo = storeInfoService.selectStoreInfoByStoreId(info.getStoreId());
            if (StringUtils.isNotNull(storeInfo)) {
                info.setStoreName(storeInfo.getStoreName());
            }
            SupplierInfo supplierInfo = supplierInfoService.selectSupplierInfoBySupplierId(info.getSupplierId());
            if (StringUtils.isNotNull(supplierInfo)) {
                info.setSupplierName(supplierInfo.getSupplierName());
            }
            GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(info.getGoodsId());
            if (StringUtils.isNotNull(goodsInfo)) {
                info.setGoodsName(goodsInfo.getGoodsName());
            }
        }
        return goodsPurchaseInfos;
    }

    /**
     * 新增商品进货信息
     *
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    @Override
    public int insertGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo) {
        //查询仓库
        StoreInfo storeInfo = storeInfoService.selectStoreInfoByStoreId(goodsPurchaseInfo.getStoreId());
        if (StringUtils.isNull(storeInfo) || !storeInfo.getStatus().equals(CommonStatusEnum.COMMON_STATUS_0.getValue())) {
            throw new ServiceException("仓库不存在！！！");
        }
        //查询商品
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsPurchaseInfo.getGoodsId());
        if (StringUtils.isNull(goodsInfo) || !goodsInfo.getStatus().equals(CommonStatusEnum.COMMON_STATUS_0.getValue())) {
            throw new ServiceException("商品不存在！！！");
        }
        //查询供应商
        SupplierInfo supplierInfo = supplierInfoService.selectSupplierInfoBySupplierId(goodsPurchaseInfo.getSupplierId());
        if (StringUtils.isNull(supplierInfo)) {
            throw new ServiceException("供应商不存在！！！");
        }
        //根据仓库还有商品查询是否有此库存
        StoreStockInfo stockInfo = storeStockInfoService.getOne(new LambdaQueryWrapper<StoreStockInfo>()
                .eq(StoreStockInfo::getStoreId, goodsPurchaseInfo.getStoreId())
                .eq(StoreStockInfo::getGoodsId, goodsPurchaseInfo.getGoodsId()));
        String username = SecurityUtils.getUsername();
        if (StringUtils.isNull(stockInfo)) {
            stockInfo = new StoreStockInfo();
            stockInfo.setStoreId(goodsPurchaseInfo.getStoreId());
            stockInfo.setGoodsId(goodsPurchaseInfo.getGoodsId());
            stockInfo.setStockName(goodsInfo.getGoodsName());
            stockInfo.setStock(0L);
            stockInfo.setCreateBy(username);
        }
        stockInfo.setStock(stockInfo.getStock() + goodsPurchaseInfo.getQuantity());
        stockInfo.setUpdateBy(username);
        Date nowDate = DateUtils.getNowDate();
        stockInfo.setUpdateTime(nowDate);
        goodsPurchaseInfo.setCreateBy(username);
        goodsPurchaseInfo.setCreateTime(nowDate);

        StoreStockInfo finalStockInfo = stockInfo;
        Integer execute = transactionTemplate.execute(res -> {
            storeStockInfoService.saveOrUpdate(finalStockInfo);
            return goodsPurchaseInfoMapper.insertGoodsPurchaseInfo(goodsPurchaseInfo);
        });
        return StringUtils.isNotNull(execute) ? execute : 0;
    }

    /**
     * 修改商品进货信息
     *
     * @param goodsPurchaseInfo 商品进货信息
     * @return 结果
     */
    @Override
    public int updateGoodsPurchaseInfo(GoodsPurchaseInfo goodsPurchaseInfo) {
        goodsPurchaseInfo.setUpdateBy(SecurityUtils.getUsername());
        goodsPurchaseInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsPurchaseInfoMapper.updateGoodsPurchaseInfo(goodsPurchaseInfo);
    }

    /**
     * 批量删除商品进货信息
     *
     * @param purchaseIds 需要删除的商品进货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsPurchaseInfoByPurchaseIds(Long[] purchaseIds) {
        return goodsPurchaseInfoMapper.deleteGoodsPurchaseInfoByPurchaseIds(purchaseIds);
    }

    /**
     * 删除商品进货信息信息
     *
     * @param purchaseId 商品进货信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsPurchaseInfoByPurchaseId(Long purchaseId) {
        return goodsPurchaseInfoMapper.deleteGoodsPurchaseInfoByPurchaseId(purchaseId);
    }

    //endregion
    @Override
    public QueryWrapper<GoodsPurchaseInfo> getQueryWrapper(GoodsPurchaseInfoQuery goodsPurchaseInfoQuery) {
        QueryWrapper<GoodsPurchaseInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsPurchaseInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long purchaseId = goodsPurchaseInfoQuery.getPurchaseId();
        queryWrapper.eq(StringUtils.isNotNull(purchaseId), "purchase_id", purchaseId);

        Long storeId = goodsPurchaseInfoQuery.getStoreId();
        queryWrapper.eq(StringUtils.isNotNull(storeId), "store_id", storeId);

        Long goodsId = goodsPurchaseInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        Long supplierId = goodsPurchaseInfoQuery.getSupplierId();
        queryWrapper.eq(StringUtils.isNotNull(supplierId), "supplier_id", supplierId);

        String purchaseTitle = goodsPurchaseInfoQuery.getPurchaseTitle();
        queryWrapper.like(StringUtils.isNotEmpty(purchaseTitle), "purchase_title", purchaseTitle);

        Date purchaseDate = goodsPurchaseInfoQuery.getPurchaseDate();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginPurchaseDate")) && StringUtils.isNotNull(params.get("endPurchaseDate")), "purchase_date", params.get("beginPurchaseDate"), params.get("endPurchaseDate"));

        String createBy = goodsPurchaseInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = goodsPurchaseInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        String updateBy = goodsPurchaseInfoQuery.getUpdateBy();
        queryWrapper.like(StringUtils.isNotEmpty(updateBy), "update_by", updateBy);

        Date updateTime = goodsPurchaseInfoQuery.getUpdateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginUpdateTime")) && StringUtils.isNotNull(params.get("endUpdateTime")), "update_time", params.get("beginUpdateTime"), params.get("endUpdateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsPurchaseInfoVo> convertVoList(List<GoodsPurchaseInfo> goodsPurchaseInfoList) {
        if (StringUtils.isEmpty(goodsPurchaseInfoList)) {
            return Collections.emptyList();
        }
        return goodsPurchaseInfoList.stream().map(GoodsPurchaseInfoVo::objToVo).collect(Collectors.toList());
    }

}
