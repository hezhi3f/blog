import React from 'react'
import Common from '../../layout/common'
import ArticleCreateForm from '../../component/ArticleCreateForm'
import './index.css'

const ArticlePage = () => {
  return (
    <Common>
      <div className='article'>
        <ArticleCreateForm></ArticleCreateForm>
      </div>
    </Common>
  )
}

export default ArticlePage