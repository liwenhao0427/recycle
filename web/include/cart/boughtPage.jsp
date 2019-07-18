
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<script>
var deleteOrder = false;
var deleteOrderid = 0;

$(function(){
	$("a[orderStatus]").click(function(){
		var orderStatus = $(this).attr("orderStatus");
		if('all'==orderStatus){
			$("table[orderStatus]").show();	
		}
		else{
			$("table[orderStatus]").hide();
			$("table[orderStatus="+orderStatus+"]").show();			
		}
		
		$("div.orderType div").removeClass("selectedOrderType");
		$(this).parent("div").addClass("selectedOrderType");
	});
	
	$("a.deleteOrderLink").click(function(){
		deleteOrderid = $(this).attr("oid");
		deleteOrder = false;
		$("#deleteConfirmModal").modal("show");
	});

	$("button.orderListItemConfirm").click(function(){
		confirmOrderid = $(this).attr("oid");
		confirmOrder = false;
		$("#ConfirmModal").modal("show");
	});



	$("button.deleteConfirmButton").click(function(){
		deleteOrder = true;
		$("#deleteConfirmModal").modal('hide');
	});	

	$("button.ConfirmButton").click(function(){
		confirmOrder = true;
		$("#ConfirmModal").modal('hide');
	});	
	
	$('#deleteConfirmModal').on('hidden.bs.modal', function (e) {
		if(deleteOrder){
			var page="foredeleteOrder";
			$.post(
				    page,
				    {"oid":deleteOrderid},
				    function(result){
						if("success"==result){
							$("table.orderListItemTable[oid="+deleteOrderid+"]").hide();
						}
						else{
							location.href="login.jsp";
						}
				    }
				);
			
		}
	})		

	$('#ConfirmModal').on('hidden.bs.modal', function (e) {
		if(confirmOrder){
			var page="foreorderConfirmed";
			$.post(
				    page,
				    {"oid":confirmOrderid},
				    function(result){
						if("success"==result){
							location.href="orderConfirmed.jsp";
						}
						else{
							location.href="orderConfirmed.jsp";
						}
				    }
				);
		}
	})		
	
	$(".ask2delivery").click(function(){
		var link = $(this).attr("link");
		$(this).hide();
		page = link;
		$.ajax({
			   url: page,
			   success: function(result){
				alert("别急呀,就快到了")
			   }
			});
		
	});
});

</script>
	
<div class="boughtDiv">

	<br>
	<br><br><br><br><br>
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="#nowhere">所有订单</a></div>
		<div><a  orderStatus="waitReceive" href="#nowhere">待接单</a></div>
		<div><a  orderStatus="waitDelivery" href="#nowhere">待配送</a></div>
		<div><a  orderStatus="waitConfirm" href="#nowhere">待确认</a></div>
		<div><a  orderStatus="finish" href="#nowhere">已完成</a></div>
		
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>
	<div style="clear:both"></div>
	<div class="orderListTitle">
		<table class="orderListTitleTable">
			<tr>
				<td>回收品</td>
				<td width="100px">单价</td>
				<td width="100px">数量</td>
				<td width="120px">预计获得金额</td>
				<td width="100px">交易操作</td>
			</tr>
		</table>
	
	</div>
	
	<div class="orderListItem">
		<c:forEach items="${os}" var="o">
			<table class="orderListItemTable" orderStatus="${o.status}" oid="${o.id}">
				<tr class="orderListItemFirstTR">
					<td colspan="2">
					<b><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></b> 
					<span>订单号: ${o.orderCode} 
					</span>
					</td>
					<td  colspan="2"><img width="13px" src="img/site/orderItemrecycle.png">回收商场</td>
					<td colspan="1">
						<a class="wangwanglink" href="#nowhere">
							<div class="orderItemWangWangGif"></div>
						</a>
						
					</td>
					<td class="orderItemDeleteTD">
						<a class="deleteOrderLink" oid="${o.id}" href="#">
							<span  class="orderListItemDelete glyphicon glyphicon-trash"></span>
						</a>
						
					</td>
				</tr>

					<tr class="orderItemProductInfoPartTR" >
						<td class="orderItemProductInfoPartTD"><img width="80" height="80" src="img/productSingle_middle/${o.product.firstProductImage.id}.jpg"></td>
						<td class="orderItemProductInfoPartTD">
							<div class="orderListItemProductLinkOutDiv">
								<a href="foreproduct?pid=${o.product.id}">${o.product.name}</a>
								<div class="orderListItemProductLinkInnerDiv">
								</div>
							</div>
						</td>
						<td  class="orderItemProductInfoPartTD" width="100px">
						
							<div class="orderListItemProductPrice">￥<fmt:formatNumber type="number" value="${o.product.price}" minFractionDigits="2"/></div>
		
		
						</td>
						
							<td valign="top" rowspan="1" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${o.number}</span>
							</td>
							<td valign="top" rowspan="1" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice">￥<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${o.sumPrice}"/></div>
								
							</td>


							<td valign="top" rowspan="1" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">

								<c:if test="${o.status=='waitConfirm' }">
									<a  href="#">
										<button class="orderListItemConfirm" oid="${o.id}">确认</button>
									</a>
								</c:if>
								<c:if test="${o.status=='waitReceive' }">
									<span>待接单</span>							
								</c:if>
								<c:if test="${o.status=='finish' }">
									<span>已完成</span>							
								</c:if>
								
								<c:if test="${o.status=='waitDelivery' }">
									<span>待配送</span>
<%-- 									<button class="btn btn-info btn-sm ask2delivery" link="admin_order_delivery?id=${o.id}">催回收员取件</button> --%>
								
							</td>						
						</c:if>
				
					</td>
				
					</tr>	
				
			</table>
		</c:forEach>
	
	</div>
	
</div>