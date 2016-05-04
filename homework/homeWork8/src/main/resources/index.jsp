<%@ page import="SimplePage.EntityManager" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            var id = 0;

            try {
                id = parseInt($("ul li").last().children().attr("href").substring(21));
            } catch (e) {

            }


            $("form").submit(function(event) {
                event.preventDefault();
                var text = $( "input:first" ).val();
                id++
                $("ul").append("<li>"+text+" <a href=\"test?action=del&data="+ id +"\""+">x</a></li>");

                $.post( "/test?action=add&data="+text, function( data ) {

                });

                $( "input:first" ).val("");
            });

            $("body").on('click',"a",function(event) {
                event.preventDefault();
                $.post( $(this).attr("href"), function( data ) {

                });
                $(this).parent().remove();
            });
        });
    </script>
    <meta>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    </meta>
</head>
<body>

<ul>
    <!-- Programmatically produce this list -->
    <%=EntityManager.getList()%>

</ul>

<form action="add" method="post">
    <input type="text" name="item" placeholder="add an item" />
    <input type="submit" name="submit" value="Add" />
</form>

</body>
</html>