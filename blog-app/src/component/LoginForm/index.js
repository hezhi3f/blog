import React from 'react'
import { Button, Form, Input } from 'antd'
import CheckCodeItem from './CheckCodeItem'
import PasswordItem from './PasswordItem'
import { useNavigate } from 'react-router-dom'
import api from '../../util/Api'

const LoginFrom = (props) => {
  const { flag } = props
  const navigate = useNavigate()

  const [form] = Form.useForm();

  const onFinish = (values) => {
    api('/user/login', values, data => {
      window.sessionStorage.setItem('login', 'true')
      window.sessionStorage.setItem('token', data)
      navigate('/')
    })
  }

  return (
    <Form
      form={form}
      name={flag}
      onFinish={onFinish}
      initialValues={{ 'email': '123@qq.com' }}
      autoComplete='off'
    >
      <Form.Item name='email'>
        <Input placeholder='邮箱' />
      </Form.Item>
      {
        flag === 'checkCode' ? <CheckCodeItem /> :
          flag === 'password' ? <PasswordItem /> : <></>
      }

      <Form.Item>
        <Button type='primary' htmlType='submit' block>登录</Button>
        没有账号？
        <Button type='link'
          size='small'
          onClick={() => { navigate('/signup') }}
        >
          立即注册
        </Button>
      </Form.Item>
    </Form>
  )
}

export default LoginFrom