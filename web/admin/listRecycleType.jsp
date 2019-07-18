<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
$(function(){
	
	$("#addForm").submit(function(){
		if(!checkEmpty("name","分类名称"))
			return false;
		if(!checkEmpty("categoryPic","分类图片"))
			return false;
		return true;
	});
});

</script>

<title>回收分类管理</title>


<div class="workingArea">
	<h1 class="label label-info" >回收分类管理</h1>
	<br>
	<br>
	
	<div class="listDataTableDiv">
		<table class="table table-striped table-bordered table-hover  table-condensed">
			<thead>
				<tr class="success">
					<th>ID</th>
					<th>图片</th>
					<th>分类名称</th>
					<th>单位</th>
					<th>单位价格</th>
					<th>起收量</th>
					<th>质量要求</th>
					<th>交易要求</th>
					<th>编辑</th>
					<th>删除</th>
					<th>图片管理</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${thercs}" var="c">
				
				<tr>
					<td>${c.id}</td>
					<td><img height="40px" src="img/recycleType/${c.id}.jpg"></td>
					<td>${c.name}</td>
					<td>${c.unit}</td>
					<td>${c.price}</td>
					<td>${c.start}</td>
					<td>${c.qRemarks}</td>
					<td>${c.tRemarks}</td>
									 
					<td><a href="admin_recycleType_edit?id=${c.id}"><span class="glyphicon glyphicon-edit"></span></a></td>
					<td><a deleteLink="true" href="admin_recycleType_delete?id=${c.id}"><span class=" 	glyphicon glyphicon-trash"></span></a></td>
					<td><a href="admin_recyclePicture_list?cid=${c.id}"><span
                                class="glyphicon glyphicon-picture"></span></a></td>
	
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="pageDiv">
		<%@include file="../include/admin/adminPage.jsp" %>
	</div>
	
	<div class="panel panel-warning addDiv">
	  <div class="panel-heading">新增分类</div>
	  <div class="panel-body">
	    	<form method="post" id="addForm" action="admin_recycleType_add" enctype="multipart/form-data">
	    		<table class="addTable">
	    			<tr>
	    				<td>分类名称</td>
	    				<td><input  id="name" name="name" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>分类图片</td>
	    				<td>
	    					<input id="recycleTypePic" accept="image/*" type="file" name="filepath" />
	    				</td>
	    			</tr>

	    			<tr>
	    				<td>单位</td>
	    				<td><input  id="unit" name="unit" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>单位价格</td>
	    				<td><input  id="price" name="price" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>起收量</td>
	    				<td><input  id="start" name="start" type="text" class="form-control"></td>
	    			</tr>
	    			
	    			<tr>
	    				<td>质量要求</td>
	    				<td><input  id="xxx" name="xxx" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>交易要求</td>
	    				<td><input  id="yyy" name="yyy" type="text" class="form-control"></td>
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