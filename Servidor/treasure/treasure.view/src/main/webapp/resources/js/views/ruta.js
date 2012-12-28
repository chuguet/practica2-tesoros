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
			$(window).bind('resize', function() {
				$('#lista').setGridWidth($('.ui-jqgrid').parent().innerWidth() - 30);
			}).trigger('resize');
		});
		
		// $("#lista").fluidGrid({ example:"#grid_wrapper", offset:-10 });
		
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
		this.rowID = null;
		
		$("#lista").jqGrid({
			datatype : 'local',
			data : [],
			colNames : [
					"Id", "Hito", "C&oacute;digo", "Latitud", "Longitud", "Pista"
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
						name : 'codigo',
						index : 'codigo',
						width : 30,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'latitud',
						index : 'latitud',
						width : 30,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'longitud',
						index : 'longitud',
						width : 30,
						sorttype : 'string',
						sortable : true,
						align : 'left'
					}, {
						name : 'pista',
						index : 'pista',
						width : 20,
						sorttype : 'string',
						sortable : true,
						align : 'left',
						hidden:true
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
				$("#btnModifyHito").button("enable");
				$("#btnDeleteHito").button("enable");
				ruta.rowID = rowid;
			}
		});
		$(window).bind('resize', function() {
			$('#lista').setGridWidth($('.ui-jqgrid').parent().innerWidth() - 30);
		}).trigger('resize');
		
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
		

		this.configureGoogleMaps();
		$("#tabs").tabs();
		$('#tabs').bind('tabsshow', function(event, ui) {
			if (ui.panel.id == "tabs-2") {
			    $(ui.panel).css("height","100%");
			    ruta.map.checkResize();
			}
		})
		$('#pista').redactor();
		
		
		
		
		$("#btnAddHito").button().click(function() {
			ruta.deseleccionarHito();
			$('#dialog-form').dialog('option', 'title', 'A&ntilde;adir Hito');
			$(".ui-dialog-buttonpane button:contains('Modificar') span").text('Crear');
			$('#tabs').tabs('select', 0);
			$("#dialog-form").dialog("open");
		});

		$("#btnModifyHito").button().click(function() {
			var hito = $('#lista').jqGrid('getRowData', ruta.rowID);
			
			$("#nombreHito").val(hito.nombre);
			$("#nombreHito").attr('key', hito.idRest);
			$("#codigo").val(hito.codigo);
			$("#pista").val(hito.pista);
			$(".redactor_editor").html(hito.pista);
			$('#longitud').val(hito.longitud);
			$('#latitud').val(hito.latitud);
			
			$('#dialog-form').dialog('option', 'title', 'Modificar Hito');
			$(".ui-dialog-buttonpane button:contains('Crear') span").text('Modificar');
			$('#tabs').tabs('select', 0);
			$("#dialog-form").dialog("open");
		});

		$("#btnDeleteHito").button().click(function() {
			$('#lista').jqGrid('delRowData',ruta.rowID);
			ruta.deseleccionarHito();
		});

		

		$("#btnCancel").button().click(function() {
			ruta.deseleccionarHito();
			generic.getList('ruta');
		});

		$("#btnSaveTreasure").button().click(function() {
			ruta.deseleccionarHito();
			ruta.getParams();
		});
		
		$("#dialog-form").dialog({
			autoOpen : false,
			height : 540,
			width : 600,
			modal : true,
			buttons : {
				"Crear" : function() {
					var error = "";
					if ($('#nombreHito').val().length == 0) {
						error += ' - Debe indicar el nombre del hito <br/>';
					}
					if ($('#codigo').val().length == 0) {
						error += ' - Debe indicar el c&oacute;digo del hito <br/>';
					}
					if ($('#pista').val() == "<p><br></p>" || $('#pista').val() == "") {
						error += ' - Debe introducir la pista para continuar la b&uacute;squeda del tesoro <br/>';
					}
					if ($('#longitud').val().length == 0 || $('#latitud').val().length == 0) {
						error += ' - Debe seleccionar una posici&oacute;n v&aacute;lida en el mapa <br/>';
					}
					if (error.length != 0){
						jAlert(error, 'Error');
						return;
					}
					
					var hito = {
							idRest : $('#nombreHito').attr('key'),
							nombre : $('#nombreHito').val(),
							codigo : $('#codigo').val(),
							longitud : $('#longitud').val(),
							latitud : $('#latitud').val(),
							pista : $('#pista').val()
						};
					
					var lastId = 1;
					if ($('#lista').getDataIDs().length > 0) {
						lastId = parseInt($('#lista').getDataIDs().length) + 1;
					}
					if (ruta.rowID == null) {
						$('#lista').jqGrid("addRowData", lastId, hito, "last");
					}
					else{
						$('#lista').jqGrid('setRowData', ruta.rowID, hito);
					}

					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				$("#nombreHito").val('');
				$("#nombreHito").attr('key', '');
				$("#codigo").val('');
				$("#longitud").val('');
				$("#latitud").val('');
				$("#pista").val('');
				$(".redactor_editor").html('');
				if (ruta.newLocation != null){
					ruta.map.removeOverlay(ruta.newLocation);
				}
			}
		});
	},
	'deseleccionarHito' : function(){
		this.rowID = null;
		$('#lista').jqGrid('resetSelection');
		$("#btnModifyHito").button("disable");
		$("#btnDeleteHito").button("disable");
	},
	'getParams' : function() {
		var id = ($("#id").val()) ? $("#id").val() : null;
		var nombre = $("#nombre").val();
		var fecha_inicio = $("#fecha_inicio").val();
		var fecha_fin = $("#fecha_fin").val();
		var hitos = $('#lista').jqGrid('getRowData');
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
				$("#btnModifyHito").button("enable");
				$("#btnDeleteHito").button("enable");
			}
		});
	},
	'getHitos' : function() {
		var hitos = [];
		var hitosArray = $('#lista').jqGrid('getRowData');
		if (hitosArray) {

			for( var i = 0; i < hitosArray.length; i++) {
				var h = hitosArray[i];
				

				var hito = {
					'id' : null,
					'nombre' : h.nombre,
					'codigo' : h.codigo,
					'longitud' : h.longitud,
					'latitud' : h.latitud,
					'pista' : h.pista
				};
				hitos.push(hito);
			}
		}
		return hitos;
	},
    'newLocation': null,
    'map': null,
	'configureGoogleMaps' : function(){
// var mapOptions = {
// center: new google.maps.LatLng(40.4199, -3.694668),
// zoom: 13,
// mapTypeId: google.maps.MapTypeId.ROADMAP
// };
// this.map = new google.maps.Map(document.getElementById("map_canvas"),
// mapOptions);
		
		
		if (GBrowserIsCompatible()) {
            this.map = new GMap2(document.getElementById("map_canvas"));
            this.map.addControl(new GSmallMapControl());
            this.map.setCenter(new GLatLng(40.4199, -3.694668), 13);

			GEvent.addListener(this.map, "click", function(marker,point) {
				if (ruta.newLocation != null){
					ruta.map.removeOverlay(ruta.newLocation);
				}
				if (marker && marker.openInfoWindowHtml) {
					ruta.newLocation = marker;
					$('#longitud').val(marker.getPoint().lng().toString());
					$('#latitud').val(marker.getPoint().lat().toString());
				} 
				else if (point) {
					ruta.newLocation = new GMarker(point);
					ruta.map.addOverlay(ruta.newLocation);
					$('#longitud').val(point.lng().toString());
					$('#latitud').val(point.lat().toString());
	            }
			});
        }
	}
};