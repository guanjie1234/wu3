Vue.component("wallpapers-pagination", {
    template: `
    <div style=" position:relative;left:30%;">
                <el-pagination background layout="prev, pager, next" :total="total" :page-size="pagesize">
                </el-pagination>
            </div>
        `,
    data() {
        return {
            total:1000,
            pagesize:20
        }

    },
    methods: {
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
        }
    },mounted() {
        console.log(`当前页:`);

    }

})