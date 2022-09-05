import React, { useEffect, useState } from 'react'
import { Skeleton } from 'antd'
import ArticleCard from '../../component/ArticleCard'
import Common from '../../layout/common'
import api from '../../util/Api'

const MainPage = () => {
  const [articles, setArticles] = useState([])
  const [ready, setReady] = useState(false)

  useEffect(() => {
    if (window.sessionStorage.getItem('login') === 'true') {
      api('/article/list', {}, data => {
        setArticles(data)
        setReady(true)
      })
    }
  }, [])

  console.log('ready', ready);
  console.log('article', articles)

  return (
    <Common>
      {ready
        ? articles.map((article, key) => <ArticleCard article={article} key={key} />)
        : <div style={{ 'padding': '16px 24px' }}><Skeleton active={true} /></div>}
    </Common>
  )
}

export default MainPage

