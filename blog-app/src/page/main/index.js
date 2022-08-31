import React from 'react'
import ArticleCard from '../../component/ArticleCard'
import Common from '../../layout/common'

import './index.css'

const MainPage = () => {
  return (
    <Common>
      <div className='articles'>

        <ArticleCard key={1} />
        {/* <ArticleCard key={2} />
        <ArticleCard key={3} />
        <ArticleCard key={4} />
        <ArticleCard key={5} />
        <ArticleCard key={6} />
        <ArticleCard key={7} /> */}
      </div>

    </Common>
  )
}

export default MainPage

