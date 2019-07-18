<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
 
<link href="directsupply.css" rel="stylesheet" type="text/css" />
 
<script>

$(function(){
    var left = $("div#carousel-of-product").offset().left;
    $("div.categoryWithCarousel div.head").css("margin-left",left-200);
    $("div.categoryMenu").css("left",left-220);
    $("div.productsAsideCategorys").css("left",left-220);
    $("span.appointment").css("left",left);
});
</script>
 
<div class="categoryWithCarousel">
 
<div class="headbar show1">
    <div class="head ">
     
        <span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
        <span style="margin-left:10px" >回收分类</span> 
         
    </div>

    <span class="appointment">
     
        <span style="margin-left:10px" class="glyphicon glyphicon-tree-conifer"></span>
        <a href="foreprototype?path=prototype/directsupply.jsp"> 预约上门回收</a> 
         
    </span>
    
     
</div>
 
<div style="position: relative">
    <%@include file="categoryMenu.jsp" %>
</div>
 
<div style="position: relative;left: 0;top: 0;">
    <%@include file="productsAsideCategorys.jsp" %>
</div>
 
<%@include file="carousel.jsp" %>
 
<div class="carouselBackgroundDiv">
</div>
 
</div>