var rellenarEncuesta = {
	'rowID' : null,
	'numberQuestions' : 0,
	'getUser' : function() {
		var user = {
			id_usuario : $('#id_usuario').val(),
			ip_usuario : $('#ip_usuario').val()
		};
		return user;
	},
	'createList' : function(information) {
		// $.mobile.changePage('#page');
		jQuery.each(information, function() {
			$("#listadoEncuestas").append("<li><a href='#' onclick='javascript:generic.getForm(\"rellenarEncuesta\", " + this.id + ");' title='" + this.nombre + "'>" + this.nombre + "</a></li>");
		});

		$("#page").trigger("create");
	},
	'formatForm' : function(encuesta) {
		$('#id_encuesta').val(encuesta.id);
		$('.titlePage').text(encuesta.nombre);
		this.numberQuestions = encuesta.preguntasDTO.length;
		for( var i = 0; i < encuesta.preguntasDTO.length; i++) {
			var preguntaDTO = encuesta.preguntasDTO[i];
			var fieldset = $('<fieldset>');
			fieldset.attr('data-role', 'controlgroup');
			fieldset.attr('data-mini', 'true');
			var legend = $('<legend>').text(preguntaDTO.title);
			fieldset.append(legend);
			for( var j = 0; j < preguntaDTO.children.length; j++) {
				var respuesta = preguntaDTO.children[j];
				var radio = $('<input>');
				radio.attr('type', 'radio');
				radio.attr('name', 'radio-choice-' + i);
				radio.attr('id', 'radio-choice-' + i + '-' + j);
				radio.attr('value', respuesta.id);
				fieldset.append(radio);

				var label = $('<label>').text(respuesta.title);
				label.attr('for', 'radio-choice-' + i + '-' + j);
				fieldset.append(label);
			}
			$('#preguntas').append(fieldset);
		}
		$("#page").trigger("create");
		$("#btnEnviar").button().click(function() {
			rellenarEncuesta.getParams();
		});
		$("#btnVolver").button().click(function() {
			generic.getList('rellenarEncuesta', rellenarEncuesta.getUser());
		});
	},

	'getParams' : function() {
		var radioButtons = $('form input[type=radio]:checked');
		if (radioButtons.length < this.numberQuestions) {
			jAlert("Debe contestar todas las preguntas", "Error");
		}
		else {
			var idRespuestasContestadas = new Array();
			for( var i = 0; i < radioButtons.length; i++) {
				idRespuestasContestadas.push(radioButtons[i].value);
			}
			var encuestaContestada = {
				'id_encuesta' : $('#id_encuesta').val(),
				'id_usuario' : $('#id_usuario').val(),
				'ip_usuario' : $('#ip_usuario').val(),
				'idRespuestasContestadas' : idRespuestasContestadas
			}
			var entity = "rellenarEncuesta";
			generic.post(entity, encuestaContestada, function() {
				// generic.getList('rellenarEncuesta',
				// rellenarEncuesta.getUser());
				generic.getForm('estadistica', $('#id_encuesta').val());
			});
		}
	}
};