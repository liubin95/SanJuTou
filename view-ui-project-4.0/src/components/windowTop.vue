<style scoped>
.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-logo {
  width: 100px;
  height: 30px;
  /* background: #5b6270; */
  border-radius: 3px;
  float: left;
  text-align: center;
  font-size: 20px;
  height: 30px;
  color: white;
  position: relative;
  top: 10px;
  left: 20px;
}
.layout-nav {
  width: 420px;
  margin: 0 auto;
  margin-right: 20px;
}
.logo {
  height: 50px;
  width: 50px;
}
</style>
<template>
  <div class="layout">
    <Layout>
      <Header>
        <Menu mode="horizontal" theme="dark" active-name="1">
          <div class="layout-logo">
            <img class="logo" src="../images/_ionicons_svg_logo-snapchat.svg" type="image/svg+xml" />
          </div>
          <div class="layout-nav">
            <MenuItem name="1" to="/login">
              <Icon type="ios-navigate"></Icon>注册/登录
            </MenuItem>
            <MenuItem name="2" to="/userInfo">
              <Icon type="md-person" />
              {{name}}
            </MenuItem>
            <!-- <MenuItem name="3">
              <Icon type="ios-analytics"></Icon>我的收藏
            </MenuItem>-->
            <MenuItem name="4" @click.native="logOut()">
              <Icon type="ios-paper"></Icon>注销
            </MenuItem>
          </div>
        </Menu>
      </Header>
    </Layout>
  </div>
</template>
<script>
export default {
  name: "windowTop",
  data() {
    return {};
  },
  computed: {
    name() {
      return this.$store.state.userName;
    }
  },
  methods: {
    logOut() {
      console.log("注销");
      localStorage.removeItem('token');
      this.$store.commit("changeLoginName", "游客");
    }
  }
};
</script>