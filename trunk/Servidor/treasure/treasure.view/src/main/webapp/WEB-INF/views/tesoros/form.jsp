<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	encuesta.formatForm();
	<c:if test="${operacion == 'edit'}">
		function showInformationIntoView(encuesta){
			$('#id').val(encuesta.id);
			$('#nombre').val(encuesta.nombre);
			$('#fecha_inicio').val(encuesta.fecha_inicio);
			$('#fecha_fin').val(encuesta.fecha_fin);
			for (var i= 0; i < encuesta.preguntasDTO.length; i++){
				$("#tree").dynatree("getRoot").addChild(encuesta.preguntasDTO[i]);
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
			<input type="button" id="btnAddQuestion" value="Añadir pregunta" />
			<input type="button" id="btnModifyQuestion" value="Modificar pregunta" disabled="true" />
			<input type="button" id="btnDeleteQuestion" value="Eliminar" disabled="true" />
		</div>
	</fieldset>
</form>

<div id="dialog-form" title="A&ntilde;adir Pregunta">
	<form id="frmPreguntas">
		<label for="nombrePregunta">Pregunta</label><br /> 
		<input type="text" name="nombrePregunta" id="nombrePregunta" class="text ui-widget-content ui-corner-all" key="" /> 
		<label for="respuesta">Respuesta</label><br /> 
		<input type="text" name="respuesta" id="respuesta" value="" class="text ui-widget-content ui-corner-all" />
		<input type="button" id="btnAddResponse" value="A&ntilde;adir" /> 
		<input type="button" id="btnDeleteResponse" value="Quitar" disabled="true" /> 
		<label for="respuestas">Respuestas</label><br /> 
		<select size="8" id="respuestas" name="respuestas" />
	</form>
</div>