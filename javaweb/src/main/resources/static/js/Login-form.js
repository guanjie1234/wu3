Vue.component("login-form", {
    template: `
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" style="margin-left: 500px;">
   <el-form-item label="账号" prop="account">
    <el-input type="text" v-model="ruleForm.account" autocomplete="off" style="width:300px;"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="pass">
    <el-input type="password" v-model="ruleForm.pass" autocomplete="off" style="width:300px;"></el-input>
  </el-form-item>
     <el-form-item label="验证码" prop="captcha">
    <el-input type="text" v-model="ruleForm.captcha" autocomplete="off" style="width:300px;"></el-input>
  </el-form-item>
  <div @click="getCaptcha" style=" width:100px;height: 50px;margin-left:100px">
 <img :src="captchaimage" style=" width: 100%;height: 100%;">
 </div><div style="position:absolute; top:200px; left:220px;color:#ff0000">点击切换验证码</div>
  <el-form-item style="position:absolute; top:270px; ">
    <el-button type="primary" @click="submitForm('ruleForm')" style="border: 2px solid rgba(128, 128, 128, 0.8);background-color:rgba(128, 128, 128, 0.8);">提交</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
        `, data() {
        var validateAccount = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入账号'));
            } else {
                regex = /^[a-zA-Z].{7,}|.[a-zA-Z].{6,}$/;

                if (regex.test(value)) {
                    callback();

                } else {
                    callback(new Error('长度最少8位，且包含字母'));
                }

            }
        };

        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatecaptcha = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入验证码'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                account: '',
                pass: '',
                captcha:"",
            },
            captchaimage:null,
            rules: {
                account: [
                    { validator: validateAccount, trigger: 'blur' }
                ],
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                captcha:[
                    { validator: validatecaptcha, trigger: 'blur' }
                ]
            }
        };
    },
    created() {
        this.getCaptcha();
    },
    methods: {

        getCaptcha() {
            var _this = this;
            $.ajax({
                url: '/user/captcha',
                type: 'POST',
                contentType: 'application/json', // 设置Content-Type为application/json
                dataType: 'json', // 期望服务器返回的数据类型为json
                success: function (data, status) {
                    _this.captchaimage=data.image;

                },
                error: function (xhr, status, error) {
                    // 当请求失败时执行的回调函数
                    console.error('验证码获取失败: ' + status, error);
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    alert(formName);
                    this.postForm(this.ruleForm.pass);
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        openmessage(message) {
            this.$message.error(message);
        },
        postForm(fromData) {
            var passmd5= md5(this.ruleForm.pass);
           var _this=this;
            $.ajax({
                url: '/user/login',
                type: 'POST',
                contentType: 'application/json', // 设置Content-Type为application/json
                data: JSON.stringify({ account: this.ruleForm.account, password: passmd5,captcha: this.ruleForm.captcha }), // 将数据转换为JSON字符串
                dataType: 'json', // 期望服务器返回的数据类型为json
                success: function (data, status) {

                    userid= getCookie("userid");
                    if (userid!=null){
                        window.location.href = "index.html";}
                    else{
                        _this.openmessage(data.message);
                    }
                },
                error: function (xhr, status, error) {
                    // 当请求失败时执行的回调函数
                    console.error('请求失败: ' + status, error);
                }
            });
        }

    }
}
)