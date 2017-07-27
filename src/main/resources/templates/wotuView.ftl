<!doctype html>
<html>
<head>
    <meta http-equiv=content-type content=text/html;charset=utf-8>
    <meta http-equiv=X-UA-Compatible content=IE=Edge>
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
<div class="container ipad">
    <table id="wotuTable" class="table">
        <thead>
        <tr>
            <th width="5%">序号</th>
            <th width="5%">分类</th>
            <th width="90%">图列</th>
        </tr>
        </thead>
        <tbody>
        <#list pageResult.content as content>
            <#setting number_format="#">
        <tr>
            <td width="5%">${content.id}</td>
            <td width="5%">${content.category}</td>
            <td width="90%"><a href="${content.url}" target="_blank"><img sloth-img="${content.url}"></a></td>
        </tr>
        </#list>
        </tbody>
    </table>

    <hr>
<#--http://v4-alpha.getbootstrap.com/components/pagination/-->
    <nav aria-label="Page navigation">
        <ul class="pagination pagination-lg  justify-content-center">
        <#assign totalPages = pageResult.totalPages>
        <#assign totalElements = pageResult.totalElements>
        <#assign number = pageResult.number>
        <#assign first = pageResult.first>
        <#assign last = pageResult.last>
        <#if first>
            <li class="page-item">
                <a class="page-link" href="#">Previous</a>
            </li>
        <#else>
            <li class="page-item">
                <a class="page-link" href="wotuView?page=${number-1}&size=20">Previous</a>
            </li>
        </#if>

        <#list 1..totalPages-1 as pageIndex>
            <#if pageIndex < 5>
                <#if number == pageIndex>
                    <li class="page-item active">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                <#else>
                    <li class="page-item">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                </#if>
            </#if>

            <#if 5 <= pageIndex >
                <#if pageIndex==number >
                    <li class="page-item active">
                        <a class="page-link" href="wotuView?page=${pageIndex}&size=20">${pageIndex}</a>
                    </li>
                </#if>
            </#if>

        </#list>

            <li class="page-item">
                <a class="page-link" href="#">...</a>
            </li>

        <#if number==totalPages>
            <li class="page-item active">
                <a class="page-link" href="wotuView?page=${totalPages}&size=20">${totalPages}</a>
            </li>
        <#else>
            <li class="page-item">
                <a class="page-link" href="wotuView?page=${totalPages}&size=20">${totalPages}</a>
            </li>
        </#if>

        <#if last>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        <#else>
            <li class="page-item">
                <a class="page-link" href="wotuView?page=${number+1}&size=20">Previous</a>
            </li>
        </#if>
        </ul>
        <div class="center">总共 ${totalPages} 页， ${totalElements} 条记录</div>
    </nav>

</div>
</body>
</html>
