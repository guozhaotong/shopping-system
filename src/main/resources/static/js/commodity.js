function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

var vm = new Vue({
    el: '#app',
    data: {
        commodity: {},
        userInfo: undefined,
        buyerNumber: 1,
        orderInfo: null,
        shoppingCartList: [],
        orderList: [],
        sumPriceOfOrderList: "计算中...",
        sumPriceOfShoppingCart: "计算中..."
    },
    methods: {
        init: function () {
            userInfoStr = $.cookie('userInfo')
            if (userInfoStr !== undefined) {
                vm.userInfo = JSON.parse(userInfoStr)
                console.log(vm.userInfo)
            }
        },
        getCommodity: function () {
            $.ajax({
                type: "get",
                url: "/getCommodity",
                data: {commodityId: getQueryString("id")},
                success: function (data) {
                    console.log(data.data)
                    vm.commodity = data.data.commodity
                    vm.orderInfo = data.data.orderInfo
                }
            });
        },
        addCommodityToShoppingCart: function () {
            if (vm.userInfo === undefined) {
                window.location = "/login.html"
            }
            $.ajax({
                type: "post",
                url: "/api/addCommodityToShoppingCart",
                data: {buyerId: vm.userInfo.id, commodityId: vm.commodity.id, num: vm.buyerNumber},
                success: function (data) {
                    console.log(data)
                    if (data.code === 200 && data.data === true) {
                        alert("添加购物车成功")
                    } else {
                        alert("添加购物车失败")
                    }
                }
            });
        },
        deleteShoppingCartOneRecord: function (id) {
            console.log("buy all")
            $.ajax({
                type: "post",
                url: "/api/deleteShoppingCartOneRecord",
                data: {buyerId: vm.userInfo.id, commodityId: id},
                success: function (data) {
                    console.log(data)
                    if (data.code === 200) {
                        alert("删除成功")
                        vm.openShoppingCart()
                    } else {
                        alert("删除失败")
                    }
                }
            });
        },
        buyAllShoppingCart: function () {
            console.log("buy all")
            $.ajax({
                type: "post",
                url: "/api/buyAllShoppingCart",
                data: {buyerId: vm.userInfo.id},
                success: function (data) {
                    console.log(data)
                    if (data.code === 200) {
                        alert("购物成功")
                        $("#myModal").modal('hide')
                    } else {
                        alert("购物成功")
                    }
                }
            });
        },
        openShoppingCart: function () {
            $.ajax({
                type: "get",
                url: "/api/getSumPriceOfShoppingCart",
                data: {buyerId: vm.userInfo.id},
                success: function (data) {
                    console.log(data.data)
                    vm.sumPriceOfShoppingCart = data.data
                }
            });
            $.ajax({
                type: "get",
                url: "/api/getShoppingCartList",
                data: {buyerId: vm.userInfo.id},
                success: function (data) {
                    console.log(data.data)
                    vm.shoppingCartList = data.data
                    $("#myModal").modal('show')
                }
            });
        },
        openOrderList: function () {
            console.log("订单列表")
            $.ajax({
                type: "get",
                url: "/api/sumPrice",
                data: {buyerId: vm.userInfo.id},
                success: function (data) {
                    console.log(data.data)
                    vm.sumPriceOfOrderList = data.data

                }
            });
            $.ajax({
                type: "get",
                url: "/api/getOrderList",
                data: {buyerId: vm.userInfo.id},
                success: function (data) {
                    console.log(data.data)
                    vm.orderList = data.data
                    $("#finance").modal('show')
                }
            });
        },
        loginout: function () {
            // alert("123")
            console.log('用户已经退出')
            $.removeCookie('userInfo')
            vm.userInfo = undefined
            location.reload()
        }
        , timestap2DateString: function (timestamp) {
            var d = new Date(timestamp);
            var date=(d.getFullYear()) + "-" +
                (d.getMonth() + 1) + "-" +
                (d.getDate()) + " " +
                (d.getHours()) + ":" +
                (d.getMinutes()) + ":" +
                (d.getSeconds());
            // console.log(date)
            return date
        }

    }
})
vm.init()
vm.getCommodity()