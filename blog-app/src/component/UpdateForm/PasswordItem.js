import React from 'react'
import { Form, Input } from 'antd'

const Password = () => {
  return (
    <>
      <Form.Item
        label="旧密码"
        name="oldPassword"
      >
        <Input.Password />
      </Form.Item>
      <Form.Item
        label="新密码"
        name="newPassword"
      >
        <Input.Password />
      </Form.Item>
      <Form.Item
        label="确认密码"
        name="checkPassord"
      >
        <Input.Password />
      </Form.Item>
    </>

  )
}

export default Password