import React from "react";
import { Tabs } from 'antd';
import LoginFrom from "../../component/LoginForm";
import Center from "../../layout/center";

const { TabPane } = Tabs;
const LoginPage = () => {


  return (
    <Center>
      <Tabs defaultActiveKey="checkCode" type="card">
        <TabPane tab="验证码登录" key={'checkCode'}>
          <LoginFrom flag={"checkCode"} />
        </TabPane>
        <TabPane tab="密码登录" key={'password'}>
          <LoginFrom flag={"password"} />
        </TabPane>
      </Tabs>
    </Center>
  )
}

export default LoginPage