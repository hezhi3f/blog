import React, { useEffect, useState } from 'react'
import { Button, Descriptions, PageHeader, Skeleton } from 'antd'
import api from '../../util/Api'
import { useNavigate } from 'react-router-dom'

const { Item } = Descriptions
const UserDetail = () => {
  const navigate = useNavigate()
  const [user, setUser] = useState({})
  const [ready, setReady] = useState(false)

  useEffect(() => {
    api('/user/info', {}, data => {
      setUser(data)
      setReady(true)
    })
  }, [])

  return (
    <PageHeader
      onBack={() => { navigate(-1) }}
      title="个人信息"
      extra={<Button type='primary' onClick={() => { navigate('/update') }}>修改信息</Button>}
    >
      {
        ready ?
          <Descriptions
            layout='vertical'
            bordered
            size='small'
          >
            <Item label='邮箱'>{user.email}</Item>
            <Item label='昵称'>{user.nickname}</Item>
            <Item label='性别'>{user.gender}</Item>
            <Item label='密码'>{user.password}</Item>
            <Item label='创建时间'>{user.gmtCreated}</Item>
            <Item label='上次修改时间'>{user.gmtModified}</Item>
          </Descriptions > : <Skeleton active={true} />
      }

    </PageHeader>

  )
}

export default UserDetail