<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>



<title>回收网点</title>


<div class="workingArea">
    <h1 class="label label-info" >回收网点</h1>
    <br>
    <br>
    
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <th>ID</th>
                    <th>小区名称</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${thecs}" var="c">
                
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                        
                    
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
    
    
</div>
<%@include file="include/footer.jsp"%>