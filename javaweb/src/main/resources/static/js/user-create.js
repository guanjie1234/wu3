//创作

Vue.component("user-create",  {
    template: `
    <el-form ref="form" :model="form" label-width="600px">
  <el-form-item label="壁纸类型">
    <el-select v-model="form.wallpapertype" placeholder="请选择活动区域" style="width:500px">
      <el-option label="动漫" value="1"></el-option>
      <el-option label="风景" value="2"></el-option>
      <el-option label="动物" value="3"></el-option>
      <el-option label="科幻" value="4"></el-option>
      <el-option label="游戏" value="5"></el-option>
      <el-option label="其他" value="6"></el-option>
    </el-select>
    <div class="custom-file-upload">选择文件
<input type="file" id="fileInput" name="file" @change="onFileChange" />
</div>
  </el-form-item>
  <el-form-item label="壁纸简介">
    <el-input type="textarea" v-model="form.wallpaperprofile" style="width:500px"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button  type="primary" @click="onSubmit">立即创建</el-button>
  </el-form-item>
</el-form>

        `,
    data() {
        return {
            form: {
                userid: 1,
                wallpapertype:"选择壁纸类型",
                wallpaperprofile:'',
                file: null // 这里只是用来追踪文件，实际上不会存储在form对象中
            },
            formData: new FormData()
        };
    },
    methods: {
        onFileChange(e) {

            this.form.file = e.target.files[0];
            // 将文件添加到FormData对象中
            this.formData.append('file', this.form.file);
            console.log(this.form.userid);
        },
        onSubmit() {
            this.form.userid= getCookie("userid");
            if (this.form.userid==null){
                window.location.href = "Login.html";
            }else{
                // 添加其他表单字段到FormData对象中
                this.formData.delete('userid');
                this.formData.append('userid', this.form.userid);
                this.formData.delete('wallpapertype');
                this.formData.append('wallpapertype', this.form.wallpapertype);
                this.formData.delete('wallpaperprofile');
                this.formData.append('wallpaperprofile', this.form.wallpaperprofile);
                // 发送POST请求
                axios.post('/Wallpapers/upload', this.formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                        // 注意：虽然设置了'Content-Type'，但实际上axios会处理这个头，
                        // 所以这里设置与否通常不会有影响。
                    }
                })
                    .then(response => {
                    // 请求成功处理
                    this.$message({
                        message: '上传成功',
                        type: 'success'
                    });
                })
                    .catch(error => {
                    // 请求失败处理
                    this.$message.error('上传失败');
                });
            }}
    }
})