<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	usuario.formatForm();
	<c:if test="${operacion == 'edit'}">
		function showInformationIntoView(usuario){
			$('input[id=id]').val(usuario.id);
			$('input[id=nombre]').val(usuario.nombre);
			$('input[id=apellidos]').val(usuario.apellidos);
			$('input[id=email]').val(usuario.email);
			$('input[id=usuario]').val(usuario.usuario);
			if(usuario.admin == 0){
				$('input:radio[name=tipoUsuario]')[0].checked = true;
			} else if (usuario.admin == 1){
				$('input:radio[name=tipoUsuario]')[2].checked = true;
			} else if (usuario.admin ==2){
				$('input:radio[name=tipoUsuario]')[1].checked = true;
				$("#btnRutas").button("enable");
			}
		};
	</c:if>
</script>
<form id="alta">
	<fieldset>
		<legend>
		<c:choose>
			<c:when test="${operacion == 'new'}">Alta de Usuario</c:when>
			<c:otherwise>Edici&oacute;n de Usuario</c:otherwise>
		</c:choose>
		</legend>
		<input type="hidden" id="id" />
		<p>
			<label for="nombre">Nombre:</label>
			<input id="nombre" type="textbox" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="apellidos">Apellidos:</label>
			<input id="apellidos" type="textbox" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="email">Email:</label>
			<input id="email" type="textbox" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="usuario">Usuario:</label>
			<input id="usuario" type="textbox" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="pwd">Contrase&ntilde;a:</label>
			<input id="pwd" type="password" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="admin">Tipo de usuario:</label>
			<input id="chk_usuario" name="tipoUsuario" type="radio" value="0" />Usuario
			<input id="chk_gestor" name="tipoUsuario" type="radio" value="2" />Gestor
			<input id="chk_administrador" name="tipoUsuario" type="radio" value="1" />Administrador
		</p>
		<div class="botonera">
			<c:choose>
				<c:when test="${operacion == 'new'}">
					<input type="button" id="btnSaveQuizz" value="Guardar" />
					<input type="button" id="btnRutas" value="Asignar Rutas" />
				</c:when>
				<c:otherwise>
					<input type="button" id="btnSaveQuizz" value="Modificar" />
					<input type="button" id="btnRutas" value="Modificar Rutas" />
				</c:otherwise>
			</c:choose>
			<input type="button" id="btnCancel" value="Cancelar" />
		</div>
		<div id="dialog-form" title="Asignar rutas">
			<div id="list-box"></div>
		</div>
	</fieldset>
</form>