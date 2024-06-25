Vue.component("search-container", {
    template: `
     <div class="search-container">
  <input type="text" id="search-input" v-model="text" placeholder="搜索...">
  <button type="button" id="search-button" @click="onsearch">搜索</button>
</div>
        `,
    data() {
        return {
            text: "",
            href: 'Login.html',
        }
    },
    methods:{
        onsearch(){
            var params = { searchvalue: this.text};
            var queryString = $.param(params);
            var urlWithParams = 'search.html?' + queryString;
            window.location.href = urlWithParams;
        }
    },
})