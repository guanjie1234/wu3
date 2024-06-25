Vue.component("index-wallpapers", {
    template: `
 <div>
 <div class="wallpapers-type-container-css">
        <div class="wallpapers-item-container-css" @click="handleClick('1')">动漫</div>
        <div class="wallpapers-item-container-css" @click="handleClick('2')">风景</div>
        <div class="wallpapers-item-container-css" @click="handleClick('3')">动物</div>
        <div class="wallpapers-item-container-css" @click="handleClick('4')">科幻</div>
        <div class="wallpapers-item-container-css" @click="handleClick('5')">游戏</div>
    </div>
    <div class="custom-container" style=" padding-top: 250px;" >
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
        <div style="background-color: #FFE4B5; position:relative;left:30%;margin-top:50px;top:1000px ">
                <el-pagination background layout="prev, pager, next" :total="total" :page-size="pagesize">
                </el-pagination>
    </div>
</div >
        `,
    data() {
        return {
            xihuan: "images/icon/xihuan.png",
            shouchang:"images/icon/shouchang.png",
            wallpapersList:[],
            total:1000,
            pagesize:20,
        };
    },
    methods: { toimage(image){
        if (image==null){
            return "images/icon/jiazsb.png";
        }else{
            return image;
        }

    },
        handleClick(wallpaperstype){
            var  _this=this;
            $.ajax({
                url: '/Wallpapers/type',
                type: 'POST',
                contentType: 'application/json', // 设置Content-Type为application/json
                data:wallpaperstype, // 将数据转换为JSON字符串
                dataType: 'json', // 期望服务器返回的数据类型为json
                success: function (data, status) {
                    _this.wallpapersList=data.wallpapersList;
                },
                error: function (xhr, status, error) {
                    // 当请求失败时执行的回调函数
                    console.error('请求失败: ' + status, error);
                }
            });
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
        this.handleClick("1");
    }

})