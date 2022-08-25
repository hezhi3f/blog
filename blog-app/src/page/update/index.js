import { Tabs } from 'antd'
import React from 'react'
import Common from '../../layout/common'
import UpdateForm from '../../component/UpdateForm'

const { TabPane } = Tabs;
const UpdatePage = () => {
  return (
    <Common>
      <Tabs>
        <TabPane tab="基本信息" key="basic">
          <UpdateForm type='basic' />
        </TabPane>
        <TabPane tab="修改密码" key="password">
          <UpdateForm type='password' />
        </TabPane>
      </Tabs>
    </Common>
  )
}

export default UpdatePage