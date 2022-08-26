import React from 'react'

import { Form, Input, Button } from 'antd'
import CheckCodeItem from '../LoginForm/CheckCodeItem'
import api from '../../util/Api';
import { useNavigate } from 'react-router-dom';

const SignupForm = () => {
  const navigate = useNavigate()

  const [form] = Form.useForm();

  const onFinish = (values) => {
    api('/user/signup', values, data => {
      window.sessionStorage.setItem('login', 'true')
      window.sessionStorage.setItem('token', data)
      navigate('/')
    })
  }


  return (

    <Form
      form={form}
      name='signup'
      onFinish={onFinish}
      autoComplete='off'
    >
      <Form.Item name='email'>
        <Input placeholder='邮箱' />
      </Form.Item>

      <Form.Item name='password'>
        <Input.Password placeholder='密码' />
      </Form.Item>
      <Form.Item name='checkPassword'>
        <Input.Password placeholder='确认密码' />
      </Form.Item>
      <CheckCodeItem />
      <Form.Item >
        <Button type='primary' htmlType='submit' block>立即注册</Button>
        已有账号？
        <Button
          type='link'
          size='small'
          onClick={() => { navigate('/login') }}
        >
          立即登录
        </Button>
      </Form.Item>
    </Form>
  )
}

export default SignupForm