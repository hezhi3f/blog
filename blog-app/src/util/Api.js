import { message } from 'antd'
import axios from 'axios'


const api = (url, data) => {
  let result
  const token = window.sessionStorage.getItem('token')
  axios({
    method: 'post',
    url,
    data,
    headers: { token }
  }).then(
    res => {
      if (res.status !== 200) {
        message.error('请求出错')
      } else {
        const { code, ok, msg, data } = res.data
        if (ok) {
          result = data
        } else {
          message.error(`${msg}[${code}]`)
          if (code === 500) {
            window.sessionStorage.clear()
            window.location.reload()
          }
        }
      }
    }
  )
  console.log('result',result);
  return result
}

export default api