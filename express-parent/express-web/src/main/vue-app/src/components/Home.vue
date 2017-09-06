<style scoped>
  .table-pagination {
    margin: 15px 0 15px 0;
    float: right;
  }
</style>
<template>
  <div class="layout-content-main">
    <Form :label-width="80" inline>
      <FormItem label="快递公司">
        <Select v-model="param.companyId" style="width:110px">
          <Option v-for="item in companies" :value="item.value" :key="item.value">{{ item.label }}</Option>
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
        <DatePicker v-model="param.date" type="datetimerange" format="yyyy-MM-dd" placeholder="选择时间"></DatePicker>
      </FormItem>
      <FormItem>
        <Button type="info" @click="query">查询</Button>
      </FormItem>
    </Form>
    <Table border :columns="columns" :data="tableData"></Table>
    <Page :total="total" size="small" :page-size="param.size" :current="param.current"
          @on-change="pageChange"
          class="table-pagination"></Page>

    <edit-modal ref="editModalRef"/>
  </div>
</template>
<script>
  import editModal from "./editModal";

  export default {
    data() {
      return {
        total: 0,
        tableData: [],
        expressStatus: [],
        param: {
          current: 1,
          size: 20,
          name: null,
          phone: null,
          expCode: null,
          pointId: null,
          date: null,
          startDate: null,
          endDate: null,
          status: null,
        },
        companies: [{value: 0, label: '全部'}],

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
            title: '状态',
            key: 'status',
            render: (h, {row}) => {
              let statusName = "", expressStatus = this.expressStatus;
              for (let i = 0; i < expressStatus.length; i++) {
                let item = expressStatus[i];
                if (item.value == row.status) {
                  statusName = item.label;
                  break;
                }
              }
              return h("div", [statusName]);
            }
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
            width: 115,
            key: 'senderPhone'
          },
          {
            title: '发件人地址',
            width: 160,
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
            width: 115,
            key: 'receiverPhone'
          }, {
            title: '收件人地址',
            width: 160,
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
            width: 170,
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
                      this.show(params.index, params.row)
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
                      this.print(params.row)
                    }
                  }
                }, "打印"),
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.remove(params.index, params.row)
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ]
      }
    },
    methods: {
      pageChange(page) {
        this.param.current = page;
        this.getPageList();
      },
      show(index, row) {
        this.$refs.editModalRef.open(row);
      },
      remove(index, row) {
        this.$http.post("api/point/deleteExpress", {id: row.id}).then(({data: result}) => {
          if (result.errCode == 0) {
            this.tableData.splice(index, 1);
          } else {
            this.$Notice.error({
              title: '错误',
              desc: result.msg,
            });
          }
        });
      },
      print(row) {
        this.$http.post("api/point/printExpress", row).then((result) => {
          debugger;
        });
      },
      query() {
        this.getPageList();
      },
      getPageList() {
        let param = Object.assign({}, this.param);
        if (param.date && param.date.length > 0) {
          param.startDate = param.date[0] || null;
          param.endDate = param.date[1] || null;
        }
        delete param.date;
        this.$http.get("api/point/getExpressList", {params: param}).then(({data: result}) => {
          this.tableData = result.data.records;
          this.total = result.data.total;
          this.param.current = result.data.current;
        });
      }
    },
    mounted() {
      this.$http.get("api/point/getHasCompanies").then(({data: result}) => {
        this.companies = this.companies.concat(result.data);
      });
      this.$http.get("api/point/getExpressStatus").then(({data: result}) => {
        this.expressStatus = result.data;
      });
      this.getPageList();
    },
    components: {
      "edit-modal": editModal
    }
  }
</script>
