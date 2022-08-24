import React from "react";

import { useRoutes } from "react-router-dom"
import LoginPage from "../page/login";
import MainPage from "../page/main";

const Route = () => {
  return useRoutes([
    { path: '/', element: <MainPage /> },
    { path: '/login', element: <LoginPage /> }
  ])
}

export default Route