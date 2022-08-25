import React from 'react'
import './index.css'

function Center(props) {
  const { children } = props
  return (
    <div className={"loginOut"}>
      <div className={"loginIn"}>
        {children}
      </div>
    </div>
  )
}

export default Center