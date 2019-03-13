var vm = new Vue({
    el: '#app',
    data: {
        userName: "",
        password: "",
    },
    methods: {
        getCommodityList: function () {
            console.log(vm.userName)
            console.log(vm.password)
            $.ajax({
                type: "post",
                url: "/loginapi",
                data: {userName: vm.userName, passwordMd: md5(vm.password)},
                success: function (data) {
                    console.log(data.data)
                    if (data.code === 200) {
                        var str = JSON.stringify(data.data);
                        $.cookie('userInfo', str);
                        if(document.referrer){
                            console.log(document.referrer)
                            window.location.href = document.referrer;
                        }else{
                            console.log("么有获取到 referrer,返回主页")
                            window.location.href = "/index.html"
                        }
                    }else{
                        alert("用户名密码不正确!")
                    }
                }
            });
        }
    }
})

