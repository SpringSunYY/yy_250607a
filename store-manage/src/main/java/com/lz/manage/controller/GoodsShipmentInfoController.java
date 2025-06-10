package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.enums.BusinessType;
import com.lz.manage.model.domain.GoodsShipmentInfo;
import com.lz.manage.model.vo.goodsShipmentInfo.GoodsShipmentInfoVo;
import com.lz.manage.model.dto.goodsShipmentInfo.GoodsShipmentInfoQuery;
import com.lz.manage.model.dto.goodsShipmentInfo.GoodsShipmentInfoInsert;
import com.lz.manage.model.dto.goodsShipmentInfo.GoodsShipmentInfoEdit;
import com.lz.manage.service.IGoodsShipmentInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 商品出货信息Controller
 *
 * @author YY
 * @date 2025-06-10
 */
@RestController
@RequestMapping("/manage/goodsShipmentInfo")
public class GoodsShipmentInfoController extends BaseController
{
    @Resource
    private IGoodsShipmentInfoService goodsShipmentInfoService;

    /**
     * 查询商品出货信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsShipmentInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsShipmentInfoQuery goodsShipmentInfoQuery)
    {
        GoodsShipmentInfo goodsShipmentInfo = GoodsShipmentInfoQuery.queryToObj(goodsShipmentInfoQuery);
        startPage();
        List<GoodsShipmentInfo> list = goodsShipmentInfoService.selectGoodsShipmentInfoList(goodsShipmentInfo);
        List<GoodsShipmentInfoVo> listVo= list.stream().map(GoodsShipmentInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出商品出货信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsShipmentInfo:export')")
    @Log(title = "商品出货信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsShipmentInfoQuery goodsShipmentInfoQuery)
    {
        GoodsShipmentInfo goodsShipmentInfo = GoodsShipmentInfoQuery.queryToObj(goodsShipmentInfoQuery);
        List<GoodsShipmentInfo> list = goodsShipmentInfoService.selectGoodsShipmentInfoList(goodsShipmentInfo);
        ExcelUtil<GoodsShipmentInfo> util = new ExcelUtil<GoodsShipmentInfo>(GoodsShipmentInfo.class);
        util.exportExcel(response, list, "商品出货信息数据");
    }

    /**
     * 获取商品出货信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsShipmentInfo:query')")
    @GetMapping(value = "/{shipmentId}")
    public AjaxResult getInfo(@PathVariable("shipmentId") Long shipmentId)
    {
        GoodsShipmentInfo goodsShipmentInfo = goodsShipmentInfoService.selectGoodsShipmentInfoByShipmentId(shipmentId);
        return success(GoodsShipmentInfoVo.objToVo(goodsShipmentInfo));
    }

    /**
     * 新增商品出货信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsShipmentInfo:add')")
    @Log(title = "商品出货信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsShipmentInfoInsert goodsShipmentInfoInsert)
    {
        GoodsShipmentInfo goodsShipmentInfo = GoodsShipmentInfoInsert.insertToObj(goodsShipmentInfoInsert);
        return toAjax(goodsShipmentInfoService.insertGoodsShipmentInfo(goodsShipmentInfo));
    }

    /**
     * 修改商品出货信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsShipmentInfo:edit')")
    @Log(title = "商品出货信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsShipmentInfoEdit goodsShipmentInfoEdit)
    {
        GoodsShipmentInfo goodsShipmentInfo = GoodsShipmentInfoEdit.editToObj(goodsShipmentInfoEdit);
        return toAjax(goodsShipmentInfoService.updateGoodsShipmentInfo(goodsShipmentInfo));
    }

    /**
     * 删除商品出货信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsShipmentInfo:remove')")
    @Log(title = "商品出货信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shipmentIds}")
    public AjaxResult remove(@PathVariable Long[] shipmentIds)
    {
        return toAjax(goodsShipmentInfoService.deleteGoodsShipmentInfoByShipmentIds(shipmentIds));
    }
}
