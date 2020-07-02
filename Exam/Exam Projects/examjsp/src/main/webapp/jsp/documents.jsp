<%--
  Created by IntelliJ IDEA.
  User: csoka
  Date: 6/25/2020
  Time: 10:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Documents</title>
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">

        function loadData(data) {
            console.log(data);
            $("list-docs").html('');
            var size = data.length;
            var tbl = document.createElement('table');
            var tr;
            tbl.setAttribute('id', 'docTable');

            tr = tbl.insertRow(0);

            var thname = document.createElement('th');
            thname.innerHTML = "Name";
            tr.appendChild(thname);

            var thk1 = document.createElement('th');
            thk1.innerHTML = "Key1";
            tr.appendChild(thk1);

            var thk2 = document.createElement('th');
            thk2.innerHTML = "Key2";
            tr.appendChild(thk2);

            var thk3 = document.createElement('th');
            thk3.innerHTML = "Key3";
            tr.appendChild(thk3);

            var thk4 = document.createElement('th');
            thk4.innerHTML = "Key4";
            tr.appendChild(thk4);

            var thk5 = document.createElement('th');
            thk5.innerHTML = "Key5";
            tr.appendChild(thk5);

            for(var i = 0; i < size; i++) {
                tr = tbl.insertRow(i+1);
                for (var column = 0; column < 6; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    var elem;
                    switch (column) {
                        case 0:
                            td.innerHTML = data[i].name;
                            break;
                        case 1:
                            //TODO input
                            td.innerHTML = data[i].keyword1;
                            break;
                        case 2:
                            td.innerHTML = data[i].keyword2;
                            break;
                        case 3:
                            td.innerHTML = data[i].keyword3;
                            break;
                        case 4:
                            td.innerHTML = data[i].keyword4;
                            break;
                        case 5:
                            td.innerHTML = data[i].keyword5;
                            break;
                    }
                }

            }

            var section = document.getElementById('list-docs');
            section.appendChild(tbl);
        }

        $(document).ready(function() {
           $("#btn-show-docs").click(function(){
              $.getJSON("documents",
                  {},
                  loadData
              );
           });
        });

    </script>
</head>
<body>
    <input id="btn-show-docs" type="button" value="Documents"/>

    <div id="list-docs">
    </div>

</body>
</html>
