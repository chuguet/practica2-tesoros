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
		
		$("#btnRutas").button().click(function() {
			$("#dialog-form").dialog("open");
		});

		$("#btnRutas").button("disable");

		$('input:radio[name=tipoUsuario]')[0].checked = true;
		
		$("input:radio[name=tipoUsuario]").change(function() {
			if($('input:radio[name=tipoUsuario]')[1].checked == true){
				$("#btnRutas").button("enable");
			}else{
				$("#btnRutas").button("disable");
			}
		});
		
		$("#btnCancel").button().click(function() {
			generic.getList('usuario');
		});

		$("#btnSaveQuizz").button().click(function() {
			usuario.getParams();
		});
		
		//Petición al servidor para recuperar todas las rutas y ejecutar la funcion de rellenar los checkboxs
		generic.get('ruta/gestor', null, usuario.rellenarListBox);
		
		//Cuadro de dialogo de jquery para los checkbox
		$("#dialog-form").dialog({
			autoOpen : false,
			height : 400,
			width : 250,
			modal : true,
			buttons : {
				"Aceptar" : function() {
					$(this).dialog("close");
				},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
			}
		});
	},
	'rellenarListBox' : function(rutas){
		$("#list-box > .checkbox").remove();
		for(var i=0;i<rutas.length;i++){
			$("#list-box").append('<label class="checkbox"><input type="checkbox" value="'+rutas[i].id+'">'+rutas[i].nombre+'</br></label>');
		}
	},
	'getParams' : function() {
		var id = ($("#id").val()) ? $("#id").val() : null;
		var input_usuario = $("input[id=usuario]").val();
		var pwd = $("input[id=pwd]").val();

		var errores = '';
		if (input_usuario == '') {
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
				usuario : input_usuario,
				pwd : pwd,
				admin : $("input:radio[name='tipoUsuario']:checked").val(),
				id_rutas : usuario.recuperarRutasSeleccionadas()
			};
			var entity = (id != null) ? 'usuario/' + id : 'usuario';
			generic.post(entity, data, function() {
				generic.getList('usuario');
			});
		}
	},
	'recuperarRutasSeleccionadas' : function(){
		var rutasSeleccionadas = new Array();
		$('#list-box input[type=checkbox]:checked').each(function (){
			rutasSeleccionadas.push(this.value);
		});
		return rutasSeleccionadas;
	}
}