<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
    
    $("#addForm").submit(function(){
        if(!checkEmpty("name","小区名称"))
            return false;
        return true;
    });
});

</script>

<title>小区管理</title>


<div class="workingArea">
    <h1 class="label label-info" >小区管理</h1>
    <br>
    <br>
    
    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <th>ID</th>
                    <th>小区名称</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${thecs}" var="c">
                
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                        
                    <td><a href="admin_area_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a deleteLink="true" href="admin_area_delete?id=${c.id}"><span class="  glyphicon glyphicon-trash"></span></a></td>
    
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
    
    <div class="panel panel-warning addDiv">
      <div class="panel-heading">新增小区</div>
      <div class="panel-body">
            <form method="post" id="addForm" action="admin_area_add" enctype="multipart/form-data">
                <table class="addTable">
                    <tr>
                        <td>小区名称</td>
                        <td><input  id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
      </div>
    </div>
    
</div>

<%@include file="../include/admin/adminFooter.jsp"%>