<style scoped>
@import "styles/common.css";
</style>
<template>
  <div id="app">
    <windowTop v-if="!(this.$route.path=='/login')"></windowTop>
    <div>
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
import windowTop from '@/components/windowTop';
export default {
  name: "App",
  data() {
    return {};
  },
  components: {
    windowTop
  },
  mounted() {},
  beforeDestroy() {},
  methods: {}
};
</script>
