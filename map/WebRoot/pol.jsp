<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>GRACE实例：绘制多边形，并保存</title>
		<!-- 页面布局样式 -->
		<link rel="stylesheet" type="text/css"
			href="/Public/css/demo.Default.css" />
		<!--样式-->
		<style type="text/css">
.aa {
	width: 200px;
	height: 44px;
	border: 0;
}

.aa:active {
	background-image: url(img2.png);
}
</style>
        <script type='text/javascript' src='/map/dwr/engine.js'></script>
		<script type='text/javascript' src='/map/dwr/util.js'></script>
		<script type='text/javascript'
			src='/map/dwr/interface/polService.js'></script>
		<script language="javascript"
			src="http://webapi.amap.com/maps?v=1.2&key=12b94ad58f95eb8e946dd1588c65d658"></script>
		<script language="javascript">
var mapObj,mouseTool;
var pointsSum,arrPoints;
//初始化地图对象，加载地图
function mapInit(){
	mapObj = new AMap.Map("iCenter");
	
	//设置多边形的属性
	var polygonOption = {
    strokeColor:"#FF33FF",	
    strokeOpacity:1,
    strokeWeight:2	
	};
	
	//在地图中添加MouseTool插件
	mapObj.plugin(["AMap.MouseTool"],function(){ 
		 mouseTool = new AMap.MouseTool(mapObj); 
		 mouseTool.polygon(polygonOption);   //使用鼠标工具绘制多边形
			AMap.event.addListener(mouseTool,"draw",function(e){
			var drawObj = e.obj;  //obj属性就是绘制完成的覆盖物对象。
			var pointsCount = e.obj.getPath().length; //获取节点个数
			document.getElementById('resultInfo').innerHTML ="多边形节点数："+pointsCount+"<br>节点坐标："+e.obj.getPath();
			pointsSum=pointsCount;
			arrPoints=e.obj.getPath();
		});
	});
	}
	
function moveMyAdd(){
	mapObj.clearMap();
}
function save(){
 	if(document.getElementById('saveType').value=="请选择"){
 		alert("请您选择你要保存的名称...");
 		return;
 	}else{
 	  	var type=document.getElementById('saveType').value;
 	  	var can=pointsSum+":"+arrPoints+":"+type;
 	  	polService.addPol(can,function(res){
 	  	  if(res=="pol"){
 	  	    alert("保存成功！");
 	  	  }
 	  	  else{
 	  	    alert("保存失败！");
 	  	  }
 	  	});
 	}
}
function show(){
    mapObj.clearMap();
    var type=document.getElementById('typeShow').value;
    alert(type);
 	if(type=="请选择"){
 		alert("请您选择你要显示的名称...");
 		return;
 	}else{	 		
 	  	polService.showPol(type,function(list){
 	  	  if(list!=null){
 	  	    for(var i=0;i<list.length;i++){//多少个多边形
 	  	    
 	  	      var arr = new Array();//经纬度坐标数组   
 	  	      for(var j=0;j<list[i].length;j++){//一个多边形有多少个经纬度做标组
 	  	       var tmp=list[i][j];
 	  	       arr.push(new AMap.LngLat(tmp[0],tmp[1])); 
 	  	      }
 	  	      
 	  	       var polygon=new AMap.Polygon({
		       id:"polygon"+i,//多边形ID
		       path:arr,//多边形顶点经纬度数组
	 	       strokeColor:"#0000ff",//线颜色
		       strokeOpacity:0.2,//线透明度
		       strokeWeight:3,	//线宽
		       fillColor: "#f5deb3",//填充色
		       fillOpacity: 0.35 //填充透明度
		       });
		       
		       mapObj.addOverlays(polygon);
	           //调整视野到合适的位置及级别
	           mapObj.setFitView();
 	  	    }
 	  	  }else{
 	  	    alert("没有历史标记信息");
 	  	  }
 	  	})
 	}
}

function clearHis(){
  polService.clearHis(function(res){
    if(res){
     alert("您的多边形历史标记信息已经清除");
    }
  });
}
</script>
	</head>
	<body onLoad="mapInit()">
		<div>
			<b>实例:绘制多边形，标记我的信息，并可保存</b>
		</div>
		<br />
		<div id="bodyDiv" style="width: 1000px;">

			<div
				style="width: 600px; height: 650px; float: left; border-color: #0000FF; border-width: 2px; border-style: solid;">
				<div id="iCenter" style="height: 550px; width: 600px;"></div>
				<div style="margin-top: 10px; margin-left: 10px">
					鼠标点击绘制多边形，右键结束绘制
				</div>
				<div id="resultInfo"
					style="margin-top: 2px; margin-left: 10px; height: 50px"></div>
			</div>

			<div
				style="height: 650px; width: 380px; float: right; border-width: 2px; border-style: solid; border-color: grey; padding-left: 5px;">

				<p>
					<input type="button" maxlength="10" value="移除我的多边形" size="20px;"
						onClick="moveMyAdd()" />
				</p>
				<p>
					<input type="button" maxlength="10" value="保存我的多边形" size="20px;"
						onClick="save()" />
					<select id="saveType" name="polygon.type">
					    <option selected="selected" value="请选择">
						    请选择
						</option>
						<option value="小区">
							小区
						</option>
						<option value="学校">
							学校
						</option>
						<option value="公交站">
							公交站
						</option>
					</select>
				</p>
				<p>
					<input type="button" maxlength="10" value="显示我的多边形" size="20px;"
						onClick="show()" />
					<select id="typeShow">
						<option selected="selected" value="请选择">
							请选择
						</option>
						<option value="小区">
							小区
						</option>
						<option value="学校">
							学校
						</option>
						<option value="公交站">
							公交站
						</option>
					</select>
				</p>
				<p>
				  <input type="button" maxlength="10" value="清除历史标记信息" size="20px;"
						onClick="clearHis()" />
				</p>
			</div>

		</div>
	</body>
</html>