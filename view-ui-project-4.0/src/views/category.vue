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
}
</style>
<template>
  <div class="category">
    <!-- 筛选部分 -->
    <div class="screen">
      <Row v-for="item in specifications" :key="item.id" class="specifications-row">
        <span class="specifications-name">{{item.name}}</span>
        <CheckboxGroup
          class="specifications-options"
          v-model="requirements[item.id]"
          @on-change="change"
        >
          <Checkbox v-for="option in item.options" :key="option.id" :label="option.id" border>{{option.name}}</Checkbox>
        </CheckboxGroup>
        <Divider />
      </Row>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      id: this.$route.params.id,
      border: [],
      specifications: [
        {
          id: 1,
          name: "操作系统",
          options: [
            { id: 1, name: "安卓系统" },
            { id: 2, name: "ios" },
            { id: 3, name: "塞班" }
          ]
        },
        {
          id: 2,
          name: "内存",
          options: [
            { id: 4, name: "16g" },
            { id: 5, name: "32g" },
            { id: 6, name: "64g" }
          ]
        },
        {
          id: 3,
          name: "摄像头",
          options: [
            { id: 7, name: "1" },
            { id: 8, name: "3" },
            { id: 9, name: "6" }
          ]
        },
        {
          id: 4,
          name: "屏幕尺寸",
          options: [
            { id: 10, name: "大" },
            { id: 11, name: "中" },
            { id: 12, name: "小" }
          ]
        }
      ],
      requirements: {}
    };
  },
  methods: {
    change(data) {
      console.log(this.requirements);
    }
  },
  created() {
    // this.$Message.info(this.id);
    // this.$axios({
    //   url: "http://localhost:3333/specification/queryCategorySpec?categoryId="+this.id, //请求的地址
    //   method: "get", //请求的方式
    //   data: { category_id: this.id } //请求的表单数据
    // }).then(data => {
    //   console.log(data);
    // });
    this.$api.specification.queryCategorySpec(this.id).then(data=>{
      console.log(data);
    });
  }
};
</script>
