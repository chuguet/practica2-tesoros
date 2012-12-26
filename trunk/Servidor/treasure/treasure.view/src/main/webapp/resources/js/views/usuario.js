var usuario = {
	'rowID' : null,
	'formatList' : function() {
		$(function() {
			$("#lista").jqGrid({
				datatype : 'local',
				data : [],
				colNames : [
						"Id", "Nombre", "Apellidos", "Email", "Usuario", "Es admin"
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
					$("#btnEditar").removeAttr("disabled");
					$("#btnEliminar").removeAttr("disabled");
					usuario.rowID = rowid;
				}
			});
			$(window).bind('resizeEnd', function() {
				$('#lista').setGridWidth($('#parent').width() - 30, true);
			}).trigger('resize');
		});
		
		$("#btnAlta").button().click(function() {
			generic.getForm('usuario');
		});
		$("#btnEditar").button().click(function() {
			generic.getForm('usuario', $('#lista').jqGrid('getRowData', usuario.rowID).id);
		});
		$("#btnEditar").attr("disabled", "disabled");

		$("#btnEliminar").button().click(function() {
			generic.delete('usuario', $('#lista').jqGrid('getRowData', usuario.rowID).id);
		});
		$("#btnEliminar").attr("disabled", "disabled");
	},

	'formatForm' : function() {
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
				admin : $("input[id=admin]").is(':checked') ? 1 : 0
			};
			var entity = (id != null) ? 'usuario/' + id : 'usuario';
			generic.post(entity, data, function() {
				generic.getList('usuario');
			});
		}
	}
}