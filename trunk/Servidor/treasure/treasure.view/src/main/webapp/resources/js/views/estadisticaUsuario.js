var estadisticaUsuario = {
	'mostrarEstadisticaUsuario' : function(registro) {
		// Se muestran los datos principales
		var nombreUsuario = $('#usuarioText').text() + registro.usuario;
		$('#usuarioText').text(nombreUsuario);
		var porcentajeRutas = 'Hay un global de ' + registro.num_rutas_totales
				+ ' rutas, con un total de ' + registro.num_rutas_terminadas
				+ ' rutas realizadas. [' + registro.porcentaje_rutas_totales
				+ ' completado]';
		$('#fieldRuta').text(porcentajeRutas);
		var porcentajeHitos = 'Hay un global de ' + registro.num_hitos_totales
				+ ' hitos, con un total de ' + registro.num_hitos_terminados
				+ ' hitos realizados. [' + registro.porcentaje_hitos_totales
				+ ' completado]';
		$('#fieldHito').text(porcentajeHitos);
		//Añadimos la tabla de porcentajes
		for(var i=0; i<registro.porcentaje_rutas_hitos.length ;i++){
			var rutaHitoPorcentaje = registro.porcentaje_rutas_hitos[i];
			$('#tabla_rutas').append('<ul id="' + rutaHitoPorcentaje.id + '">Ruta: '+rutaHitoPorcentaje.ruta+'</ul>');
			$('#'+rutaHitoPorcentaje.id).append('<li>Numero de hitos totales de la ruta: ' + rutaHitoPorcentaje.num_hitos_totales + '.</li>');
			$('#'+rutaHitoPorcentaje.id).append('<li>Numero de hitos necesarios para superar la ruta: ' + rutaHitoPorcentaje.num_hitos_necesarios + '.</li>');
			$('#'+rutaHitoPorcentaje.id).append('<li>Numero de hitos totales chequeados por el usuario: ' + rutaHitoPorcentaje.num_hitos_checkeados + '. [' + rutaHitoPorcentaje.porcentaje_finalizado + ' completado]</li>');
		}
	}
};