Vue.component("wallpapers-item", {
    template: `
    <div>
        <div class="custom-container" >
         <div style="width: 300px;height: 400px;border-radius: 5px 5px 5px 5px;position: relative;" v-for="(item, index) in items"
        :key="item.id"  >
            <img src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                 style=" width: 100%;height: 100%;border-radius: 5px 5px 5px 5px;">

            <img style="position:absolute; right:30px;top:170px; width:32px;height:32px"
                 :src="isxihuan(item.isxh)" @click="Onisxihuan(item)"></img>
            <i style="font-size:80%;color: wheat; position:absolute; right:40px;top:210px;">{{item.xhvalue}}</i>
            <img style="position:absolute; right:30px;top:250px; width:32px;height:32px"
                             :src="isshouchang(item.issc)" @click="Onisshouchang(item)"></img>
            <i style="font-size:80%;color: wheat; position:absolute; right:40px;top:290px;">{{item.scvalue}}</i>
        </div>
        </div >
        <div style="background-color: #FFE4B5; position:relative;left:30%;margin-top:50px">
                <el-pagination background layout="prev, pager, next" :total="total" :page-size="pagesize">
                </el-pagination>
            </div>
        </div >
        `,
    data() {
        return {total:1000,
            pagesize:20,
            showComponentA: false,
            items:[{id:1,isxh:1,xhvalue:10,issc:1,scvalue:10},{id:2,isxh:0,xhvalue:10,issc:1,scvalue:10},{id:3,isxh:1,xhvalue:10,issc:0,scvalue:10}],

        };
    },
    methods: {
        isxihuan(isxh) {
            if(isxh){
                return "images/icon/xihuanxz.png"
            }else{
                return "images/icon/xihuanwxz.png"
            }
        },isshouchang(issc) {
            if(issc){
                return "images/icon/shouchangxz.png"
            }else{
                return "images/icon/shouchangwxz.png"
            }
        },
        Onisxihuan(item){
            item.isxh=!item.isxh;
            if (item.isxh){
                item.xhvalue= item.xhvalue+1;}else{
                item.xhvalue= item.xhvalue-1;}
            this.isxihuan(item.isxh);
        },
        Onisshouchang(item){
            item.issc=!item.issc;
            if (item.issc){
                item.scvalue= item.scvalue+1;
                addurl="/collect/add";
                this.collectwallpapers(addurl,item.id);
            }else{
                item.scvalue= item.scvalue-1;
                this.isshouchang(item.isxh);
                removeurl="/collect/remove";
                this.collectwallpapers(removeurl,item.id);
            }
        },
        collectwallpapers(url,wallpapersid) {
            userid= getCookie("userid");
            if (userid==null){
                window.location.href = "Login.html";
            }else{
                $.ajax({
                    url:url,
                    type: 'POST',
                    contentType: 'application/json', // 设置Content-Type为application/json
                    data: JSON.stringify({ userid: userid,wallpapersid:wallpapersid}), // 将数据转换为JSON字符串
                    dataType: 'json', // 期望服务器返回的数据类型为json
                    success: function (data, status) {
                        // 请求成功后的回调函数
                        console.log(data); // 打印返回的数据
                        console.log(status); // 打印请求状态（例如："success"）
                        //登陆成功后跳转到首页
                        window.location.href = "index.html";
                        console.log(sessionStorage.getItem("userdata"));
                    },
                    error: function (xhr, status, error) {
                        // 当请求失败时执行的回调函数
                        console.error('请求失败: ' + status, error);
                    }
                });

            }}




    },
})