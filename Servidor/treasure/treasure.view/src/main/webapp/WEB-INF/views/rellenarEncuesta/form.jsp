<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function showInformationIntoView(encuesta){
		rellenarEncuesta.formatForm(encuesta);
	}
</script>
<form id="alta">
	<input type="hidden" id="id_encuesta" />
	<div class="titlePage"></div>
	<div data-role="fieldcontain" id="preguntas">
	
	</div>
	<p style="text-align:center">
		<a href="#" id="btnEnviar" data-role="button" data-inline="true" data-icon="check" data-mini="true">Enviar</a>
		<a href="#" id="btnVolver" data-role="button" data-inline="true" data-icon="back" data-mini="true">Volver</a>
	</p>
</form>