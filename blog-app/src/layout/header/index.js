import React from 'react'
import { Button, Layout } from 'antd';
import './index.css'

const { Header } = Layout
const HeaderLayout = () => {
  const logout = () => {
    window.sessionStorage.removeItem("login")
    window.sessionStorage.removeItem("token")
    window.location.reload()
  }
  return (
    <Header className='header'>
      <Button type='primary' onClick={logout}>退出登录</Button>
    </Header>
  )

}

export default HeaderLayout