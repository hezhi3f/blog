import React from "react"
import { CaretDownOutlined, CaretUpOutlined } from '@ant-design/icons'
import { Space } from "antd"


const CommentOpen = (props) => {
  const { open, hasMore, getMore, onClose, total = 0, onOpen } = props
  const Open = () => {
    return (
      <Space style={{ 'cursor': 'pointer' }}>
        <span className='clickable' onClick={getMore} hidden={!hasMore}>
          继续展开
          <CaretDownOutlined />
        </span>
        <span className='clickable' onClick={onClose}>
          收起
          <CaretUpOutlined />
        </span>
      </Space>
    )
  }

  const Close = () => {
    return (
      <div onClick={onOpen} style={{ 'cursor': 'pointer' }}>
        展开{total}条评论
        <CaretDownOutlined />
      </div>
    )
  }

  return (
    <div>
      {open ? <Open /> : <Close />}
    </div>
  )
}

export default CommentOpen