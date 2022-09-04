import React from 'react'
import { Button, Form, Input, Radio, Divider, PageHeader } from 'antd'
import ArticleTag from './ArticleTag'
import { useNavigate } from 'react-router-dom'
import api from '../../util/Api'


const ArticleCreateForm = () => {
  const navigate = useNavigate()

  const onFinish = values => {
    api('/article/create', values, () => { navigate('/') })
  }

  return (
    <PageHeader
      title='发布文章'
      onBack={() => { navigate(-1) }}
    >
      <Form
        layout='horizontal'
        onFinish={onFinish}
        colon={true}
        labelCol={{ span: 2 }}
        wrapperCol={{ span: 22 }}
      >
        <Form.Item
          name={'title'}
        >
          <Input
            placeholder='标题 64个字以内'
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
            placeholder='正文，支持markdown'
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
    </PageHeader>

  )
}

export default ArticleCreateForm