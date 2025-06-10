<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="出货编号" prop="shipmentId">
        <el-input
          v-model="queryParams.shipmentId"
          placeholder="请输入出货编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="仓库编号" prop="storeId">
        <el-input
          v-model="queryParams.storeId"
          placeholder="请输入仓库编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品编号" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出货标题" prop="shipmentTitle">
        <el-input
          v-model="queryParams.shipmentTitle"
          placeholder="请输入出货标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出货时间">
        <el-date-picker
          v-model="daterangeShipmentDate"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['manage:goodsShipmentInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['manage:goodsShipmentInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['manage:goodsShipmentInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['manage:goodsShipmentInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsShipmentInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="出货编号" align="center" v-if="columns[0].visible" prop="shipmentId" />
        <el-table-column label="仓库编号" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="storeId" />
        <el-table-column label="商品编号" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="goodsId" />
        <el-table-column label="出货标题" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="shipmentTitle" />
        <el-table-column label="出货数量" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="quantity" />
        <el-table-column label="出货价格" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="shipmentPrice" />
        <el-table-column label="出货时间" align="center" v-if="columns[6].visible" prop="shipmentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.shipmentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible" prop="createBy" />
        <el-table-column label="创建时间" align="center" v-if="columns[8].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="更新人" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible" prop="updateBy" />
        <el-table-column label="更新时间" align="center" v-if="columns[10].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['manage:goodsShipmentInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['manage:goodsShipmentInfo:remove']"
          >删除</el-button>
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

    <!-- 添加或修改商品出货信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="仓库编号" prop="storeId">
          <el-input v-model="form.storeId" placeholder="请输入仓库编号" />
        </el-form-item>
        <el-form-item label="商品编号" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品编号" />
        </el-form-item>
        <el-form-item label="出货标题" prop="shipmentTitle">
          <el-input v-model="form.shipmentTitle" placeholder="请输入出货标题" />
        </el-form-item>
        <el-form-item label="出货数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入出货数量" />
        </el-form-item>
        <el-form-item label="出货价格" prop="shipmentPrice">
          <el-input v-model="form.shipmentPrice" placeholder="请输入出货价格" />
        </el-form-item>
        <el-form-item label="出货时间" prop="shipmentDate">
          <el-date-picker clearable
            v-model="form.shipmentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择出货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listGoodsShipmentInfo, getGoodsShipmentInfo, delGoodsShipmentInfo, addGoodsShipmentInfo, updateGoodsShipmentInfo } from "@/api/manage/goodsShipmentInfo";

export default {
  name: "GoodsShipmentInfo",
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '出货编号', visible: true },
          { key: 1, label: '仓库编号', visible: true },
          { key: 2, label: '商品编号', visible: true },
          { key: 3, label: '出货标题', visible: true },
          { key: 4, label: '出货数量', visible: true },
          { key: 5, label: '出货价格', visible: true },
          { key: 6, label: '出货时间', visible: true },
          { key: 7, label: '创建人', visible: true },
          { key: 8, label: '创建时间', visible: true },
          { key: 9, label: '更新人', visible: true },
          { key: 10, label: '更新时间', visible: true },
          { key: 11, label: '备注', visible: true },
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
      // 商品出货信息表格数据
      goodsShipmentInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 备注时间范围
      daterangeShipmentDate: [],
      // 备注时间范围
      daterangeCreateTime: [],
      // 备注时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        shipmentId: null,
        storeId: null,
        goodsId: null,
        shipmentTitle: null,
        shipmentDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeId: [
          { required: true, message: "仓库编号不能为空", trigger: "blur" }
        ],
        goodsId: [
          { required: true, message: "商品编号不能为空", trigger: "blur" }
        ],
        shipmentTitle: [
          { required: true, message: "出货标题不能为空", trigger: "blur" }
        ],
        quantity: [
          { required: true, message: "出货数量不能为空", trigger: "blur" }
        ],
        shipmentPrice: [
          { required: true, message: "出货价格不能为空", trigger: "blur" }
        ],
        shipmentDate: [
          { required: true, message: "出货时间不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询商品出货信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeShipmentDate && '' != this.daterangeShipmentDate) {
        this.queryParams.params["beginShipmentDate"] = this.daterangeShipmentDate[0];
        this.queryParams.params["endShipmentDate"] = this.daterangeShipmentDate[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listGoodsShipmentInfo(this.queryParams).then(response => {
        this.goodsShipmentInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        shipmentId: null,
        storeId: null,
        goodsId: null,
        shipmentTitle: null,
        quantity: null,
        shipmentPrice: null,
        shipmentDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeShipmentDate = [];
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.shipmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品出货信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const shipmentId = row.shipmentId || this.ids
      getGoodsShipmentInfo(shipmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品出货信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.shipmentId != null) {
            updateGoodsShipmentInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoodsShipmentInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const shipmentIds = row.shipmentId || this.ids;
      this.$modal.confirm('是否确认删除商品出货信息编号为"' + shipmentIds + '"的数据项？').then(function() {
        return delGoodsShipmentInfo(shipmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('manage/goodsShipmentInfo/export', {
        ...this.queryParams
      }, `goodsShipmentInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
