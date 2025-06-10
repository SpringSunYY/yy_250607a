<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="库存编号" prop="stockId">
        <el-input
          v-model="queryParams.stockId"
          placeholder="请输入库存编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="仓库编号" prop="storeId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.storeId"-->
      <!--          placeholder="请输入仓库编号"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="商品编号" prop="goodsId">
        <el-select
          v-model="queryParams.goodsId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入商品名称"
          :remote-method="selectGoodsInfo"
          :loading="goodsInfoLoading"
        >
          <el-option
            v-for="item in goodsInfoList"
            :key="item.goodsId"
            :label="item.goodsName"
            :value="item.goodsId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="供应商" prop="supplierId">
        <el-select
          v-model="queryParams.supplierId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入供应商名称"
          :remote-method="selectSupplierInfo"
          :loading="supplierInfoLoading"
        >
          <el-option
            v-for="item in supplierInfoList"
            :key="item.supplierId"
            :label="item.supplierName"
            :value="item.supplierId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="库存别名" prop="stockName">
        <el-input
          v-model="queryParams.stockName"
          placeholder="请输入库存别名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="更新人" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入更新人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="daterangeUpdateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddPurchase"
          v-hasPermi="['manage:goodsPurchaseInfo:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:storeStockInfo:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:storeStockInfo:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:storeStockInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="storeStockInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="库存编号" align="center" v-if="columns[0].visible" prop="stockId"/>
      <el-table-column label="仓库编号" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="storeName"
      />
      <el-table-column label="商品编号" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="goodsName"
      />
      <el-table-column label="库存别名" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="stockName"
      />
      <el-table-column label="库存" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="stock"
      />
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="createBy"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[6].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[8].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:storeStockInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:storeStockInfo:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改仓库库存对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="仓库编号" prop="storeId">-->
        <!--          <el-input v-model="form.storeId" placeholder="请输入仓库编号"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="商品编号" prop="goodsId">-->
        <!--          <el-input v-model="form.goodsId" placeholder="请输入商品编号"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="库存别名" prop="stockName">
          <el-input v-model="form.stockName" placeholder="请输入库存别名"/>
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input v-model="form.stock" placeholder="请输入库存"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改商品进货信息对话框 -->
    <el-dialog :title="title" :visible.sync="openPurchase" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="仓库编号" prop="storeId">-->
        <!--          <el-input v-model="form.storeId" placeholder="请输入仓库编号"/>-->
        <!--        </el-form-item>-->
        <el-form-item label="商品编号" prop="goodsId">
          <el-select
            v-model="form.goodsId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入商品名称"
            :remote-method="selectGoodsInfo"
            :loading="goodsInfoLoading"
          >
            <el-option
              v-for="item in goodsInfoList"
              :key="item.goodsId"
              :label="item.goodsName"
              :value="item.goodsId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="供应商" prop="supplierId">
          <el-select
            v-model="form.supplierId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入供应商名称"
            :remote-method="selectSupplierInfo"
            :loading="supplierInfoLoading"
          >
            <el-option
              v-for="item in supplierInfoList"
              :key="item.supplierId"
              :label="item.supplierName"
              :value="item.supplierId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="进货标题" prop="purchaseTitle">
          <el-input v-model="form.purchaseTitle" placeholder="请输入进货标题"/>
        </el-form-item>
        <el-form-item label="进货价格" prop="purchasePrice">
          <el-input-number :min="0" :precision="2" v-model="form.purchasePrice" placeholder="请输入进货价格"/>
        </el-form-item>
        <el-form-item label="进货数量" prop="quantity">
          <el-input-number :min="0" v-model="form.quantity" placeholder="请输入进货数量"/>
        </el-form-item>
        <el-form-item label="进货时间" prop="purchaseDate">
          <el-date-picker clearable
                          v-model="form.purchaseDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择进货时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormPurchase">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listStoreStockInfo,
  getStoreStockInfo,
  delStoreStockInfo,
  addStoreStockInfo,
  updateStoreStockInfo
} from '@/api/manage/storeStockInfo'
import { listGoodsInfo } from '@/api/manage/goodsInfo'
import { listSupplierInfo } from '@/api/manage/supplierInfo'
import { addGoodsPurchaseInfo } from '@/api/manage/goodsPurchaseInfo'

