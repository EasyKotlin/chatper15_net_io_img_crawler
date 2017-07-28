<!doctype html>
<html>
<head>
    <meta http-equiv=content-type content=text/html;charset=utf-8>
    <meta http-equiv=X-UA-Compatible content=IE=Edge>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>我图</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
    <link href="DataTables/media/css/jquery.dataTables.css" rel="stylesheet">
    <link href="app.css" rel="stylesheet">
    <script src="DataTables/media/js/jquery.js"></script>
    <script src="DataTables/media/js/jquery.dataTables.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <script src="sloth.js"></script>
    <script src="app.js"></script>
</head>
<body>
<nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">我图</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="wotuView">图片列表 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="doCraw" target="_blank">执行抓取 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="http://www.jianshu.com/nb/12976878" target="_blank">Kotlin极简教程</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="hello" target="_blank">极简百度首页</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdown" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Dropdown</a>

                <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
                    <a class="dropdown-item" href="#">Java</a>
                    <a class="dropdown-item" href="#">Scala</a>
                    <a class="dropdown-item" href="#">Groovy</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">关于</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>
<div class="container">
    <table id="wotuTable" class="table table-striped table-responsive">
        <thead class="thead">
        <tr>
            <th width="5%">序号</th>
            <th width="8%">分类</th>
            <th width="90%">图列</th>
        </tr>
        </thead>
        <tbody>
        <#setting number_format="#">
        <#list pageResult.content as content>
        <tr>
            <td>${content.id}</td>
            <td>${content.category}</td>
            <td><a href="${content.url}" target="_blank"><img sloth-img="${content.url}"></a></td>
        </tr>
        </#list>
        </tbody>
    </table>

    <hr>
<#-- 表格服务端分页：完美简单实现 http://v4-alpha.getbootstrap.com/components/pagination/-->
    <nav aria-label="Page navigation">
        <ul class="pagination pagination-lg  justify-content-center">
        <#assign totalPages = pageResult.totalPages>
        <#assign totalElements = pageResult.totalElements>
        <#assign number = pageResult.number>
        <#assign first = pageResult.first>
        <#assign last = pageResult.last>
        <#--上一页-->
        <#if first>
            <li class="page-item">
                <a class="page-link" href="#">上一页</a>
            </li>
        <#else>
            <li class="page-item">
                <a class="page-link" href="wotuView?page=${number-1}&size=20">上一页</a>
            </li>
        </#if>

        <#--小于等于10页全部显示-->
        <#if totalPages <= 10>
            <#list 1..totalPages as pageIndex>
                <#if number == pageIndex>
                    <li class="page-item active">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                <#else>
                    <li class="page-item active">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                </#if>
            </#list>
        </#if>

        <#--大于10页：显示前5页，最后3页，中间用 ...-->
        <#if totalPages gt 10>
        <#--显示前5页-->
            <#list 1..5 as pageIndex>
                <#if number == pageIndex>
                    <li class="page-item active">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                </#if>
            </#list>

        <#--中间部分的显示 ...  number: currentPage, 区间逻辑的判断-->
            <#if number == 6 >
                <li class="page-item active">
                    <a class="page-link" href="wotuView?page=${number}&size=20">${number}</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#">...</a>
                </li>
            <#elseif number == totalPages-3>
                <li class="page-item">
                    <a class="page-link" href="#">...</a>
                </li>
                <li class="page-item active">
                    <a class="page-link" href="wotuView?page=${number}&size=20">${number}</a>
                </li>
            <#elseif number gt 6 && number lt totalPages-3>
                <li class="page-item">
                    <a class="page-link" href="#">...</a>
                </li>
                <li class="page-item active">
                    <a class="page-link" href="wotuView?page=${number}&size=20">${number}</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#">...</a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="#">...</a>
                </li>
            </#if>

        <#--显示最后3页-->
            <#list totalPages-2..totalPages as pageIndex>
                <#if number == pageIndex>
                    <li class="page-item active">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                </#if>
            </#list>
        </#if>

        <#--下一页-->
        <#if last>
            <li class="page-item">
                <a class="page-link" href="#">下一页</a>
            </li>
        <#else>
            <li class="page-item">
                <a class="page-link" href="wotuView?page=${number+1}&size=20">下一页</a>
            </li>
        </#if>
        </ul>
        <div class="center">总共 ${totalPages} 页， ${totalElements} 条记录</div>
    </nav>
    <script>
        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 38 || e && e.keyCode == 37) {//上,左

                if (${number}>
                0
            )
                {
                    // 上一页
                    location.href = "wotuView?page=${number-1}&size=20"
                }
            }
            if (e && e.keyCode == 40 || e && e.keyCode == 39) {//下,右
                // 下一页
                location.href = "wotuView?page=${number+1}&size=20"
            }
        };
    </script>

</div>
</body>
</html>
