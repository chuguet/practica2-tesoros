<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<head>
		<title>Treasures Administrator</title>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="description" content="Pantalla de acceso al sistema" />
		
		<link href="resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/generic.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/prettify.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="resources/js/jquery-1.8.2.min.js"></script>
		<script type="text/javascript" src="resources/js/jquery.alerts.js"></script>
		<link href="resources/css/jquery.alerts.css" rel="stylesheet" type="text/css">
	    <script src="resources/js/jquery-ui-1.9.1.min.js" type="text/javascript"></script>
		<script src="resources/js/jquery.alerts.js" type="text/javascript"></script>
	</head>
	<body onload='document.f.j_username.focus();' class="web">
		<form name='f' id='f' action="<c:url value='j_spring_security_check' />" method='POST'>
			<fieldset class="login">
				<legend>Acceso a Treasures Administrator</legend>
					<span>Usuario:</span>
					<input type='text' name='j_username' value='' class="text ui-widget-content ui-corner-all" />
					<span>Contrase&ntilde;a:</span>
					<input type='password' name='j_password'  class="text ui-widget-content ui-corner-all" />
					<input name="submit" type="submit" value="Aceptar" class="btn" /><input name="reset" type="reset" value="Cancelar" class="btn" />
			</fieldset>
		</form>
	</body>
	<script type="text/javascript">
		<c:if test="${not empty error}">
		jAlert('${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}', 'Error');
		</c:if>
		<c:if test="${noAccess}">
		jAlert('No tiene acceso a la administraci&oacute;n del sistema', 'Acceso prohibido');
		</c:if>
		$('input[type=submit]').button();
		$('input[type=reset]').button();
		
		var height = $('body').height
	</script>

</html>