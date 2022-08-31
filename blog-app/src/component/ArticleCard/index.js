import React, { useState } from 'react'
import { Button, Card, Typography } from 'antd'
import { SettingOutlined, EditOutlined, EllipsisOutlined, DownOutlined } from '@ant-design/icons'
import ArticleContent from './ArticleContent'

const { Paragraph } = Typography
const { Meta } = Card
const data = {
  nickName: '我叫迢大傻',
  title: '文章标题1',
  kind: '自创',
  content: "文章内容".repeat(50) + 'end',
  tags: ['springboot', 'springcloud', 'nacos'],
  gmtCreated: '2022-03-13 19:32:44',
  gmtModified: '2022-05-23 23:32:42'
}
const ArticleCard = () => {
  const [open, setOpen] = useState(true)
  return (
    <Card
      title={<>{'12.3k'}<SettingOutlined key="setting" /></>}
      actions={[
        <SettingOutlined key="setting" />,
        <EditOutlined key="edit" />,
        <EllipsisOutlined key="ellipsis" />,
        <Button onClick={() => setOpen(open => !open)}>{open ? '收起' : '打开'}</Button>
      ]}
    >
      {!open || data.nickName}
      <div>
        {
          open ? data.content : <>{data.content.slice(0, 60)}…<span>阅读全文<DownOutlined /></span></>
        }
      </div>
      {
        data.gmtModified === null
          ? <Meta description={`发表于 ${data.gmtCreated}`} />
          : <Meta description={`修改于 ${data.gmtModified}`} />
      }
    </Card>
  )
}

export default ArticleCard