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
    width: 10%;
    font-size: 16px;
    line-height: 16px;
  }
  .specifications-options {
    width: 90%;
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: flex-start;
  }
  .specifications-row {
    padding: 10px;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: flex-start;
    align-items: center;
  }
  img {
    height: 220px;
    width: 220px;
  }
  .priceSpan {
    color: red;
  }
  .skuList {
    width: 100%;
    float: left;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
<template>
  <div class="category">
    <!-- 筛选部分 -->
    <div class="screen">
      <Row v-for="item in specifications" :key="item.id" class="specifications-row">
        <div class="specifications-name">{{item.specificationName}}</div>
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
      <div v-for="spu in spuList" :key="spu.id">
        <Card>
          <div style="text-align:center">
            <img :src="spu.cover" />
            <p slot="title">{{spu.proName}}</p>
            <Icon type="logo-yen" color="red" size="18" />
            <span class="priceSpan">{{spu.price}}</span>
            <h4 style="color:gray">{{spu.info}}</h4>
            <Rate disabled v-model="spu.rate" />
            <div>
              <Tag color="primary">放心购</Tag>
              <Tag color="success">新品</Tag>
              <Tag color="error">赠品</Tag>
            </div>
          </div>
        </Card>
      </div>
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
      spuList: []
    };
  },
  methods: {
    // 筛选框的变化
    change(data) {
      console.log(this.requirements);
      let array = new Array();
      let requirements = this.requirements;
      let isAllEmpty = true;
      for (const key in requirements) {
        const element = requirements[key];
        let sonArray = new Array();
        if (element.length !== 0) {
          isAllEmpty = false;
        }
        element.forEach(item => {
          sonArray.push(item);
        });
        array.push(sonArray);
      }
      console.log(JSON.stringify(array));
      if (isAllEmpty) {
        this.querySpuByCategoryId();
      } else {
        this.querySpuBySpecOptions(array);
      }
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
    },
    //根据规格获取spu
    querySpuBySpecOptions(array) {
      this.$Spin.show();
      this.$api.spu
        .querySpuBySpecOptions(array)
        .then(data => {
          if (data.succeeded) {
            this.spuList = data.obj;
          } else {
            this.$Message.error({ content: data.msg, background: true });
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          this.$Spin.hide();
        });
    },
    //根据分类获取spu
    querySpuByCategoryId() {
      this.$Spin.show();
      this.$api.spu
        .querySpuByCategoryId(this.id)
        .then(data => {
          if (data.succeeded) {
            this.spuList = data.obj;
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
    this.querySpuByCategoryId();
  }
};
</script>
