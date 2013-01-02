var estadisticaRuta = {
	'mostrarEstadisticaRuta' : function(registro) {
		// Introduzco nombre de la encuesta en un span del jsp
		var nombreRuta = $('#rutaText').text() + registro.ruta;
		$('#rutaText').text(nombreRuta);
		var fechaInicio = 'Fecha de inicio: ' + registro.fecha_inicio;
		$('#fecha_inicio').text(fechaInicio);
		var fechaFin = 'Fecha de fin: ' + registro.fecha_fin;
		$('#fecha_fin').text(fechaFin);
		var contadorTotal = 'Con un total de ' + registro.contador_total
				+ ' hitos chequeados';
		$('#hitos_check').text(contadorTotal);

		this.map = new GMap2(document.getElementById("map_canvas"));
		this.map.addControl(new GSmallMapControl());
		this.map.setCenter(new GLatLng(40.4199, -3.694668), 13);

		for ( var i = 0; i < registro.hitos.length; i++) {
			var hito = registro.hitos[i];
			var latitud = hito.latitud;
			var longitud = hito.longitud;
			var point = new GLatLng(latitud, longitud);
			var html = createPopUp(hito);
			var hitoMapa = createMarker(point, html);
			this.map.addOverlay(hitoMapa);
		}
	},

	'rounding' : function(numero) {
		var original = parseFloat(numero);
		var result = Math.round(original * 100) / 100;
		return result;
	}
};
function createMarker(point, nombre) {
	var marker = new GMarker(point);
	GEvent.addListener(marker, 'click', function() {
		marker.openInfoWindowHtml(nombre);
	});
	return marker;
}
function createPopUp(hito) {
	var html = "<b>Nombre del hito: </b>" + hito.nombre + ".<br/><br/>";
	html = html
			+ "<span style='color: #808080; font-size: 9px;'>Latitud: "
			+ hito.latitud + "</span><br/>";
	html = html
			+ "<span style='color: #808080; font-size: 9px;'>Longitud: "
			+ hito.longitud + "</span>";
	html = html + "<br/><br/><br/>Usuarios registrados que han checkeado:"
			+ hito.contador_usuarios_identificados;
	html = html + "<br/>Usuarios no registrados que han checkeado:"
			+ hito.contador_no_usuarios_identificados;
	return html;
}