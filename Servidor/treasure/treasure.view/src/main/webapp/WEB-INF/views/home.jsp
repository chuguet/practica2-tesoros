<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no" />
		<title>Treasures Administrator</title>
		
		<link href="resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/generic.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/ui.dynatree.css" rel="stylesheet" type="text/css">
		<link href="resources/css/prettify.css" rel="stylesheet" type="text/css">
		<link href="resources/css/jquery.alerts.css" rel="stylesheet" type="text/css">
		<link href="resources/css/ui.jqgrid.css" rel="stylesheet" type="text/css">
		<link href="resources/css/ui.graph.css" rel="stylesheet" type="text/css">
		<link href="resources/css/redactor.css" rel="stylesheet" type="text/css">
		<!--  <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAIuX3SNPXeixU2JzGntpW2KgHvwOxq-tM&sensor=true"></script>-->
		<script src="http://maps.google.com/maps?file=api&amp;v=2&key=AIzaSyAIuX3SNPXeixU2JzGntpW2KgHvwOxq-tM&sensor=false" type="text/javascript"></script>
		<script src="resources/js/jquery.require.js" type="text/javascript"></script>
		<script src="resources/js/jquery-1.8.2.min.js" type="text/javascript"></script>
		<script src="resources/js/jquery-ui-1.9.1.min.js" type="text/javascript"></script>
		<script src="resources/js/jquery.jstree.js" type="text/javascript"></script>
		<script src="resources/js/generic.js" type="text/javascript"></script>
		
	  	<script src="resources/js/jquery.dynatree.js" type="text/javascript"></script>
		<script src="resources/js/jquery.alerts.js" type="text/javascript"></script>
		<script src="resources/js/jquery.jqGrid.min.js" type="text/javascript"></script>
		<script src="resources/js/i18n/grid.locale-es.js" type="text/javascript"></script>
		<script src="resources/js/redactor.js" type="text/javascript"></script>
		
		<script src="resources/js/views/ruta.js" type="text/javascript"></script>
		<script src="resources/js/views/usuario.js" type="text/javascript"></script>
		<script src="resources/js/views/estadisticaRuta.js" type="text/javascript"></script>
		<script src="resources/js/views/estadisticaUsuario.js" type="text/javascript"></script>
		<script src="resources/js/views/configuracion.js" type="text/javascript"></script>
		
	</head>
	<body id="quizz" class="web">
		
		<div id="header">
			<h1>Gestor de rutas del tesoro</h1>
		</div>
		
		<div id="menu">
		<c:if test="${usuario == '1'}">
		<a href="javascript:void" onclick="javascript:generic.getList('usuario');" title="Ver usuarios" class="itemMenu">Usuarios</a>
			<a href="javascript:void" onclick="javascript:generic.getList('ruta');" title="Ver rutas del tesoro" class="itemMenu">Rutas del tesoro</a>
			<a href="javascript:void" onclick="javascript:generic.getForm('configuracion', 1);" title="Ver configuraci&oacute;n del sistema" class="itemMenu">Configuraci&oacute;n</a>
		</c:if>
		<c:if test="${usuario == '2'}">
			<a href="javascript:void" onclick="javascript:generic.getList('gestor',${id_usuario});" title="Ver gesti&oacute;n de rutas" class="itemMenu">Gestor</a>
		</c:if>
			<div id="usuario">
				<span>Usuario : ${nombre} ${apellidos}</span> 
				<a href="<c:url value="/j_spring_security_logout" />" id="btnUnlogin" title="Salir"><span>Salir</span></a>
			</div>
		</div>
		
		
		<div id="content">
			<img src="resources/imgs/tesoro.png" alt="imagenes"/>
		</div>
		
		<div id="footer">
			Movember S.L [Yamaradax & Huguet Association] &#174;
		</div>
	</body>
	<script type="text/javascript">
		generic.resizeEvent();
	</script>
</html>