<style scoped lang="less">
.category {
  width: 80%;
  text-align: center;
  margin: 0 auto;
  position: relative;
  top: 50px;
  .screen {
    width: 100%;
    float: left;
  }
  .specifications-name {
    margin-right: 10px;
    margin-top: 10px;
    float: left;
    font-size: 16px;
    line-height: 16px;
  }
  .specifications-options {
    margin-right: 10px;
    margin-bottom: 10px;
    float: left;
  }
  .specifications-row {
    padding: 10px;
  }
  img {
    height: 220px;
    width: 220px;
  }
  .priceSpan {
    color: red;
  }
}
</style>
<template>
  <div class="category">
    <!-- 筛选部分 -->
    <div class="screen">
      <Row v-for="item in specifications" :key="item.id" class="specifications-row">
        <span class="specifications-name">{{item.specificationName}}</span>
        <CheckboxGroup
          class="specifications-options"
          v-model="requirements[item.id]"
          @on-change="change"
        >
          <Checkbox
            v-for="option in item.options"
            :key="option.id"
            :label="option.id"
            border
          >{{option.optionName}}</Checkbox>
        </CheckboxGroup>
        <Divider />
      </Row>
    </div>
    <div class="skuList">
      <Col span="6">
        <Card>
          <div style="text-align:center">
            <img src="../images/01.png" />
            <p slot="title">华为 HUAWEI Mate 30 Pro 麒麟990旗舰芯片OLED环幕屏双4000万徕卡电影四摄8GB+128GB星河银4G全网通手机</p>
            <Icon type="logo-yen" color="red" size="18" />
            <span class="priceSpan">57.00</span>
            <h4 style="color:gray">华为京东自营官方旗舰店</h4>
            <Rate disabled v-model="valueDisabled" />
            <div>
              <Tag color="primary">放心购</Tag>
              <Tag color="success">新品</Tag>
              <Tag color="error">赠品</Tag>
            </div>
          </div>
        </Card>
      </Col>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      spinShow: true,
      id: this.$route.params.id,
      specifications: [],
      requirements: {},
      valueDisabled: 4
    };
  },
  methods: {
    change(data) {
      console.log(this.requirements);
    },
    //  分类下规格及选项
    queryCategorySpec() {
      this.$Spin.show();
      this.$api.specification
        .queryCategorySpec(this.id)
        .then(data => {
          if (data.succeeded) {
            this.specifications = data.obj;
          } else {
            this.$Message.error({ content: data.msg, background: true });
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          this.$Spin.hide();
        });
    }
  },
  created() {
    this.queryCategorySpec();
  }
};
</script>
