var estadisticaUsuario = {
	'mostrarEstadisticaUsuario' : function(registro) {
		var nombreUsuario = $('#usuarioText').text() + registro.usuario;
		$('#usuarioText').text(nombreUsuario);
	}
};