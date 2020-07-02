<%--
  Created by IntelliJ IDEA.
  User: csoka
  Date: 6/25/2020
  Time: 10:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Websites</title>
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">

        function loadData(data) {
            console.log(data);
            $("list-sites").html('');
            var size = data.length;
            var tbl = document.createElement('table');
            var tr;
            tbl.setAttribute('id', 'siteTable');

            tr = tbl.insertRow(0);

            var thname = document.createElement('th');
            thname.innerHTML = "Name";
            tr.appendChild(thname);

            for(var i = 0; i < size; i++) {
                tr = tbl.insertRow(i+1);
                for (var column = 0; column < 1; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    var elem;
                    switch (column) {
                        case 0:
                            td.innerHTML = data[i].url;
                            break;
                        //TODO
                    }
                }

            }

            var section = document.getElementById('list-docs');
            section.appendChild(tbl);
        }

        $(document).ready(function() {
            $("#btn-show-sites").click(function(){
                $.getJSON("websites",
                    {},
                    loadData
                );
            });
        });

    </script>
</head>
<body>
    <input id="btn-show-sites" type="button" value="Websites"/>

    <div id="list-sites">
    </div>

</body>
</html>
