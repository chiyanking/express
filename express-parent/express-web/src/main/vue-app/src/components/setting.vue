<template>
  <div>
    <div class="table-toolbar">
      <i-row>
        <i-col span="2">
          <Button type="text">打印单配置</Button>

        </i-col>
        <i-col offset="21" span="1">
          <Button @click="handleAdd">新增</Button>
        </i-col>
      </i-row>
    </div>
    <Table border :columns="columns" :data="tableData"></Table>
    <setting-modal ref="settingModalRef" @close="getPageList"/>
  </div>
</template>
<script>
  import settingModal from "./settingModel.vue";

  export default {
    components: {
      "setting-modal": settingModal
    },
    data() {
      return {
        tableData: [],
        columns: [
          {
            title: "快递名",
            align: 'center',
            key: 'companyName'
          }, {
            title: '类型',
            key: 'action',
            width: 125,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                params.row.isElectronic ? "电子面单" : "一般面单"
              ]);
            }
          }, {
            title: '模版名称',
            align: 'center',
            key: 'expModelName'
          }, {
            title: '账号',
            align: 'center',
            key: 'account'
          }, {
            title: '操作',
            key: 'action',
            width: 125,
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
                      this.handleEdit(params.index, params.row)
                    }
                  }
                }, '编辑'),
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
      getPageList() {
        let param = Object.assign({}, this.param);
        this.$http.get("api/setting/getHasCompanies", {params: param}).then(({data: result}) => {
          this.tableData = result.data;
        });
      },
      handleAdd() {
        this.$refs.settingModalRef.open();
      },
      handleEdit(index, row) {
        this.$refs.settingModalRef.open(row);
      },
    },
    mounted() {
      this.getPageList();
    }
  }
</script>
<style>
  .table-toolbar {
    padding:10px;
  }
</style>
