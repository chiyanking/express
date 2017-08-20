<template>
  <div>
    <Row>
      <Col span="15">
      <img src="../assets/login.jpg" style="width: 30%">
      </Col>
      <Col span="6">
      <Form style="margin-top: 100px;" ref="formInline" :model="formInline" :rules="ruleInline">
        <Form-item prop="phone">
          <Input type="text" v-model="formInline.phone" placeholder="Username">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
          </Input>
        </Form-item>
        <Form-item prop="password">
          <Input type="password" v-model="formInline.password" placeholder="Password">
          <Icon type="ios-locked-outline" slot="prepend"></Icon>
          </Input>
        </Form-item>
        <Form-item>
          <Button type="primary" @click="handleSubmit('formInline')">登录</Button>
        </Form-item>
      </Form>
      </Col>
      <Col span="6"></Col>
    </Row>
  </div>
</template>
<script>
  export default {
    data:()=>({
      formInline: {
        phone: '15757125092',
        password: '123456'
      },
      ruleInline: {
        phone: [
          { required: true, message: '请填写手机号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请填写密码', trigger: 'blur' },
          { type: 'string', min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
        ]
      }
    }),
    methods:{
      handleSubmit(name) {
        debugger
        const _this = this;
        _this.$refs[name].validate((valid) => {
          if (valid) {
            _this.$http.post("/api/point/checkLogin",_this.formInline).then(function (response) {
              debugger
              _this.$Message.success('提交成功!');
            }).catch(function(error){
              debugger
              _this.$Message.error(error.response.data.msg);
            })

          } else {
            this.$Message.error('表单验证失败!');
          }
        })
      }
    }
  }
</script>
