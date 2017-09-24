<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>评价书籍</title>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
    href="<c:url value='/jsps/css/order/list.css'/>" />
<link rel="stylesheet" type="text/css"
    href="<c:url value='/jsps/pager/pager.css'/>" />
<%-- <script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script> --%>
</head>

<body>
    <div class="divMain"> 
    	<form  action="<c:url value='/EvaluateServlet?method=evaluate&bid=${book.bid }&oid=${order.oid}'/>" method="post">     
        <table align="center" border="0" width="100%" cellpadding="0" cellspacing="0">
            <tr class="tt">
                <td width="320px" class="brlf">订单号：${order.oid }</td>
                <td>&nbsp;</td>
                 <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td class="brrt">&nbsp;</td>
            </tr>
            <tr style="padding-top: 10px; padding-bottom: 10px;">
                <td colspan="2">
                    <a class="link2" href="<c:url value='/bookServlet?method=load&bid=${book.bid }'/>"> 
                    <img border="0" width="200" height="200" src="<c:url value='/${book.image_w }'/>" /></a>
                    <br/>                   
                </td>
           	</tr>
	           	<td >
	           	 ${book.bname}
	           	</td>
           	<tr>
           	</tr>
           	 <tr style="padding-top: 10px; padding-bottom: 10px;">                             
                <td width="142px">
                    <input type="text" style="width:500;height:200 " name="evaluate"/> 
                    <br/>          
                </td>               
           	</tr>
           	
        </table>
        <input type="submit" value="评价">
       </form> 
    </div>
</body>
</html>
