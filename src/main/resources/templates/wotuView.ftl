<#include 'head.ftl'>
<#include 'nav.ftl'>

<table id="wotuTable" class="table table-striped table-responsive">
    <thead class="thead">
    <tr>
        <th>序号</th>
        <th>分类</th>
        <th>图列</th>
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
    <ul class="pagination pagination-sm  justify-content-center">
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
            if (${number}>0){
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

</body>
</html>
