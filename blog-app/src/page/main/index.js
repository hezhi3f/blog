import React from 'react'
import { Tabs } from 'antd'
import Common from '../../layout/common'
import MainPane from './main'
import MinePane from './mine'

const { TabPane } = Tabs
const MainPage = () => {
  return (
    <Common>
      <div style={{ 'padding': '16px 24px' }}>
        <Tabs defaultActiveKey={'main'}>
          <TabPane tab="我的文章" key="mine">
            <MinePane />
          </TabPane>
          <TabPane tab="最新发布" key="main">
            <MainPane />
          </TabPane>
        </Tabs>
      </div>
    </Common>
  )
}

export default MainPage

