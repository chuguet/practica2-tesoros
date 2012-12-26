var estadistica = {
	'mostrarEstadistica' : function(registro) {
		// Introduzco nombre de la encuesta en un span del jsp
		var nombreEncuesta = $('legend.quizzText').text() + registro.encuesta;
		$('legend.quizzText').text(nombreEncuesta);

		// Meto las preguntas
		for( var i = 0; i < registro.preguntas.length; i++) {
			var pregunta = registro.preguntas[i];
			var nombrePregunta = $("<span class='questionText'>" + pregunta.pregunta + "</span>");
			$('#estadistica').append(nombrePregunta);
			// Meto las respuestas
			var lista = $('<ul class="chart" id="pregunta_' + pregunta.pregunta + '">');
			$('#estadistica').append(lista);
			var count = 0;
			for( var j = 0; j < pregunta.respuestas.length; j++) {
				var nombreRespuesta = $('<li count="' + pregunta.respuestas[j].vecesContestada + '" class="red"><span class="graphText">' + pregunta.respuestas[j].respuesta + '</span><span class="bar"></span><span class="percent"></span></li>');
				count += pregunta.respuestas[j].vecesContestada;
				lista.append(nombreRespuesta);
			}
			lista.find("li").each(function() {
				var result = (this.getAttribute("count") / count) * 100;
				this.setAttribute("title", result);
			});
		}
		this.visualizeGraph();
	},
	'visualizeGraph' : function() {
		$('.chart li').each(function() {
			var pc = $(this).attr('title');
			pc = pc > 100 ? 100 : pc;
			pc = estadistica.rounding(pc);
			if (pc + "" == "NaN") {
				pc = "0";
				$(this).attr('title', pc);
			}
			$(this).children('.percent').html(pc + '%');
			var ww = $(this).width();
			var len = parseInt(ww, 10) * parseInt(pc, 10) / 100;
			$(this).children('.bar').animate({
				'width' : len + 'px'
			}, 1500);
		});
	},
	'rounding' : function(numero) {
		var original = parseFloat(numero);
		var result = Math.round(original * 100) / 100;
		return result;
	}
};