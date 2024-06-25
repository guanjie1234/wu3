//足迹
Vue.component("user-footprint", {
    template: `
<div>
 <div>
    <div class="custom-container">
        <div style="width: 300px;height: 400px;border-radius: 5px 5px 5px 5px;position: relative;" v-for="(item, index) in wallpapersList"
             :key="item.id" @click="getWallpapersdetail(item.id)">
            <img :src="toimage(item.wallpaperAddress)"
                 style=" width: 100%;height: 100%;border-radius: 5px 5px 5px 5px;">

            <img style="position:absolute; right:30px;top:170px; width:32px;height:32px"
                 :src="xihuan">
            <i style="font-size:80%;color: wheat; position:absolute; right:40px;top:210px;">{{item.love}}</i>
            <img style="position:absolute; right:30px;top:250px; width:32px;height:32px"
                 :src="shouchang" @click="Onisshouchang(item)">
            <i style="font-size:80%;color: wheat; position:absolute; right:40px;top:290px;">{{item.collect}}</i>
        </div>
    </div >
    </div>

    <div style="left:30%;margin-top:50px;margin-left:45%; width:500px ">
                <el-pagination background layout="prev, pager, next" :total="total" :page-size="pagesize" @current-change="handleCurrentChange">
                </el-pagination>
</div >
        `,
    data() {
        return {
            xihuan: "images/icon/xihuanwxz.png",
            shouchang:"images/icon/shouchangwxz.png",
            wallpapersList:[],
            total:null,
            pagesize:8,
            userid:null
        }

    },

    methods: {  toimage(image){
        if (image==null){
            return "images/icon/jiazsb.png";
        }else{
            return image;
        }

    },
        handleCurrentChange(val){
            userId= getCookie("userid");
            var _this=this;
            this.userid=userId;
            if (userId!=null){
                var  _this=this;
                $.ajax({
                    url: '/footprint/get',
                    type: 'POST',
                    contentType: 'application/json', // 设置Content-Type为application/json
                    data:JSON.stringify({ userid:_this.userid,recordsperpage:_this.pagesize, page:val}), // 将数据转换为JSON字符串value, // 将数据转换为JSON字符串
                    dataType: 'json', // 期望服务器返回的数据类型为json
                    success: function (data, status) {
                        console.error(data);
                        _this.wallpapersList=data.wallpapersList;
                        _this.total=data.total;
                    },
                    error: function (xhr, status, error) {
                        // 当请求失败时执行的回调函数
                        console.error('请求失败: ' + status, error);
                    }
                });
            }else{
                window.location.href = "Login.html";

            }
        },
        getWallpapersdetail(id){
            var params = { wallpapersid: id};
            var queryString = $.param(params);
            var urlWithParams = 'detail.html?' + queryString;

            // 跳转到新页面
            window.location.href = urlWithParams;
        }
    },
    mounted(){
        this.handleCurrentChange(1);
    }

})

