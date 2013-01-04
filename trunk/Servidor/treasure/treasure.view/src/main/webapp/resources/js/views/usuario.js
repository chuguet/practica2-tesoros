var usuario = {
	'rowID' : null,
	'formatList' : function() {
		
		$("#lista").jqGrid({
			datatype : 'local',
			data : [],
			colNames : [
					"Id", "Nombre", "Apellidos", "Email", "Usuario", "Tipo usuario"
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
						width : 30,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'apellidos',
						index : 'fecha_inicio',
						width : 40,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'email',
						index : 'email',
						width : 20,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'usuario',
						index : 'usuario',
						width : 20,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'admin',
						index : 'admin',
						width : 20,
						sorttype : 'boolean',
						sortable : true,
						align : 'center'
					}
			],
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
				$("#btnEditar").button("enable");
				$("#btnEliminar").button("enable");
				$("#btnEstadistica").button("enable");
				usuario.rowID = rowid;
			}
		});
		$(window).bind('resize', function() {
			$('#lista').setGridWidth($('.ui-jqgrid').parent().innerWidth() - 30);
		}).trigger('resize');
		
		/*******Configuración de los botones del formulario de usuarios***********/
		$("#btnAlta").button().click(function() {
			generic.getForm('usuario');
		});
		$("#btnEditar").button().click(function() {
			generic.getForm('usuario', $('#lista').jqGrid('getRowData', usuario.rowID).id);
		});
		$("#btnEditar").button("disable");

		$("#btnEliminar").button().click(function() {
			generic.delete('usuario', $('#lista').jqGrid('getRowData', usuario.rowID).id, function() {
				generic.getList('usuario');
			});
		});
		$("#btnEliminar").button("disable");
		
		$("#btnEstadistica").button().click(function() {
			generic.getForm('estadisticaUsuario', $('#lista').jqGrid('getRowData', usuario.rowID).id);
		});
		$("#btnEstadistica").button("disable");
	},

	'formatForm' : function() {
		$(window).bind('resize', function() {
			$('#lista').setGridWidth($('.ui-jqgrid').parent().innerWidth() - 30);
		}).trigger('resize');
		
		
		
		$("#btnCancel").button().click(function() {
			generic.getList('usuario');
		});

		$("#btnSaveQuizz").button().click(function() {
			usuario.getParams();
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
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				allFields.val("");
			}
		});
	},
	'getParams' : function() {
		var id = ($("#id").val()) ? $("#id").val() : null;
		var usuario = $("input[id=usuario]").val();
		var pwd = $("input[id=pwd]").val();

		var errores = '';
		if (usuario == '') {
			errores = '- Debe introducir un usuario<br />';
		}
		if (pwd == '') {
			errores += '- Debe introducir una contrase&ntilde;a';
		}
		if (errores != '') {
			jAlert(errores, "Validaci&oacute;n");
		}
		else {
			var data = {
				id : id,
				nombre : $("input[id=nombre]").val(),
				apellidos : $("input[id=apellidos]").val(),
				email : $("input[id=email]").val(),
				usuario : usuario,
				pwd : pwd,
				admin : $("input:radio[name='tipoUsuario']:checked").val()
			};
			var entity = (id != null) ? 'usuario/' + id : 'usuario';
			generic.post(entity, data, function() {
				generic.getList('usuario');
			});
		}
	}
}