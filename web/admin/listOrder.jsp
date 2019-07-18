<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<title>订单管理</title>

<div class="workingArea">
    <h1 class="label label-info" >订单管理</h1>
    <br>
    <br>
     
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover1  table-condensed">
            <thead>
                <tr class="success">
                    <th>ID</th>
                    <th>状态</th>
                    <th>图书名称</th>
                    <th>金额</th>
                    <th>数量</th>
                    <th>用户名称</th>
                    <th>创建时间</th>
                    <th>回收时间</th>
                    <th>确认收货时间</th>
                   
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${os}" var="o">
                    <tr>
                        <td>${o.id}</td>
                        <td>${o.statusDesc}</td>
                        <td>${o.product.name}</td>
                        <td>${o.sumPrice}</td>
                        <td align="center">${o.number}</td>
                        <td align="center">${o.user.name}</td>
                         
                        <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${o.deliveryDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${o.confirmDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        
                        
                    </tr>
                    
                    
                </c:forEach>
            </tbody>
        </table>
    </div>
     
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
     
</div>
 
<%@include file="../include/admin/adminFooter.jsp"%>