import React, { useRef } from 'react'
import api from '../../util/Api'
import './index.css'
import { Button, Input, Row, Col, message } from 'antd'

const Reply = (props) => {
  const ref = useRef()
  const { articleId, superId, toId, insert } = props
  const onClick = () => {
    const content = ref.current.input.value
    api(
      '/article/comment/create',
      { articleId, superCommentId: superId, toUserId: toId, content },
      data => {
        message.success('评论成功')
        console.log('data', data);
        console.log('f insert', insert);
        insert(data)
        ref.current.input.value = null
      }
    )
  }

  return (
    <Row>
      <Col>
        <span>发表评论：</span>
      </Col>
      <Col flex={'auto'}>
        <Input
          ref={ref}
          placeholder={'善语结善缘，恶言伤人心~'}
          size='small'
          style={{ 'width': 'calc(100%-72px)' }}
        />
      </Col>
      <Col flex={'72px'}>
        <Button type='primary' size='small' onClick={onClick}>
          立即发表
        </Button>
      </Col>
    </Row>
  )
}

export default Reply