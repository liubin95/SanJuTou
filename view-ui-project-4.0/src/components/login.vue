<style scoped lang="less">
.login {
  width: 80%;
  text-align: center;
  margin: 0 auto;
  position: relative;
  .form {
    margin: 20px auto;
    width: 50%;
    height: 100%;
  }
  .logo {
    height: 20%;
    width: 20%;
  }
  .login-button {
    display: flex;
    justify-content: space-around;
  }
}
</style>
<template>
  <div class="login">
    <img class="logo" src="../images/_ionicons_svg_logo-snapchat.svg" type="image/svg+xml" />
    <Tabs type="card">
      <TabPane label="登 录">
        <Card class="form">
          <Form ref="loginForm" :model="loginForm" :rules="ruleValidate" :label-width="80">
            <FormItem label="登录账号" prop="loginName">
              <Input v-model="loginForm.loginName" placeholder="输入账户"></Input>
            </FormItem>
            <br />
            <FormItem label="登录密码" prop="password">
              <Input type="password" v-model="loginForm.password" placeholder="请输入密码"></Input>
            </FormItem>
          </Form>
          <br />
          <br />
          <div class="login-button">
            <Button @click="reset('loginForm')" type="error">清 空</Button>
            <Button @click="login('loginForm')" type="success">登 录</Button>
          </div>
        </Card>
      </TabPane>
      <TabPane label="注 册">
        <Card class="form">
          <Form ref="regForm" :model="regForm" :rules="regValidate" :label-width="80">
            <FormItem label="用户昵称" prop="customerName">
              <Input v-model="regForm.customerName" placeholder="输入账户"></Input>
            </FormItem>
            <br />
            <FormItem label="登录账号" prop="loginName">
              <Input v-model="regForm.loginName" placeholder="输入账户"></Input>
            </FormItem>
            <br />
            <FormItem label="登录密码" prop="password">
              <Input type="password" v-model="regForm.password" placeholder="请输入密码"></Input>
            </FormItem>
            <br />
            <FormItem label="确认密码" prop="checkPassword">
              <Input type="password" v-model="regForm.checkPassword" placeholder="请再次输入密码"></Input>
            </FormItem>
          </Form>
          <br />
          <br />
          <div class="login-button">
            <Button @click="reset('regForm')" type="error">清 空</Button>
            <Button @click="reg('regForm')" type="success">注 册</Button>
          </div>
        </Card>
      </TabPane>
    </Tabs>
  </div>
</template>
<script>
export default {
  data() {
    const validatePassCheck = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Please enter your password again"));
      } else if (value !== this.regForm.password) {
        callback(new Error("密码不一致！！"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        loginName: "",
        password: ""
      },
      regForm: {
        loginName: "",
        password: "",
        checkPassword: "",
        customerName: ""
      },
      ruleValidate: {
        loginName: [
          {
            required: true,
            message: "登录名不能为空！！",
            trigger: "blur"
          },
          { type: "email", message: "登录名必须为邮箱！！", trigger: "blur" }
        ],
        password: [
          {
            required: true,
            message: "密码不能为空！！",
            trigger: "blur"
          }
        ]
      },
      regValidate: {
        loginName: [
          {
            required: true,
            message: "登录名不能为空！！",
            trigger: "blur"
          },
          { type: "email", message: "登录名必须为邮箱！！", trigger: "blur" }
        ],
        password: [
          {
            required: true,
            message: "密码不能为空！！",
            trigger: "blur"
          },
          {
            type: "string",
            min: 6,
            message: "密码过短！！",
            trigger: "blur"
          }
        ],
        checkPassword: [
          {
            required: true,
            message: "确认密码不能为空！！",
            trigger: "blur"
          },
          { validator: validatePassCheck, trigger: "blur" }
        ],
        customerName: [
          {
            required: true,
            message: "昵称不能为空！！",
            trigger: "blur"
          },
          {
            type: "string",
            max: 6,
            message: "昵称过长！！",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    // 登录
    login(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          this.$api.customer
            .login(this.loginForm)
            .then(data => {
              if (data.succeeded) {
                this.$Message.success({ content: data.msg, background: true });
                this.$store.commit("changeLogin", data.obj.token);
                this.$store.commit("changeLoginName", data.obj.customerName);
                this.$router.go(-1); //返回上一层
              } else {
                this.$Message.error({
                  content: data.msg + "-" + data.code,
                  background: true
                });
              }
              console.log(data);
            })
            .catch(error => {
              this.$Message.error("请求失败");
              console.error(error);
            });
        } else {
          this.$Message.error({ content: "信息错误", background: true });
        }
      });
    },
    reset(name) {
      console.log("清空" + name);
      this.$refs[name].resetFields();
    },
    reg(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          console.log(this.regForm);
          this.$api.customer
            .reg(this.regForm)
            .then(data => {
              if (data.succeeded) {
                this.$Message.success({ content: data.msg, background: true });
                this.$store.commit("changeLogin", data.obj.token);
                this.$store.commit("changeLoginName", data.obj.customerName);
                this.$router.go(-1); //返回上一层
              } else {
                this.$Message.error({
                  content: data.msg + "-" + data.code,
                  background: true
                });
              }
              console.log(data);
            })
            .catch(error => {
              this.$Message.error("请求失败");
              console.error(error);
            });
        } else {
          this.$Message.error({ content: "信息错误", background: true });
        }
      });
    }
  },
  created() {},
  beforeCreate() {}
};
</script>
