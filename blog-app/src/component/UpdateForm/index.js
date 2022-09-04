import React, { useEffect } from 'react'
import BasicItem from './BasicItem'
import PasswordItem from './PasswordItem'
import { Form, Button, message } from 'antd'
import api from '../../util/Api'
import { useNavigate } from 'react-router-dom'

const UpdateForm = (props) => {
  const navigate = useNavigate()
  const [form] = Form.useForm()
  const { type } = props
  const onFinish = (values) => {
    api('/user/update', values, data => {
      message.success(data)
      navigate(-1)
    })
  }

  useEffect(() => {
    api('/user/info', {}, form.setFieldsValue)
  }, [form.setFieldsValue])

  return (
    <Form
      name='update'
      form={form}
      labelCol={{ span: 4 }}
      wrapperCol={{ span: 20 }}
      onFinish={onFinish}
      autoComplete="off"
    >
      {type === 'password' && <PasswordItem />}
      {type === 'basic' && <BasicItem />}
      <Form.Item wrapperCol={{ offset: 4, span: 20 }}>
        <Button type='primary' htmlType='submit'>提交</Button>
      </Form.Item>
    </Form>
  )
}

export default UpdateForm