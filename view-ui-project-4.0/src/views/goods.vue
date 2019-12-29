<style scoped lang="less">
.goods {
  width: 100%;
  text-align: center;
  margin: 0 auto;
  position: relative;
  top: 50px;
  .good {
    display: flex;
    flex-direction: row;
    align-items: center;
    .img {
      width: 40%;
    }
    .diver {
      height: 450px;
    }
    .property {
      height: 450px;
      width: 60%;
      display: flex;
      flex-direction: column;
      align-content: center;
      .info {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
      }
      .price {
        font-size: 25px;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
      }
      .stock {
        width: 10%;
        font-size: 18px;
        position: relative;
        left: 300px;
        padding-top: 2px;
        color: gray;
      }
      .property-list {
        width: 100%;
        height: 77%;
        float: left;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        .property-name {
          width: 10%;
          font-size: 16px;
          line-height: 16px;
        }
        .property-options {
          width: 90%;
          display: flex;
          flex-direction: row;
          flex-wrap: nowrap;
          justify-content: flex-start;
        }
      }
      .property-row {
        padding: 10px;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: flex-start;
        align-items: center;
      }
    }
    .buy {
      display: flex;
      flex-direction: row;
      justify-content: space-around;
    }
  }
}
</style>
<template>
  <div class="goods">
    <div class="good">
      <div class="img">
        <img :src="sku.cover" />
      </div>
      <Divider class="diver" type="vertical" />
      <div class="property">
        <h1>{{spu.proName }} {{spu.info}}</h1>
        <div class="info">
          <div class="price">
            <Icon type="logo-yen" color="red" size="30" />
            <span>{{sku.oldPrice}}</span>
          </div>
          <div class="stock">
            <span>库存 {{sku.stock}} 件</span>
          </div>
        </div>
        <div class="property-list">
          <Row v-for="item in propertyList" :key="item.id" class="property-row">
            <div class="property-name">{{item.propertyName}}</div>
            <RadioGroup v-model="property[item.id]" class="property-options" @on-change="change">
              <Radio
                v-for="sonItem in item.optionList"
                :label="sonItem.id"
                :key="sonItem.id"
              >{{sonItem.option}}</Radio>
            </RadioGroup>
          </Row>
        </div>
        <div class="buy">
          <Button type="success" @click="collection">加入收藏</Button>
          <InputNumber v-model="number" :min="1" :max="sku.stock" :editable="false" size="large"></InputNumber>
          <Button type="error" class="buy-button" @click="buy">立即购买</Button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      id: this.$route.params.id,
      spu: {},
      propertyList: [],
      property: {},
      sku: {},
      number: 1
    };
  },
  methods: {
    // 查询spu详情
    querySpuById() {
      this.$Spin.show();
      console.log(this.id);
      this.$api.spu
        .querySpuById(this.id)
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            this.spu = data.obj;
          } else {
            this.$Message.error({
              content: data.msg + "-" + data.code,
              background: true
            });
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
          this.$Spin.hide();
        });
    },
    // 根据spu查询属性选项
    queryPropertyBySpu() {
      this.$Spin.show();
      this.$api.property
        .queryPropertyBySpu(this.id)
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            this.propertyList = data.obj;
            // 设置默认选项
            data.obj.forEach(element => {
              this.property[element.id] = element.optionList[0].id;
            });
            this.change(this.property);
          } else {
            this.$Message.error({
              content: data.msg + "-" + data.code,
              background: true
            });
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
          this.$Spin.hide();
        });
    },
    change(data) {
      console.log(this.property);
      let arr = [];
      for (const key in this.property) {
        let object = {};
        object.propertyId = key;
        object.optionId = this.property[key];
        arr.push(object);
      }
      this.querySkuByOptions(arr);
    },
    // 根据选项集合查询sku
    querySkuByOptions(property) {
      this.$api.sku
        .querySkuByPropertyOptions(property)
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            this.sku = data.obj;
          } else {
            this.$Message.error({
              content: data.msg + "-" + data.code,
              background: true
            });
          }
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
        });
    },
    //下单
    buy() {
      this.$api.customer.checkLogin()
        .then(data => {
          if (data.succeeded) {
            this.$router.push("/new-oder/" + this.sku.id + "/" + this.number);
          } else {
            this.$Message.error({
              content: "请先登录",
              background: true
            });
            this.$router.push("/login");
          }
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
        });
    },
    //收藏
    collection() {
      console.log("收藏");
    }
  },
  created() {
    this.querySpuById();
    this.queryPropertyBySpu();
  },
  mounted() {},
  beforeDestroy() {}
};
</script>
