<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function showInformationIntoView(registro) {
		estadistica.mostrarEstadistica(registro);
	};
</script>

<fieldset id="estadistica">
	<legend id="rutaText">Resultados de la ruta </legend>
	<p id="fecha_inicio"></p> 
	<p id="fecha_fin"></p>
	<p id="hitos_check"></p>
	<div id="map_canvas" style="width:100%; height:300px"></div>
</fieldset>