import { PageHeader, Tag, Row, Col, Space, Button, Input } from 'antd'
import React, { useEffect, useState } from 'react'
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
import api from '../../util/Api'

const ArticleDetail = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const articleId = location.state
  const [article, setArticles] = useState(null)

  useEffect(() => {
    api(`/article/get?articleId=${articleId}`, {}, setArticles)
  }, [articleId])

  const handleUpdate = () => {
    navigate("/article/update", { state: article.articleId })
  }

  return (
    <>
      {
        article === null ||
        <PageHeader
          onBack={() => { navigate(-1) }}
          title={article.title}
          subTitle={<Tag>{article.kind}</Tag>}
          extra={!article.mine || <Button type='primary' onClick={handleUpdate}>修改</Button>}
        >
          <div style={{'padding': '4px 11px','fontWeight':'600'}}>
            {article.nickName} {article.tags.map(tag => `#${tag}`)}
          </div>
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
      }
    </>
  )
}

export default ArticleDetail