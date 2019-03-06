function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
var vm = new Vue({
    el: '#app',
    data: {
        commodity:{}
    },
    methods: {
        getCommodityList: function () {
            $.ajax({
                type: "get",
                url: "/getCommodity",
                data: {commodityId:getQueryString("id")},
                success: function (data) {
                    console.log(data.data)
                    vm.commodity = data.data
                }
            });
        }
    }
})

vm.getCommodityList()