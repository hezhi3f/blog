import React, { useEffect } from "react";
import Route from "./route";
import { useNavigate, useLocation } from 'react-router-dom';


const App = () => {
  const navigate = useNavigate()
  const location = useLocation()
  console.log("location", location);
  useEffect(() => {
    // todo 这里可以获得cookie判断是否登录成功
  })
  return (
    <Route />
  )
}

export default App;
