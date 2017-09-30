<template>
  <Card>
    <div slot="title">配置界面</div>
    <Table border :columns="columns" :data="tableData"></Table>
  </Card>
</template>
<script>
  export default {
    data() {
      return {
        tableData: [],
        columns: [
          {
            title: "快递名",
            key: 'expNo'
          },
          {
            title: '模版名称',
            key: 'date'
          }
        ]
      }
    }
  }
</script>
