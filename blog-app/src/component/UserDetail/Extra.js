import React from 'react'
import { useNavigate } from 'react-router-dom'
import { Button } from 'antd'

const Extra = () => {
  const navigate = useNavigate()

  const update = () => {
    navigate('/update')
  }

  const back = () => {
    navigate(-1)
  }

  return (
    <>
      <Button onClick={update}>修改信息</Button>
      <Button onClick={back}>返回</Button>
    </>
  )
}

export default Extra