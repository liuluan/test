<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>GRACEʵ�������ƶ���Σ�������</title>
		<!-- ҳ�沼����ʽ -->
		<link rel="stylesheet" type="text/css"
			href="/Public/css/demo.Default.css" />
		<!--��ʽ-->
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
//��ʼ����ͼ���󣬼��ص�ͼ
function mapInit(){
	mapObj = new AMap.Map("iCenter");
	
	//���ö���ε�����
	var polygonOption = {
    strokeColor:"#FF33FF",	
    strokeOpacity:1,
    strokeWeight:2	
	};
	
	//�ڵ�ͼ�����MouseTool���
	mapObj.plugin(["AMap.MouseTool"],function(){ 
		 mouseTool = new AMap.MouseTool(mapObj); 
		 mouseTool.polygon(polygonOption);   //ʹ����깤�߻��ƶ����
			AMap.event.addListener(mouseTool,"draw",function(e){
			var drawObj = e.obj;  //obj���Ծ��ǻ�����ɵĸ��������
			var pointsCount = e.obj.getPath().length; //��ȡ�ڵ����
			document.getElementById('resultInfo').innerHTML ="����νڵ�����"+pointsCount+"<br>�ڵ����꣺"+e.obj.getPath();
			pointsSum=pointsCount;
			arrPoints=e.obj.getPath();
		});
	});
	}
	
function moveMyAdd(){
	mapObj.clearMap();
}
function save(){
 	if(document.getElementById('saveType').value=="��ѡ��"){
 		alert("����ѡ����Ҫ���������...");
 		return;
 	}else{
 	  	var type=document.getElementById('saveType').value;
 	  	var can=pointsSum+":"+arrPoints+":"+type;
 	  	polService.addPol(can,function(res){
 	  	  if(res=="pol"){
 	  	    alert("����ɹ���");
 	  	  }
 	  	  else{
 	  	    alert("����ʧ�ܣ�");
 	  	  }
 	  	});
 	}
}
function show(){
    mapObj.clearMap();
    var type=document.getElementById('typeShow').value;
    alert(type);
 	if(type=="��ѡ��"){
 		alert("����ѡ����Ҫ��ʾ������...");
 		return;
 	}else{	 		
 	  	polService.showPol(type,function(list){
 	  	  if(list!=null){
 	  	    for(var i=0;i<list.length;i++){//���ٸ������
 	  	    
 	  	      var arr = new Array();//��γ����������   
 	  	      for(var j=0;j<list[i].length;j++){//һ��������ж��ٸ���γ��������
 	  	       var tmp=list[i][j];
 	  	       arr.push(new AMap.LngLat(tmp[0],tmp[1])); 
 	  	      }
 	  	      
 	  	       var polygon=new AMap.Polygon({
		       id:"polygon"+i,//�����ID
		       path:arr,//����ζ��㾭γ������
	 	       strokeColor:"#0000ff",//����ɫ
		       strokeOpacity:0.2,//��͸����
		       strokeWeight:3,	//�߿�
		       fillColor: "#f5deb3",//���ɫ
		       fillOpacity: 0.35 //���͸����
		       });
		       
		       mapObj.addOverlays(polygon);
	           //������Ұ�����ʵ�λ�ü�����
	           mapObj.setFitView();
 	  	    }
 	  	  }else{
 	  	    alert("û����ʷ�����Ϣ");
 	  	  }
 	  	})
 	}
}

function clearHis(){
  polService.clearHis(function(res){
    if(res){
     alert("���Ķ������ʷ�����Ϣ�Ѿ����");
    }
  });
}
</script>
	</head>
	<body onLoad="mapInit()">
		<div>
			<b>ʵ��:���ƶ���Σ�����ҵ���Ϣ�����ɱ���</b>
		</div>
		<br />
		<div id="bodyDiv" style="width: 1000px;">

			<div
				style="width: 600px; height: 650px; float: left; border-color: #0000FF; border-width: 2px; border-style: solid;">
				<div id="iCenter" style="height: 550px; width: 600px;"></div>
				<div style="margin-top: 10px; margin-left: 10px">
					��������ƶ���Σ��Ҽ���������
				</div>
				<div id="resultInfo"
					style="margin-top: 2px; margin-left: 10px; height: 50px"></div>
			</div>

			<div
				style="height: 650px; width: 380px; float: right; border-width: 2px; border-style: solid; border-color: grey; padding-left: 5px;">

				<p>
					<input type="button" maxlength="10" value="�Ƴ��ҵĶ����" size="20px;"
						onClick="moveMyAdd()" />
				</p>
				<p>
					<input type="button" maxlength="10" value="�����ҵĶ����" size="20px;"
						onClick="save()" />
					<select id="saveType" name="polygon.type">
					    <option selected="selected" value="��ѡ��">
						    ��ѡ��
						</option>
						<option value="С��">
							С��
						</option>
						<option value="ѧУ">
							ѧУ
						</option>
						<option value="����վ">
							����վ
						</option>
					</select>
				</p>
				<p>
					<input type="button" maxlength="10" value="��ʾ�ҵĶ����" size="20px;"
						onClick="show()" />
					<select id="typeShow">
						<option selected="selected" value="��ѡ��">
							��ѡ��
						</option>
						<option value="С��">
							С��
						</option>
						<option value="ѧУ">
							ѧУ
						</option>
						<option value="����վ">
							����վ
						</option>
					</select>
				</p>
				<p>
				  <input type="button" maxlength="10" value="�����ʷ�����Ϣ" size="20px;"
						onClick="clearHis()" />
				</p>
			</div>

		</div>
	</body>
</html>