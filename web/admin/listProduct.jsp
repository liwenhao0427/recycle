<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>
 
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
 
<title>回收品管理</title>
 
<div class="workingArea">
 
    <ol class="breadcrumb">
      <li><a href="admin_category_list">所有分类</a></li>
      <li><a href="admin_product_list?cid=${c.id}">${c.name}</a></li>
      <li class="active">回收品管理</li>
    </ol>
 
    <div class="listDataTableDiv">
        <table
            class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
                <tr class="success">
                    <th>ID</th>
                    <th>图片</th>
                    <th>回收品名称</th>
                    <th>单价</th>
                    <th>单位</th>
                    <th>起收量</th>
                    <th>图片管理</th>
                    <th>编辑</th>
                    <th>删除</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${ps}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>
                        <c:if test="${!empty p.firstProductImage}">
                            <img width="40px" src="img/productSingle/${p.firstProductImage.id}.jpg">
                        </c:if>
                         
                        </td>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.unit}</td>
                        <td>${p.startNumber}</td>
                        
                        <td><a href="admin_productImage_list?pid=${p.id}"><span
                                class="glyphicon glyphicon-picture"></span></a></td>
                        <td><a href="admin_product_edit?id=${p.id}"><span
                                class="glyphicon glyphicon-edit"></span></a></td>
                        <td><a deleteLink="true"
                            href="admin_product_delete?id=${p.id}"><span
                                class="     glyphicon glyphicon-trash"></span></a></td>
 
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
 
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp"%>
    </div>
 
    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增回收品</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_product_add">
                <table class="addTable">
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
                            <input type="hidden" name="cid" value="${c.id}">
                            <button type="submit" class="btn btn-success">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
 
</div>
 
<%@include file="../include/admin/adminFooter.jsp"%>