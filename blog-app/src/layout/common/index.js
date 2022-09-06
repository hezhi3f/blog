import React from 'react'
import { Layout } from 'antd'
import HeaderLayout from '../header'
import ContentLayout from '../content'
import { Spin } from 'antd'

const Common = (props) => {
  const { children, ready = true } = props
  console.log(props);
  const wait = <Spin size="large" />
  return (
    <Layout>
      <HeaderLayout />
      <ContentLayout >
        {children}
      </ContentLayout>
      {/* <FooterLayout /> */}
    </Layout>
  )
}

export default Common