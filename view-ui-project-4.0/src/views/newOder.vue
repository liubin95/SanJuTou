<style scoped lang="less">
.new-oder {
  width: 80%;
  text-align: center;
  margin: 0 auto;
  position: relative;
  top: 50px;
  .buttons {
    margin: 20px;
    display: flex;
    justify-content: space-between;
  }
  h2 {
    display: -webkit-flex;
    display: flex;
    span:first-child {
      width: 50%;
      text-align: right;
      display: block;
      margin-right: 10px;
    }
    span:last-child {
      flex: 1;
      width: 50%;
      text-align: left;
      display: block;
    }
  }
  .address-info {
    .addAddress {
      position: relative;
      top: -51px;
    }

    .address-list {
      .title {
        height: 25px;
      }
      display: flex;
      justify-content: space-between;
      .address-card {
        width: 45%;
      }
    }
  }
}
</style>
<template>
  <div class="new-oder">
    <div class="steps">
      <Steps :current="current">
        <Step title="步骤1" content="核对订单信息"></Step>
        <Step title="步骤2" content="选择收货地址"></Step>
        <Step title="步骤3" content="付款"></Step>
        <Step title="步骤4" content="完成"></Step>
      </Steps>
    </div>
    <div class="buttons">
      <Button type="success" @click="prev" :disabled="current===0">上一步</Button>
      <Button type="error" @click="next" :disabled="current===3 && !canNext">下一步</Button>
    </div>
    <div class="oder-info" v-if="current===0">
      <Card>
        <p slot="title">订单信息</p>

        <h2>
          <span>商品：</span>
          <span>{{spu.proName}}</span>
        </h2>
        <h2 v-for="item in propertyList" :key="item.id">
          <span>{{item.propertyName}}：</span>
          <span>{{item.propertyOption.option}}</span>
        </h2>
        <h2>
          <span>单价：</span>
          <span>{{sku.oldPrice.toFixed(2)}} 元</span>
        </h2>
        <h2>
          <span>数量：</span>
          <span>{{number}} 件</span>
        </h2>
        <h2>
          <span>总价：</span>
          <span>{{(sku.oldPrice*number).toFixed(2)}} 元</span>
        </h2>
        <br />
        <Input
          v-model="note"
          maxlength="100"
          show-word-limit
          type="textarea"
          placeholder="买家留言......."
          style="width: 200px"
        />
      </Card>
    </div>
    <div class="address-info" v-if="current===1">
      <Button type="info" class="addAddress">新增收货地址</Button>
      <div class="address-list">
        <Card class="address-card" v-for="item in address" :key="item.id">
          <p slot="title" class="title">
            收货地址信息
            <Switch
              true-value="1"
              false-value="0"
              v-model="item.isDefault"
              true-color="#13ce66"
              false-color="#ff4949"
              @before-change="choseAddress(item)"
            />
          </p>
          <h3>
            <span>收件人姓名：</span>
            <span>{{item.name}}</span>
          </h3>
          <h3>
            <span>收件人电话：</span>
            <span>{{item.mobile}}</span>
          </h3>
          <br />
          <v-region :town="true" :disabled="true" v-model="item.address"></v-region>
        </Card>
      </div>
    </div>
    <div class="pay" v-if="current===2"></div>
    <div class="finish" v-if="current===3"></div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      current: 0,
      id: this.$route.params.id,
      number: this.$route.params.num,
      sku: {
        oldPrice: 0.0
      },
      spu: {},
      note: "",
      address: [],
      choseAddressId: "",
      propertyList: [],
      canNext: true
    };
  },
  methods: {
    //下一步
    next() {
      if (this.current == 1) {
        if (this.address.length == 0) {
          this.$Message.error({
            content: "请新增地址信息！",
            background: true
          });
          return false;
        }
        if (this.choseAddressId === "") {
          this.$Message.error({
            content: "请选择地址信息！",
            background: true
          });
          return false;
        }
      }
      this.current += 1;
    },
    //上一步
    prev() {
      this.current -= 1;
    },
    querySkuById() {
      this.$api.sku
        .querySkuById(this.id)
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
    // 根据sku查询spu
    querySpuBySku() {
      this.$api.spu
        .querySpuBySku(this.id)
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
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
        });
    },
    //查询购买商品的属性
    queryPropertyBySku() {
      this.$api.property
        .queryPropertyBySku(this.id)
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            this.propertyList = data.obj;
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
    // 查询地址信息列表
    queryAddressByCustomId() {
      this.$api.address
        .queryAddressByCustomId()
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            data.obj.forEach(element => {
              element.address = JSON.parse(element.address);
              if (element.isDefault === 1) {
                this.choseAddressId = element.id;
              }
              element.isDefault = element.isDefault.toString();
            });
            this.address = data.obj;
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
    // 选择呢地址信息
    choseAddress(item) {
      console.log(item);
      // 开启
      if (item.isDefault === "1") {
        if (this.choseAddressId === "") {
          this.choseAddressId = item.id;
        } else {
          this.$Message.error({
            content: '不能同时选中两个',
            background: true
          });
          return new Promise(false)
        }
      } else {
        //关闭
        this.choseAddressId = "";
      }
    }
  },
  created() {
    console.log(this.number);
    this.querySkuById();
    this.querySpuBySku();
    this.queryPropertyBySku();
    this.queryAddressByCustomId();
  },
  beforeCreate() {}
};
</script>
