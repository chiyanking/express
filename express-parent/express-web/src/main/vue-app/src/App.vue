<template>
  <div id="app">
    <div class="layout" :class="textShow">
      <i-row type="flex">
        <i-col :span="spanLeft" class="layout-menu-left">
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
              <div @click="turnToSetting">
                <Icon type="ios-analytics" :size="iconSize"></Icon>
                <span class="layout-text">打印设置</span>
              </div>
            </MenuItem>
          </Menu>
        </i-col>
        <i-col :span="spanRight">
          <i-row type="flex" class="layout-header">
            <i-col span="22">
              <Button type="text" @click="toggleClick">
                <Icon type="navicon" size="32"></Icon>
              </Button>
            </i-col>
            <i-col span="2">
              <Dropdown class="float-right">
                <a href="javascript:void(0)">
                  <Avatar icon="person" size="large" class="person-avatar"/>
                </a>
                <DropdownMenu slot="list">
                  <DropdownItem>
                    <div @click="getCode">
                      <Icon type="settings"></Icon>
                      个人信息
                    </div>
                  </DropdownItem>
                  <DropdownItem>
                    <div @click="turnToSetting">
                      <Icon type="ios-flower"></Icon>
                      打印单配置
                    </div>
                  </DropdownItem>
                </DropdownMenu>
              </Dropdown>
            </i-col>
          </i-row>
          <div class="layout-content">
            <!-- 路由入口-->
            <router-view></router-view>
          </div>
          <div class="layout-copy">
            2011-2020 &copy; wwww.dagemen.com
          </div>
        </i-col>
      </i-row>
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
      },
      getCode() {
        let url = "http://www.dagemen.com/mobile/index.html?pointId=" + 1;
        window.open("https://cli.im/api/qrcode/code?text=" + url + "&mhid=sBPACl7rzs4hMHctKdRTP64");
      }
    },
    mounted() {
//      this.$http.post("api/point/login", {
//        phone: 15757125092,
//        password: 123
//      }).then(() => {
//
//      });
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

  .float-right {
    float: right;
    margin-right: 20px;
  }

</style>
