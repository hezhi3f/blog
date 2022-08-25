import React from 'react'
import { Form, Input, Radio } from 'antd'

const BasicItem = (props) => {
  return (
    <>
      <Form.Item label="昵称" name="nickname">
        <Input />
      </Form.Item>
      <Form.Item label="性别" name="gender">
        <Radio.Group>
          <Radio value={'未知'}>未知</Radio>
          <Radio value={'男'}>男</Radio>
          <Radio value={'女'}>女</Radio>
        </Radio.Group>
      </Form.Item>
    </>
  )
}

export default BasicItem