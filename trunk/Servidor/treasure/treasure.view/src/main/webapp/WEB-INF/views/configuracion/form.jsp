<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	configuracion.formatForm();
	function showInformationIntoView(conf){
		configuracion.showInfo(conf);
	}
</script>
<form id="alta">
	<fieldset>
		<legend>Configuraci&oacute;n</legend>
		<p>
			<label for="numero">N&uacute;mero m&uacute;ltiplo para mensaje:</label>
			<input id="numero" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<fieldset>
			<legend>Mensajes</legend>
			<table id="lista"></table>
			<div id=paginadorLista></div>
		</fieldset>
		<div class="botonera">
			<input type="button" id="btnSaveConfiguration" value="Modificar" />
			<input type="button" id="btnCancel" value="Cancelar" /> 
			<input type="button" id="btnAddMessage" value="Añadir Mensaje" />
			<input type="button" id="btnModifyMessage" value="Modificar Mensaje" disabled="true" />
			<input type="button" id="btnDeleteMessage" value="Eliminar Mensaje" disabled="true" />
		</div>
	</fieldset>
</form>

<div id="dialog-form" title="A&ntilde;adir Mensaje">
	<form id="frmMensaje">
		<textarea id="mensaje" name="mensaje" style="height:200px;"></textarea>
	</form>
</div>