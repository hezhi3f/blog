import React, { useState } from 'react'
import { Form, Row, Col, Input, Button } from 'antd'
import api from '../../util/Api'

const CheckCodeItem = () => {
  const [state, setState] = useState({ disabled: false, timeout: 0 })

  const form = Form.useFormInstance()

  const countDown = (timeout) => {
    if (timeout <= 0) {
      setState({ disabled: false, timeout: 30 })
      return
    }

    setTimeout(() => {
      setState(state => ({ disabled: true, timeout: state.timeout - 1 }))
      countDown(timeout - 1)
    }, 1000);
  }

  const handleClick = () => {
    const email = form.getFieldValue('email')
    api('/verify/email', { email })
    setState({ disabled: true, timeout: 30 })
    countDown(30)
  }

  return (
    <Form.Item name='checkCode'>
      <Row>
        <Col span={16}>
          <Input placeholder='验证码' />
        </Col>
        <Col span={8}>
          <Button
            type='link'
            disabled={state.disabled}
            onClick={handleClick}
          >
            {state.disabled ? `${state.timeout}s重新发送` : '发送验证码'}
          </Button>
        </Col>
      </Row>
    </Form.Item>
  )
}

export default CheckCodeItem