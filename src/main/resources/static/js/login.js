$.cookie('the_cookie', 'the_value', {expires: 7});
var vm = new Vue({
    el: '#app',
    data: {
        userName: "seller",
        password: "relles",
        user:{}
    },
    methods: {
        getCommodityList: function () {
            console.log()
            $.ajax({
                type: "post",
                url: "/loginapi",
                data: {userName: this.userName, passwordMd: md5(vm.password)},
                success: function (data) {
                    console.log(data.data)
                    vm.user = data.data
                    // console.log(vm.kvList)
                }
            });
        }
    }
})

