import React from "react";
import { Tabs } from 'antd';
import './index.css'
import LoginFrom from "../../component/LoginForm";

const { TabPane } = Tabs;
const LoginPage = () => {


  return (
    <div className={"loginOut"}>
      <div className={"loginIn"}>
        <Tabs defaultActiveKey="checkCode" type="card">
          <TabPane tab="验证码登录" key={'checkCode'}>
            <LoginFrom flag={"checkCode"} />
          </TabPane>
          <TabPane tab="密码登录" key={'password'}>
            <LoginFrom flag={"password"} />
          </TabPane>
        </Tabs>
      </div>
    </div>
  )
}

export default LoginPage