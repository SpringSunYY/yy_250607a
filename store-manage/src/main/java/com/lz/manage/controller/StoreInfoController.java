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
import com.lz.manage.model.domain.StoreInfo;
import com.lz.manage.model.vo.storeInfo.StoreInfoVo;
import com.lz.manage.model.dto.storeInfo.StoreInfoQuery;
import com.lz.manage.model.dto.storeInfo.StoreInfoInsert;
import com.lz.manage.model.dto.storeInfo.StoreInfoEdit;
import com.lz.manage.service.IStoreInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 仓库信息Controller
 *
 * @author YY
 * @date 2025-06-10
 */
@RestController
@RequestMapping("/manage/storeInfo")
public class StoreInfoController extends BaseController
{
    @Resource
    private IStoreInfoService storeInfoService;

    /**
     * 查询仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:storeInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreInfoQuery storeInfoQuery)
    {
        StoreInfo storeInfo = StoreInfoQuery.queryToObj(storeInfoQuery);
        startPage();
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        List<StoreInfoVo> listVo= list.stream().map(StoreInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:storeInfo:export')")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreInfoQuery storeInfoQuery)
    {
        StoreInfo storeInfo = StoreInfoQuery.queryToObj(storeInfoQuery);
        List<StoreInfo> list = storeInfoService.selectStoreInfoList(storeInfo);
        ExcelUtil<StoreInfo> util = new ExcelUtil<StoreInfo>(StoreInfo.class);
        util.exportExcel(response, list, "仓库信息数据");
    }

    /**
     * 获取仓库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:storeInfo:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        StoreInfo storeInfo = storeInfoService.selectStoreInfoByStoreId(storeId);
        return success(StoreInfoVo.objToVo(storeInfo));
    }

    /**
     * 新增仓库信息
     */
    @PreAuthorize("@ss.hasPermi('manage:storeInfo:add')")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreInfoInsert storeInfoInsert)
    {
        StoreInfo storeInfo = StoreInfoInsert.insertToObj(storeInfoInsert);
        return toAjax(storeInfoService.insertStoreInfo(storeInfo));
    }

    /**
     * 修改仓库信息
     */
    @PreAuthorize("@ss.hasPermi('manage:storeInfo:edit')")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreInfoEdit storeInfoEdit)
    {
        StoreInfo storeInfo = StoreInfoEdit.editToObj(storeInfoEdit);
        return toAjax(storeInfoService.updateStoreInfo(storeInfo));
    }

    /**
     * 删除仓库信息
     */
    @PreAuthorize("@ss.hasPermi('manage:storeInfo:remove')")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(storeInfoService.deleteStoreInfoByStoreIds(storeIds));
    }
}
