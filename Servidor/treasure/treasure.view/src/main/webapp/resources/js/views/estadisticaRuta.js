var estadisticaRuta = {
	'mostrarEstadisticaRuta' : function(registro) {
		// Introduzco nombre de la encuesta en un span del jsp
		var nombreRuta = $('#rutaText').text() + registro.ruta;
		$('#rutaText').text(nombreRuta);
		var usuarios = 'Numero de usuarios que han conseguido el tesoro de la ruta: ' + registro.usuarios_ruta_completada;
		$('#usuarios').text(usuarios);
		var fechaInicio = 'Fecha de inicio: ' + registro.fecha_inicio;
		$('#fecha_inicio').text(fechaInicio);
		var fechaFin = 'Fecha de fin: ' + registro.fecha_fin;
		$('#fecha_fin').text(fechaFin);
		var contadorTotal = 'Con un total de ' + registro.contador_total + ' check-in en hitos';
		$('#hitos_check').text(contadorTotal);

		this.map = new GMap2(document.getElementById("map_canvas"));
		this.map.addControl(new GSmallMapControl());
		this.map.setCenter(new GLatLng(40.4199, -3.694668), 13);

		for( var i = 0; i < registro.hitos.length; i++) {
			var hito = registro.hitos[i];
			var latitud = hito.latitud;
			var longitud = hito.longitud;
			var point = new GLatLng(latitud, longitud);
			var html = this.createPopUpRuta(hito);
			var hitoMapa = this.createMarker(point, html);
			this.map.addOverlay(hitoMapa);
		}

		$("#dialog-hito-en-tiempo").dialog({
			autoOpen : false,
			height : 540,
			width : 600,
			modal : true,
			buttons : {
				"Aceptar" : function() {

					$(this).dialog("close");
				}
			},
			close : function() {

			}
		});
	},
	'rounding' : function(numero) {
		var original = parseFloat(numero);
		var result = Math.round(original * 100) / 100;
		return result;
	},
	'createMarker' : function(point, nombre) {
		var marker = new GMarker(point);
		GEvent.addListener(marker, 'click', function() {
			marker.openInfoWindowHtml(nombre);
		});
		return marker;
	},
	'createPopUpRuta' : function(hito) {
		var html = "<b>Nombre del hito: </b>" + hito.nombre + ".<br/>";
		html = html + "<b>C&oacute;digo de etiqueta:</b> " + hito.codigo;
		html = html + "<p><span style='color: #808080; font-size: 9px;'>Latitud: " + hito.latitud + "</span><br/>";
		html = html + "<span style='color: #808080; font-size: 9px;'>Longitud: " + hito.longitud + "</span></p>";
		html = html + "Usuarios <b>registrados</b> que han checkeado:" + hito.contador_usuarios_identificados + "<br/>";
		html = html + "Usuarios <b>no registrados</b> que han checkeado:" + hito.contador_no_usuarios_identificados + "<br/>";
		html = html + "<a href='javascript:void' onclick='javascript:estadisticaRuta.verHitoEnTiempo(\"" + hito.nombre + "\"," + hito.id + ");' title='Ver estad&iacute;stica de tiempo'>Ver estad&iacute;stica en tiempo</a>";
		return html;
	},
	'verHitoEnTiempo' : function(nombreHito, idHito) {
		$('#dialog-hito-en-tiempo').dialog('option', 'title', 'An&aacute;lisis del Hito ' + nombreHito);
		chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				type : 'line',
				marginRight : 0,
				marginBottom : 25
			},
			xAxis : {
				categories : [
						'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
				]
			},
			yAxis : {
				title : {
					text : 'Total de checks'
				},
				plotLines : [
					{
						value : 0,
						width : 1,
						color : '#808080'
					}
				]
			},
			tooltip : {
				formatter : function() {
					return this.y;
				}
			},
			legend : {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'top',
				x : -10,
				y : 100,
				borderWidth : 0
			},
			series : [
					{
						name : 'Ident.',
						data : [
								7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6
						]
					}, {
						name : 'No ident.',
						data : [
								1, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5
						]
					}
			]
		});
		var l = $("g.highcharts-legend").attr("transform", "translate(450,20)");
		$("#dialog-hito-en-tiempo").dialog("open");
	}
};
