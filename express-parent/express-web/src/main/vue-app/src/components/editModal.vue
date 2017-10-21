<template>
  <Modal
    v-model="visual"
    :width="700"
    title="快递信息编辑">
    <h3 v-if="tip" class="model-tip">未安装插件请前往<a href="http://www.lodop.net/download.html">lodop下载安装</a></h3>
    <Form :model="express" label-position="right" :label-width="100">
      <i-row>
        <i-col span="11">
          <FormItem label="发件人姓名">
            <Input v-model="express.senderName"/>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="发件人手机号">
            <Input v-model="express.senderPhone"/>
          </FormItem>
        </i-col>
      </i-row>
      <i-row>
        <i-col span="11">
          <FormItem label="发件人省市区">
            <Cascader :data="pcdOption" v-model="express.senderPCDCode" @on-change="senderPCDChange"></Cascader>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="发件人详细地址">
            <Input v-model="express.senderAddress"/>
          </FormItem>
        </i-col>
      </i-row>
      <i-row>
        <i-col span="11">
          <FormItem label="收件人姓名">
            <Input v-model="express.receiverName"/>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="收件人手机号">
            <Input v-model="express.receiverPhone"/>
          </FormItem>
        </i-col>
      </i-row>
      <i-row>
        <i-col span="11">
          <FormItem label="收件人省市区">
            <Cascader :data="pcdOption" v-model="express.receiverPCDCode" @on-change="receiverPCDChange"></Cascader>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="收件人详细地址">
            <Input v-model="express.receiverAddress"/>
          </FormItem>
        </i-col>
      </i-row>
      <i-row>
        <i-col span="11">
          <FormItem label="快递重量">
            <Input v-model="express.weight"/>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="快递费用">
            <Input v-model="express.price"/>
          </FormItem>
        </i-col>
      </i-row>
      <i-row>
        <i-col span="11">
          <FormItem label="快递公司" prop="companyId">
            <i-select v-model="express.companyId">
              <Option v-for="item in companiesOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </i-select>
          </FormItem>
        </i-col>
      </i-row>
    </Form>
    <div slot="footer">
      <Button @click="printElement" :disabled="printing">打印电子面单</Button>
      <Button @click="printElementForce" :disabled="printing">重新生成电子面单</Button>
      <Button @click="cancel">取消</Button>
    </div>
  </Modal>
</template>
<script>

  import {getLodop} from '../utils/LodopFuncs.js';

  export default {
    data() {
      return {
        printing: false,
        tip: false,
        LODOP: null,
        visual: false,
        pcdOption: [],
        companiesOptions: [],
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
      printElement() {
        this.printing = true;
        this.updateDate().then(() => {
          this.print("api/point/getElectronicSheet");
        });
//        let frame = window.open("api/point/getElectronicSheet?id=" + this.express.id);
//        frame.print();
      },
      printElementForce() {
        this.printing = true;
        this.updateDate().then(() => {
          this.print("api/point/getElectronicSheetForce");
        });
      },
      print(url) {
        this.$http.get(url, {params: {id: this.express.id}}).then(({data: result}) => {
          if (!result.error) {
            this.initPlugin();//重复调用
            if (this.LODOP) {
              this.LODOP.PRINT_INIT("打印电子面单");
              this.LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", result.msg);
              this.LODOP.PREVIEW();
              this.printing = false;
            }
          }
        });
      },
      updateDate() {
        return this.$http.post("api/point/updateExpress", this.express).then((result) => {
          return new Promise(function (resolve, reject) {
            if (result.data.errCode == 0) {
              resolve();
            } else {
              this.$Message.error(result.data.msg);
              reject();
            }
          });
        });
      },
      cancel() {
        this.visual = false;
        this.$emit("close", true);
      },
      getCompaniesOptions() {
        this.$http.get("api/point/getHasCompanies").then((({data: result}) => {
          this.companiesOptions = result.data;
        }));
      },
      initPlugin() {
        if (!this.LODOP) {
          try {
            this.LODOP = getLodop();
            this.LODOP ? this.tip = false : this.tip = true;
          } catch (err) {
            this.tip = true;
          }
        }
      }
    },
    mounted() {
      this.getCompaniesOptions();
      if (!this.pcdOption.length > 0) {
        this.$http.get("api/point/getPCDTree").then(({data: result}) => {
          this.pcdOption = result.data;
        })
      }
    }
  }
</script>
<style scoped>
  .model-tip {
    margin-bottom: 10px;
  }
</style>
