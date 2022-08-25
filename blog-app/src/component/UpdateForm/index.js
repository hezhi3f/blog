import React, { useState } from 'react'
import BasicItem from './BasicItem'
import PasswordItem from './PasswordItem'
import { Form, Button } from 'antd'

const UpdateForm = (props) => {
  const { type } = props
  const onFinish = (value) => {
    console.log('value', value);
  }

  return (
    <Form
      name='update'
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 20 }}
      onFinish={onFinish}
      autoComplete="off"
    >
      {
        type === 'password' ? <PasswordItem /> :
          type === 'basic' ? <BasicItem /> : <></>
      }
      <Form.Item wrapperCol={{ offset: 4, span: 20 }}>
        <Button type='primary' htmlType='submit'>提交</Button>
      </Form.Item>
    </Form>
  )
}

export default UpdateForm