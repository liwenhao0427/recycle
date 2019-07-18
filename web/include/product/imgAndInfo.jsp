



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<c:forEach items="${p.productImages}" var="pi" varStatus="stc">
<c:if test="${stc.count<=5}">
 <script>
    $(function(){
       $("#Img${stc.count}").hover(function(){
            $("#midimg").attr("src","img/productSingle/${pi.id}.jpg" );
       });
    });
</script>
</c:if>
</c:forEach>


<div class="main_le">
        <div class="preview">

            <div id="vertical" class="bigImg">
                
            <img id="midimg" src="img/productSingle/${p.firstProductImage.id}.jpg"></div>
            

            <div class="smallImg">
                <div class="scrollbutton smallImgUp disabled"></div>
                <div id="imageMenu">
                    <ul>

                        <c:forEach items="${p.productImages}" var="pi" varStatus="stc">
                         <li><img id="Img${stc.count}" src="img/productSingle/${pi.id}.jpg"></li>
                        </c:forEach>


                </div>
                <div class="scrollbutton smallImgDown disabled"></div>
            </div>
        </div>
    </div>