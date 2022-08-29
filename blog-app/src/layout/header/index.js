import React from 'react'
import { Layout, Row, Col, Button } from 'antd'
import { EditOutlined } from '@ant-design/icons'
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
        <Col span={17}>
          <span />
        </Col>
        <Col span={2}>
          <Button
            type='primary'
            shape="round"
            onClick={() => { navigate('/create') }}
            icon={<EditOutlined />}
          >
            发布
          </Button>
        </Col>
        <Col span={2}>
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