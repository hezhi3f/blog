import React from 'react'

import { Form, Input, Button ,Row,Col} from 'antd'

const SignupForm = () => {


  const onFinish = () => {

  }


  return (

    <Form
      name='signup'
      onFinish={onFinish}
      autoComplete='off'
    >
      <Form.Item name='username'>
        <Input placeholder='邮箱' />
      </Form.Item>

      <Form.Item name='password'>
        <Input.Password placeholder='密码' />
      </Form.Item>
      <Form.Item name='checkPassword'>
        <Input.Password placeholder='确认密码' />
      </Form.Item>
      <Form.Item name='checkCode'>
          <Row>
            <Col span={16}>
              <Input placeholder='验证码' />
            </Col>
            <Col span={8}>
              <Button type='link'>发送验证码</Button>
            </Col>
          </Row>
      </Form.Item>
      <Form.Item >
        <Button type='primary' htmlType='submit' block>立即注册</Button>
      </Form.Item>
    </Form>
  )
}

export default SignupForm