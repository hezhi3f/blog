import React from 'react'
import { UserOutlined } from '@ant-design/icons'
import { Dropdown, Avatar } from 'antd' 
import CenterMenu from './CenterMenu' 

const UserCenter = () => {
  return (
    <Dropdown overlay={<CenterMenu />} placement='bottom'>
      <Avatar icon={<UserOutlined />} style={{ backgroundColor: '#288063' }} size='large' />
    </Dropdown>
  )
}

export default UserCenter