<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="include/header.jsp"%>
<%@include file="include/top.jsp"%>

<script>

$(function(){

	$("div[xuan=password]").show();			
	$("li[xuan=password]").addClass("active");
	$("li[xuan]").click(function(){
		var xuan = $(this).attr("xuan");
		
			$("div[xuan]").hide();
			$("div[xuan="+xuan+"]").show();			
		
		$("li").removeClass("active");
		$(this).addClass("active");
	});
	
});

</script>


<script>
$(function(){
	
	<c:if test="${!empty msg}">
	$("span.errorMessage").html("${msg}");
	$("div.registerErrorMessageDiv").css("visibility","visible");		
	</c:if>
	
})
</script>

<div style="width:800px;margin:100px auto;" id="namepasswordDiv">

	<div class="registerErrorMessageDiv">
		<div class="alert alert-danger" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
		  	<span class="errorMessage"></span>
		</div>		
	</div>
		<div id="errorDiv" class="alert alert-danger alert-dismissable" "="" style="display: none;">
			 <span id="errorMessage"></span>
		</div>	

		<ul class="nav nav-tabs">
		  
		  <li role="presentation" xuan="password" class=""><a  href="#nowhere">密码</a></li>
		  
		  <li role="presentation" xuan="mobile" class=""><a  href="#nowhere">手机号配置</a></li>
		  
		  <li role="presentation" xuan="area" class=""><a  href="#nowhere">修改地区号</a></li>

		  <li role="presentation" xuan="realname" class=""><a  href="#nowhere">修改真实姓名</a></li>
		  

		  
	  	<li role="presentation" xuan="alipay" class=""><a  href="#nowhere">支付宝账号配置</a></li>

	  	<li role="presentation" xuan="withdraw" class=""><a  href="#nowhere">提现</a></li>

	  

		</ul>			

		<br><br><br><br><br>
		
		
		<div xuan="password" style="display: none;">
				<br>
				<form class="configform" method="post" action="foreupdatePassword">
			        <input type="password"  name="prepassword"  id="user.password" class="form-control" placeholder="请输入原始密码" required="">
			        <br>
			        <input type="password" id="password" name="password" class="form-control" placeholder="新密码 6-16位,区分大小写,不能用空格" required="">
			        <br>
			        <input type="password" id="password2" name="password2" class="form-control" placeholder="新密码 6-16位,区分大小写,不能用空格" required="">
			        <br>
			        
			        <button class="btn btn-lg btn-info btn-block " type="submit">更新</button>
			        
				</form>
		</div>
		
		<div xuan="mobile" style="display: none;">
				<br>
				<form class="configform" method="post" action="foreupdateMobile">
			        <h4>当前手机号:${us.mobile}</h2>

					<br>
			       <input  type="text" name="mobile" class="form-control" placeholder="新的手机号" required="">
			        <br>
			        
			        <button class="btn btn-lg btn-info btn-block " type="submit">更新</button>
			        
				</form>
		</div>
		
		<div xuan="area" style="display: none;">
				<br>
				<form class="configform" method="post" action="foreupdateArea">
			        <h4>当前地区:${us.area.name}</h2>

					<br>
			        <span style = "font-size:200%">选择新的地区:</span>
			        <select name="aid" style = "font-size:200%">

			        	<c:forEach items="${as}" var="a">
                		<option  value="${a.id}"> ${a.name}</option>
			        	</c:forEach>
			        </select>
			        <br>
			        <br>

			        <br>
			        
			        <button class="btn btn-lg btn-info btn-block " type="submit">更新</button>
			        
				</form>
		</div>
		

		<div xuan="realname" style="display: none;">
				<br>
				<form class="configform" method="post" action="foreupdateRealname">
			         <h4>当前姓名:${us.realName}</h2>

					<br>
			       
			        <input type="text" name="realname" class="form-control" placeholder="您的真实姓名" required="">
			        <br>
			        
			        <button class="btn btn-lg btn-info btn-block " type="submit">更新</button>
			        
				</form>
		</div>

		<div xuan="alipay" style="display: none;">
				<br>
				<form class="configform" method="post" action="foreupdateAlipay">
			         <h4>当前支付宝账号:${us.alipay}</h2>

					<br>
			       
			        <input type="text" name="alipay" class="form-control" placeholder="您的支付宝账号" required="">
			        <br>

			        
			        <button class="btn btn-lg btn-info btn-block " type="submit">更新</button>
			        
				</form>
		</div>

		<div xuan="withdraw" style="display: none;">
				<br>
				<form class="configform" method="post" action="forewithdraw">
			        
					<h4>您的余额为:${us.balance}</h2>

					<br>
			        <input type="text" name="withdraw" class="form-control" placeholder="提现金额" required="">
			        <br>
			        
			        <button class="btn btn-lg btn-info btn-block " type="submit">发起请求</button>
			        
				</form>
		</div>
		


			
		

</div>

<%@include file="include/footer.jsp"%>