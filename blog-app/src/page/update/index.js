import {  PageHeader, Tabs } from 'antd'
import React from 'react'
import Common from '../../layout/common'
import UpdateForm from '../../component/UpdateForm'
import { useNavigate } from 'react-router-dom';
import './index.css'

const { TabPane } = Tabs;
const UpdatePage = () => {
  const navigate = useNavigate()

  return (
    <Common>
      <div className='update'>
        <PageHeader
          onBack={() => { navigate(-1) }}
          title={'修改信息'}
        >
          <Tabs>
            <TabPane tab="基本信息" key="basic">
              <UpdateForm type='basic' />
            </TabPane>
            <TabPane tab="修改密码" key="password">
              <UpdateForm type='password' />
            </TabPane>
          </Tabs>
        </PageHeader>
      </div>
    </Common >
  )
}

export default UpdatePage