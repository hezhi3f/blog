import React, { useEffect, useState } from 'react'
import { Descriptions, Spin } from 'antd'
import api from '../../util/Api'
import Extra from './Extra'

const { Item } = Descriptions
const UserDetail = () => {
  const [user, setUser] = useState({})

  useEffect(() => {
    api('/user/info', {}, setUser)
  }, [])

  return (
    <Descriptions
      title='个人信息'
      layout='vertical'
      bordered
      size='small'
      extra={<Extra />}
    >
      <Item label='邮箱'>{user.email}</Item>
      <Item label='昵称'>{user.nickname}</Item>
      <Item label='性别'>{user.gender}</Item>
      <Item label='密码'>{user.password}</Item>
      <Item label='创建时间'>{user.gmtCreated}</Item>
      <Item label='上次修改时间'>{user.gmtModified}</Item>
    </Descriptions >
  )
}

export default UserDetail