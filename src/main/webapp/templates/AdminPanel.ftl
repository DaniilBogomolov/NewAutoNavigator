<#if user??>
    Hello, admin!
    <#else>
        <form method="post" action="/admin/manage">
            <input type="text" name="username">
            <input type="password" name="password">
            <button type="submit">Submit</button>
        </form>
</#if>