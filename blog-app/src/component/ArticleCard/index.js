import React, { useState } from 'react'
import { Button, Card, Tag, Col, Row, Space } from 'antd'
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

const data = {
  nickName: '我叫迢大傻',
  title: '文章标题1',
  kind: '自创',
  content: "文章内容".repeat(50) + 'end',
  tags: ['springboot', 'springcloud', 'nacos'],
  gmtCreated: '2022-03-13 19:32:44',
  gmtModified: '2022-05-23 23:32:42'
}
const ArticleCard = (props) => {
  const { article } = props
  const navigate = useNavigate()
  const [open, setOpen] = useState(false)
  const content = () => {
    const { content } = article
    if (content.length < 64) {
      return content
    }

    if (open) {
      return content
    }

    return content.slice(0, 64) + "…"
  }

  return (
    <div className='card'>
      <div className='head'>
        <span className='head-kind'>
          <Tag>
            kind
          </Tag>
        </span>
        <span className='head-title' onClick={() => { navigate('/detail', { state: article }) }}>
          {article.title}
        </span>
      </div>
      {
        !open ||
        <div className='article-author'>
          <span>{article.nickName}</span>
        </div>
      }
      <div
        onClick={() => setOpen(true)}
        className={`content-common ${open ? '' : 'content-close'}`}
      >
        {content()}
        {
          open ||
          <span className={'content-switch'}>
            阅读全文
            <DownOutlined />
          </span>
        }
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
      <div>
        <Row>
          <Col flex={6}>
            <Space>
              <Button type="primary"><LikeOutlined />赞同1.3k</Button>
              <Button type="primary" ><DislikeOutlined /></Button>
            </Space>
          </Col>
          <Col flex={4}>
            <Button type='text'>
              <CommentOutlined />
              19条评论
            </Button>
          </Col>
          <Col flex={3}>
            <Button
              type='text'
            >
              <ShareAltOutlined />
              分享
            </Button>
          </Col>
          <Col flex={3}>
            <Button
              type='text'
            >
              <HeartOutlined />
              喜欢
            </Button>
          </Col>
          <Col flex={2}>
            <Button
              type='text'
            >
              <EllipsisOutlined />
            </Button>
          </Col>
          <Col span={3}>
            {
              !open ||
              <Button
                type='text'
                onClick={() => {
                  setOpen(!open)
                }}
              >
                收起
                <UpOutlined />
              </Button>}
          </Col>
        </Row>
      </div>
    </div>
  )
}

export default ArticleCard