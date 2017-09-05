<template>
  <Modal
    v-model="visual"
    :width="700"
    title="快递信息编辑"
    @on-ok="ok"
    @on-cancel="cancel">
    <Form :model="express" label-position="right" :label-width="100">
      <Row>
        <Col span="11">
        <FormItem label="收件人姓名">
          <Input v-model="express.receiverName"/>
        </FormItem>
        </Col>
        <Col span="12">
        <FormItem label="收件人手机号">
          <Input v-model="express.receiverPhone"/>
        </FormItem>
        </Col>
      </Row>


      <Row>
        <Col span="11">
        <FormItem label="收件人省">
          <Input v-model="express.receiverProvinceName"/>
        </FormItem>
        </Col>
        <Col span="12">
        <FormItem label="收件人省">
          <Input v-model="express.receiverProvinceName"/>
        </FormItem>
        </Col>
      </Row>


    </Form>
  </Modal>
</template>
<script>
  export default {
    data() {
      return {
        visual: false,
        express: {}
      }
    },
    methods: {
      open(row) {
        console.log(row);
        this.express = row;
        this.visual = true;
      },
      ok() {
        this.$Message.info('点击了确定');
        this.visual = false;
      },
      cancel() {
        this.$Message.info('点击了取消');
        this.visual = false;
      }
    }
  }
</script>
