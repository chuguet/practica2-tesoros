<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript">
	function showInformationIntoView(registro) {
		estadisticaUsuario.mostrarEstadisticaUsuario(registro);
	};
</script>

<fieldset id="estadistica">
	<legend id="usuarioText">Estadistica del usuario </legend>
	<p id="fieldRuta"></p>
	<p id="fieldHito"></p>
	<div id="tabla_rutas"></div>
	<p>Hitos <b>checkeados</b> por el usuario:</p>
	<div id="map_canvas" style="width:100%; height:300px"></div>
</fieldset>