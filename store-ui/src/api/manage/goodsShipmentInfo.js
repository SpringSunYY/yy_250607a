import request from '@/utils/request'

// 查询商品出货信息列表
export function listGoodsShipmentInfo(query) {
  return request({
    url: '/manage/goodsShipmentInfo/list',
    method: 'get',
    params: query
  })
}

// 查询商品出货信息详细
export function getGoodsShipmentInfo(shipmentId) {
  return request({
    url: '/manage/goodsShipmentInfo/' + shipmentId,
    method: 'get'
  })
}

// 新增商品出货信息
export function addGoodsShipmentInfo(data) {
  return request({
    url: '/manage/goodsShipmentInfo',
    method: 'post',
    data: data
  })
}

// 修改商品出货信息
export function updateGoodsShipmentInfo(data) {
  return request({
    url: '/manage/goodsShipmentInfo',
    method: 'put',
    data: data
  })
}

// 删除商品出货信息
export function delGoodsShipmentInfo(shipmentId) {
  return request({
    url: '/manage/goodsShipmentInfo/' + shipmentId,
    method: 'delete'
  })
}
