var ruta = {
	'rowID' : null,
	'formatList' : function() {
		$(function() {
			$("#lista").jqGrid({
				datatype : 'local',
				data : [],
				colNames : [
						"Id", "Tesoro", "Fecha Inicio", "Fecha Fin"
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
					ruta.rowID = rowid;
				}
			});
			$(window).bind('resizeEnd', function() {
				$('#lista').setGridWidth($('#parent').width() - 30, true);
			}).trigger('resize');
		});
		
		$("#btnAlta").button().click(function() {
			generic.getForm('ruta');
		});
		$("#btnEditar").button().click(function() {
			generic.getForm('ruta', $('#lista').jqGrid('getRowData', ruta.rowID).id);
		});
		$("#btnEditar").attr("disabled", "disabled");

		$("#btnEliminar").button().click(function() {
			generic.delete('ruta', $('#lista').jqGrid('getRowData', ruta.rowID).id);
		});
		$("#btnEliminar").attr("disabled", "disabled");
		
		$("#btnEstadistica").button().click(function() {
			generic.getForm('estadistica', $('#lista').jqGrid('getRowData', ruta.rowID).id);
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
		ruta.generateQuestionsTree("#tree");
		 $( "#tabs" ).tabs();
		 $("#pista").cleditor({width:515, height:185, useCSS:true})[0].focus();

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
			generic.getList('ruta');
		});

		$("#btnSaveQuizz").button().click(function() {
			ruta.getParams();
		});

		$('#respuestas').change(function() {
			$('#btnDeleteResponse').button("enable");
		});
		$("#btnCoordinates").button().click(function() {
			var x = gApplication.getMap().getCenter();
			alert(x);
		});
		
		$("#dialog-form").dialog({
			autoOpen : false,
			height : 540,
			width : 600,
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
		var hitos = ruta.getHitos();
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

		if (hitos.length == 0) {
			errores += "- Debe introducir al menos un hito";
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
				hitosDTO : hitos
			};
			var entity = (id != null) ? 'ruta/' + id : 'ruta';
			generic.post(entity, data, function() {
				generic.getList('ruta');
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
	'getHitos' : function() {
		var hitos = [];
		var hitosArray = $("#tree").dynatree("getTree").toDict().children;
		if (hitosArray) {

			for( var i = 0; i < hitosArray.length; i++) {
				var h = hitosArray[i];
				

				var hito = {
					'id' : null,
					'title' : h.title,
					'key' : h.key,
					'isFolder' : true,
					'codigo' : h.codigo,
					'longitud' : h.longitud,
					'latitud' : h.latitud,
					'pista' : h.pista
				};
				hitos.push(hito);
			}
		}
		return hitos;
	}
};