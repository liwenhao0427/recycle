
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
	<c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>



<script>

$(function(){
	$(function(){
		$("div[categoryId]").hide();
		$("div[categoryId="+1+"]").show();	
	});
	$("a[categoryId]").click(function(){
		var categoryId = $(this).attr("categoryId");
		$("div[categoryId]").hide();
		$("div[categoryId="+categoryId+"]").show();	
		
	});
});

</script>

<div class=" crl"></div>
<div class="nav_all">
  <dl class="bor_bot">
  <dt>回收行业：</dt>
  <dd>
  <ul>

	<c:forEach items="${cs}" var="c" varStatus="stc">
		<c:if test="${stc.count<=categorycount}">
			<li><a href="#}" categoryId="${stc.count}">
				<span>${c.name}</span></a></li>
		</c:if>
	</c:forEach>
	

  </ul>
</dd>
</dl>

</div>


<div id="piclist" class="main" style="margin-top:10px;">
      <div class="main_le2">
          <div class="list wyZoomimgparent">
          

          	<c:forEach items="${cs}" var="c" varStatus="stc">
			<c:if test="${stc.count<=categorycount}">
				<div class="eachHomepageCategoryProducts" categoryId="${stc.count}" >
					<c:forEach items="${c.products}" var="p" varStatus="st">
						<c:if test="${st.count<=16}">
							
							

				<dl>
                <dt><a href="foreproduct?pid=${p.id}" title="${p.name}"><div style="min-width: 280px; height: 220px; margin: 0px auto; display: none;" onmouseover="activeIt(this,1)" onmouseout="activeIt(this,0)" id="line192929"></div><img onmouseover="activeIt(this,1)" onmouseout="activeIt(this,0)" style="" id="image${p.id}" src="img/productSingle_middle/${p.firstProductImage.id}.jpg" width="280" height="190" title="${p.name}"></a></dt>
                <dd>
                    <a href="foreproduct?pid=${p.id}" class="listname" title="${p.name}">${p.name}</a>
                     
                      <p><span>回收价：</span><s class="swd01"><em>${p.price}</em> 元/${p.unit}</s><i> <td><fmt:formatDate value="${p.createDate}" pattern="yyyy-MM-dd"/></td> </i></p>
                     <p><span>回收方：</span><s>回收平台</s></p>
                     <p><span>起收量：</span><s class="swd01">${p.startNumber}&nbsp;${p.unit}起收</s>


                     <a href="foreorder?pid=${p.id}" class="gongh" title="预约上门回收">预约上门回收</a></p>
                </dd>
            </dl>


							



						</c:if>				
					</c:forEach>

					<div style="clear:both"></div>
				</div>
			</c:if>
			</c:forEach>

          	

      </div>
  </div>

	
	

</div>