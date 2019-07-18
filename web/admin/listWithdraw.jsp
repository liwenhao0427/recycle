<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>


<title>提现请求管理</title>


</script>
<div class="workingArea">
    <h1 class="label label-info" >提现请求管理</h1>
    <br>
    <br>
     
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover1  table-condensed">
            <thead>
                <tr class="success">
                    <th>ID</th>
                    <th>用户</th>
                    <th>金额</th>
                    <th>创建时间</th>
                    <th>支付时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ws}" var="w">
                    <tr>
                        <td>${w.id}</td>
                        <td>${w.user.id}</td>
                        <td>${w.val}</td>
                         
                        <td><fmt:formatDate value="${w.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${w.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${w.status}</td>
                        

                        <td>
                             
                            <c:if test="${w.status=='waitPay'}">
                                <a href="admin_withdraw_pay?id=${w.id}">
                                    <button class="btn btn-primary btn-xs">完成转账</button>
                                </a>                         
                            </c:if>
                        </td>
                        
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