import request from '@/utils/request'

// 查询仓库信息列表
export function listStoreInfo(query) {
  return request({
    url: '/manage/storeInfo/list',
    method: 'get',
    params: query
  })
}

// 查询仓库信息详细
export function getStoreInfo(storeId) {
  return request({
    url: '/manage/storeInfo/' + storeId,
    method: 'get'
  })
}

// 新增仓库信息
export function addStoreInfo(data) {
  return request({
    url: '/manage/storeInfo',
    method: 'post',
    data: data
  })
}

// 修改仓库信息
export function updateStoreInfo(data) {
  return request({
    url: '/manage/storeInfo',
    method: 'put',
    data: data
  })
}

// 删除仓库信息
export function delStoreInfo(storeId) {
  return request({
    url: '/manage/storeInfo/' + storeId,
    method: 'delete'
  })
}
