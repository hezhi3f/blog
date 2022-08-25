import React from "react";
import { useRoutes } from "react-router-dom"
import LoginPage from "../page/login";
import MainPage from "../page/main";
import SignupPage from "../page/signup";

const Route = () => {
  return useRoutes([
    { path: '/', element: <MainPage /> },
    { path: '/login', element: <LoginPage /> },
    { path: '/signup', element: <SignupPage /> }
  ])
}

export default Route