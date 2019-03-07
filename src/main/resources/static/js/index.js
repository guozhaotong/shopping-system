var vm = new Vue({
    el: '#app',
    data: {
        kvList: {},
        picPath:""
    },
    methods: {
        getCommodityList: function () {
            $.ajax({
                type: "get",
                url: "/getCommodityList",
                data: {},
                success: function (data) {
                    // console.log(data.data)
                    vm.kvList = data.data
                    // console.log(vm.kvList)
                }
            });
        }
    }
})

vm.getCommodityList()