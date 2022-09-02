import React from 'react'
import Common from '../../layout/common'
import ArticleUpdateForm from '../../component/ArticleUpdateForm'
import './index.css'
import { useLocation } from 'react-router-dom'

const ArticleUpdatePage = () => {
  const location = useLocation()
  console.log(location);
  const articleId = location.state
  return (
    <Common>
      <div className='article-update'>
        <ArticleUpdateForm articleId={articleId} />
      </div>
    </Common>
  )
}

export default ArticleUpdatePage