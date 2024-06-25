
//未登录
Vue.component("notlogged-user", {
    template: `
      <a :href="href" style="text-decoration: none; "target="_blank">
     <div class="block"  class="logged-user-css">
     {{text}} </div>
     </a>
        `,
    data() {
        return {
            text: "请先登录",
            href: 'Login.html',
        }
    },
})
//注册
Vue.component("register-user", {
    template: `
      <a :href="href" style="text-decoration: none; "target="_blank">
     <div class="block"  class="logged-user-css">
     {{text}} </div>
     </a>
        `,
    data() {
        return {
            text: "点击注册",
            href: 'register.html',
        }
    },
})
//已登录
Vue.component("logged-user", {

    template: `
    <div class="block" style="float:right;padding-right:50px;" @click="gethomepage(userid)">
      <el-container>
        <el-aside width="60px"><a href="Homepage.html"><el-avatar shape="circle" :size="50" fit="fill" :src="cavatar" style="position: relative;top: 50%;transform: translateY(-50%);"></el-avatar></a></el-aside>
        <el-main ><a href="Homepage.html" style="text-decoration: none;"><span class="title" style="color: rgba(128, 128, 128, 0.8);font-size:26px;">{{ cnikename }}</span></a></el-main>
      </el-container>
    </div>
        `,
    props: ['cuserid','cnikename','cavatar'],
    watch:{
        'cuserid':function(val){
            this.userid=val;
        },
        'cnikename':function(val){
            this.nikename=val;
        },
        'cavatar':function(val){
            this.avatar=val;
        },
    },
    data() {
        return {
            userid:this.cuserid,
            nikename:this.cnikename,
            avatar:this.cavatar
        };
    },

    mounted() {console.log (this.nikename);},
    methods: {
        gethomepage(userid) {
            console.log(this.cnikename);
        }
    },

})
//登录状态切换
Vue.component("loginstatus-user", {
    template: `
    <div style="float:right;padding-right:50px;">
    <logged-user v-show="fislogins" :cuserid="fuserid" :cnikename="fnikename" :cavatar="favatar" ></logged-user>
    <register-user v-show="!fislogins" :cuserid="fuserid"></register-user>
    <notlogged-user v-show="!fislogins" :cuserid="fuserid"></notlogged-user>

  </div>
        `,
    data() {
        return {
            fislogins:null,
            fuserid:null,
            fnikename:null,
            favatar:null,
        };
    },
    mounted(){
        userId= getCookie("userid");
        var _this=this;
        this.userid=userId;
        if(this.userid==null){
            this.isLogin=false;
            window.location.href = "Login.html";
            console.error( this.userid);

        }else{
            $.ajax({
                url: '/user/basedata',
                type: 'POST',
                contentType: 'application/json', // 设置Content-Type为application/json
                data: JSON.stringify({ "userid": userId }), // 将数据转换为JSON字符串
                dataType: 'json', // 期望服务器返回的数据类型为json
                success: function (data, status) {
                    console.log(data);
                    _this.fuserid=data.userdata.id;
                    _this.fnikename=data.userdata.nikename;
                    _this.favatar=data.userdata.avatar;
                    _this.fislogins=true;
                },
                error: function (xhr, status, error) {
                    // 当请求失败时执行的回调函数
                    console.error('请求失败: ' + status, error);
                }
            });
        }


    }
})