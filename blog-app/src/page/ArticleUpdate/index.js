import React from 'react'
import Common from '../../layout/common'
import ArticleUpdateForm from '../../component/ArticleUpdateForm'
import { useLocation } from 'react-router-dom'

const ArticleUpdatePage = () => {
  const location = useLocation()
  const articleId = location.state
  return (
    <Common>
      <ArticleUpdateForm articleId={articleId} />
    </Common>
  )
}

export default ArticleUpdatePage