import React, { useEffect } from "react"
import Route from "./route"
import { useNavigate, useLocation } from 'react-router-dom'
import { ConfigProvider } from "antd"
import zhCN from 'antd/es/locale/zh_CN';


const App = () => {
  const navigate = useNavigate()
  const location = useLocation()

  useEffect(() => {
    // 过滤掉登录和注册
    if (['/login', '/signup'].indexOf(location.pathname) !== -1) {
      return
    }

    if (window.sessionStorage.getItem('login') !== 'true') {
      navigate('/login')
    }
  })
  return (
    <ConfigProvider locale={zhCN}>
    <Route />
    </ConfigProvider>

  )
}

export default App 
