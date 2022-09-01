import { message } from 'antd'
import axios from 'axios'


const api = (url, data = {}, func = () => { }) => {
  console.log('url', url)
  const token = window.sessionStorage.getItem('token')
  console.log('token', token);
  axios({ method: 'post', url, data, headers: { token } })
    .then(res => {
      const { code, ok, msg, data } = res.data
      console.log(res);
      if (ok) {
        func(data)
      } else {
        message.error(`${msg}[${code}]`)
        if (code === 500) {
          window.sessionStorage.clear()
          window.location.reload()
        }
      }
    }).catch(error => {
      message.error(`请求出错[${error.code}]`)
    })
}

export default api