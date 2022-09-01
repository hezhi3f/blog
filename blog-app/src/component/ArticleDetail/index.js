import { PageHeader, Tag, Row, Col, Space, Button } from 'antd'
import React from 'react'
import {
  CommentOutlined,
  DislikeOutlined,
  ShareAltOutlined,
  DownOutlined,
  UpOutlined,
  LikeOutlined,
  StarOutlined,
  HeartOutlined,
  EllipsisOutlined
} from '@ant-design/icons'
import './index.css'
import { useLocation, useNavigate } from 'react-router-dom'


const data = {
  nickName: '我叫迢大傻',
  title: '文章标题1',
  kind: '自创',
  content: "文章内容".repeat(500) + 'end',
  tags: ['springboot', 'springcloud', 'nacos'],
  gmtCreated: '2022-03-13 19:32:44',
  gmtModified: '2022-05-23 23:32:42'
}
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
      extra={<Button>修改</Button>}
    >
      {article.content}
      <div className='aritcle-time'>
        {
        article.gmtModified === null 
        ? `发布于 ${article.gmtCreated}`
        :`修改于 ${article.gmtModified}`}
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