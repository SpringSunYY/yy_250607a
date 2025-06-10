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
import com.lz.manage.model.domain.StoreStockInfo;
import com.lz.manage.model.vo.storeStockInfo.StoreStockInfoVo;
import com.lz.manage.model.dto.storeStockInfo.StoreStockInfoQuery;
import com.lz.manage.model.dto.storeStockInfo.StoreStockInfoInsert;
import com.lz.manage.model.dto.storeStockInfo.StoreStockInfoEdit;
import com.lz.manage.service.IStoreStockInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 仓库库存Controller
 *
 * @author YY
 * @date 2025-06-10
 */
@RestController
@RequestMapping("/manage/storeStockInfo")
public class StoreStockInfoController extends BaseController
{
    @Resource
    private IStoreStockInfoService storeStockInfoService;

    /**
     * 查询仓库库存列表
     */
    @PreAuthorize("@ss.hasPermi('manage:storeStockInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreStockInfoQuery storeStockInfoQuery)
    {
        StoreStockInfo storeStockInfo = StoreStockInfoQuery.queryToObj(storeStockInfoQuery);
        startPage();
        List<StoreStockInfo> list = storeStockInfoService.selectStoreStockInfoList(storeStockInfo);
        List<StoreStockInfoVo> listVo= list.stream().map(StoreStockInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出仓库库存列表
     */
    @PreAuthorize("@ss.hasPermi('manage:storeStockInfo:export')")
    @Log(title = "仓库库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreStockInfoQuery storeStockInfoQuery)
    {
        StoreStockInfo storeStockInfo = StoreStockInfoQuery.queryToObj(storeStockInfoQuery);
        List<StoreStockInfo> list = storeStockInfoService.selectStoreStockInfoList(storeStockInfo);
        ExcelUtil<StoreStockInfo> util = new ExcelUtil<StoreStockInfo>(StoreStockInfo.class);
        util.exportExcel(response, list, "仓库库存数据");
    }

    /**
     * 获取仓库库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:storeStockInfo:query')")
    @GetMapping(value = "/{stockId}")
    public AjaxResult getInfo(@PathVariable("stockId") Long stockId)
    {
        StoreStockInfo storeStockInfo = storeStockInfoService.selectStoreStockInfoByStockId(stockId);
        return success(StoreStockInfoVo.objToVo(storeStockInfo));
    }

    /**
     * 新增仓库库存
     */
    @PreAuthorize("@ss.hasPermi('manage:storeStockInfo:add')")
    @Log(title = "仓库库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreStockInfoInsert storeStockInfoInsert)
    {
        StoreStockInfo storeStockInfo = StoreStockInfoInsert.insertToObj(storeStockInfoInsert);
        return toAjax(storeStockInfoService.insertStoreStockInfo(storeStockInfo));
    }

    /**
     * 修改仓库库存
     */
    @PreAuthorize("@ss.hasPermi('manage:storeStockInfo:edit')")
    @Log(title = "仓库库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreStockInfoEdit storeStockInfoEdit)
    {
        StoreStockInfo storeStockInfo = StoreStockInfoEdit.editToObj(storeStockInfoEdit);
        return toAjax(storeStockInfoService.updateStoreStockInfo(storeStockInfo));
    }

    /**
     * 删除仓库库存
     */
    @PreAuthorize("@ss.hasPermi('manage:storeStockInfo:remove')")
    @Log(title = "仓库库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stockIds}")
    public AjaxResult remove(@PathVariable Long[] stockIds)
    {
        return toAjax(storeStockInfoService.deleteStoreStockInfoByStockIds(stockIds));
    }
}
