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
        <FormItem label="发件人姓名">
          <Input v-model="express.senderName"/>
        </FormItem>
        </Col>
        <Col span="12">
        <FormItem label="发件人手机号">
          <Input v-model="express.senderPhone"/>
        </FormItem>
        </Col>
      </Row>

      <Row>
        <Col span="11">
        <FormItem label="发件人省市区">
          <Cascader :data="pcdOption" v-model="express.senderPCDCode" @on-change="senderPCDChange"></Cascader>
        </FormItem>
        </Col>
        <Col span="12">
        <FormItem label="发件人详细地址">
          <Input v-model="express.senderAddress"/>
        </FormItem>
        </Col>
      </Row>
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
        <FormItem label="收件人省市区">
          <Cascader :data="pcdOption" v-model="express.receiverPCDCode" @on-change="receiverPCDChange"></Cascader>
        </FormItem>
        </Col>
        <Col span="12">
        <FormItem label="收件人详细地址">
          <Input v-model="express.receiverAddress"/>
        </FormItem>
        </Col>
      </Row>

      <Row>
        <Col span="11">
        <FormItem label="快递重量">
          <Input v-model="express.weight"/>
        </FormItem>
        </Col>
        <Col span="12">
        <FormItem label="收件人详细地址">
          <Input v-model="express.receiverAddress"/>
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
        pcdOption: [],
        express: {}
      }
    },
    methods: {
      open(row) {
        console.log(row);
        row.senderPCDCode = [row.senderProvinceCode, row.senderCityCode, row.senderDistrictCode];
        row.receiverPCDCode = [row.receiverProvinceCode, row.receiverCityCode, row.receiverDistrictCode];
        this.express = row;
        this.visual = true;
      },
      senderPCDChange(value1, value2) {
        let express = this.express;
        let province = value2[0];
        express.senderProvinceCode = province.value;
        express.senderProvinceName = province.label;
        let city = value2[1];
        express.senderCityCode = city.value;
        express.senderCityName = city.label;
        let district = value2[2];
        express.senderDistrictCode = district.value;
        express.senderDistrictName = district.label;
      },
      receiverPCDChange(value1, value2) {
        let express = this.express;
        let province = value2[0];
        express.receiverProvinceCode = province.value;
        express.receiverProvinceName = province.label;
        let city = value2[1];
        express.receiverCityCode = city.value;
        express.receiverCityName = city.label;
        let district = value2[2];
        express.receiverDistrictCode = district.value;
        express.receiverDistrictName = district.label;
      },
      ok() {
        this.$Message.info('点击了确定');
        this.visual = false;
      },
      cancel() {
        this.$Message.info('点击了取消');
        this.visual = false;
      }
    },
    mounted() {
      if (!this.pcdOption.length > 0) {
        this.$http.get("api/point/getPCDTree").then(({data: result}) => {
          this.pcdOption = result.data;
        })
      }
    }

  }
</script>
