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
			for (var i= 0; i < ruta.hitosDTO.length; i++){
				$("#tree").dynatree("getRoot").addChild(ruta.hitosDTO[i]);
			}
		};
	</c:if>
</script>
<form id="alta">
	<fieldset>
		<legend>
		<c:choose>
			<c:when test="${operacion == 'new'}">Alta de Encuesta</c:when>
			<c:otherwise>Edici&oacute;n de Encuesta</c:otherwise>
		</c:choose>
		</legend>
		<input type="hidden" id="id" />
		<p>
			<label for="nombre">Nombre:</label>
			<input id="nombre" maxlength="200" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="fecha_inicio">Fecha de inicio:</label>
			<input id="fecha_inicio" class="text ui-widget-content ui-corner-all" />
		</p>
		<p>
			<label for="fecha_fin">Fecha de fin:</label>
			<input id="fecha_fin" class="text ui-widget-content ui-corner-all" />
		</p>
		<fieldset style="height: 200px;">
			<legend>Preguntas</legend>
			<div id="tree" style="height: 180px;"></div>
		</fieldset>
		<div class="botonera">
			<c:choose>
				<c:when test="${operacion == 'new'}">
					<input type="button" id="btnSaveQuizz" value="Guardar" />
				</c:when>
				<c:otherwise>
					<input type="button" id="btnSaveQuizz" value="Modificar" />
				</c:otherwise>
			</c:choose>
			<input type="button" id="btnCancel" value="Cancelar" /> 
			<input type="button" id="btnAddQuestion" value="Añadir Hito" />
			<input type="button" id="btnModifyQuestion" value="Modificar Hito" disabled="true" />
			<input type="button" id="btnDeleteQuestion" value="Eliminar" disabled="true" />
		</div>
	</fieldset>
</form>

<div id="dialog-form" title="A&ntilde;adir Pregunta">
	<form id="frmHitos">
	<div id="tabs">
	    <ul>
	        <li><a href="#tabs-1">Datos</a></li>
	        <li><a href="#tabs-2">Geoposicionamiento</a></li>
	    </ul>
	    <div id="tabs-1">
			<p>
				<label for="nombreHito">Hito</label> 
				<input type="text" name="nombreHito" id="nombreHito" class="text ui-widget-content ui-corner-all" key="" />
			</p>
			<p> 
				<label for="codigo">C&oacute;digo</label>
				<input type="text" name="codigo" id="codigo" value="" class="text ui-widget-content ui-corner-all" />
			</p>
			
			<p>  
				<label for="pista">Pista</label><br /> 
				<textarea id="pista" name="pista"></textarea>
			</p>
		</div>
		<div id="tabs-2" style="position:relative;">
			<div style="z-index:1; background-color:red; width:3px; height:3px; position:absolute;top:50px; left:50px"></div>
			<iframe width="510" height="270" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="http://maps.google.com/?ie=UTF8&amp;ll=40.420292,-3.67939&amp;spn=0.141926,0.338173&amp;t=m&amp;z=12&amp;output=embed"></iframe>
			 
				<label for="longitud">Longitud</label>
				<input type="text" name="longitud" id="longitud" value="" class="text ui-widget-content ui-corner-all" style="width:170px"/><input type="button" id="btnCoordinates" value="Recuperar Coordenadas" />
				<label for="latitud">Latitud</label>
				<input type="text" name="latitud" id="latitud" value="" class="text ui-widget-content ui-corner-all" style="width:170px" />
			
		</div>
		
	</form>
</div>