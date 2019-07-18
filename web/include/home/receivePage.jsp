
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<title>接单</title>

<div class="workingArea">
    <br><br><br><br><br>
    <a href="foredeliver"><h1 class="label label-info" style="font-size: 200%" >查看我的所有待配送订单</h1></a>
    <a href="forerecyclerSpace"><h1 class="label label-info" style="font-size: 200%" >我的个人空间</h1></a>
    
    <br>
    <br>
     
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover1  table-condensed">
            <thead>
                <tr class="success">
                    <th>订单号</th>
                    <th>用户姓名</th>
                    <th>用户手机</th>
                    <th>地址</th>
                    <th>创建时间</th>
                    <th>备注</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${os}" var="o">
                    <tr>
                        <td>${o.orderCode}</td>
                        <td>${o.receiver}</td>
                        <td>${o.mobile}</td>
                        <td>${o.address}</td>
                        <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${o.userMessage}</td>
                        <td>
                                <a href="foredelivery?oid=${o.id}">
                                    <button class="btn btn-primary btn-xs">接受订单</button>
                                </a>                         
                        </td>
                        
                    </tr>
                    
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
     
    <div class="pageDiv">
        <%@include file="../admin/adminPage.jsp" %>
    </div>
     
</div>

