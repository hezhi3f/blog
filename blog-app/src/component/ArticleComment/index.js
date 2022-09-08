import React, { useEffect, useState } from 'react'
import SuperComment from './SuperComment'
import api from '../../util/Api'
import { CaretDownOutlined, CaretUpOutlined } from '@ant-design/icons'
import './index.css'
import Reply from './Reply'

const ArticleComment = (props) => {
  const { articleId, onClose, comment } = props
  const [current, setCurrent] = useState(0)
  const [comments, setComments] = useState([])
  const [total, setTotal] = useState(0)

  useEffect(() => {
    if (comment && comments.length === 0) {
      getMore()
    }
  })

  const getMore = () => {
    api('/article/comment/list', { articleId, page: current + 1 }, data => {
      const { total, comments, current } = data
      setComments(origin => [...origin, ...comments])
      setCurrent(current)
      setTotal(total)
    })
  }

  return (
    <div hidden={!comment}>
      <Reply
        insert={c => setComments([c, ...comments])}
        articleId={articleId}
      />
      {
        comments.map(comment =>
          <SuperComment
            articleId={articleId}
            key={comment.id}
            comment={comment}
          />
        )
      }
      <div className='action-container'>
        <span className='clickable'
          onClick={getMore} hidden={comments.length >= total}>
          加载更多
          <CaretDownOutlined />
        </span>
        <span className='clickable' onClick={onClose}>
          收起
          <CaretUpOutlined />
        </span>
      </div>
    </div>
  )
}

export default ArticleComment