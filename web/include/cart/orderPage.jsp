<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
	
<script>



$(function(){

   $("#sumbitform").mouseenter(function(){

      $("#sumbitform").attr("href","#");
      if(!checkNumber("number","回收品数量"))return;
      if(!checkEmpty("reservation","预约时间"))return;
      if(!checkEmpty("address","看货地点"))return;
      if(!checkEmpty("receiver","联系人"))return;
      if(!checkEmpty("mobile","联系电话"))return;
      

      var url = "forecreateOrder";
      var link = url+"?pid="+$("#pid").val()+"&number="+$("#number").val()+"&reservation="+$("#reservation").val()+"&address="+$("#address").val()+"&receiver="+$("#receiver").val()+"&mobile="+$("#mobile").val()+"&userMessage="+$("#userMessage").val() ;
      $("#sumbitform").attr("href",link);



   });
});
    
</script>


<!-- <script>
$(function(){
   $("sumbitform").hover(function(){
      var data = $("#allform").serialize();
      var url = "forecreateOrder";
      var link = url+"?"+ data;
      $("sumbitform").html(link);
      $("sumbitform").attr("href",link);
   });
});
</script> -->

<title>上门预约</title>


<div class="main" style="height:800px">
	
	<div class="main_le" style="height:800px">
    	<span class="bianhao"><a href="foreproduct?pid=${p.id}">回收编号：${p.id}</a></span>
        <div class="le_con">
        	<span class="name"><a href="foreproduct?pid=${p.id}">${p.name} </a></span>
            <span class="tu"><a href="foreproduct?pid=${p.id}"><img id="midimg" src="img/productSingle_middle/${p.firstProductImage.id}.jpg" width="200" height="160">
        </a></span>
            <ul class="info1">
            	<li><span class="grey">回收图书：</span>${p.name}</li>
            	<li><span class="grey">回收价格：</span><span class="red">${p.price}元</span>/${p.unit}</li>
            	<li><span class="grey">起 收 量 ：</span>${p.startNumber}${p.unit}起收</li>
            	<li><span class="grey">回 收 方 ：</span>回收O2O平台</li>
                <li><span class="grey">报价日期：</span><fmt:formatDate value="${p.createDate}" pattern="yyyy-MM-dd"/></li>
            </ul>
        </div>
        <div class="liuch">
          <dl>
              <dt>回收流程</dt>
              <dd><s class="lc01"></s><span>1.发布预约</span></dd>
              <dd><s class="lc02"></s><span>2.上门验货</span></dd>
              <dd><s class="lc03"></s><span>3.确认项目</span></dd>
              <dd><s class="lc04"></s><span>4.回收交割</span></dd>
          </dl>
        </div>
    </div>


    <form  id="allform" method="post"  action="forecreateOrder" enctype="multipart/form-data">


    <div class="main_ri">
		<div class="ri_tit">
        	<h3>您有${p.name}需要处理吗？请预约上门回收</h3>
        </div>
        <div class="ri_con">
        	<div class="ri_line">
                <div class="fpqk">
                    <h3><span style=" float:left">1.</span><i class="yybz01"></i></h3>
                    <dl>
                        <dt><em>*</em>废品名称：</dt>
                        <dd><span>${p.name}</span>
                        </dd>
                         
                    </dl>
                    <dl>
                        <dt><em>*</em>数量：</dt>
                        <dd><input type="text" class="number" id="number" >
                            <span>${p.unit}</span>
                        </dd>
                    </dl>
                </div>
                
                <div class="yysm">
                   <h3><span class="zfd">2.</span><i class="yybz02"></i></h3>
                    <dl>
                        <dt><em>*</em>预约时间：</dt>
                        <dd><input name="reservation" type="text" id="reservation" class="reservation" ><span>起</span>
                            
                            <span>可看货</span>
                        </dd>
                    </dl>
                    <dl>
                        <dt><em>*</em>看货地点：</dt>
                        <dd><input type="text" class="address" id="address"  >
                            <span class="tishi" id="address_ts"></span>
                        </dd>
                    </dl>
                </div>
            </div>


          <!--   <div class="photos" id="photos">
            <h3><span class="zfd">3.</span><i class="yybz03"></i><span class="red1">请至少上传一张图片</span></h3>
          

         <table class="addPictureTable" align="center">
        <tr>


            <td class="addPictureTableTD">
              <div>
                <div class="panel panel-warning addPicturediv" "style="float:left;">
                    <div class="panel-heading">新增订单<b class="text-primary"> 单个 </b>图片</div>
                      <div class="panel-body">
                            
                            <form method="post" class="addFormSingle" action="foreorderImage" enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 尺寸400X400 为佳</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="filepathSingle" type="file" name="filepath" />
                                        </td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="pid" value="${p.id}" />
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                      </div>
                  </div>      
			</div>
			</td>
		

                
                         
            
			<td class="addPictureTableTD">
              <div>
            
            <table class="table table-striped table-bordered table-hover  table-condensed "style="float:right;">
                    <thead>
                        <tr class="success">
                            <th>订单单个图片缩略图</th>
                            <th>删除</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pisSingle}" var="pi">
                            <tr>
                                <td>
                                <a title="点击查看原图" href="img/productSingle/${pi.id}.jpg"><img height="50px" src="img/productSingle/${pi.id}.jpg"></a> 
                                </td>
                                <td><a deleteLink="true"
                                    href="admin_productImage_delete?id=${pi.id}"><span
                                        class="     glyphicon glyphicon-trash"></span></a></td>
         
                            </tr>
                        </c:forEach>
                    </tbody>   
                </table> 
                  </div>         
            </td>
			
            
        </tr>
   		</table>




          </div> -->


            <div class="lxfs">
                <h3><span class="zfd">3.</span><i class="yybz04"></i></h3>
                <dl>
                    <dt><em class="zfd">*</em><i class="yybz05"></i><span class="zfd">联系人：</span></dt>
                    <dd><input name="receiver" type="text" id="receiver" class="inputstyle01"  >
                    	<span class="tishi" id="linkman_ts"></span>
                    </dd>
                </dl>
                <dl>
                    <dt><em class="zfd">*</em><i class="yybz06"></i><span class="zfd">手机号：</span></dt>
                    <dd><input name="mobile" type="text" id="mobile" class="inputstyle01"  >
                    </dd>
                </dl>
                <dl>
                    <dt><i class="yybz06"></i><span class="zfd">备注：</span></dt>
                    <dd><input name="userMessage" type="text" id="userMessage" class="inputstyle01"  >
                    </dd>
                </dl>
                
                
            </div>
            <input id="pid" type="hidden" name="pid" value="${p.id}" />
                                            
            <a href="#" id="sumbitform"><div class="tijiao"><input type="button"  value="提交预约回收" >  </div></a>



        </div>
		</div>
		</form>
</div>