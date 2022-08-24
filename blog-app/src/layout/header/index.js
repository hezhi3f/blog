import React from 'react'
import { Layout, Menu } from 'antd';
import './index.css'

const { Header } = Layout
const HeaderLayout = () => {
  return (
    <Header className='header'>
      <Menu
        mode="horizontal"
        defaultSelectedKeys={['2']}
        items={new Array(7).fill(null).map((_, index) => ({
          key: String(index + 1),
          label: `nav ${index + 1}`,
        }))}
      />
    </Header>
  )

}

export default HeaderLayout