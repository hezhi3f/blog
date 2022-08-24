import React from 'react'
import { Button, Form, Input } from 'antd';
import CheckCodeItem from './CheckCodeItem';
import PasswordItem from './PasswordItem';
import { useNavigate } from 'react-router-dom';

const LoginFrom = (props) => {
  const { flag } = props
  const navigate = useNavigate()

  const onFinish = (values) => {
    console.log('Success:', values)
    window.localStorage.setItem('login',true)
    navigate('/')
  }
  return (
    <Form
      name={flag}
      onFinish={onFinish}
      autoComplete="off"
    >
      <Form.Item name="email">
        <Input placeholder='邮箱' />
      </Form.Item>
      {
        flag === "checkCode" ? <CheckCodeItem /> :
          flag === "password" ? <PasswordItem /> : <></>
      }
      <Form.Item>
        <Button type="primary" htmlType="submit" block>登录</Button>
      </Form.Item>
    </Form>
  )
}

export default LoginFrom