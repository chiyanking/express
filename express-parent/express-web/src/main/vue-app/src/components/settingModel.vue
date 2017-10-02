<template>
  <Modal
    v-model="visual"
    :width="700"
    title="设置快递模版">
    <Form :model="data" ref="modelRef" label-position="right" :label-width="100">
      <i-row>
        <i-col span="11">
          <FormItem label="是否电子面单" prop="isElectronic">
            <Switch v-model="data.isElectronic"/>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem prop="id">
          </FormItem>
        </i-col>
      </i-row>
      <i-row>
        <i-col span="11">
          <FormItem label="快递公司" prop="companyId">
            <i-select v-model="data.companyId" @on-change="onCompanyChange">
              <Option v-for="item in companiesOptions" :value="item.value" :key="item.value">{{ item.label }}</Option>
            </i-select>
          </FormItem>
        </i-col>
        <i-col span="12" v-if="!data.isElectronic">
          <FormItem label="模版" prop="expModelId">
            <i-select v-model="data.expModelId">
              <Option v-for="item in expModelOptions" :value="item.id" :key="item.id">{{ item.expModelName }}</Option>
            </i-select>
          </FormItem>
        </i-col>
      </i-row>
      <i-row v-if="data.isElectronic">
        <i-col span="11">
          <FormItem label="账号" prop="account">
            <Input v-model="data.account"/>
          </FormItem>
        </i-col>
        <i-col span="12">
          <FormItem label="密码" prop="password">
            <Input v-model="data.password"/>
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
    components: {},
    data() {
      return {
        visual: false,
        companiesOptions: [],
        expModelOptions: [],
        data: {
          id: null,
          isElectronic: false,
          account: null,
          password: null,
          companyId: null,
          expModelId: null
        }
      }
    },
    methods: {
      open(row) {
        this.data = Object.assign(this.data, row);
        this.visual = true;
      },
      ok() {
        this.$http.post("api/setting/bindPointModel", this.data).then(({data: result}) => {
          if (result.errCode == 0) {
            this.visual = false;
            this.$emit("close", true);
          } else {
            this.$Message.error(result.msg);
          }
          this.$refs.modelRef.resetFields();
        })
      },
      cancel() {
        this.visual = false;
        this.$emit("close", true);
        this.$refs.modelRef.resetFields();
      },
      getCompaniesOptions() {
        this.$http.get("api/setting/getAllCompanies").then((({data: result}) => {
          this.companiesOptions = result.data;
        }));
      },
      onCompanyChange() {
        this.$http.get("api/setting/getModels", {params: {companyId: this.data.companyId}}).then(({data: result}) => {
          this.expModelOptions = result.data;
        })
      }
    },
    mounted() {
      this.getCompaniesOptions();
    }
  }
</script>
