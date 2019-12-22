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
      .property-list {
        width: 100%;
        float: left;
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
  }
}
</style>
<template>
  <div class="goods">
    <div class="good">
      <div class="img">
        <img
          src="//img13.360buyimg.com/n1/s450x450_jfs/t1/18843/4/12656/169965/5c99ef93Eab039a45/51c1d29ce04ebea8.jpg"
        />
      </div>
      <Divider class="diver" type="vertical" />
      <div class="property">
        <h4>{{spu.proName }} {{spu.info}}</h4>
        <div>
          <Icon type="logo-yen" color="red" size="18" />
          <span>{{sku.oldPrice}}</span>
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
      sku: {}
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
            this.$Message.error({ content: data.msg, background: true });
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
            this.$Message.error({ content: data.msg, background: true });
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
          if (data.succeeded) {
            this.sku = data.obj;
          } else {
            this.$Message.error({ content: data.msg, background: true });
          }
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.error(error);
          this.$Spin.hide();
        });
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
