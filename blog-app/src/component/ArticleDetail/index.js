import { PageHeader, Tag, Row, Col, Space, Button, Input } from 'antd'
import React from 'react'
import {
  CommentOutlined,
  DislikeOutlined,
  ShareAltOutlined,
  LikeOutlined,
  HeartOutlined,
  EllipsisOutlined,
} from '@ant-design/icons'
import './index.css'
import { useLocation, useNavigate } from 'react-router-dom'

const ArticleDetail = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const article = location.state

  return (
    <PageHeader
      onBack={() => { navigate(-1) }}
      title={article.title}
      subTitle={article.nickName}
      tags={<>
        分类：<Tag>  {article.kind}</Tag>
        标签：{article.tags.map(tag => <Tag>#{tag}</Tag>)}
      </>}
    >
      <Input.TextArea
        bordered={false}
        autoSize
        readOnly
        value={article.content}
      />

      <div className='aritcle-time'>
        {
          article.gmtModified === null
            ? `发布于 ${article.gmtCreated}`
            : `修改于 ${article.gmtModified}`}
      </div>
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
      </Row>

    </PageHeader>
  )
}

export default ArticleDetail