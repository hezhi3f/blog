import React from 'react'
import { Button, Form, Input, message } from 'antd'
import CheckCodeItem from './CheckCodeItem'
import PasswordItem from './PasswordItem'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'

const LoginFrom = (props) => {
  const { flag } = props
  console.log(flag);
  const navigate = useNavigate()

  const onFinish = (values) => {
    console.log(values);
    axios({
      method: 'post',
      data: values,
      url: '/user/login'
    }).then(res => {
      const { code, ok, data, msg } = res.data
      if (ok) {
        window.sessionStorage.setItem('login', 'true')
        window.sessionStorage.setItem('token', data)
        navigate('/')
      } else {
        message.error(`${msg}[${code}]`)
      }
    })
  }

  return (
    <Form
      name={flag}
      onFinish={onFinish}
      initialValues={{'email': '123@qq.com' }}
      autoComplete='off'
    >
      <Form.Item name='email'>
        <Input placeholder='邮箱' />
      </Form.Item>
      {
        flag === 'checkCode' ? <CheckCodeItem/> :
          flag === 'password' ? <PasswordItem/> : <></>
      }
      <Form.Item>
        <Button type='primary' htmlType='submit' block>登录</Button>
      </Form.Item>
    </Form>
  )
}

export default LoginFrom