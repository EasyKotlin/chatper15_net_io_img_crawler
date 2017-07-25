<!doctype html>
<html>
<head>
    <meta http-equiv=content-type content=text/html;charset=utf-8>
    <meta http-equiv=X-UA-Compatible content=IE=Edge>
    <title>我图</title>
    <link href="DataTables/media/css/jquery.dataTables.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="dsl.css" rel="stylesheet">

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    <script src="DataTables/media/js/jquery.dataTables.js"></script>
    <script src="sloth.js"></script>
    <script src="dsl.js"></script>
</head>
<body>

<nav class="navbar navbar-light bg-faded col-md-12">
    <a class="navbar-brand" href="#">我图</a>
    <ul class="nav navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="/">图片列表 <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item active">
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
    <form class="form-inline navbar-form pull-right">
        <input class="form-control" type="text" placeholder="Search">
        <button class="btn btn-success-outline" type="submit">Search</button>
    </form>
</nav>


<div class="container">


    <h1 class="center ipad">我图</h1>
    <table id="wotuTable" class="table">
        <thead>
        <tr>
            <th width="10%">序号</th>
            <th width="90%">我图</th>
        </tr>
        </thead>
        <tbody>
        <#list imageUrls as url>
            <#assign index="${url_index+1}"/>
        <tr>
            <td width="10%">${index}</td>
            <td width="90%"><img sloth-img="${url}"></td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>

