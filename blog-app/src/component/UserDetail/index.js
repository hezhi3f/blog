import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { message, Descriptions, Button } from 'antd'
import axios from 'axios'
import api from '../../util/Api'

const { Item } = Descriptions
const UserDetail = () => {

  const navigate = useNavigate()

  const [user, setUser] = useState({
    email: '邮箱',
    gender: '性别',
    nickname: '昵称',
    password: '密码',
    gmtCreated: '创建时间',
    gmtModified: '上次修改时间'
  })

  useEffect(() => {
    api('/user/info', {}, setUser)
  }, [])

  const handleUpdate = () => {
    navigate('/update', { state: user })
  }

  return (
    <Descriptions
      title='个人信息'
      layout='vertical'
      bordered
      size='small'
      extra={<Button onClick={handleUpdate}>修改信息</Button>}>
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