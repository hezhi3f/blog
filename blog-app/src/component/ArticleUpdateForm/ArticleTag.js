import React, { useState } from 'react';
import { Input, Tag } from 'antd';

const ArticleTag = ({ value = [], onChange }) => {
  const [tag, setTag] = useState('')
  const [tags, setTags] = useState(value)


  const add = e => {
    e.preventDefault()
    if (tags.indexOf(tag) === -1) {
      const newTags = [...tags, tag]
      onChange(newTags)
      setTags(newTags)
    }
    setTag('')
  }

  const remove = (tag) => {
    const newTags = tags.filter(t => t !== tag)
    onChange(newTags)
    setTags(newTags)
  }


  return (
    <div>
      {tags.map(tag =>
        <Tag
          key={tag}
          closable
          onClose={() => remove(tag)}
        >
          {tag}
        </Tag>)}
      <Input
        size='small'
        style={{ width: '72px', height: '22px' }}
        value={tag}
        onChange={e => setTag(e.target.value)}
        onPressEnter={add}
        placeholder={'添加标签'}
      />
    </div>
  )
};

export default ArticleTag;