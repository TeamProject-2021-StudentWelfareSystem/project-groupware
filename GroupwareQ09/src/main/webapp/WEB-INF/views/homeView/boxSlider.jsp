<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/jquery.bxslider.js"></script>
    <link href="css/jquery.bxslider.css" rel="stylesheet"/>
    <link href="css/boxSlider.css" rel="stlyesheet"/>
    <link rel="stylesheet" href="css/main.css">
    <script>
        $(function(){
            $(".bxSlider").bxSlider({
                
                auto: true,
                speed:500,
                mode: 'fade',
                autoControls: true,
                stopAutoOnClick: true,
                pager: true,
                controls: true,
            });
        });

    </script>
 
</head>
<body>
    
    <section id="main">
        <div class="bxSlider">
            <div><img src="resources/image/ad1.png" alt="ad1"></div>
            <div><img src="resources/image/ad2.png" alt="ad2"></div>
            <div><img src="resources/image/ad3.png" alt="ad3"></div>
        </div>
    </section>
</body>
</html> 