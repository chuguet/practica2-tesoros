<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	ruta.formatForm();
	<c:if test="${operacion == 'edit'}">
		function showInformationIntoView(ruta){
			$('#id').val(ruta.id);
			$('#nombre').val(ruta.nombre);
			$('#fecha_inicio').val(ruta.fecha_inicio);
			$('#fecha_fin').val(ruta.fecha_fin);
			$('#hitos_necesarios').val(ruta.hitos_necesarios);
			$('#hitos_distintos').val(ruta.hitos_distintos);
			$('#premio_identificados').val(ruta.premio_identificados);
			$('#premio_no_identificados').val(ruta.premio_no_identificados);
			$("#lista").setGridParam({
				data : ruta.hitosDTO
			}).trigger("reloadGrid");
		};
	</c:if>
</script>
<form id="alta">
	<fieldset>
		<legend>
		<c:choose>
			<c:when test="${operacion == 'new'}">Alta de Ruta</c:when>
			<c:otherwise>Edici&oacute;n de Ruta</c:otherwise>
		</c:choose>
		</legend>
		<input type="hidden" id="id" />
		<input type="hidden" id="premio_identificados" />
		<input type="hidden" id="premio_no_identificados" />
		<p>
			<label for="nombre">Nombre:</label>
			<input id="nombre" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="fecha_inicio">Fecha de inicio:</label>
			<input id="fecha_inicio" class="text ui-widget-content ui-corner-all" />
			<label for="fecha_fin" style="margin-left:30px">Fecha de fin:</label>
			<input id="fecha_fin" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="hitos_necesarios">Hitos necesarios para ganar:</label>
			<input id="hitos_necesarios" class="text ui-widget-content ui-corner-all" />
			<label for="hitos_distintos">Hitos distintos para ganar:</label>
			<input id="hitos_distintos" class="text ui-widget-content ui-corner-all" />
		</p>
		<fieldset>
			<legend>Hitos</legend>
			<table id="lista"></table>
			<div id=paginadorLista></div>
		</fieldset>
		<div class="botonera">
			<c:choose>
				<c:when test="${operacion == 'new'}">
					<input type="button" id="btnSaveTreasure" value="Guardar" />
				</c:when>
				<c:otherwise>
					<input type="button" id="btnSaveTreasure" value="Modificar" />
				</c:otherwise>
			</c:choose>
			<input type="button" id="btnCancel" value="Cancelar" /> 
			<input type="button" id="btnAddHito" value="Añadir Hito" />
			<input type="button" id="btnModifyHito" value="Modificar Hito" disabled="true" />
			<input type="button" id="btnDeleteHito" value="Eliminar Hito" disabled="true" />
			<input type="button" id="btnPremio" value="Premios" />
		</div>
	</fieldset>
</form>

<div id="dialog-form-hitos" title="A&ntilde;adir Hito">
	<form id="frmHitos">
		<div id="tabs">
		    <ul>
		        <li><a href="#tabs-1">Datos</a></li>
		        <li><a href="#tabs-2">Código QR</a></li>
		        <li><a href="#tabs-3">Geoposicionamiento</a></li>
		    </ul>
		    <div id="tabs-1">
				<label for="nombreHito">Hito</label> 
				<input type="text" name="nombreHito" id="nombreHito" class="text ui-widget-content ui-corner-all" key="" /><br/><br />
				
				<label for="pista">Pista</label><br /> 
				<textarea id="pista" name="pista" style="height:200px;"></textarea>
			</div>
			<div id="tabs-2">
				<label for="codigo">C&oacute;digo</label>
				<input type="text" name="codigo" id="codigo" value="" class="text ui-widget-content ui-corner-all" />
				<input type="button" id="btnGenerarQR" value="Generar QR" /><br/>
				<input type="hidden" id="contextQR" value="${contextQR}" />
				<div id="containerQR"></div> 
			</div>
			<div id="tabs-3" style="position:relative;">
				<div id="map_canvas"></div>
				<label for="longitud">Longitud</label>
				<input type="text" name="longitud" id="longitud" value="" class="text ui-widget-content ui-corner-all" /><br/>
				<label for="latitud">Latitud</label>
				<input type="text" name="latitud" id="latitud" value="" class="text ui-widget-content ui-corner-all" />
			</div>
		</div>
	</form>
</div>

<div id="dialog-form-premios" title="Premios usuario">
	<form id="frmPremios">
		<div id="tabs-premios">
		    <ul>
		        <li><a href="#tabs-premios-1">Premio identificados</a></li>
		        <li><a href="#tabs-premios-2">Premio no identificados</a></li>
		    </ul>
		    <div id="tabs-premios-1">
				<textarea id="premio-identificados" name="premio-identificados" style="height:400px;"></textarea>
			</div>
			<div id="tabs-premios-2" style="position:relative;">
				<textarea id="premio-no-identificados" name="premio-no-identificados" style="height:400px;"></textarea>
			</div>
		</div>
	</form>
</div>