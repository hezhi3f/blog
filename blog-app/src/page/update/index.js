import { Tabs } from 'antd'
import React from 'react'
import Common from '../../layout/common'
import UpdateForm from '../../component/UpdateForm'
import { useLocation } from 'react-router-dom';

const { TabPane } = Tabs;
const UpdatePage = () => {
  const location = useLocation()
  const user = location.state
  return (
    <Common>
      <Tabs>
        <TabPane tab="基本信息" key="basic">
          <UpdateForm type='basic' user={user} />
        </TabPane>
        <TabPane tab="修改密码" key="password">
          <UpdateForm type='password' />
        </TabPane>
      </Tabs>
    </Common>
  )
}

export default UpdatePage