import React from 'react'
import { Button, Form, Input, Row, Col } from 'antd';

const LoginFrom = () => {


  const onFinish = (values) => {
    console.log('Success:', values);
  };

  const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
  };
  return (
    <Form
      name="basic"
      onFinish={onFinish}
      onFinishFailed={onFinishFailed}
      autoComplete="off"
    >
      <Form.Item name="email">
        <Input placeholder='邮箱' />
      </Form.Item>

      <Form.Item >
        <Row gutter={8}>
          <Col span={16}>
            <Form.Item name="checkCode" noStyle>
              <Input placeholder='验证码'/>
            </Form.Item>
          </Col>
          <Col span={8}>
            <Button>发送验证码</Button>
          </Col>
        </Row>
      </Form.Item>

      <Form.Item wrapperCol={{ offset: 8, span: 16 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  )
}

export default LoginFrom