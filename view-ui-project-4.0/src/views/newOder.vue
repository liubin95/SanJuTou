<style scoped lang="less">
li {
  list-style-type: none;
}
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
      flex-wrap: wrap;
      .address-card {
        width: 45%;
        margin-top: 20px;
      }
    }
  }
  #he {
    /* width: 100%; */
    display: flex; /*弹性盒模型*/
    justify-content: center; /*主轴方向居中显示*/
    align-items: center; /*交叉轴方向居中显示*/
    height: 20vh;
    ul {
      height: 100px;
    }
    li {
      float: left;
      width: 20px;
      height: 20px;
      border-radius: 20px;
      margin-right: 10px;
    }
    li:nth-child(1) {
      background-color: #f62e74;
      animation: love1 4s infinite;
    }
    li:nth-child(2) {
      background-color: #f45330;
      animation: love2 4s infinite;
      animation-delay: 0.15s;
    }
    li:nth-child(3) {
      background-color: #ffc883;
      animation: love3 4s infinite;
      animation-delay: 0.3s;
    }
    li:nth-child(4) {
      background-color: #30d268;
      animation: love4 4s infinite;
      animation-delay: 0.45s;
    }
    li:nth-child(5) {
      background-color: #006cb4;
      animation: love5 4s infinite;
      animation-delay: 0.6s;
    }
    li:nth-child(6) {
      background-color: #784697;
      animation: love4 4s infinite;
      animation-delay: 0.75s;
    }
    li:nth-child(7) {
      background-color: #ffc883;
      animation: love3 4s infinite;
      animation-delay: 0.9s;
    }
    li:nth-child(8) {
      background-color: #f45330;
      animation: love2 4s infinite;
      animation-delay: 1.05s;
    }
    li:nth-child(9) {
      background-color: #f62e74;
      animation: love1 4s infinite;
      animation-delay: 1.2s;
    }
    @keyframes love1 {
      30%,
      50% {
        height: 60px;
        transform: translateY(-30px);
      }
      75%,
      100% {
        height: 20px;
        transform: translateY(0);
      }
    }
    @keyframes love2 {
      30%,
      50% {
        height: 125px;
        transform: translateY(-62.5px);
      }
      75%,
      100% {
        height: 20px;
        transform: translateY(0);
      }
    }
    @keyframes love3 {
      30%,
      50% {
        height: 160px;
        transform: translateY(-75px);
      }
      75%,
      100% {
        height: 20px;
        transform: translateY(0);
      }
    }
    @keyframes love4 {
      30%,
      50% {
        height: 180px;
        transform: translateY(-60px);
      }
      75%,
      100% {
        height: 20px;
        transform: translateY(0);
      }
    }
    @keyframes love5 {
      30%,
      50% {
        height: 190px;
        transform: translateY(-45px);
      }
      75%,
      100% {
        height: 20px;
        transform: translateY(0);
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
      <Button type="error" @click="next" :disabled="current===3">下一步</Button>
    </div>
    <!-- 订单信息 -->
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
    <!-- 选择地址 -->
    <div class="address-info" v-if="current===1">
      <Button type="info" @click="newAddressMethod" class="addAddress">新增收货地址</Button>
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
          <v-region :town="true" :disabled="true" v-model="item.vreion"></v-region>
          <h3>
            <span>详细地址：</span>
            <span>{{item.address}}</span>
          </h3>
        </Card>
      </div>
    </div>
    <!-- 付款 -->
    <div class="pay" v-if="current===2">
      <RadioGroup v-model="pay">
        <Radio label="1">
          <span>立刻付款</span>
        </Radio>
        <Radio label="0">
          <span>稍后付款</span>
        </Radio>
      </RadioGroup>
    </div>
    <!-- 完成 -->
    <div class="finish" v-if="current===3">
      <div id="he">
        <ul>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
      </div>
      <br />
      <br />
      <br />
      <h3>
        <Time :time="time3" :interval="1" />
        <span>下单成功</span>
      </h3>
      <br />
      <br />
      <br />
      <br />
      <Alert type="success" show-icon v-if="finishType==='1'">
        付款成功
        <span slot="desc">可以在个人中心页面，我的订单中查看详情</span>
      </Alert>
      <Alert type="warning" show-icon v-if="finishType==='0'">
        下单成功
        <span slot="desc">请尽快完成付款，避免影响您的正常使用</span>
      </Alert>
      <Alert type="error" show-icon v-if="finishType==='2'">
        下单失败
        <span slot="desc">{{errorInfo}}</span>
      </Alert>
    </div>
    <!-- 新增地址信息弹出框 -->
    <Modal
      v-model="addressModal"
      :mask-closable="false"
      :closable="false"
      :loading="true"
      width="600"
    >
      <p slot="header" style="color:green;text-align:center">
        <Icon type="ios-information-circle"></Icon>
        <span>新增收货地址</span>
      </p>
      <div slot="footer">
        <Button type="error" style="float: left;" size="large" @click="cancel">取 消</Button>
        <Button type="success" size="large" @click="add">新 增</Button>
      </div>
      <Form ref="newAddress" :model="newAddressForm" :rules="newAddress" :label-width="100">
        <FormItem label="地址信息" prop="vreion">
          <v-region :town="true" v-model="newAddressForm.vreion"></v-region>
        </FormItem>
        <FormItem label="收件人姓名" prop="name">
          <Input v-model="newAddressForm.name" placeholder="请输入收件人姓名。。。" />
        </FormItem>
        <FormItem label="手机号码" prop="mobile">
          <Input v-model="newAddressForm.mobile" placeholder="请输入手机号。。。" />
        </FormItem>
        <FormItem label="详细地址" prop="address">
          <Input v-model="newAddressForm.address" placeholder="请输入详细地址。。。" />
        </FormItem>
        <FormItem>
          <Radio
            true-value="1"
            @on-change="defaultAddress"
            false-value="0"
            v-model="newAddressForm.isDefault"
          >是否默认地址</Radio>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>
<script>
export default {
  data() {
    const valiMoblieCheck = (rule, value, callback) => {
      let patt = /^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
      if (!patt.test(value)) {
        callback(new Error("手机号格式错误"));
      } else {
        callback();
      }
    };
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
      addressModal: false,
      newAddressForm: {
        vreion: {},
        name: "",
        mobile: "",
        address: "",
        isDefault: "0"
      },
      newAddress: {
        name: [
          {
            required: true,
            message: "收件人姓名不能空",
            trigger: "blur"
          }
        ],
        mobile: [
          {
            required: true,
            message: "手机号码不能为空",
            trigger: "blur"
          },
          { validator: valiMoblieCheck, trigger: "blur" }
        ],
        address: [
          {
            required: true,
            message: "详细地址不能为空",
            trigger: "blur"
          }
        ]
      },
      validateList: ["province", "city", "area", "town"],
      pay: "0",
      time3: "",
      finishType: "0",
      errorInfo: ""
    };
  },
  methods: {
    //下一步
    next() {
      // 选择地址信息
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
      // 付款页面
      if (this.current === 2) {
        //下单方法
        console.log("下单");
        this.newOder();
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
              let temp = {};
              temp.province = element.province;
              temp.city = element.city;
              temp.area = element.area;
              temp.town = element.town;
              element.vreion = temp;
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
            content: "不能同时选中两个",
            background: true
          });
          return new Promise(false);
        }
      } else {
        //关闭
        this.choseAddressId = "";
      }
    },
    // 新增收货信息按钮
    newAddressMethod() {
      this.addressModal = true;
    },
    //新增收货信息请求
    add() {
      const vreion = this.newAddressForm.vreion;
      this.$refs["newAddress"].validate(valid => {
        let b = true;
        this.validateList.forEach(element => {
          b = vreion.hasOwnProperty(element) && vreion[element] !== null;
        });
        if (valid && b) {
          this.$Spin.show();
          this.newAddressForm.province = vreion.province;
          this.newAddressForm.city = vreion.city;
          this.newAddressForm.area = vreion.area;
          this.newAddressForm.town = vreion.town;
          console.log(this.newAddressForm);
          this.$api.address
            .addAddress(this.newAddressForm)
            .then(data => {
              if (data.succeeded) {
                this.$Message.success({
                  content: data.obj,
                  background: true
                });
              } else {
                this.$Message.error({
                  content: data.msg + "-" + data.code,
                  background: true
                });
              }
              console.log(data);
              this.queryAddressByCustomId();
              this.$Spin.hide();
            })
            .catch(error => {
              this.$Message.error("请求失败");
              console.error(error);
              this.$Spin.hide();
            });
        } else {
          this.$Message.error({ content: "信息错误", background: true });
        }
      });
    },
    //新增地址信息关闭
    cancel() {
      this.addressModal = false;
      this.newAddressForm.isDefault = "0";
      this.$refs["newAddress"].resetFields();
    },
    defaultAddress(val) {
      if (val === "1") {
        this.$Message.warning({
          content: "原有默认地址会失效",
          background: true
        });
      }
    },
    // 下单
    newOder() {
      this.$Spin.show();
      let from = {
        typeId: this.pay,
        skuId: this.id,
        counts: this.number,
        addressId: this.choseAddressId,
        note: this.note
      };
      this.$api.oderInfo
        .newOder(from)
        .then(data => {
          if (data.succeeded) {
            this.$Message.success({
              content: "下单成功，可在个人信息也查看",
              background: true
            });
            this.time3 = new Date();
            this.finishType = this.pay;
          } else {
            this.$Message.error({
              content: data.msg + "-" + data.code,
              background: true
            });
            this.finishType = "3";
            this.errorInfo = data.msg + "-" + data.code;
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
          this.$Spin.hide();
        });
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
