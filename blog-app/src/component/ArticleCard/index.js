import React, { useState } from 'react'
import { Button, Tag, Col, Row, PageHeader, message } from 'antd'
import {
  CommentOutlined,
  ShareAltOutlined,
  DownOutlined,
  UpOutlined,
  LikeOutlined,
  HeartOutlined,
  EllipsisOutlined
} from '@ant-design/icons'
import './index.css'
import { useNavigate } from 'react-router-dom'
import api from '../../util/Api'
import ArticleComment from '../ArticleComment'


const ArticleCard = (props) => {
  const { article } = props
  const navigate = useNavigate()
  const [open, setOpen] = useState(false)
  const [like, setLike] = useState(article.like)
  const [comment, setComment] = useState(false)

  const handleLikeClick = () => {
    if (like) {
      api(`/article/like/del?articleId=${article.articleId}`, {}, () => {
        message.success("取消点赞成功")
      })
    } else {
      api(`/article/like/add?articleId=${article.articleId}`, {}, () => {
        message.success("点赞成功")
      })
    }

    setLike(!like)
  }

  const handleClick = () => {
    message.info('小河之已经在拼命开发中啦~')
  }

  const handleTitleClick = () => {
    navigate('/detail', { state: article.articleId })
  }

  const handleCommentClick = () => {
    setComment(!comment)
  }

  const title = (
    <span
      style={{ 'cursor': 'pointer' }}
      onClick={handleTitleClick}
    >
      {article.title}
    </span>
  )

  const tag = (<Tag>{article.kind}</Tag>)

  const actions = (
    <Row>
      <Col flex={3}>
        <Button
          type={like ? "primary" : "text"}
          onClick={handleLikeClick}>
          <LikeOutlined />
          点赞1.3k
        </Button>
      </Col>
      <Col flex={3}>
        <Button
          type='text'
          onClick={handleCommentClick}>
          <CommentOutlined />
          19条评论
        </Button>
      </Col>
      <Col flex={2}>
        <Button
          type='text'
          onClick={handleClick}>
          <ShareAltOutlined />
          分享
        </Button>
      </Col>
      <Col flex={2}>
        <Button
          type='text'
          onClick={handleClick}>
          <HeartOutlined />
          喜欢
        </Button>
      </Col>
      <Col flex={1}>
        <Button
          type='text'
          onClick={handleClick}
        >
          <EllipsisOutlined />
        </Button>
      </Col>
      <Col span={3}>
        <Button
          type='text'
          onClick={() => { setOpen(!open) }}
          hidden={!open}
        >
          收起
          <UpOutlined />
        </Button>
      </Col>
    </Row >
  )

  return (<>
    <PageHeader
      title={title}
      tags={tag}
      footer={actions}
    >
      <div className='article-author' hidden={!open}>
        <span>
          {article.nickName}
        </span>
      </div>
      <div
        onClick={() => setOpen(true)}
        className={`content-common ${open ? '' : 'content-close'}`}
      >
        {open ? article.content : article.content.slice(0, 64) + "…"}
        <span
          className={'content-switch'}
          hidden={open}>
          阅读全文
          <DownOutlined />
        </span>
      </div>

      <div className='content-time' hidden={!open}>
        {
          article.gmtModified === null
            ? `发布于 ${article.gmtCreated}`
            : `修改于 ${article.gmtModified}`
        }
      </div>
    </PageHeader>
    <div style={{ 'padding': '16px 24px' }}>
      <ArticleComment
        comment={comment}
        articleId={article.articleId}
        onClose={() => setComment(false)}
      />
    </div>
  </>
  )
}

export default ArticleCard