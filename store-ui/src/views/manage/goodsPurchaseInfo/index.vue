<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="进货编号" prop="purchaseId">
        <el-input
          v-model="queryParams.purchaseId"
          placeholder="请输入进货编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库编号" prop="storeId">
        <el-select
          v-model="queryParams.storeId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入仓库"
          :remote-method="selectStoreInfo"
          :loading="storeInfoLoading"
        >
          <el-option
            v-for="item in storeInfoList"
            :key="item.storeId"
            :label="item.storeName"
            :value="item.storeId"
          >
          </el-option>
        </el-select>
      </el-form-item>
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
      <el-form-item label="进货标题" prop="purchaseTitle">
        <el-input
          v-model="queryParams.purchaseTitle"
          placeholder="请输入进货标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="进货时间">
        <el-date-picker
          v-model="daterangePurchaseDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['manage:goodsPurchaseInfo:add']"-->
      <!--        >新增-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:goodsPurchaseInfo:edit']"
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
          v-hasPermi="['manage:goodsPurchaseInfo:remove']"
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
          v-hasPermi="['manage:goodsPurchaseInfo:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsPurchaseInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="进货编号" align="center" v-if="columns[0].visible" prop="purchaseId"/>
      <el-table-column label="仓库编号" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="storeName"
      />
      <el-table-column label="商品编号" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="goodsName"
      />
      <el-table-column label="供应商" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="supplierName"
      />
      <el-table-column label="进货标题" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="purchaseTitle"
      />
      <el-table-column label="进货价格" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="purchasePrice"
      />
      <el-table-column label="进货数量" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="quantity"
      />
      <el-table-column label="进货时间" align="center" v-if="columns[7].visible" prop="purchaseDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.purchaseDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="createBy"
      />
      <el-table-column label="创建时间" align="center" v-if="columns[9].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="updateBy"
      />
      <el-table-column label="更新时间" align="center" v-if="columns[11].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="remark"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:goodsPurchaseInfo:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:goodsPurchaseInfo:remove']"
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

    <!-- 添加或修改商品进货信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="仓库编号" prop="storeId">-->
        <!--          <el-input v-model="form.storeId" placeholder="请输入仓库编号"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="商品编号" prop="goodsId">-->
        <!--          <el-input v-model="form.goodsId" placeholder="请输入商品编号"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="供应商" prop="supplierId">-->
        <!--          <el-input v-model="form.supplierId" placeholder="请输入供应商"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="进货标题" prop="purchaseTitle">-->
        <!--          <el-input v-model="form.purchaseTitle" placeholder="请输入进货标题"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="进货价格" prop="purchasePrice">-->
        <!--          <el-input v-model="form.purchasePrice" placeholder="请输入进货价格"/>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="进货数量" prop="quantity">-->
        <!--          <el-input v-model="form.quantity" placeholder="请输入进货数量"/>-->
        <!--        </el-form-item>-->
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
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listGoodsPurchaseInfo,
  getGoodsPurchaseInfo,
  delGoodsPurchaseInfo,
  addGoodsPurchaseInfo,
  updateGoodsPurchaseInfo
} from '@/api/manage/goodsPurchaseInfo'
import { listSupplierInfo } from '@/api/manage/supplierInfo'
import { listGoodsInfo } from '@/api/manage/goodsInfo'
import { listStoreInfo } from '@/api/manage/storeInfo'

export default {
  name: 'GoodsPurchaseInfo',
  data() {
    return {
      //仓库信息
      storeInfoList: [],
      storeInfoLoading: false,
      storeInfoQueryParams: {
        pageNum: 1,
        pageSize: 10,
        storeName: ''
      },
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
      //表格展示列
      columns: [
        { key: 0, label: '进货编号', visible: false },
        { key: 1, label: '仓库编号', visible: true },
        { key: 2, label: '商品编号', visible: true },
        { key: 3, label: '供应商', visible: true },
        { key: 4, label: '进货标题', visible: true },
        { key: 5, label: '进货价格', visible: true },
        { key: 6, label: '进货数量', visible: true },
        { key: 7, label: '进货时间', visible: true },
        { key: 8, label: '创建人', visible: true },
        { key: 9, label: '创建时间', visible: true },
        { key: 10, label: '更新人', visible: false },
        { key: 11, label: '更新时间', visible: false },
        { key: 12, label: '备注', visible: false }
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
      // 商品进货信息表格数据
      goodsPurchaseInfoList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangePurchaseDate: [],
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        purchaseId: null,
        storeId: null,
        goodsId: null,
        supplierId: null,
        purchaseTitle: null,
        purchaseDate: null,
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
    this.getList()
    this.getGoodsInfoList()
    this.getSupplierInfoList()
    this.getStoreInfoList()
  },
  methods: {
    /**
     * 获取商品列表推荐
     * @param query
     */
    selectStoreInfo(query) {
      if (query !== '') {
        this.storeInfoLoading = true
        this.storeInfoQueryParams.storeName = query
        setTimeout(() => {
          this.getStoreInfoList()
        }, 200)
      } else {
        this.storeInfoList = []
      }
    },
    /**
     * 获取商品信息列表
     */
    getStoreInfoList() {
      //添加查询参数
      if (this.form.storeId != null) {
        this.storeInfoQueryParams.storeId = this.form.storeId
      } else {
        this.storeInfoQueryParams.storeId = null
      }
      if (this.storeInfoQueryParams.storeName != null) {
        this.storeInfoQueryParams.storeId = null
      }
      listStoreInfo(this.storeInfoQueryParams).then(res => {
        this.storeInfoList = res.rows
        this.storeInfoLoading = false
      })
    },
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
    },
    /**
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
    /** 查询商品进货信息列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangePurchaseDate && '' != this.daterangePurchaseDate) {
        this.queryParams.params['beginPurchaseDate'] = this.daterangePurchaseDate[0]
        this.queryParams.params['endPurchaseDate'] = this.daterangePurchaseDate[1]
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params['beginCreateTime'] = this.daterangeCreateTime[0]
        this.queryParams.params['endCreateTime'] = this.daterangeCreateTime[1]
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params['beginUpdateTime'] = this.daterangeUpdateTime[0]
        this.queryParams.params['endUpdateTime'] = this.daterangeUpdateTime[1]
      }
      listGoodsPurchaseInfo(this.queryParams).then(response => {
        this.goodsPurchaseInfoList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        purchaseId: null,
        storeId: null,
        goodsId: null,
        supplierId: null,
        purchaseTitle: null,
        purchasePrice: null,
        quantity: null,
        purchaseDate: null,
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
      this.daterangePurchaseDate = []
      this.daterangeCreateTime = []
      this.daterangeUpdateTime = []
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.purchaseId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加商品进货信息'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const purchaseId = row.purchaseId || this.ids
      getGoodsPurchaseInfo(purchaseId).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改商品进货信息'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.purchaseId != null) {
            updateGoodsPurchaseInfo(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addGoodsPurchaseInfo(this.form).then(response => {
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
      const purchaseIds = row.purchaseId || this.ids
      this.$modal.confirm('是否确认删除商品进货信息编号为"' + purchaseIds + '"的数据项？').then(function() {
        return delGoodsPurchaseInfo(purchaseIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/goodsPurchaseInfo/export', {
        ...this.queryParams
      }, `goodsPurchaseInfo_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
