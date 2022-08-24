import React from "react";
import { Tabs  } from 'antd';
import './index.css'
import LoginFrom from "../../component/LoginForm";
import SignupForm from "../../component/SignupForm";

const { TabPane } = Tabs;
const LoginPage = () => {


  return (
    <div className={"loginOut"}>
      <div className={"loginIn"}>
        <Tabs defaultActiveKey="checkCode" type="card"> 
          <TabPane tab="验证码登录" key={'checkCode'}>
            <LoginFrom/>
          </TabPane>
          <TabPane tab="密码登录" key={'password'}>
            <SignupForm/>
          </TabPane>
        </Tabs>
      </div>
    </div>
  )
}

export default LoginPage