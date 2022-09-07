import React, { Children, useEffect, useState } from 'react'
import { Button, Comment, Avatar, Row, Col, Divider } from 'antd'
import { CaretRightOutlined, MinusOutlined, DownOutlined, UpOutlined } from '@ant-design/icons'
import api from '../../util/Api'

const Son = (props) => {
  const { commentNickname, toNickname, content, gmtCreated } = props.comment

  return (
    <Row>
      <Col flex={'30px'}> 头像</Col>
      <Col flex={'auto'}>
        <div>    <div>
          <span>{commentNickname}</span>
          {toNickname === null || <span><CaretRightOutlined />{toNickname}</span>}
        </div></div>
        <div>{content}</div>
        <div className='action-container'>
          <span className='action-left'>{gmtCreated}</span>
          <span className='action-right'>{'回复 点赞'}</span>
        </div>
      </Col>
    </Row>
  )
}

const ArticleComment = (props) => {
  const { articleId } = props
  const [comments, setComments] = useState(null)
  const [current, setCurrent] = useState(1)
  const [pages, setPages] = useState(0)
  const [total, setTotal] = useState(0)

  useEffect(() => {
    api('/article/comment/list', { articleId, current }, page => {
      console.log('@page', page);
      const { pages, total, comments } = page
      setComments(comments)
      setPages(pages)
      setTotal(total)
    })

  }, [articleId, current])

  return (
    <>
      {comments === null ? <></> : comments.map(comment => <Super key={comment.id} articleId={articleId} comment={comment} />)}
    </>
  )
}

const Super = (props) => {
  const { articleId } = props
  const {
    id,
    commentUserNickname: from,
    toUserNickname: to,
    content,
    childrenCount: count,
    gmtCreated
  } = props.comment

  const [sons, setSons] = useState([])
  const [current, setCurrent] = useState(1)
  const [total, setTotal] = useState(0)
  const [pages, setPages] = useState(0)

  const [open, setOpen] = useState(false)

  useEffect(() => {
    if (open) {
      api('/article/comment/list', { articleId, superCommentId: id, current }, page => {
        const { pages, total, comments } = page
        setTotal(total)
        setSons(comments)
        setPages(pages)
      })
    }
  }, [open, current, articleId, id])

  return (
    <Row>
      <Col flex={'30px'}> 头像</Col>
      <Col flex={'auto'}>
        <div>    <div>
          <span>{from}</span>
          {to === null || <span><CaretRightOutlined />{to}</span>}
        </div></div>
        <div>{content}</div>
        <div className='action-container'>
          <span className='action-left'>{gmtCreated}</span>
          <span className='action-right'>{'回复 点赞'}</span>
        </div>
        {!open || <>{sons.map(comment => <Son key={comment.id} comment={comment} />)}</>}
        {
          open ? (current >= pages ||
            <div>
              <span onClick={() => { setCurrent(current + 1) }}><MinusOutlined /> 继续展开 <DownOutlined /></span>
              <span onClick={() => setOpen(false)}>收起<UpOutlined /></span>
            </div>)
            :
            <div onClick={() => { setOpen(true) }}>
              <MinusOutlined /> 展开{total}条评论 <DownOutlined />
            </div>
        }
      </Col>
    </Row >
  )
}

export default ArticleComment