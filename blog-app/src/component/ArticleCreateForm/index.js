import React from 'react'
import { Button, Form, Input, Radio, Tag, Divider } from 'antd'
import ArticleTag from './ArticleTag'


const ArticleCreateForm = () => {

  const onFinish = values => {
    console.log(values);
  }

  return (
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
          bordered={false}
        />
      </Form.Item>
      <Divider />
      <Form.Item
        name={'content'}
      >
        <Input.TextArea
          placeholder='正文，支持markdown'
          bordered={false}
          autoSize={{ minRows: 14, maxRows: 50 }}
        />
      </Form.Item>
      <Form.Item
        label={'分类'}
        name={'kind'}
      >
        <Radio.Group >
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
      <Form.Item wrapperCol={{ offset: 2, span: 22 }}>
        <Button type="primary" htmlType="submit">
          提交
        </Button>
      </Form.Item>
    </Form>
  )
}

export default ArticleCreateForm