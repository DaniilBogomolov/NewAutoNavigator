<html>
<body>
<#if user??>
    <form method="post" action="/admin/manage/addcar">
        <input type="text" name="maker" placeholder="Maker"><br>
        <input type="text" name="model" placeholder="Model"><br>
        <input type="text" name="type" placeholder="Type"><br>
        <input type="text" name="year" placeholder="Year"><br>
        <input type="text" name="avgPrice" placeholder="AvgPrice"><br>
        <input type="text" name="engine" placeholder="Engine"><br>
        <input type="text" name="transmission" placeholder="Transmission"><br>
        <input type="text" name="capacity" placeholder="Capacity"><br>
        <button type="submit">Submit</button>
    </form>
</#if></body>
</html>