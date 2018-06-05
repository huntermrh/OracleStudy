<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<base  href="${pageContext.request.scheme }://${pageContext.request.serverName}:${pageContext.request.serverPort}/DRManager/">
	<meta charset="UTF-8">
	<title>DR商城后台管理系统</title>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body background="images/login1.jpg" style="background-size: cover">

<canvas id="c" style="position: absolute;z-index: -100;margin: 0px;width:96%"></canvas>
	<div style="width: 400px;margin: auto;margin-top: 100px;">
		<div class="easyui-panel" title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DR商城-后台管理系统-用户登陆"  region="center" style="width:400px;max-width:400px;padding:30px 60px;margin: auto">
	
		<form id="ff" method="post" action="admin/UserAction!login.action">
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="u.shouJiHao" value="admin" style="width:100%" data-options="label:'账户:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-passwordbox" name="u.password" value=""  style="width:100%" data-options="label:'密码:',required:true">
			</div>
			<div style="margin-bottom:20px">
				<input class="easyui-textbox" name="code"  maxlength="4" style="width:60%" data-options="label:'验证码:'">
				<img id="code" src="admin/CodeAction.action" style="width: 90px;height: 30px;position: relative;top: 11px;margin-left: 10px;" />
			</div>
		</form>
		<div style="text-align:center;padding:5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px">登陆</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
		</div>
	</div>
	</div>
	<script>
		function alert1(title,message){
			$.messager.alert(title,message);
		}
		function errorMessage(title,message){
			$.messager.alert(title,message,'error');
		}
		function infoMessage(title,message){
			$.messager.alert(title,message,'info');
		}
		function questionMessage(title,message){
			$.messager.alert(title,message,'question');
		}
		function warningMessage(title,message){
			$.messager.alert(title,message,'warning');
		}
	</script>
	
	
	<script>
		function submitForm(){
			var  code=$("[name='code']").val();
			$.get("admin/CodeAction!checkCode.action?code="+code,function(data){
				if(data=='false')
				{
					$('#dlg').html("<span style='color:red;font-weight:bold'>验证码错误！</span>");
					errorMessage('温馨提示','验证码错误！');
					$('#dlg').dialog('open');
				}else
				{
					$('#ff').submit();
				}
			})
		}
		function clearForm(){
			$('#ff').form('clear');
		}
		$(document).ready(function(){
			$('#dlg').dialog('close');//网页一打开让提示框消失
			
			$("#code").click(function(){
				$(this).attr("src","admin/CodeAction.action?time="+new Date())
			})
			
		})
	</script>

</body>
</html>