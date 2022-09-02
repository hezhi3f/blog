import React, { useState } from 'react'
import { Button, Card, Tag, Col, Row, Space, PageHeader } from 'antd'
import {
  CommentOutlined,
  DislikeOutlined,
  ShareAltOutlined,
  DownOutlined,
  UpOutlined,
  LikeOutlined,
  HeartOutlined,
  EllipsisOutlined
} from '@ant-design/icons'
import './index.css'
import { useNavigate } from 'react-router-dom'


const ArticleCard = (props) => {
  const { article } = props
  const navigate = useNavigate()
  const [open, setOpen] = useState(false)
  const actions = (
    <Row>
      <Col flex={3}>
        <Button type="text">
          <LikeOutlined />
          赞同1.3k
        </Button>
      </Col>
      <Col flex={3}>
        <Button type='text'>
          <CommentOutlined />
          19条评论
        </Button>
      </Col>
      <Col flex={2}>
        <Button type='text'>
          <ShareAltOutlined />
          分享
        </Button>
      </Col>
      <Col flex={2}>
        <Button type='text'>
          <HeartOutlined />
          喜欢
        </Button>
      </Col>
      <Col flex={1}>
        <Button type='text'>
          <EllipsisOutlined />
        </Button>
      </Col>
      <Col span={3}>
        {
          !open ||
          <Button type='text'
            onClick={() => { setOpen(!open) }}>收起<UpOutlined />
          </Button>
        }
      </Col>
    </Row>
  )

  const handleTitleClick = () => {
    navigate('/detail', { state: article })
  }

  return (
    <PageHeader
      title={
        <span
        style={{'cursor':'pointer'}}
        onClick={handleTitleClick}
        >
          {article.title}
        </span>
      }
      tags={<Tag>{article.kind}</Tag>}
      footer={actions}
    >
      {
        !open ||
        <div className='article-author'>
          <span>
            {article.nickName}
          </span>
        </div>
      }
      <div
        onClick={() => setOpen(true)}
        className={`content-common ${open ? '' : 'content-close'}`}
      >
        {open ? article.content : article.content.slice(0, 64) + "…"}
        {open || <span className={'content-switch'}>阅读全文<DownOutlined /></span>}
      </div>
      {
        !open ||
        <div className='content-time'>
          {
            article.gmtModified === null
              ? '发布于 ' + article.gmtCreated
              : '修改于 ' + article.gmtModified
          }
        </div>
      }

    </PageHeader>
  )
}

export default ArticleCard