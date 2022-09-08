import React from 'react'
import { Layout } from 'antd'
import HeaderLayout from '../header'
import ContentLayout from '../content'


const Common = (props) => {
  const { children } = props
  console.log(props);
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