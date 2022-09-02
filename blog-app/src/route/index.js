import React from 'react'
import { useRoutes } from 'react-router-dom'
import ArticlePage from '../page/article/ArticlePage'
import ArticleDetailPage from '../page/ArticleDetail'
import ArticleUpdatePage from '../page/ArticleUpdate'
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
    { path: '/update', element: <UpdatePage /> },
    { path: '/create', element: <ArticlePage /> },
    { path: '/detail', element: <ArticleDetailPage /> },
    { path: '/article/update', element: <ArticleUpdatePage /> }
  ])
}

export default Route