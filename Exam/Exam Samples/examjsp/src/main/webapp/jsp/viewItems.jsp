<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ubb.exam.model.User" %>
<%@ page import="ubb.exam.controller.UserController"%>
<%@ page import="ubb.exam.controller.ItemController"%>
<%
    User currentUser = (User)(session.getAttribute("currentSessionUser"));
    if (currentUser == null) {
        session.invalidate();
        response.sendRedirect("../index.html");
    }
%>

<html>
<head>
    <title>Items</title>
    <script src="../lib/jquery-2.0.3.js"></script>
    <script lang="javascript">

        function createItem() {
            //TODO
        }

        function newItem() {
            var tbl = document.createElement('table');
            var tr;
            tbl.setAttribute('id', 'itemTable');

            tr = tbl.insertRow(0);

            var thname = document.createElement('th');
            thname.innerHTML = "Name";
            tr.appendChild(thname);

            var thprice = document.createElement('th');
            thprice.innerHTML = "Description";
            tr.appendChild(thprice);

            var thcateg = document.createElement('th');
            thcateg.innerHTML = "Value";
            tr.appendChild(thcateg);

            for(var i = 0; i < 1; i++) {
                tr = tbl.insertRow(i+1);
                for (var column = 0; column < 4; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    var elem;
                    switch (column) {
                        case 0:
                            td.innerHTML = '<input type="text" name="name"/>';
                            break;
                        case 1:
                            td.innerHTML = '<input type="text" name="description"/>';
                            break;
                        case 2:
                            td.innerHTML = '<input type="text" name="value"/>';
                            break;
                        case 3:
                            td.innerHTML = '<input id="create-item" type="button" value="Create Item" onclick="createItem()">';
                    }
                }

            }

            var section = document.getElementById('add-items-div');
            section.appendChild(tbl);

        }

        function loadData(items) {
            console.log(items);
            $("#items-div").html('');
            var size = items.length;
            var tbl = document.createElement('table');
            var tr;
            tbl.setAttribute('id', 'itemTable');

            tr = tbl.insertRow(0);

            var thname = document.createElement('th');
            thname.innerHTML = "Name";
            tr.appendChild(thname);

            var thprice = document.createElement('th');
            thprice.innerHTML = "Description";
            tr.appendChild(thprice);

            var thcateg = document.createElement('th');
            thcateg.innerHTML = "Value";
            tr.appendChild(thcateg);

            for(var i = 0; i < size; i++) {
                tr = tbl.insertRow(i+1);
                if (items[i].value > 100) {
                    tr.style.background = "red";
                }
                for (var column = 0; column < 3; column++) {
                    var td = document.createElement('td');
                    td = tr.insertCell(column);
                    var elem;
                    switch (column) {
                        case 0:
                            td.innerHTML = items[i].name;
                            break;
                        case 1:
                            td.innerHTML = items[i].description;
                            break;
                        case 2:
                            td.innerHTML = items[i].value;
                            break;
                    }
                }

            }

            var section = document.getElementById('items-div');
            section.appendChild(tbl);
        }

        $(document).ready(function() {
            $("#show-items-btn").click(function(){
                $.getJSON('viewItemsServlet',
                    {},
                    loadData
                );
            });
        });


    </script>
</head>
<body>
    <h3>Your Items</h3>
    <h5>Logged in as: <%=currentUser.getUsername()%></h5>
    <input id="show-items-btn" type="button" value="Show Items">

    <div id="items-div">
    </div>
    <br/>

    <div id="add-items-div">
        <input id="new-item-btn" type="button" value="New Item" onclick="newItem()">
    </div>

    <br/>
    <input id="log-out-btn" type="button" value="Log Out" onclick="document.location.href='logout.jsp'">

    <input type="hidden" id="user_id" name="user_id" value="<%=currentUser.getId()%>">
</body>
</html>
