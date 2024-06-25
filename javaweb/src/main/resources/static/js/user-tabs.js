
Vue.component("user-tabs", {
    template: `
    <el-tabs type="border-card" style="  margin-left:200px;
 ">
  <el-tab-pane style="width:8%"><span slot="label"><img style=" width:20px;hight:20px" src="images/icon/personal.png"></img> 个人信息</span ><user-information></user-information></el-tab-pane>
  <el-tab-pane style="width:8%"><span slot="label"><img style=" width:20px;hight:20px" src="images/icon/creact.png"></img> 我的创作</span><user-create></user-create></el-tab-pane>
    <el-tab-pane style="width:8%"><span slot="label"><img style=" width:20px;hight:20px" src="images/icon/work.png"></img> 我的作品</span><user-works></user-works></el-tab-pane>
  <el-tab-pane style="width:8%"><span slot="label"><img style=" width:20px;hight:20px" src="images/icon/collect.png"></img> 我的收藏</span><user-collect></user-collect></el-tab-pane>
  <el-tab-pane style="width:8%"><span slot="label"><img style=" width:20px;hight:20px" src="images/icon/footprint.png"></img> 我的足迹</span><user-footprint></user-footprint></el-tab-pane>
</el-tabs>
        `,
    data() {
        return {

        }

    },
    methods: {
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
        }
    },

})