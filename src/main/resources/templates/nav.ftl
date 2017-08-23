<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">美图中国</a>
        </div>
        <div>
            <ul class="nav navbar-nav">

                <li class='<#if requestURI=="/meituView">active</#if>'><a href="meituView">美图列表</a></li>
                <li class='<#if requestURI=="/meituFavoriteView">active</#if>'><a href="meituFavoriteView">精选收藏</a></li>

                <li class=""><a href="doCraw" target="_blank">执行抓取</a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Kotlin极简教程 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="http://www.jianshu.com/nb/12976878" target="_blank">Kotlin</a></li>
                        <li><a href="#">SpringBoot</a></li>
                        <li><a href="#">Java</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Scala</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Groovy</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">关于</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


