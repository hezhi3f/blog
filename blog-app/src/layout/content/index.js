import React from 'react'
import { Layout } from 'antd'
import './index.css'

const { Content } = Layout
const ContentLayout = (props) => {
  const { children } = props
  return (
    <Content className='content'>
      <div className='box'>
        {children}
      </div>
    </Content>
  )
}

export default ContentLayout