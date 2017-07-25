$(function () {

    // 默认加载body体全部sloth图片
    var sloth = new Sloth()
    // 默认滚动条加载
    sloth.init()
    // 手动加载
    // sloth.load();

    $('#wotuTable').on('order.dt',
        function () {
            console.log('排序')
        }).on('search.dt',
        function () {
            console.log('搜索')
        }).on('page.dt',
        function () {
            console.log('翻页')
            sloth.init()
        }).DataTable()

    $('#baiduBtn').on('click', function () {
        var wd = $('#wd').val()
        window.open("https://www.baidu.com/s?wd=" + wd)
    })

})
