import { message } from 'antd'
import axios from 'axios'


const api = (url, data = {}, func = () => { }) => {
  const token = window.sessionStorage.getItem('token')
  axios({ method: 'post', url: 'http://localhost:8080' + url, data, headers: { token } })
    .then(res => {
      const { code, ok, msg, data } = res.data
      if (ok) {
        return func(data)
      } else {
        console.log('res=',res);
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