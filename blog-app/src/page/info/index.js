import React from 'react'
import UserDetail from '../../component/UserDetail'
import Common from '../../layout/common'
import './index.css'

const InfoPage = () => {
  return (
    <Common>
      <div className='info'>
        <UserDetail />
      </div>
    </Common>
  )
}

export default InfoPage