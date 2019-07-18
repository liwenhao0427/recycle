<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<title>在线用户管理</title>


<div class="workingArea">
    <h1 class="label label-info" >当前在线人数:${total}人</h1>
    <br>
    <br>
    
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <th>用户名</th>
                    <th>上线时间</th>
                    <th>最后访问时间</th>
                    <th>IP</th>
                    <th>操作</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${onLines}" var="c">
                
                <tr>
                    <td>${c.user.name}</td>
                    <td>${c.creationTime}</td>
                    <td>${c.lastAccessTime}</td>
                    <td>${c.ip}</td>
                    <td><a deleteLink="true" href="admin_online_delete?name=${c.user.name}"><span class="  glyphicon glyphicon-trash"></span></a></td>
    
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