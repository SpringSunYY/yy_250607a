package com.lz.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lz.common.utils.DateUtils;
import com.lz.common.utils.SecurityUtils;
import com.lz.common.utils.StringUtils;
import com.lz.manage.mapper.GoodsInfoMapper;
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.model.dto.goodsInfo.GoodsInfoQuery;
import com.lz.manage.model.vo.goodsInfo.GoodsInfoVo;
import com.lz.manage.service.IGoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
/**
 * 商品信息Service业务层处理
 *
 * @author YY
 * @date 2025-06-10
 */
@Service
public class GoodsInfoServiceImpl extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements IGoodsInfoService {
    @Resource
    private GoodsInfoMapper goodsInfoMapper;

    //region mybatis代码

    /**
     * 查询商品信息
     *
     * @param goodsId 商品信息主键
     * @return 商品信息
     */
    @Override
    public GoodsInfo selectGoodsInfoByGoodsId(Long goodsId) {
        return goodsInfoMapper.selectGoodsInfoByGoodsId(goodsId);
    }

    /**
     * 查询商品信息列表
     *
     * @param goodsInfo 商品信息
     * @return 商品信息
     */
    @Override
    public List<GoodsInfo> selectGoodsInfoList(GoodsInfo goodsInfo) {
        return goodsInfoMapper.selectGoodsInfoList(goodsInfo);
    }

    /**
     * 新增商品信息
     *
     * @param goodsInfo 商品信息
     * @return 结果
     */
    @Override
    public int insertGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfo.setCreateBy(SecurityUtils.getUsername());
        goodsInfo.setCreateTime(DateUtils.getNowDate());
        return goodsInfoMapper.insertGoodsInfo(goodsInfo);
    }

    /**
     * 修改商品信息
     *
     * @param goodsInfo 商品信息
     * @return 结果
     */
    @Override
    public int updateGoodsInfo(GoodsInfo goodsInfo) {
        goodsInfo.setUpdateBy(SecurityUtils.getUsername());
        goodsInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsInfoMapper.updateGoodsInfo(goodsInfo);
    }

    /**
     * 批量删除商品信息
     *
     * @param goodsIds 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByGoodsIds(Long[] goodsIds) {
        return goodsInfoMapper.deleteGoodsInfoByGoodsIds(goodsIds);
    }

    /**
     * 删除商品信息信息
     *
     * @param goodsId 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsInfoByGoodsId(Long goodsId) {
        return goodsInfoMapper.deleteGoodsInfoByGoodsId(goodsId);
    }

    //endregion
    @Override
    public QueryWrapper<GoodsInfo> getQueryWrapper(GoodsInfoQuery goodsInfoQuery) {
        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        //如果不使用params可以删除
        Map<String, Object> params = goodsInfoQuery.getParams();
        if (StringUtils.isNull(params)) {
            params = new HashMap<>();
        }
        Long goodsId = goodsInfoQuery.getGoodsId();
        queryWrapper.eq(StringUtils.isNotNull(goodsId), "goods_id", goodsId);

        String goodsName = goodsInfoQuery.getGoodsName();
        queryWrapper.like(StringUtils.isNotEmpty(goodsName), "goods_name", goodsName);

        String status = goodsInfoQuery.getStatus();
        queryWrapper.eq(StringUtils.isNotEmpty(status), "status", status);

        String createBy = goodsInfoQuery.getCreateBy();
        queryWrapper.like(StringUtils.isNotEmpty(createBy), "create_by", createBy);

        Date createTime = goodsInfoQuery.getCreateTime();
        queryWrapper.between(StringUtils.isNotNull(params.get("beginCreateTime")) && StringUtils.isNotNull(params.get("endCreateTime")), "create_time", params.get("beginCreateTime"), params.get("endCreateTime"));

        return queryWrapper;
    }

    @Override
    public List<GoodsInfoVo> convertVoList(List<GoodsInfo> goodsInfoList) {
        if (StringUtils.isEmpty(goodsInfoList)) {
            return Collections.emptyList();
        }
        return goodsInfoList.stream().map(GoodsInfoVo::objToVo).collect(Collectors.toList());
    }

}
