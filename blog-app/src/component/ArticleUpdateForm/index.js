import { PageHeader, Form, Button, Input, Divider, Radio, message } from 'antd'
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import ArticleTag from './ArticleTag'
import api from '../../util/Api'

const ArticleUpdateForm = (props) => {
  const { articleId } = props
  const navigate = useNavigate()
  const [article, setArticles] = useState(null)

  useEffect(() => {
    api(`/article/get?articleId=${articleId}`, {}, setArticles)
  }, [articleId])

  const onFinish = values => {
    console.log('values', values);
    api('/article/update', {...values,articleId:1}, message.success)
  }

  return (
    <PageHeader
      onBack={() => navigate(-1)}
      title='修改文章'
    >
      {
        article === null ||
        <Form
          layout='horizontal'
          initialValues={article}
          onFinish={onFinish}
          colon={true}
          labelCol={{ span: 2 }}
          wrapperCol={{ span: 22 }}
        >
          <Form.Item name={'articleId'} initialValue={1}>
            <Input hidden />
          </Form.Item>
          <Form.Item
            name={'title'}
          >
            <Input
              onPressEnter={e => e.preventDefault()}
              bordered={false}
              style={{ fontSize: 'large', fontWeight: 'bold' }}
            />
          </Form.Item>
          <Divider orientation="left" plain>正文</Divider>

          <Form.Item
            name={'content'}
          >
            <Input.TextArea
              bordered={false}
              autoSize={{ minRows: 20 }}
            />
          </Form.Item>
          <Divider
            orientation="left"
            plain
          >
            归档
          </Divider>
          <Form.Item
            label={'分类'}
            name={'kind'}
          >
            <Radio.Group size="small">
              <Radio.Button value={'kind1'}>kind1</Radio.Button>
              <Radio.Button value={'kind2'}>kind2</Radio.Button>
              <Radio.Button value={'kind3'}>kind3</Radio.Button>
            </Radio.Group>
          </Form.Item>
          <Form.Item
            label='标签'
            name='tags'
          >
            <ArticleTag />
          </Form.Item>
          <Form.Item
            wrapperCol={{ offset: 2, span: 4 }}
          >
            <Button
              type="primary"
              htmlType="submit"
            >
              立即发布
            </Button>
          </Form.Item>
        </Form>
      }
    </PageHeader>
  )
}

export default ArticleUpdateForm