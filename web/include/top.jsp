



<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<nav class="top ">
		<a href="${contextPath}">
			<span style="color:#68B92E;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
			回收首页
		</a>	
		
		<span>你好，欢迎来回收</span>
		
		<c:if test="${!empty user}">
			<a href="foremySpace">${user.name}</a>
			<a href="forelogout">退出</a>		
		</c:if>
		
		<c:if test="${empty user}">
			<a href="login.jsp">请登录</a>
			<a href="register.jsp">免费注册</a>		
		</c:if>

		<span class="pull-right">
			<a href="foremySpace">个人空间</a>
			
		</span>

		<span class="pull-right">
			<a href="forebought">回收订单</a>
			
		</span>
		
		
</nav>




<div class="head_ri">
    
    <ul class="nav">
        <li><a href="forehome">首  页</a></li>
        <li><a href="forehome">在线回收</a></li>
        <li><a href="forerecycler" >回收员加盟</a></li>
        <li><a href="forereceive" >回收员页面</a></li>
        <li><a href="forelistArea">回收网点</a></li>
    </ul>
    
</div>