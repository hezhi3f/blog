import React from 'react'
import { Layout, Row, Col } from 'antd' 
import './index.css'
import UserCenter from '../../component/UserCenter' 
import { useNavigate } from 'react-router-dom' 

const { Header } = Layout


const HeaderLayout = () => {
  const navigate = useNavigate()

  return (
    <Header className='header'>
      <Row>
        <Col span={2}>
          <div className='logo' onClick={() => { navigate('/') }} />
        </Col>
        <Col span={21}>
          <span />
        </Col>
        <Col span={1}>
          <UserCenter />
        </Col>
      </Row>
    </Header>
  )
}

export default HeaderLayout