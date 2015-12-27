<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath %>" />
<title>ajax 测试</title>
<script type="text/javascript" src="<%=basePath %>js/jquery/jquery.js"></script> 
<script type="text/javascript">
$(function(){  
    $("form :button").click(function(){  
        var name = $("#name").val();  
        var age = $("#age").val();  
        $.ajax({  
               type: "POST",  
               url: "user/addUser.html",  
               data: {name:name , age:age},  
               success:function(data){
                   alert("名字:" + data.name + "年龄:" + data.age);  
               }  
        });  
    });  
}); 
</script>  
</head>
<body>  
<form action="user/addUser.html" method="post">  
        用户名:<input type="text" id="name" name="name" /><br/>  
        年龄:<input type="text" id="age" name="age" />  
        <input type="button" value="提交" />  
    </form>    
</body>  
</html>