//用户信息
Vue.component("user-information", {
    template: `
<div class="grid-container">
  <div class="grid-item"><i>灵动号:</i></div>
  <div class="grid-item">{{padNumberWithZeros(userid)}}</div>
  <div class="grid-item"><i>昵称:</i></div>
  <div class="grid-item">{{nikename}}</div>
  <div class="grid-item"><i>头像:</i></div>
  <div class="grid-item"><el-aside width="80px"><el-avatar shape="circle" :size="40" fit="fill" :src="avatar" style="position: relative;top: 25%;transform: translateY(-50%);"></el-avatar></el-aside></div>
  <div class="grid-item"><i>性别:</i></div>
  <div class="grid-item"><img :src="getsexicon(sex)" style="width:30px;hight:30px"></img></div>
  <div class="grid-item"><i>个性化签名:</i></div>
  <div class="grid-item">10</div>
</div>
        `,
    data() {
        return {
            isLogin:false,
            userid:null,
            nikename:null,
            avatar:null,
            sex:1,
            personaprofile:null

        }

    },
    methods: {
        getsexicon(sex){
            if (sex==null){
                return "images/icon/sexUnknown.png"
            }if (sex==1){
                return "images/icon/man.png"
            }if (sex==2){
                return "images/icon/woman.png"
            }

        },
        padNumberWithZeros(num) {
            // 确保输入是数字
            if (typeof num !== 'number' || isNaN(num)) {
                throw new Error('Input must be a number');
            }

            // 将数字转换为字符串
            var numStr = num.toString();

            // 如果字符串长度小于8，则在其前面用0补齐
            while (numStr.length < 8) {
                numStr = '0' + numStr;
            }

            // 返回补齐后的字符串
            return numStr;
        }
    },

    mounted(){
        userId= getCookie("userid");
        var _this=this;
        this.userid=userId;
        if(this.userid==null){
            this.isLogin=false;
            console.error( this.userid);

        }else{
            $.ajax({
                url: '/user/basedata',
                type: 'POST',
                contentType: 'application/json', // 设置Content-Type为application/json
                data: JSON.stringify({ "userid": userId }), // 将数据转换为JSON字符串
                dataType: 'json', // 期望服务器返回的数据类型为json
                success: function (data, status) {
                    // 请求成功后的回调函数
                    console.log(data); // 打印返回的数据
                    _this.userid=data.userdata.id;
                    _this.nikename=data.userdata.nikename;
                    _this.avatar=data.userdata.avatar;
                    _this.sex=data.userdata.sex;
                    _this.personaprofile=data.userdata.personaProfile;
                    _this.islogins=true;

                },
                error: function (xhr, status, error) {
                    // 当请求失败时执行的回调函数
                    console.error('请求失败: ' + status, error);
                }
            });
        }


    }

})
