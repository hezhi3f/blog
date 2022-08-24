import React from 'react'
import { Layout } from 'antd'
import './index.css'

const { Content } = Layout
const ContentLayout = () => {

  return (
    <Content className="content">
      {
        new Array(50).fill("content").map(
          (i, text) => <div>{i}:{text}</div>
        )
      }
    </Content>
  )
}

export default ContentLayout