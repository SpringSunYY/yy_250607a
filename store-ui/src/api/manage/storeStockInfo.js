import request from '@/utils/request'

// 查询仓库库存列表
export function listStoreStockInfo(query) {
  return request({
    url: '/manage/storeStockInfo/list',
    method: 'get',
    params: query
  })
}

// 查询仓库库存详细
export function getStoreStockInfo(stockId) {
  return request({
    url: '/manage/storeStockInfo/' + stockId,
    method: 'get'
  })
}

// 新增仓库库存
export function addStoreStockInfo(data) {
  return request({
    url: '/manage/storeStockInfo',
    method: 'post',
    data: data
  })
}

// 修改仓库库存
export function updateStoreStockInfo(data) {
  return request({
    url: '/manage/storeStockInfo',
    method: 'put',
    data: data
  })
}

// 删除仓库库存
export function delStoreStockInfo(stockId) {
  return request({
    url: '/manage/storeStockInfo/' + stockId,
    method: 'delete'
  })
}
