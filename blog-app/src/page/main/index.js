import React, { useEffect, useState } from 'react'
import { Skeleton, Pagination } from 'antd'
import ArticleCard from '../../component/ArticleCard'
import Common from '../../layout/common'
import api from '../../util/Api'

const MainPage = () => {
  const [records, setRecords] = useState(null)

  const [total, setTotal] = useState(0)
  const [page, setPage] = useState(1)
  const [size, setSize] = useState(5)

  const handleChange = (page, size) => {
    setPage(page)
    setSize(size)
    setTotal(0)
    setRecords(null)
  }

  useEffect(() => {
    if (window.sessionStorage.getItem('login') === 'true') {
      api('/article/main', { page, size }, data => {
        const { size, current, total, records } = data
        setRecords(records)
        setSize(size)
        setPage(current)
        setTotal(total)
      })
    }
  }, [page, size])

  return (
    <Common>
      <div style={{ 'padding': '16px 24px' }}>
        {records !== null
          ? <>
            {
              records.map(article => <ArticleCard article={article} key={article.articleId} />)
            }
            <Pagination
              current={page}
              total={total}
              pageSize={size}
              onChange={handleChange}
              showSizeChanger
              size='small'
              pageSizeOptions={['5', '10', '15']}
            />
          </>
          : <Skeleton active={true} />}
      </div>
    </Common>
  )
}

export default MainPage

