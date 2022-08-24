import React from 'react'
import {Form,Row,Col,Input,Button} from 'antd'

const CheckCodeItem = () => {

  return (
    <Form.Item name="checkCode">
      <Row>
        <Col span={16}>
          <Input placeholder='验证码' />
        </Col>
        <Col span={8}>
          <Button type='link'>发送验证码</Button>
        </Col>
      </Row>
    </Form.Item>
  )
}

export default CheckCodeItem