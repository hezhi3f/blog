import { message } from 'antd'
import axios from 'axios'

const api = (url, data = {}, callback = data => { console.log(data) }) => {
  const token = window.sessionStorage.getItem('token')
  // axios({ method: 'post', url: 'http://43.143.70.32/api' + url, data, headers: { token } })
  axios({ method: 'post', url, data, headers: { token } })
    .then(res => {
      const { code, ok, msg, data } = res.data
      if (ok) {
        return callback(data)
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