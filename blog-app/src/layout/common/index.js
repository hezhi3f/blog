import React from 'react'
import { Layout } from 'antd'
import HeaderLayout from '../header'
import ContentLayout from '../content'
import FooterLayout from '../footer'

const Common = (props) => {
  const { children } = props
  return (
    <Layout className='layout'>
      <HeaderLayout />
      <ContentLayout >
        {children}
      </ContentLayout>
      {/* <FooterLayout /> */}
    </Layout>
  )
}

export default Common