<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post">
            <label>
                <input type="text" name="text" placeholder="Введите цитату">
                <input type="text" name="author" placeholder="Введите автора">
            </label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список цитат</div>
    <form method="get" action="/main">
        <label>
            <input type="text" name="filter" value="${filter}">
        </label>
        <button type="submit">Найти</button>
    </form>
    <#list quotes as quote>
        <div>
            <b>${quote.id}</b>
            <span>${quote.text}</span>
            <i>${quote.author}</i>
            <strong>${quote.userName}</strong>
        </div>
    <#else>
        No quote
    </#list>
</@c.page>
