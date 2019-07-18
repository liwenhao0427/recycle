<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>



<title>编辑小区</title>


<script>
$(function(){
	
	$("#editForm").submit(function(){
		if(!checkEmpty("name","小区名称"))
			return false;

		return true;
	});
});

</script>

<div class="workingArea">

	<ol class="breadcrumb">
	  <li><a href="admin_area_list">所有小区</a></li>
	  <li class="active">编辑小区</li>
	</ol>

	<div class="panel panel-warning editDiv">
	  <div class="panel-heading">编辑小区</div>
	  <div class="panel-body">
	    	<form method="post" id="editForm" action="admin_area_update"  enctype="multipart/form-data">
	    		<table class="editTable">
	    			<tr>
	    				<td>小区名称</td>
	    				<td><input  id="name" name="name" value="${c.name}" type="text" class="form-control"></td>
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