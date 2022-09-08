import React, { useState } from 'react'
import CommentOpen from './CommentOpen'
import Comment from './Comment'
import api from '../../util/Api'

const SuperComment = (props) => {
  const { comment } = props
  const [count, setCount] = useState(comment.childCommentCount)
  const [sons, setSons] = useState([])
  const [open, setOpen] = useState(false)
  const [current, setCurrent] = useState(0)

  const onOpen = () => {
    if (sons.length === 0) {
      getMore()
    }
    setOpen(true)
  }

  const getMore = () => api(
    '/article/comment/list',
    { articleId: comment.articleId, superCommentId: comment.id, page: current + 1 },
    data => {
      const { comments, current } = data
      setSons(origin => [...origin, ...comments])
      setCurrent(current)
    }
  )

  const insert = son => {
    setCount(count + 1)
    setOpen(true)
    if (open) {
      setSons([son, ...sons])
    } else {
      getMore()
    }
  }

  return (
    <div>
      <Comment
        insert={insert}
        key={comment.id}
        comment={comment}>
        <div hidden={!open}>
          {sons.map(son =>
            <Comment
              insert={insert}
              key={son.id}
              comment={son}
            />
          )}
        </div>
        <div hidden={count === 0}>
          <CommentOpen
            total={count}
            open={open}
            hasMore={sons.length < count}
            onOpen={onOpen}
            getMore={getMore}
            onClose={() => { setOpen(false) }}
          />
        </div>
      </Comment>
    </div>
  )
}

export default SuperComment