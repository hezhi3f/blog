import React from 'react'
import { useRoutes } from 'react-router-dom'
import InfoPage from '../page/info'
import LoginPage from '../page/login'
import MainPage from '../page/main'
import SignupPage from '../page/signup'
import UpdatePage from '../page/update'

const Route = () => {
  return useRoutes([
    { path: '/', element: <MainPage /> },
    { path: '/login', element: <LoginPage /> },
    { path: '/signup', element: <SignupPage /> },
    { path: '/info', element: <InfoPage /> },
    { path: '/update', element: <UpdatePage /> }
  ])
}

export default Route