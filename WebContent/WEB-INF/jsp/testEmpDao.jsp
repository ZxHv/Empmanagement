<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TestEmpDao</title>
</head>
<body>
	<form action="" method="get">
		员工编号: <input type="text" name="eid" value='${requestScope.eid}'/> 
		员工名称: <input type="text" name="ename" value='${requestScope.ename}'/> 
		所属部门：<input type="text" name="dept" value='${requestScope.dept}'/> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>