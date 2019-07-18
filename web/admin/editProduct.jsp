<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
 
<title>编辑回收品</title>
 
<script>

<script>
    $(function() {
        $("#addForm").submit(function() {
            if (!checkEmpty("name", "回收品名称"))
                return false;
            if (!checkNumber("price", "单价"))
                return false;
            if (!checkEmpty("unit", "单位"))
                return false;
            if (!checkNumber("startNumber", "起收量"))
                return false;
            
            return true;
        });
    });
</script>
</script>
 
<div class="workingArea">
    <ol class="breadcrumb">
      <li><a href="admin_category_list">所有分类</a></li>
      <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
      <li class="active">${p.name}</li>
      <li class="active">编辑回收品</li>
    </ol>
     
    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑回收品</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_product_update">
                <table class="editTable">
                    <tr>
                        <td>回收品名称</td>
                        <td><input id="name" name="name" type="text"
                            class="form-control"></td>
                    </tr>
                    <tr>
                        <td>回收品单价</td>
                        <td><input id="price" name="price" type="text"
                            class="form-control"></td>
                    </tr>
                    <tr>
                        <td>回收品单位</td>
                        <td><input id="unit" name="unit" type="text"
                            class="form-control"></td>
                    </tr>
                    <tr>
                        <td>起收量</td>
                        <td><input id="startNumber" value="1" name="startNumber" type="text"
                            class="form-control"></td>
                    </tr>
                    <tr>
                        <td>质量要求</td>
                        <td><input id="qRemarks"  value="有回收价值" name="qRemarks" type="text"
                            class="form-control"></td>
                    </tr>
                    <tr>
                        <td>交易要求</td>
                        <td><input id="tRemarks"  value="当面点清,概不退货" name="tRemarks" type="text"
                            class="form-control"></td>
                    </tr>         
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                        <input type="hidden" name="id" value="${p.id}">
                        <input type="hidden" name="cid" value="${p.category.id}">
                        <button type="submit" class="btn btn-success">提 交</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>