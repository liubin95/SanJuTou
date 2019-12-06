<style scoped lang="less">
.index {
  width: 100%;
  text-align: center;
  margin: 0 auto;
  position: relative;
  top: 50px;
  h1 {
    height: 100px;
  }
  h2 {
    color: #666;
    margin-bottom: 200px;
    p {
      margin: 0 0 50px;
    }
  }
  .ivu-row-flex {
    height: 100%;
  }
  .row {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-direction: row;
  }
  .cateGory {
    width: 30%;
    margin-left: 2%;
  }
  .carousel {
    width: 40%;
    margin-left: 2%;
  }
  .ad {
    height: 100%;
    width: 10%;
    height: 475px;
    margin-left: 2%;
  }
  .demo-carousel {
    height: 539px;
    background: #000;
  }
  img {
    max-height: 100%;
    max-width: 100%;
  }
  .ad-image {
    height: 100%;
    width: 100%;
  }
  .list-span {
    cursor: pointer;
  }
  .miao-sha {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    flex-direction: row;
    .title {
      margin-left: 2%;
      width: 20%;
      .miao-sha-span {
        font-size: 20px;
        font-weight: 700;
        font-weight: bolder;
        font-family: "Microsoft YaHei", "Heiti SC", tahoma, arial,
          "Hiragino Sans GB", sans-serif;
      }
      .flash{
        height: 80px;
        width: 80xp;
      }
    }
    .divider-inline {
      height: 250px;
    }
    .miao-sha-list {
      width: 78%;
      height: 250px;
      .ivu-carousel-list {
        height: 250px;
      }
      .item {
        display: flex;
        justify-content: space-around;;
        align-items: center;
        flex-direction: row;
      }
      img {
        max-height: 140px;
      }
    }
  }
}
</style>
<template>
  <div class="index">
    <Row class="row">
      <div class="ad">
        <img class="ad-image" src="../images/ad.png" />
      </div>
      <div class="cateGory">
        <List header="分类列表" border size="large">
          <ListItem v-for="item in cateList" :key="item.id">
            <span class="list-span" @click="handleStart(item.id)">{{item.categoryName}}</span>
          </ListItem>
        </List>
      </div>
      <div class="carousel">
        <Carousel autoplay v-model="value2" loop>
          <CarouselItem>
            <div class="demo-carousel">
              <img
                src="//img10.360buyimg.com/pop/s1180x940_jfs/t1/102495/8/4512/70351/5de761caE5d217467/72832d4736885978.jpg.webp"
              />
            </div>
          </CarouselItem>
          <CarouselItem>
            <div class="demo-carousel">
              <img
                src="//img13.360buyimg.com/pop/s1180x940_jfs/t1/72782/10/16371/93353/5dde45deE6072f122/7f97ae732f93a6e2.jpg.webp"
              />
            </div>
          </CarouselItem>
          <CarouselItem>
            <div class="demo-carousel">
              <img
                src="//img14.360buyimg.com/babel/s1180x940_jfs/t1/91465/5/3263/90705/5dde422bEe181db9f/ec220ad5df9c43f1.jpg.webp"
                alt
              />
            </div>
          </CarouselItem>
          <CarouselItem>
            <div class="demo-carousel">
              <img
                src="//img20.360buyimg.com/pop/s1180x940_jfs/t1/83050/27/16074/98786/5dd797dcE99736314/8e9e247522ebf4f1.jpg.webp"
                alt
              />
            </div>
          </CarouselItem>
        </Carousel>
      </div>
    </Row>
    <Divider />
    <Row class="miao-sha">
      <div class="title">
        <h1>巨头秒杀</h1>
        <h3>{{time_hour}}:00 点场 倒计时</h3>
        <img class="flash" src="../images/_ionicons_svg_ios-flash.svg" type="image/svg+xml" />
        <p>
          <span class="miao-sha-span">{{hour}}</span>
          <span class="miao-sha-span">:</span>
          <span class="miao-sha-span">{{minute}}</span>
          <span class="miao-sha-span">:</span>
          <span class="miao-sha-span">{{second}}</span>
        </p>
      </div>
      <Divider type="vertical" class="divider-inline" />
      <div class="miao-sha-list">
        <Carousel v-model="value1" loop class="miao-sha-list-carousel" dots="none" arrow="always">
          <CarouselItem v-for="(list,index) in miao_sha_list" :key="index" class="item">
            <Card v-for="spu in list" :key="spu.id">
              <img :src="spu.cover" />
              <p slot="title">{{spu.proName}}</p>
              <p>
                <Icon type="logo-yen" color="red" size="18" />
                <span class="priceSpan">{{spu.price}}</span>
              </p>
            </Card>
          </CarouselItem>
        </Carousel>
      </div>
    </Row>
    <Divider />
    <div class="list">
      <h1>
        <img src="../images/logo.png" />
      </h1>
      <h2>
        <p>Welcome to your View UI app!</p>
        <Button @click="handleStart">Start View UI</Button>
      </h2>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      cateList: [],
      value1: 0,
      value2: 0,
      hour: 0,
      minute: 0,
      second: 0,
      timer: "",
      start_time: 0,
      time_hour: 0,
      miao_sha_list: []
    };
  },
  methods: {
    // 分类跳转
    handleStart(id) {
      console.log(id);
      this.$router.push("/category/" + id);
    },
    // 查询所有分类
    queryAllCategory() {
      this.$Spin.show();
      this.$api.cate
        .queryAllCategory()
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            this.cateList = data.obj;
          } else {
            this.$Message.error({ content: data.msg, background: true });
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.log(error);
          this.$Spin.hide();
        });
    },
    queryIndexSpuList() {},
    //查询秒杀
    queryFlashSale() {
      this.$api.flashSale
        .queryFlashSale()
        .then(data => {
          console.log(data);
          if (data.succeeded) {
            this.start_time = data.obj.startTime;
            this.miao_sha_list = [];
            for (let i = 0, len = data.obj.spuList.length; i < len; i += 3) {
              this.miao_sha_list.push(data.obj.spuList.slice(i, i + 3));
            }
          } else {
            this.$Message.error({ content: data.msg, background: true });
          }
          this.$Spin.hide();
        })
        .catch(error => {
          this.$Message.error("请求失败");
          console.log(error);
          this.$Spin.hide();
        });
    },
    //计时器
    countTime() {
      //获取当前时间
      var now = Date.now();
      //设置截止时间
      var endDate = new Date(this.start_time);
      this.time_hour = endDate.getHours();
      var end = endDate.getTime();
      //时间差
      var leftTime = end - now;
      // console.log(endDate);

      //定义变量 d,h,m,s保存倒计时的时间
      if (leftTime >= 0) {
        let hour = Math.floor((leftTime / 1000 / 60 / 60) % 24);
        this.hour = hour > 10 ? "" + hour : "0" + hour;
        let minute = Math.floor((leftTime / 1000 / 60) % 60);
        this.minute = minute > 10 ? "" + minute : "0" + minute;
        let second = Math.floor((leftTime / 1000) % 60);
        this.second = second > 10 ? "" + second : "0" + second;
      }
      //递归每秒调用countTime方法，显示动态时间效果
    }
  },
  created() {
    this.queryAllCategory();
    this.queryFlashSale();
  },
  mounted() {
    this.timer = setInterval(this.countTime, 1000);
  },
  beforeDestroy() {}
};
</script>
