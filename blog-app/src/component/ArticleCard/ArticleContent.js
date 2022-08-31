import React from 'react'

const ArticleContent = (props) => {
  const { open, content } = props

  return (
    <div>
      {
        open ? content : content.slice(0, 60)
      }

    </div>
  )
}

export default ArticleContent