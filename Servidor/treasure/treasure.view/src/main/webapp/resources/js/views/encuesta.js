var encuesta = {
	'rowID' : null,
	'formatList' : function() {
		$(function() {
			$("#lista").jqGrid({
				datatype : 'local',
				data : [],
				colNames : [
						"Id", "Encuesta", "Fecha Inicio", "Fecha Fin"
				],
				colModel : [
						{
							name : 'id',
							index : 'idRest',
							width : 0,
							hidden : true
						}, {
							name : 'nombre',
							index : 'nombre',
							width : 60,
							sorttype : 'string',
							sortable : true,
							align : 'left'
						}, {
							name : 'fecha_inicio',
							index : 'fecha_inicio',
							width : 15,
							sorttype : 'string',
							sortable : true,
							align : 'left'
						}, {
							name : 'fecha_fin',
							index : 'fecha_fin',
							width : 15,
							sorttype : 'string',
							sortable : true,
							align : 'left'
						}
				],
				autowidth : true,
				shrinkToFit : true,
				rowNum : 20,
				rowList : [
						10, 20, 30
				],
				pager : '#paginadorLista',
				sortname : 'nombre',
				sortorder : 'asc',
				viewrecords : true,
				rownumbers : false,
				scroll : false,
				onSelectRow : function(rowid, status) {
					$("#btnEditar").attr('disabled', false);
					$("#btnEliminar").attr('disabled', false);
					$("#btnEstadistica").attr('disabled', false);
					encuesta.rowID = rowid;
				}
			});
			$(window).bind('resizeEnd', function() {
				$('#lista').setGridWidth($('#parent').width() - 30, true);
			}).trigger('resize');
		});
		
		$("#btnAlta").button().click(function() {
			generic.getForm('encuesta');
		});
		$("#btnEditar").button().click(function() {
			generic.getForm('encuesta', $('#lista').jqGrid('getRowData', encuesta.rowID).id);
		});
		$("#btnEditar").attr("disabled", "disabled");

		$("#btnEliminar").button().click(function() {
			generic.delete('encuesta', $('#lista').jqGrid('getRowData', encuesta.rowID).id);
		});
		$("#btnEliminar").attr("disabled", "disabled");
		
		$("#btnEstadistica").button().click(function() {
			generic.getForm('estadistica', $('#lista').jqGrid('getRowData', encuesta.rowID).id);
		});
		$("#btnEstadistica").attr("disabled", "disabled");
	},

	'formatForm' : function() {
		var datePickerParams = {
			"dateFormat" : 'dd/mm/yy',
			"dayNamesMin" : [
					"D", "L", "M", "X", "J", "V", "S"
			],
			"firstDay" : 1,
			"monthNames" : [
					"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
			]
		};
		$("#fecha_inicio").datepicker(datePickerParams);
		$("#fecha_fin").datepicker(datePickerParams);
		encuesta.generateQuestionsTree("#tree");

		$("#btnAddQuestion").button().click(function() {
			var tree = $("#tree").dynatree("getTree");
			if (tree.getActiveNode()) {
				tree.getActiveNode().deactivate();
			}

			$('#btnDeleteResponse').button("disable");
			$('#dialog-form').dialog('option', 'title', 'A&ntilde;adir Pregunta');
			$(".ui-dialog-buttonpane button:contains('Modificar') span").text('Crear');
			$("#dialog-form").dialog("open");
		});

		$("#btnModifyQuestion").button().click(function() {
			var tree = $("#tree").dynatree("getTree");
			var selectedQuestion = tree.getActiveNode();
			$("#nombrePregunta").val(selectedQuestion.data.title);
			$("#nombrePregunta").attr('key', selectedQuestion.data.key);

			$('#btnDeleteResponse').button("disable");
			var responses = selectedQuestion.childList;
			for( var i = 0; i < responses.length; i++) {
				$('#respuestas').append('<option value="' + responses[i].data.title + '" key="' + responses[i].data.key + '">' + responses[i].data.title + '</option>');
			}
			$('#dialog-form').dialog('option', 'title', 'Modificar Pregunta');
			$(".ui-dialog-buttonpane button:contains('Crear') span").text('Modificar');
			$("#dialog-form").dialog("open");
		});

		$("#btnDeleteQuestion").button().click(function() {
			var tree = $("#tree").dynatree("getTree");
			var activeNode = tree.getActiveNode();
			activeNode.removeChildren();
			activeNode.remove();
		});

		$("#btnAddResponse").button().click(function() {
			var respuesta = $("#respuesta").val();
			if (respuesta.length == 0) {
				jAlert('No puede insertar respuestas vac&iacute;as', 'Error');
			}
			else {
				$('#respuestas').append('<option value="' + respuesta + '" key="">' + respuesta + '</option>');
				$("#respuesta").val('');
			}
		});
		$("#btnDeleteResponse").button().click(function() {
			$('#respuestas option:selected').remove();
		});

		$("#btnCancel").button().click(function() {
			generic.getList('encuesta');
		});

		$("#btnSaveQuizz").button().click(function() {
			encuesta.getParams();
		});

		$('#respuestas').change(function() {
			$('#btnDeleteResponse').button("enable");
		});

		$("#dialog-form").dialog({
			autoOpen : false,
			height : 400,
			width : 550,
			modal : true,
			buttons : {
				"Crear" : function() {
					if ($('#nombrePregunta').val().length == 0) {
						jAlert('No puede insertar preguntas vac&iacute;as', 'Error');
						return;
					}
					if ($('#respuestas option').length < 2) {
						jAlert('Debe a&ntildeadir al menos dos respuestas para la pregunta', 'Error');
						return;
					}
					var respuestas = [];
					$("#respuestas option").each(function() {
						respuestas.push({
							title : this.value,
							key : this.getAttribute('key')
						});
					});

					var pregunta = [
						{
							title : $('#nombrePregunta').val(),
							isFolder : true,
							children : respuestas,
							key : $('#nombrePregunta').attr('key')
						}
					];
					if ($("#tree").dynatree("getTree").getActiveNode() != null) {
						$("#tree").dynatree("getRoot").addChild(pregunta, $("#tree").dynatree("getTree").getActiveNode());
						var newQuestion = $("#tree").dynatree("getTree").getActiveNode().getPrevSibling();
						if ($("#tree").dynatree("getTree").getActiveNode().isExpanded()) {
							newQuestion.expand();
						}
						$("#tree").dynatree("getTree").getActiveNode().remove();
						newQuestion.activate();
					}
					else {
						$("#tree").dynatree("getRoot").addChild(pregunta);
					}

					$(this).dialog("close");
					/*
					 * var bValid = true; allFields.removeClass(
					 * "ui-state-error" );
					 * 
					 * bValid = bValid && checkLength( name, "username", 3, 16 );
					 * bValid = bValid && checkLength( email, "email", 6, 80 );
					 * bValid = bValid && checkLength( password, "password", 5,
					 * 16 );
					 * 
					 * bValid = bValid && checkRegexp( name,
					 * /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z,
					 * 0-9, underscores, begin with a letter." ); // From
					 * jquery.validate.js (by joern), contributed by Scott
					 * Gonzalez:
					 * http://projects.scottsplayground.com/email_address_validation/
					 * bValid = bValid && checkRegexp( email,
					 * /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
					 * "eg. ui@jquery.com" ); bValid = bValid && checkRegexp(
					 * password, /^([0-9a-zA-Z])+$/, "Password field only allow :
					 * a-z 0-9" );
					 */

				},
				Cancel : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				$("#nombrePregunta").val('');
				$("#nombrePregunta").attr('key', '');
				$("#respuesta").val('');
				$('#respuestas').find('option').remove();
			}
		});
	},
	'getParams' : function() {
		var id = ($("#id").val()) ? $("#id").val() : null;
		var nombre = $("#nombre").val();
		var fecha_inicio = $("#fecha_inicio").val();
		var fecha_fin = $("#fecha_fin").val();
		var preguntas = encuesta.getQuestions();
		var errores = '';
		if (nombre == '') {
			errores = "- El nombre es obligatorio<br />";
		}
		if (fecha_inicio == '') {
			errores += "- La fecha de inicio es obligatoria<br />";
		}
		if (fecha_fin == '') {
			errores += "- La fecha de finalizaci&oacute;n es obligatoria<br />";
		}

		if (preguntas.length == 0) {
			errores += "- Debe introducir al menos una pregunta";
		}
		if (errores != '') {
			jAlert(errores, "Validaci&oacute;n");
		}
		else {
			var data = {
				id : id,
				nombre : nombre,
				fecha_inicio : fecha_inicio,
				fecha_fin : fecha_fin,
				preguntasDTO : preguntas
			};
			var entity = (id != null) ? 'encuesta/' + id : 'encuesta';
			generic.post(entity, data, function() {
				generic.getList('encuesta');
			});
		}
	},
	'generateQuestionsTree' : function(selector) {
		$(selector).dynatree({
			selectMode : 1,
			onActivate : function(question) {
				$("#btnModifyQuestion").button("enable");
				$("#btnDeleteQuestion").button("enable");
			}
		});
	},
	'getQuestions' : function() {
		var preguntas = [];
		var pregArray = $("#tree").dynatree("getTree").toDict().children;
		if (pregArray) {

			for( var i = 0; i < pregArray.length; i++) {
				var p = pregArray[i];
				var respuestas = [];
				var respArray = p.children;
				for( var j = 0; j < respArray.length; j++) {
					var respuesta = {
						'id' : null,
						'title' : respArray[j].title,
						'key' : respArray[j].key,
						'isFolder' : false
					};
					respuestas.push(respuesta);
				}

				var pregunta = {
					'id' : null,
					'title' : p.title,
					'key' : p.key,
					'isFolder' : true,
					'children' : respuestas
				};
				preguntas.push(pregunta);
			}
		}
		return preguntas;
	}
};