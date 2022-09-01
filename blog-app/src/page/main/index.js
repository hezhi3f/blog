import React, { useEffect, useState } from 'react'
import ArticleCard from '../../component/ArticleCard'
import Common from '../../layout/common'
import api from '../../util/Api'

import './index.css'

const MainPage = () => {
  const [articles, setArticles] = useState([])
  useEffect(() => {
    api('/article/list', {}, setArticles)
  }, [])


  return (
    <Common>
      {
        articles.map((article, key) => <ArticleCard article={article} key={key} />)
      }
    </Common>
  )
}

export default MainPage

