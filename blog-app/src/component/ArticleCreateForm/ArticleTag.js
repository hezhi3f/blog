import React, { useState } from 'react';
import { Input, Tag } from 'antd';

const ArticleTag = ({ value = [], onChange }) => {
  const [tag, setTag] = useState('')
  const [tags, setTags] = useState(value)


  const add = () => {
    if (tags.indexOf(tag) === -1) {
      setTags(tags => {
        const newTags = [...tags, tag]
        onChange(newTags)
        return newTags
      })
    }
    setTag('')
  }

  const remove = (tag) => {
    setTags(tags => {
      const a = tags.filter(t => t !== tag)
      return a
    })

    
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