export default {
  name: 'StoreStockInfo',
  data() {
    return {
      //供应商信息
      supplierInfoList: [],
      supplierInfoLoading: false,
      supplierInfoQueryParams: {
        pageNum: 1,
        pageSize: 10,
        supplierName: '',
        status: '0'
      },
      //商品信息
      goodsInfoList: [],
      goodsInfoLoading: false,
      goodsInfoQueryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: '',
        status: '0'
      },
      //商品进货信息
      openPurchase: false,
      // 仓库编号
      storeId: 0,
      //表格展示列
      columns: [
        { key: 0, label: '库存编号', visible: false },
        { key: 1, label: '仓库编号', visible: true },
        { key: 2, label: '商品编号', visible: true },
        { key: 3, label: '库存别名', visible: true },
        { key: 4, label: '库存', visible: true },
        { key: 5, label: '创建人', visible: true },
        { key: 6, label: '创建时间', visible: true },
        { key: 7, label: '更新人', visible: false },
        { key: 8, label: '更新时间', visible: true },
        { key: 9, label: '备注', visible: false }
      ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 仓库库存表格数据
      storeStockInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stockId: null,
        storeId: null,
        goodsId: null,
        stockName: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeId: [
          { required: true, message: '仓库编号不能为空', trigger: 'blur' }
        ],
        goodsId: [
          { required: true, message: '商品编号不能为空', trigger: 'blur' }
        ],
        supplierId: [
          { required: true, message: '供应商不能为空', trigger: 'blur' }
        ],
        purchaseTitle: [
          { required: true, message: '进货标题不能为空', trigger: 'blur' }
        ],
        purchasePrice: [
          { required: true, message: '进货价格不能为空', trigger: 'blur' }
        ],
        quantity: [
          { required: true, message: '进货数量不能为空', trigger: 'blur' }
        ],
        purchaseDate: [
          { required: true, message: '进货时间不能为空', trigger: 'blur' }
        ],
        createBy: [
          { required: true, message: '创建人不能为空', trigger: 'blur' }
        ],
        createTime: [
          { required: true, message: '创建时间不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    const storeId = this.$route.params && this.$route.params.storeId
    this.storeId = storeId
    this.queryParams.storeId = storeId
    this.getList()
    this.getGoodsInfoList()
    this.getSupplierInfoList()
  },
  methods: {
    /**
     * 获取商品列表推荐
     * @param query
     */
    selectSupplierInfo(query) {
      if (query !== '') {
        this.supplierInfoLoading = true
        this.supplierInfoQueryParams.supplierName = query
        setTimeout(() => {
          this.getGoodsInfoList()
        }, 200)
      } else {
        this.supplierInfoList = []
      }
    },
    /**
     * 获取商品信息列表
     */
    getSupplierInfoList() {
      //添加查询参数
      if (this.form.supplierId != null) {
        this.supplierInfoQueryParams.supplierId = this.form.supplierId
      } else {
        this.supplierInfoQueryParams.supplierId = null
      }
      if (this.supplierInfoQueryParams.supplierName != null) {
        this.supplierInfoQueryParams.supplierId = null
      }
      listSupplierInfo(this.supplierInfoQueryParams).then(res => {
        this.supplierInfoList = res.rows
        this.supplierInfoLoading = false
      })
    }, /**
     * 获取商品列表推荐
     * @param query
     */
    selectGoodsInfo(query) {
      if (query !== '') {
        this.goodsInfoLoading = true
        this.goodsInfoQueryParams.goodsName = query
        setTimeout(() => {
          this.getGoodsInfoList()
        }, 200)
      } else {
        this.goodsInfoList = []
      }
    },
    /**
     * 获取商品信息列表
     */
    getGoodsInfoList() {
      //添加查询参数
      if (this.form.goodsId != null) {
        this.goodsInfoQueryParams.goodsId = this.form.goodsId
      } else {
        this.goodsInfoQueryParams.goodsId = null
      }
      if (this.goodsInfoQueryParams.goodsName != null) {
        this.goodsInfoQueryParams.goodsId = null
      }
      listGoodsInfo(this.goodsInfoQueryParams).then(res => {
        this.goodsInfoList = res.rows
        this.goodsInfoLoading = false
      })
    },
    //提交商品进货信息
    submitFormPurchase() {
      this.form.storeId=this.storeId
      addGoodsPurchaseInfo(this.form).then(response => {
        this.$modal.msgSuccess('新增成功')
        this.openPurchase = false
        this.getList()
      })
    },
    //打开商品进货信息
    handleAddPurchase() {
      this.reset()
      this.openPurchase = true
      this.title = '添加采购信息'
    },
    /** 查询仓库库存列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listStoreStockInfo(this.queryParams).then(response => {
        this.storeStockInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.openPurchase = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        stockId: null,
        storeId: null,
        goodsId: null,
        stockName: null,
        stock: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.stockId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加仓库库存'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const stockId = row.stockId || this.ids
      getStoreStockInfo(stockId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改仓库库存'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.stockId != null) {
            updateStoreStockInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addStoreStockInfo(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const stockIds = row.stockId || this.ids
      this.$modal.confirm('是否确认删除仓库库存编号为"' + stockIds + '"的数据项？').then(function() {
        return delStoreStockInfo(stockIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/storeStockInfo/export', {
        ...this.queryParams
      }, `storeStockInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
