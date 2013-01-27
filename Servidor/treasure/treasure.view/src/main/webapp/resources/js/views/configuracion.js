var configuracion = {
	'rowID' : null,
	'formatForm' : function() {
		this.rowID = null;

		$("#lista").jqGrid({
			datatype : 'local',
			data : [],
			colNames : [
				"Mensaje"
			],
			colModel : [
				{
					name : 'mensaje',
					index : 'mensaje',
					width : 100,
					sorttype : 'string',
					align : 'left'
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
				$("#btnModifyMessage").button("enable");
				$("#btnDeleteMessage").button("enable");
				configuracion.rowID = rowid;
			}
		});
		$(window).bind('resize', function() {
			$('#lista').setGridWidth($('.ui-jqgrid').parent().innerWidth() - 30);
		}).trigger('resize');

		$('#mensaje').redactor();
		$('.numbersOnly').keyup(function() {
			this.value = this.value.replace(/[^0-9\.]/g, '');
		});

		$("#btnAddMessage").button().click(function() {
			configuracion.deseleccionarMensaje();
			$('#dialog-form').dialog('option', 'title', 'A&ntilde;adir Mensaje');
			$(".ui-dialog-buttonpane button:contains('Modificar') span").text('Crear');
			$("#dialog-form").dialog("open");
		});

		$("#btnModifyMessage").button().click(function() {
			var mensaje = $('#lista').jqGrid('getRowData', configuracion.rowID);
			$("#mensaje").val(mensaje.mensaje);
			$("#mensaje").setCode(mensaje.mensaje);
			$('#dialog-form').dialog('option', 'title', 'Modificar Mensaje');
			$(".ui-dialog-buttonpane button:contains('Crear') span").text('Modificar');
			$("#dialog-form").dialog("open");
		});

		$("#btnDeleteMessage").button().click(function() {
			$('#lista').jqGrid('delRowData', configuracion.rowID);
			configuracion.deseleccionarMensaje();
		});

		$("#btnCancel").button().click(function() {
			configuracion.deseleccionarMensaje();
			generic.goHome();
		});

		$("#btnSaveConfiguration").button().click(function() {
			configuracion.deseleccionarMensaje();
			configuracion.getParams();
		});

		$("#dialog-form").dialog({
			autoOpen : false,
			height : 540,
			width : 600,
			modal : true,
			buttons : {
				"Crear" : function() {
					var error = "";

					if ($('#mensaje').val() == "<p><br></p>" || $('#mensaje').val() == "") {
						jAlert('Debe indicar un mensaje', 'Error');
						return;
					}

					var mensaje = {
						mensaje : $('#mensaje').val()
					};

					var lastId = 1;
					if ($('#lista').getDataIDs().length > 0) {
						lastId = parseInt($('#lista').getDataIDs().length) + 1;
					}
					if (configuracion.rowID == null) {
						$('#lista').jqGrid("addRowData", lastId, mensaje, "last");
					}
					else {
						$('#lista').jqGrid('setRowData', configuracion.rowID, mensaje);
					}

					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				$("#mensaje").val('');
				$("#mensaje").setCode('');
			}
		});
	},
	'deseleccionarMensaje' : function() {
		this.rowID = null;
		$('#lista').jqGrid('resetSelection');
		$("#btnModifyMessage").button("disable");
		$("#btnDeleteMessage").button("disable");
	},
	'getParams' : function() {
		var numero = $("#numero").val();
		var distanciaMaxima = $('#distanciaMaxima').val();
		var controlDistancia = ($('#controlDistancia').is(':checked')) ? '1' : '0';
		var messages = $('#lista').jqGrid('getRowData');
		var errores = '';
		if (numero == '') {
			errores = "- Hay que indicar el n&uacute;mero m&uacute;ltiplo para los mensajes<br />";
		}
		if (distanciaMaxima == '') {
			errores = "- Hay que indicar la distancia m&aacute;xima de control de checkin<br />";
		}
		if (messages.length < 5) {
			errores += "- Debe introducir al menos 5 mensajes";
		}
		if (errores != '') {
			jAlert(errores, "Validaci&oacute;n");
		}
		else {
			var mensajes = new Array();
			for( var i = 0; i < messages.length; i++) {
				mensajes.push(messages[i].mensaje);
			}

			var itemsConfiguracion = new Array();

			itemsConfiguracion.push({
				'clave' : 'controlDistancia',
				'valor' : controlDistancia
			})
			itemsConfiguracion.push({
				'clave' : 'distanciaMaxima',
				'valor' : distanciaMaxima
			})
			var data = {
				numero : numero,
				mensajes : mensajes,
				itemsConfiguracion : itemsConfiguracion
			};
			generic.post('configuracion', data, function() {
				generic.goHome();
			});
		}
	},
	'showInfo' : function(conf) {
		$('#numero').val(conf.numero);
		var messages = new Array();
		for( var i = 0; i < conf.mensajes.length; i++) {
			messages.push({
				'mensaje' : conf.mensajes[i]
			});
		}
		for( var i = 0; i < conf.itemsConfiguracion.length; i++) {
			switch (conf.itemsConfiguracion[i].clave) {
			case "controlDistancia":
				$('#controlDistancia').attr('checked', (conf.itemsConfiguracion[i].valor == 1));
				break;
			case "distanciaMaxima":
				$('#distanciaMaxima').val(conf.itemsConfiguracion[i].valor);
				break;
			}
		}

		$("#lista").setGridParam({
			data : messages
		}).trigger("reloadGrid");
	}
};