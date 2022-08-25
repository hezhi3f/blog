import React, { useEffect } from "react" 
import Route from "./route" 
import { useNavigate, useLocation } from 'react-router-dom' 


const App = () => {
  const navigate = useNavigate()
  const location = useLocation()

  useEffect(() => {
    // todo 这里可以获得cookie判断是否登录成功
    if (['/login', '/signup'].indexOf(location.pathname) !== -1) {
      return
    }

    if (window.sessionStorage.getItem('login') !== 'true') {
      navigate('/login')
    }

    
  })
  return (
    <Route />
  )
}

export default App 
