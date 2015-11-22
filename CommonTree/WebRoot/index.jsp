<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="./plugin/demo.css" type="text/css">
	<link rel="stylesheet" href="./plugin/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="./plugin/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="./plugin/jquery.ztree.all-3.5.js"></script>
	
	<SCRIPT type="text/javascript">
		
		var setting = {
			async: {
				enable: true,
				url: getUrl
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				addDiyDom: addDiyDom
			},
			callback: {
				beforeExpand: beforeExpand,
				onAsyncSuccess: onAsyncSuccess,
				onAsyncError: onAsyncError
			}
		};
		var curPage = 0;
		function getUrl(treeId, treeNode) {
			var param = "pid="+ treeNode.id +"&className=Node&page="+treeNode.page +"&pageSize="+treeNode.pageSize,
			aObj = $("#" + treeNode.tId + "_a");
			aObj.attr("title", "当前第 " + treeNode.page + " 页 / 共 " + treeNode.maxPage + " 页");
			return "/CommonTree/GetChildrenAction.action?" + param;
		}
		function goPage(treeNode, page) {
			treeNode.page = page;
			if (treeNode.page<1) treeNode.page = 1;
			if (treeNode.page>treeNode.maxPage) treeNode.page = treeNode.maxPage;
			if (curPage == treeNode.page) return;
			curPage = treeNode.page;
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			zTree.reAsyncChildNodes(treeNode, "refresh");
		}
		function beforeExpand(treeId, treeNode) {
			if (treeNode.page == 0) treeNode.page = 1;
			return !treeNode.isAjaxing;
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			
		}
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			alert("异步获取数据出现异常。");
			treeNode.icon = "";
			zTree.updateNode(treeNode);
		}
		function addDiyDom(treeId, treeNode) {
			if (treeNode.count<treeNode.pageSize) return;
			var aObj = $("#" + treeNode.tId + "_a");
			if ($("#addBtn_"+treeNode.id).length>0) return;
			var addStr = "<span class='button lastPage' id='lastBtn_" + treeNode.id
				+ "' title='last page' onfocus='this.blur();'></span><span class='button nextPage' id='nextBtn_" + treeNode.id
				+ "' title='next page' onfocus='this.blur();'></span><span class='button prevPage' id='prevBtn_" + treeNode.id
				+ "' title='prev page' onfocus='this.blur();'></span><span class='button firstPage' id='firstBtn_" + treeNode.id
				+ "' title='first page' onfocus='this.blur();'></span>";
			aObj.after(addStr);
			var first = $("#firstBtn_"+treeNode.id);
			var prev = $("#prevBtn_"+treeNode.id);
			var next = $("#nextBtn_"+treeNode.id);
			var last = $("#lastBtn_"+treeNode.id);
			treeNode.maxPage = Math.round(treeNode.count/treeNode.pageSize - .5) + (treeNode.count%treeNode.pageSize == 0 ? 0:1);
			first.bind("click", function(){
				if (!treeNode.isAjaxing) {
					goPage(treeNode, 1);
				}
			});
			last.bind("click", function(){
				if (!treeNode.isAjaxing) {
					goPage(treeNode, treeNode.maxPage);
				}
			});
			prev.bind("click", function(){
				if (!treeNode.isAjaxing) {
					goPage(treeNode, treeNode.page-1);
				}
			});
			next.bind("click", function(){
				if (!treeNode.isAjaxing) {
					goPage(treeNode, treeNode.page+1);
				}
			});
		};		
	</SCRIPT>
	
	<style type="text/css">
.ztree li span.button.firstPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -16px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.prevPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -48px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.nextPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -64px; vertical-align:top; *vertical-align:middle}
.ztree li span.button.lastPage {float:right; margin-left:2px; margin-right: 0; background-position:-144px -32px; vertical-align:top; *vertical-align:middle}
	</style>
  </head>
  
  <body>
  <button onclick="load()">Load</button>
    <div>
    	<ul id="zTree" class="ztree"></ul>
    </div>
  </body>
  <script type="text/javascript">
  	function load(){
  		$.post("/CommonTree/GetChildrenAction.action",{
  			pid:-1,
  			className:"Node",
  			page:"1",
  			pageSize:"10",
  		},function(data,status){
  			$.fn.zTree.init($("#zTree"), setting, data);
  		});
  	}
  </script>
</html>
