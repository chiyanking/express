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

  .table-pagination {
    margin: 15px 0 15px 0;
    float: right;
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
          <Icon type="stats-bars" :size="iconSize"></Icon>
          <!--<Icon type="ios-keypad" :size="iconSize"></Icon>-->
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
          <Form :label-width="80" inline>
            <FormItem label="快递公司">
              <Select v-model="param.companyId">
                <Option v-for="item in companies" :value="item.id" :key="item.id">{{ item.name }}</Option>
              </Select>
            </FormItem>
            <FormItem label="快递单号">
              <Input v-model="param.expCode" placeholder=""/>
            </FormItem>
            <FormItem label="名字">
              <Input v-model="param.name" placeholder="寄件人或者收件人"/>
            </FormItem>
            <FormItem label="手机号">
              <Input v-model="param.phone" placeholder="寄件人或者收件人"/>
            </FormItem>
            <FormItem label="快递日期">
              <DatePicker type="datetimerange" placeholder="选择日期和时间"></DatePicker>
            </FormItem>
            <FormItem>
              <Button type="info" @click="query">查询</Button>
            </FormItem>
          </Form>
          <Table border :columns="columns" :data="tableData"></Table>
          <Page :total="total" size="small" :page-size="param.size" :current="param.current"
                @on-change="pageChange"
                class="table-pagination"></Page>
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
        total: 0,
        tableData: [],
        param: {
          current: 1,
          size: 20,
          name: null,
          phone: null,
          expCode: null,
          pointId: null,
          startDate: null,
          endDate: null,
          isPrint: true,
        },
        companies: [{id: 0, name: '全部'}, {id: 1, name: '中通快递'}],

        columns: [
          {
            title: '快递单号',
            key: 'expCode'
          },
          {
            title: '金额',
            key: 'price'
          },
          {
            title: '重量',
            key: 'weight'
          },
          {
            title: '是否打印',
            key: 'isPrint'
          },
          {
            title: '发件人姓名',
            key: 'senderName',
            render: (h, {row}) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'person'
                  }
                }),
                h('strong', row.senderName)
              ]);
            }
          },
          {
            title: '发件人手机号',
            width:115,
            key: 'senderPhone'
          },
          {
            title: '发件人地址',
            width:150,
            key: 'senderAddress',
            render(h, {row}) {
              return h("div", [h("p", [
                  row.senderProvinceName
                  + row.senderCityName
                  + row.senderDistrictName
                ]
              ), h("p", [
                row.senderAddress
              ])]);
            }
          },
          {
            title: '收件人姓名',
            key: 'receiverName',
            render: (h, {row}) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'person'
                  }
                }),
                h('strong', row.receiverName)
              ]);
            }
          }, {
            title: '收件人手机号',
            width:115,
            key: 'receiverPhone'
          }, {
            title: '收件人地址',
            width:150,
            key: 'address',
            render(h, {row}) {
              return h("div", [h("p", [
                  row.receiverProvinceName
                  + row.receiverCityName
                  + row.receiverDistrictName
                ]
              ), h("p", [
                row.receiverAddress
              ])]);
            }
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
                    type: 'text',
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
                    type: 'text',
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
        ]
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
      pageChange(page) {
        this.param.current = page;
        this.getPageList();
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
      },
      getPageList() {
        let param = Object.assign({}, this.param);
        this.$http.get("api/point/getExpressList", {params: param}).then(({data: result}) => {
          this.tableData = result.data.records;
          this.total = result.data.total;
          this.param.current = result.data.current;
        })
      }
    },
    mounted() {
      this.$http.post("api/point/checkLogin", {
        phone: 15757125092,
        password: 123
      }).then(() => {
        this.getPageList();
      });
    }

  }
</script>
