function addFavorite(id) {
    $.ajax({
        url: 'addFavorite',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (resp) {
            // alert(JSON.stringify(resp))
            $('#meituFavoriteTable').bootstrapTable('refresh')
            $('#meituTable').bootstrapTable('refresh')
        },
        error: function (msg) {
            alert(JSON.stringify(msg))
        }
    })
}

function deleteById(id) {
    $.ajax({
        url: 'delete',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (resp) {
            // alert(JSON.stringify(resp))
            $('#meituFavoriteTable').bootstrapTable('refresh')
            $('#meituTable').bootstrapTable('refresh')
        },
        error: function (msg) {
            alert(JSON.stringify(msg))
        }
    })
}


function downloadImage(src) {
    var $a = $("<a></a>").attr("href", src).attr("download", "meitu.png");
    $a[0].click();
}

$(function () {
    $('#baiduBtn').on('click', function () {
        var wd = $('#wd').val()
        window.open("https://www.baidu.com/s?wd=" + wd)
    })
})
