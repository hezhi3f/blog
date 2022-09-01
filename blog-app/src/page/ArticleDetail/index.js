import React from 'react'
import Common from '../../layout/common'
import ArticleDetail from '../../component/ArticleDetail'
import './index.css'

const ArticleDetailPage = () => {
  return (
    <Common>
      <div className='detail'>
        <ArticleDetail />
      </div>
    </Common>
  )
}

export default ArticleDetailPage