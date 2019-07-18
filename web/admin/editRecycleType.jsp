<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑分类</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("name","分类名称"))
			return false;

		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_category_list">所有分类</a></li>
	  <li class="active">编辑分类</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">编辑分类</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_recycleType_update"  enctype="multipart/form-data">
	    		<table class="editTable">
	    			<tr>
	    				<td>分类名称</td>
	    				<td><input  id="name1" name="name1" value="${c.name}" type="text" class="form-control"></td>
	    			</tr>
	    			<tr>
	    				<td>分类圖片</td>
	    				<td>
	    					<input id="recycleTypePic" accept="image/*" type="file" name="filepath" /> 
	    				</td>
	    			</tr>	   


	    			<tr>
	    				<td>单位</td>
	    				<td><input  id="unit1" name="unit1" type="text" class="form-control" value="${c.unit}"></td>
	    			</tr>
	    			<tr>
	    				<td>单位价格</td>
	    				<td><input  id="price1" name="price1" type="text" class="form-control" value="${c.price}" ></td>
	    			</tr>
	    			<tr>
	    				<td>起收量</td>
	    				<td><input  id="start1" name="start1" type="text" class="form-control" value="${c.start}"></td>
	    			</tr>
	    			
	    			<tr>
	    				<td>质量要求</td>
	    				<td><input  id="xxx1" name="xxx1" type="text" class="form-control"  value="${c.qRemarks}"></td>
	    			</tr>
	    			<tr>
	    				<td>交易要求</td>
	    				<td><input  id="yyy1" name="yyy1" type="text" class="form-control"  value="${c.tRemarks}"></td>
	    			</tr>

	    			<tr class="submitTR">
	    				<td colspan="2" align="center">
	    					<input type="hidden" name="id" value="${c.id}">
	    					<button type="submit" class="btn btn-success">提 交</button>
	    				</td>
	    			</tr>
	    		</table>
	    	</form>
	  </div>
	</div>	
</div>