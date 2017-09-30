<template>
  <div id="app">
    <div class="layout" :class="textShow">
      <Row type="flex">
        <Col :span="spanLeft" class="layout-menu-left">
        <Menu active-name="home" theme="dark" width="auto" @on-select="menuSelect">
          <MenuItem name="home">
            <Icon type="ios-navigate" :size="iconSize"></Icon>
            <span class="layout-text">首页</span>
          </MenuItem>
          <MenuItem name="chart">
            <Icon type="stats-bars" :size="iconSize"></Icon>
            <!--<Icon type="ios-keypad" :size="iconSize"></Icon>-->
            <span class="layout-text">统计</span>
          </MenuItem>
          <MenuItem name="waite">
            <Icon type="ios-analytics" :size="iconSize"></Icon>
            <span class="layout-text">打印记录</span>
          </MenuItem>
        </Menu>
        </Col>
        <Col :span="spanRight">
        <Row type="flex" class="layout-header">
          <Col span="23">
          <Button type="text" @click="toggleClick">
            <Icon type="navicon" size="32"></Icon>
          </Button>
          </Col>
          <Col span="1">
          <Dropdown>
            <a href="javascript:void(0)">
              <Avatar icon="person" size="large" class="person-avatar"/>
            </a>
            <DropdownMenu slot="list">
              <DropdownItem>
                <div @click="turnToSetting">
                  <Icon type="settings"></Icon>
                  个人信息</div>
              </DropdownItem>
              <DropdownItem>打印单配置</DropdownItem>
            </DropdownMenu>
          </Dropdown>
          </Col>
        </Row>
        <div class="layout-content">
          <!-- 路由入口-->
          <router-view></router-view>
        </div>
        <div class="layout-copy">
          2011-2020 &copy; wwww.dagemen.com
        </div>
        </Col>
      </Row>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'app',
    data() {
      return {
        spanLeft: 1,
        spanRight: 23,
      }
    },
    computed: {
      iconSize() {
        return this.spanLeft > 2 ? 14 : 24;
      },
      textShow() {
        return this.spanLeft > 2 ? 'layout-show-text' : 'layout-hide-text';
      }
    },
    methods: {
      toggleClick() {
        if (this.spanLeft > 2) {
          this.spanLeft = 1;
          this.spanRight = 23;
        } else {
          this.spanLeft = 3;
          this.spanRight = 21;
        }
      },
      menuSelect(value) {
        if (value != "waite") {
          this.$router.push("/" + value);
        }
      },
      turnToSetting(value) {
        this.$router.push("/setting");
      }
    },
    mounted() {
      this.$http.post("api/point/checkLogin", {
        phone: 15757125092,
        password: 123
      }).then(() => {

      });
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left;
    color: #2c3e50;
  }

  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-content {
    min-height: 700px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
  }

  .layout-content-main {
    padding: 10px;
  }

  .layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }

  .layout-menu-left {
    background: #464c5b;
  }

  .layout-header {
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .1);
  }

  .layout-ceiling-main a {
    color: #9ba7b5;
  }

  .layout-show-text .layout-text {
    transition: width .9s ease-in-out;
  }

  .layout-hide-text .layout-text {
    transition: width .3s ease-in-out;
    display: none;
  }

  .person-avatar {
    margin: 10px 25px 0 0;
  }

</style>
