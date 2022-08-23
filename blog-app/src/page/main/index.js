import React from 'react'
import { Layout } from 'antd'
import HeaderLayout from '../../layout/header'
import ContentLayout from '../../layout/content'
import FooterLayout from '../../layout/footer'

const MainPage = () => {
  return (
    <Layout className="layout" style={{ backgroundColor: 'white' }}>
      <HeaderLayout />
      <ContentLayout >
        main
      </ContentLayout>
      <FooterLayout />
    </Layout>
  )
}

export default MainPage

