<template>
  <Modal
    v-model="visual"
    :width="700"
    title="设置快递模版">
    <Form :model="data" label-position="right" :label-width="100">
      <i-row>
        <i-col span="11">
          <FormItem label="发件人姓名">
            <i-switch v-model="data.isElectronic"/>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="发件人手机号">
          </FormItem>
        </i-col>
      </i-row>
    </Form>
    <div slot="footer">
      <Button @click="ok">确定</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </Modal>
</template>
<script>
  export default {
    data() {
      return {
        visual: false,
        data: {
          isElectronic: false,
        }
      }
    },
    methods: {
      open() {
        this.visual = true;
      },
      ok() {
        this.$http.post("api/point/updateExpress", this.express).then((result) => {
          if (result.data.errCode == 0) {
            this.visual = false;
            this.$emit("close", true);
          } else {
            this.$Message.error(result.data.msg);
          }
        })
      },
      cancel() {
        this.visual = false;
        this.$emit("close", true);
      }
    },
    mounted() {
    }
  }
</script>
