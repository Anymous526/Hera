<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎进入会生活商户管理平台 - 会员管理-快速导航</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<!--top板块-->
	<c:import url="/manager/include/top.jsp"/>
	<!-- -->
	<!--中间板块-->
	<div id="content">
		<!--left导航-->
		<c:import url="/manager/include/left.htm"/>
		<!-- -->
		<!--right内容-->
		<div id="right">
			<div class="turn_btn">
				<a href="#"><img src="/manager/image/turnleft_btn.jpg" width="4"
					height="37" />
				</a>
			</div>
			<div class="right_all" style="min-height: 440px">
				<!--快速导航-->
				<div class="c_title_left"></div>
				<div class="c_title_right">
					<div class="c_all">
						<div class="Q_nav">
							<img src="/manager/image/icon/07.gif" width="47" height="30" />会员管理-快速导航
						</div>
					</div>
				</div>
				<div class="Q_nav_all">
					<div class="Q_left">
						<img src="/manager/image/btn/Q_left01.jpg" width="12" height="115" />
					</div>
					<div class="Q_right">
						<img src="/manager/image/btn/Q_right01.jpg" width="12" height="115" />
					</div>
					<div class="Q_content">
						<ul>
							<li class="xz"><a href="#"><dd></dd>
							</a>
							</li>
							<li class="cx"><a href="#"><dd></dd>
							</a>
							</li>
							<li class="fb"><a href="#"><dd></dd>
							</a>
							</li>
						</ul>
					</div>
				</div>
				<!--数据一览-->

			</div>
		</div>
	</div>
	<c:import url="/manager/include/footer.jsp"/>
</body>
</html>
