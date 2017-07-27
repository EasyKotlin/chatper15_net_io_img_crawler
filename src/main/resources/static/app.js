$(function () {
    // 默认加载body体全部sloth图片
    var sloth = new Sloth()
    // 默认滚动条加载
    sloth.init()
    // 手动加载
    // sloth.load();

    $('#baiduBtn').on('click', function () {
        var wd = $('#wd').val()
        window.open("https://www.baidu.com/s?wd=" + wd)
    })

    // $('#wotuTable').on('order.dt',
    //     function () {
    //         console.log('排序')
    //         sloth.init()
    //     }).on('search.dt',
    //     function () {
    //         console.log('搜索')
    //         sloth.init()
    //     }).on('page.dt',
    //     function () {
    //         console.log('翻页')
    //         sloth.init()
    //     }).dataTable() // 前端分页

})
