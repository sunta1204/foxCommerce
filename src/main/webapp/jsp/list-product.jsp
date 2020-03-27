<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head><meta charset="utf-8"></head>
<body>
	<table border="1">
		<tr>
			<th>ชื่อสินค้า</th>
			<th></th>
		</tr>
		<c:forEach items="${productList}" var="product">
			<tr>
         		<td><a href="/product/${product.pid}">${product.productName}</a></td>
         		<td>
         			<a href="">แก้ไข</a> | 
         			<a href="">ลบ</a>
         		</td>
         	</tr>
		</c:forEach>
	</table>

	<br><a href="">เพิ่มสินค้าใหม่</a>
</body>
</html>