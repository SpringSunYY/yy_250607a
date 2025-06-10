import request from '@/utils/request'

// 查询供货商信息列表
export function listSupplierInfo(query) {
  return request({
    url: '/manage/supplierInfo/list',
    method: 'get',
    params: query
  })
}

// 查询供货商信息详细
export function getSupplierInfo(supplierId) {
  return request({
    url: '/manage/supplierInfo/' + supplierId,
    method: 'get'
  })
}

// 新增供货商信息
export function addSupplierInfo(data) {
  return request({
    url: '/manage/supplierInfo',
    method: 'post',
    data: data
  })
}

// 修改供货商信息
export function updateSupplierInfo(data) {
  return request({
    url: '/manage/supplierInfo',
    method: 'put',
    data: data
  })
}

// 删除供货商信息
export function delSupplierInfo(supplierId) {
  return request({
    url: '/manage/supplierInfo/' + supplierId,
    method: 'delete'
  })
}
