import React, { useState } from 'react'
import { Row, Col, Avatar } from 'antd'
import { UserOutlined, CaretRightOutlined, MessageOutlined } from '@ant-design/icons'
import './index.css'
import Reply from './Reply'


const Comment = (props) => {
  const [replay, setReplay] = useState(false)
  const {
    children,
    insert,
    comment: {
      id,
      articleId,
      commentUserId: superId,
      commentUserNickname: from,
      toUserNickname: to,
      content,
      gmtCreated: time
    }
  } = props

  return (
    <Row gutter={16}>
      <Col flex={'30px'}>
        <Avatar
          icon={<UserOutlined />}
          style={{ 'backgroundColor': '#288063' }}
          size={32}
        />
      </Col>
      <Col flex={'auto'}>
        <div>
          <span className='comment-from'>
            {from}
          </span>
          <span hidden={to === null}>
            <CaretRightOutlined />
          </span>
          <span
            className='comment-to'
            hidden={to === null}
          >
            {to}
          </span>
        </div>
        <div
          className='comment-content clickable'
          onClick={() => { setReplay(!replay) }}
        >
          {content}
        </div>
        <div hidden={!replay}>
          <Reply
            insert={insert}
            articleId={articleId}
            superId={id}
            toId={superId}
          />
        </div>
        <div className='action-container'>
          <span className='action-left comment-time'>
            {time}
          </span>
          <span
            className='action-right clickable'
            onClick={() => { setReplay(!replay) }}
          >
            <MessageOutlined />
            {replay ? '收起' : '回复'}
          </span>
        </div>
        {children}
      </Col>
    </Row >
  )
}

export default Comment