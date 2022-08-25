import React from 'react'
import { Menu } from 'antd'
import { useNavigate } from 'react-router-dom' 

const CenterMenu = () => {
  const navigate = useNavigate()
  const onClick = ({ key }) => {
    if (key === 'info') {
      navigate('/info')
    }
    if (key === 'logout') {
      window.sessionStorage.removeItem('login')
      window.sessionStorage.removeItem('token')
      window.location.reload()
    }
  } 

  return (
    <Menu
      onClick={onClick}
      items={[
        { label: '我的信息', key: 'info', },
        { label: '退出登录', key: 'logout', },
      ]}
    />
  )
}

export default CenterMenu
