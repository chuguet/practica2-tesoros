var estadisticaUsuario = {
	'mostrarEstadisticaUsuario' : function(registro) {
		// Se muestran los datos principales
		var nombreUsuario = $('#usuarioText').text() + registro.usuario;
		$('#usuarioText').text(nombreUsuario);
		var porcentajeRutas = 'Hay un global de ' + registro.num_rutas_totales + ' rutas, con un total de ' + registro.num_rutas_terminadas + ' rutas realizadas. [' + registro.porcentaje_rutas_totales + '  completado]';
		$('#fieldRuta').text(porcentajeRutas);
		var porcentajeHitos = 'Hay un global de ' + registro.num_hitos_totales + ' hitos, con un total de ' + registro.num_hitos_terminados + ' hitos realizados. [' + registro.porcentaje_hitos_totales + ' completado]';
		$('#fieldHito').text(porcentajeHitos);
		// A�adimos la tabla de porcentajes
		for( var i = 0; i < registro.porcentaje_rutas_hitos.length; i++) {
			var rutaHitoPorcentaje = registro.porcentaje_rutas_hitos[i];

			$('#tabla_rutas').append(
					'<ul><li><a>Ruta: <b>' + rutaHitoPorcentaje.ruta + '</b></a>' + '<ul><li><a>Numero de hitos totales de la ruta: ' + rutaHitoPorcentaje.num_hitos_totales + '.</a></li>' + '<li><a>Numero de hitos necesarios para superar la ruta: ' + rutaHitoPorcentaje.num_hitos_necesarios + '.</a></li>' + '<li><a>Numero de hitos distintos para superar la ruta: '
							+ rutaHitoPorcentaje.num_hitos_distintos + '.</a></li>' + '<li><a>Numero de hitos totales chequeados por el usuario: ' + rutaHitoPorcentaje.num_hitos_checkeados + '. <b>[' + rutaHitoPorcentaje.porcentaje_finalizado + ' completado]</b></a></li><li><a>Numero de hitos distintos chequeados por el usuario: ' + rutaHitoPorcentaje.num_hitos_distintos_checkeados + '. <b>['
							+ rutaHitoPorcentaje.porcentaje_distintos_finalizado + ' completado]</b></a></li></ul></li></ul>');
		}
		$('#tabla_rutas').jstree();
		// A�adimos el mapa con los hitos checkeados
		this.map = new GMap2(document.getElementById("map_canvas"));
		this.map.addControl(new GSmallMapControl());
		this.map.setCenter(new GLatLng(40.4199, -3.694668), 13);

		var bounds = new GLatLngBounds();
		for( var i = 0; i < registro.hitos_terminados.length; i++) {
			var hito = registro.hitos_terminados[i];
			var latitud = hito.latitud;
			var longitud = hito.longitud;
			var point = new GLatLng(latitud, longitud);
			bounds.extend(point);
			var html = createPopUpUsuario(hito);
			var hitoMapa = createMarker(point, html);
			this.map.addOverlay(hitoMapa);
		}
		this.map.setZoom(this.map.getBoundsZoomLevel(bounds));
		this.map.setCenter(bounds.getCenter());
	}
};
function createMarker(point, nombre) {
	var marker = new GMarker(point);
	GEvent.addListener(marker, 'click', function() {
		marker.openInfoWindowHtml(nombre);
	});
	return marker;
}
function createPopUpUsuario(hito) {
	var html = "<b>Nombre del hito: </b>" + hito.nombre + ".<br/>";
	html = html + "<b>C&oacute;digo de etiqueta:</b> " + hito.codigo + "<br/><br/>";
	html = html + "<span style='color: #808080; font-size: 9px;'>Latitud: " + hito.latitud + "</span><br/>";
	html = html + "<span style='color: #808080; font-size: 9px;'>Longitud: " + hito.longitud + "</span></br></br>";
	html = html + "<b>Fecha</b> y <b>hora</b> de checkin: " + hito.fecha_checkin;
	return html;
}