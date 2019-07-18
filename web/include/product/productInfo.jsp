

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<div class="main_ri1">
		<h1>${p.name}</h1>
        <span class="huoyuan">回收物ID：${p.id}</span>
        <dl><dt>废品名称：</dt><dd>${p.name}</dd></dl>
        <dl><dt>回收价格：</dt><dd><span class="hsjg">${p.price}元/${p.unit}</span></dd></dl>
        <dl><dt>起 收 量 ：</dt><dd>${p.startNumber}${p.unit}起收</dd></dl>
        <dl><dt>回 收 方：</dt><dd>回收O2O平台</dd></dl>
        <dl><dt>报价日期：</dt><dd><fmt:formatDate value="${p.createDate}" pattern="yyyy-MM-dd"/></dd></dl>
        <div class="btnbox">
            <span class="getlink" style="display:"><a href="foreorder?pid=${p.id}">预约上门回收</a></span>
            
        </div>
        
    </div>
</div>
