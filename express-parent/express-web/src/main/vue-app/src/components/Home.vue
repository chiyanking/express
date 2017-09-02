<style scoped>
  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }

  .layout-content {
    min-height: 200px;
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
</style>
<template>
  <div class="layout" :class="textShow">
    <Row type="flex">
      <Col :span="spanLeft" class="layout-menu-left">
      <Menu active-name="1" theme="dark" width="auto">
        <MenuItem name="1">
          <Icon type="ios-navigate" :size="iconSize"></Icon>
          <span class="layout-text">首页</span>
        </MenuItem>
        <MenuItem name="2">
          <Icon type="ios-keypad" :size="iconSize"></Icon>
          <span class="layout-text">统计</span>
        </MenuItem>
        <MenuItem name="3">
          <Icon type="ios-analytics" :size="iconSize"></Icon>
          <span class="layout-text">打印记录</span>
        </MenuItem>
      </Menu>
      </Col>
      <Col :span="spanRight">
      <Row type="flex" class="layout-header">
        <Col span="2">
        <Button type="text" @click="toggleClick">
          <Icon type="navicon" size="32"></Icon>
        </Button>
        </Col>
        <Col span="22">
        <div style="float:right;padding:10px 10px;">
          <Avatar icon="person" size="large"/>
        </div>
        </Col>
      </Row>
      <div class="layout-content">
        <div class="layout-content-main">
          <Row style="padding: 20px;">
            <Col span="24">
            快递公司
            <Select v-model="search.companyId" style="width:150px">
              <Option v-for="item in companies" :value="item.id" :key="item.id">{{ item.name }}</Option>
            </Select>
            寄件人
            <Input v-model="search.senderName" placeholder="" style="width: 150px;"></Input>
            快递单号
            <Input v-model="search.expCode" placeholder="" style="width: 150px;"></Input>
            快递日期：
            <DatePicker type="datetimerange" placeholder="选择日期和时间"></DatePicker>
            <Button type="info" @click="query">查询</Button>
            </Col>
          </Row>
          <Table border :columns="columns" :data="tableData"></Table>
          <Page :total="100" show-sizer></Page>
        </div>
      </div>
      <div class="layout-copy">
        2011-2016 &copy; TalkingData
      </div>
      </Col>
    </Row>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        spanLeft: 1,
        spanRight: 23,
        columns: [
          {
            title: '姓名',
            key: 'name',
            render: (h, params) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'person'
                  }
                }),
                h('strong', params.row.name)
              ]);
            }
          },
          {
            title: '年龄',
            key: 'age'
          },
          {
            title: '地址',
            key: 'address'
          },
          {
            title: '操作',
            key: 'action',
            width: 150,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.show(params.index)
                    }
                  }
                }, '查看'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.remove(params.index)
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        tableData: [
          {
            name: '王小明',
            age: 18,
            address: '北京市朝阳区芍药居'
          },
          {
            name: '张小刚',
            age: 25,
            address: '北京市海淀区西二旗'
          },
          {
            name: '李小红',
            age: 30,
            address: '上海市浦东新区世纪大道'
          },
          {
            name: '周小伟',
            age: 26,
            address: '深圳市南山区深南大道'
          }
        ],
        companies: [{id: 0, name: '全部'}, {id: 1, name: '中通快递'}],
        search: {
          companyId: 0,
          senderName: "",
          expCode: "",
          pointId: "",
          startDate: "",
          endDate: "",
          isPrnt: false,
        }
      }
    },
    computed: {
      iconSize() {
        return this.spanLeft > 2 ? 14 : 24;
      },
      textShow() {
        return this.spanLeft > 2 ? 'layout-show-text': 'layout-hide-text';
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
      show(index) {
        this.$Modal.info({
          title: '用户信息',
          content: `姓名：${this.tableData[index].name}<br>年龄：${this.tableData[index].age}<br>地址：${this.tableData[index].address}`
        })
      },
      remove(index) {
        this.tableData.splice(index, 1);
      },
      query() {
        debugger
      }
    }
  }
</script>
