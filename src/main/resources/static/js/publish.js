function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

var vm = new Vue({
    el: '#app',
    data: {
        userInfo: undefined,
        commodity: undefined,
        title: "",
        brief: "",
        picAddr: "",
        intro: "",
        price: "",
    },
    methods: {
        init: function () {
            userInfoStr = $.cookie('userInfo')
            if (userInfoStr !== undefined) {
                vm.userInfo = JSON.parse(userInfoStr)
                console.log(vm.userInfo)
                if (vm.userInfo.identity === 'seller') {
                    console.log("是编辑者")
                    $("#btn_submit_img").click(function () {
                        if ($("input[name='imgFile']").val() === "") {
                            alert("没有需要上传的图片")
                            return
                        }
                        console.log("开始上传图片")
                        $("#form_img").ajaxSubmit({
                            success: function (data) {
                                console.log(data)
                                vm.picAddr = data.data
                            },
                            error: function (error) {
                                console.log(error)
                            },
                            url: '/api/updatePic', /*设置post提交到的页面*/
                            type: "post", /*设置表单以post方法提交*/
                            dataType: "json" /*设置返回值类型为文本*/
                        });
                    });
                    return
                }
            }
            alert("您没有权限编辑")
            window.location = "index.html"
        },
        getCommodity: function () {
            if (getQueryString("commodityId") !== null) {
                $.ajax({
                    type: "get",
                    url: "/getCommodity",
                    data: {commodityId: getQueryString("commodityId")},
                    success: function (data) {
                        console.log(data.data)
                        vm.commodity = data.data.commodity
                        vm.title = vm.commodity.title
                        vm.brief = vm.commodity.brief
                        vm.intro = vm.commodity.intro
                        vm.price = vm.commodity.price
                        vm.picAddr = vm.commodity.picAddr
                    }
                });
            }

        },
        publish: function () {
            if (vm.commodity === undefined) {
                console.log("新建商品")
                $.ajax({
                    type: "post",
                    url: "/api/addNewCommodity",
                    data: {
                        title: vm.title,
                        brief: vm.brief,
                        intro: vm.intro,
                        price: vm.price,
                        picAddr: vm.picAddr,
                        sellerId: vm.userInfo.id
                    },
                    success: function (data) {
                        console.log(data.data)
                        alert("添加成功！跳转到首页查看")
                        window.location="/index.html"
                    }
                });
            } else {
                console.log("更新商品：" + vm.commodity.title)
                $.ajax({
                    type: "post",
                    url: "/api/updateCommodity",
                    data: {
                        id: vm.commodity.id,
                        title: vm.title,
                        brief: vm.brief,
                        intro: vm.intro,
                        price: vm.price,
                        picAddr: vm.picAddr,
                        sellerId: vm.userInfo.id
                    },
                    success: function (data) {
                        console.log(data.data)
                        alert("更新成功！跳转查看")
                        window.location="/commodity.html?id="+vm.commodity.id
                    }
                });
            }
        },
        loginout: function () {
            // alert("123")
            console.log('用户已经退出')
            $.removeCookie('userInfo')
            vm.userInfo = undefined
            window.location = "/index.html"
        }
    }
})

vm.init()
vm.getCommodity()