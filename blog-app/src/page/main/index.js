import React from 'react'
import { Layout } from 'antd'
import HeaderLayout from '../../layout/header'
import ContentLayout from '../../layout/content'
import FooterLayout from '../../layout/footer'

import './index.css'

const MainPage = () => {
  console.log('session', window.sessionStorage);
  return (
    <Layout className='layout'>
      <HeaderLayout />
      <ContentLayout />
      <FooterLayout />
    </Layout>
  )
}

export default MainPage

