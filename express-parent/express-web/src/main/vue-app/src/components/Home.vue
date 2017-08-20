<style scoped>
  .layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    line-height: 45px;
  }

  .layout-logo {
    width: 100px;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
  }

  .layout-header {
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .1);
  }

  .layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }

  .layout-ceiling {
    background: #464c5b;
    padding: 10px 0;
    overflow: hidden;
  }

  .layout-ceiling-main {
    float: right;
    margin-right: 15px;
  }

  .layout-ceiling-main a {
    color: #9ba7b5;
  }
</style>
<template>
  <div class="layout">
    <div class="layout-ceiling">
      <div class="layout-ceiling-main">
        <a href="#">注册登录</a> |
        <a href="#">帮助中心</a> |
        <a href="#">安全中心</a> |
        <a href="#">服务大厅</a>
      </div>
    </div>
    <!--<div class="layout-header">-->
    <!--<div class="layout-logo"></div>-->
    <!--</div>-->
    <div>
      <Row style="padding: 20px;">
        <Col span="24">
        快递公司<Select v-model="search.companyId" style="width:150px">
        <Option v-for="item in companies" :value="item.id" :key="item.id">{{ item.name }}</Option>
      </Select>
        寄件人<Input v-model="search.senderName" placeholder="" style="width: 150px;"></Input>
        快递单号<Input v-model="search.expCode" placeholder="" style="width: 150px;"></Input>
        开始日期
        <Date-picker type="date" :options="search.startDate" placeholder="选择日期" style="width: 150px"></Date-picker>
        截止日期
        <Date-picker type="date" :options="search.endDate" placeholder="选择日期" style="width: 150px"></Date-picker>
        <Button type="info" @click="query">查询</Button>
        </Col>
      </Row>
      <Table border :columns="columns7" :data="data6"></Table>
      <Page :total="100" show-sizer></Page>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        columns7: [
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
        data6: [
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
    methods: {
      show(index) {
        this.$Modal.info({
          title: '用户信息',
          content: `姓名：${this.data6[index].name}<br>年龄：${this.data6[index].age}<br>地址：${this.data6[index].address}`
        })
      },
      remove(index) {
        this.data6.splice(index, 1);
      },
      query(){
        debugger
      }
    }
  }
</script>
