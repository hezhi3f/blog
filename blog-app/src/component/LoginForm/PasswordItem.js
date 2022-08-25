import React from 'react'
import { Form, Input } from 'antd'

const PasswordItem = () => {
  return (
    <Form.Item name='password'>
      <Input.Password placeholder='密码' />
    </Form.Item>
  )
}

export default PasswordItem