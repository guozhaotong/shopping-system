var vm = new Vue({
    el: '#app',
    data: {
        allKvList: [],
        kvList: [],
        picPath: "",
        userInfo: undefined,
        onlyLookNoBuy:false,
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
        getCommodityList: function () {
            getParamData = {}
            if (vm.userInfo !== undefined) {
                getParamData.userName = vm.userInfo.name
            }
            $.ajax({
                type: "get",
                url: "/getCommodityList",
                data: getParamData,
                success: function (data) {
                    console.log(data.data)
                    vm.allKvList = data.data
                    vm.kvList = data.data
                }
            });
        },
        onlyLookNoBuyFunction: function () {
            console.log("onlyLookNoBuy")
            temp = []
            for (index in vm.allKvList){
                console.log(vm.allKvList[index])
                if (vm.allKvList[index].v===0){
                    temp.push(vm.allKvList[index])
                }
            }
            vm.kvList=temp
            vm.onlyLookNoBuy=true
            
        },
        lookAll: function () {
            console.log("lookAllFunction")
            vm.kvList=vm.allKvList
            vm.onlyLookNoBuy=false
        },
        loginout: function () {
            // alert("123")
            console.log('用户已经退出')
            $.removeCookie('userInfo')
            vm.userInfo = undefined
            location.reload()
        },
        deleteCommodity: function (commodity) {
            console.log(commodity)
            $.ajax({
                type: "post",
                url: "/api/deleteCommodity",
                data: commodity,
                success: function (data) {
                    console.log(data.data)
                    vm.getCommodityList()
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
                        alert("购物失败")
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
vm.getCommodityList()