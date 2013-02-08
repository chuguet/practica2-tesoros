var ruta = {
	'rowID' : null,
	'distanciaControl' : null,
	'formatList' : function() {
		$(function() {
			$("#lista").jqGrid({
				datatype : 'local',
				data : [],
				colNames : [
						"Id", "Tesoro", "Fecha Inicio", "Fecha Fin", "Hitos Necesarios", "Hitos Distintos"
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
							width : 50,
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
						}, {
							name : 'hitos_necesarios',
							index : 'hitos_necesarios',
							width : 10,
							sorttype : 'string',
							sortable : true,
							align : 'right'
						}, {
							name : 'hitos_distintos',
							index : 'hitos_distintos',
							width : 10,
							sorttype : 'string',
							sortable : true,
							align : 'right'
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
		$("#btnEditar").button("disable");

		$("#btnEliminar").button().click(function() {
			generic.delete('ruta', $('#lista').jqGrid('getRowData', ruta.rowID).id, function() {
				generic.getList('ruta');
			});
		});
		$("#btnEliminar").button("disable");
		
		$("#btnEstadistica").button().click(function() {
			generic.getForm('estadisticaRuta', $('#lista').jqGrid('getRowData', ruta.rowID).id);
		});
		$("#btnEstadistica").button("disable");
	},

	'formatForm' : function() {
		generic.get('configuracion', 1, this.controlarConfiguracion);
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

		// Configuracion como objetos de jquery los siguientes elementos de
		// hitos
		$("#tabs").tabs();
		$('#tabs').bind('tabsshow', function(event, ui) {
			if (ui.panel.id == "tabs-3") {
			    $(ui.panel).css("height","100%");
			    ruta.map.checkResize();
			}
		})
		$('#pista').redactor();
		// Configuracion como objetos de jquery los siguientes elementos de
		// premios
		$("#tabs-premios").tabs();
		$('#premio-identificados').redactor();
		$('#premio-no-identificados').redactor();

		/**
		 * *******************************GESTION DE BOTONES DE
		 * HITOS***********************************************
		 */
		$("#btnGenerarQR").button().click(function() {
			var codigo = $('#codigo').val();
			if (codigo == '') {
				jAlert("Debe indicar el c&oacute;digo del hito", "Error");
			}
			else{
				var entity = 'codigoQR/';
				generic.post(entity, codigo, ruta.showQRCode);
			}
		});
		
		$("#btnAddHito").button().click(function() {
			ruta.deseleccionarHito();
			$('#dialog-form-hitos').dialog('option', 'title', 'A&ntilde;adir Hito');
			$(".ui-dialog-buttonpane button:contains('Modificar') span").text('Crear');
			$('#tabs').tabs('select', 0);
			$("#dialog-form-hitos").dialog("open");
		});

		$("#btnModifyHito").button().click(function() {
			var hito = $('#lista').jqGrid('getRowData', ruta.rowID);
			
			$("#nombreHito").val(hito.nombre);
			$("#nombreHito").attr('key', hito.idRest);
			$("#codigo").val(hito.codigo);
			ruta.paintQr(hito.codigo + '.png');
			$("#pista").val(hito.pista);
			$("#pista").setCode(hito.pista);
			$('#longitud').val(hito.longitud);
			$('#latitud').val(hito.latitud);
			ruta.paintPoint(hito.latitud, hito.longitud);
			
			$('#dialog-form-hitos').dialog('option', 'title', 'Modificar Hito');
			$(".ui-dialog-buttonpane button:contains('Crear') span").text('Modificar');
			$('#tabs').tabs('select', 0);
			$("#dialog-form-hitos").dialog("open");
		});

		$("#btnDeleteHito").button().click(function() {
			$('#lista').jqGrid('delRowData',ruta.rowID);
			ruta.deseleccionarHito();
		});

		$("#btnPremio").button().click(function() {
			$('#tabs-premios').tabs('select', 0);
			$('#premio-identificados').setCode($('#premio_identificados').val());
			$('#premio-identificados').val($('#premio_identificados').val());
			$('#premio-no-identificados').setCode($('#premio_no_identificados').val());
			$('#premio-no-identificados').val($('#premio_no_identificados').val());
			$("#dialog-form-premios").dialog("open");
		});

		
		$("#btnCancel").button().click(function() {
			ruta.deseleccionarHito();
			generic.getList('ruta');
		});

		$("#btnSaveTreasure").button().click(function() {
			ruta.deseleccionarHito();
			ruta.getParams();
		});
		
		$("#dialog-form-hitos").dialog({
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
				"Cancelar" : function() {
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
				$("#pista").setCode('');
				$('div#containerQR').empty();
				if (ruta.newLocation != null){
					ruta.map.removeOverlay(ruta.newLocation);
					ruta.map.removeOverlay(ruta.polygon);
				}
			}
		});
		
		
		$("#dialog-form-premios").dialog({
			autoOpen : false,
			height : 540,
			width : 600,
			modal : true,
			buttons : {
				"Aceptar" : function() {
					var error = "";
					if($('#premio-identificados').val() == "<p><br></p>" || $('#premio-identificados').val() == ""){
						error += ' - Debe indicar el premio de usuarios identificados <br/>';
					}
					if ($('#premio-no-identificados').val() == "<p><br></p>" || $('#premio-no-identificados').val() == "") {
						error += ' - Debe indicar el premio de usuarios no identificados <br/>';
					}
					if (error.length != 0){
						jAlert(error, 'Error');
						return;
					}
					$('#premio_identificados').val($('#premio-identificados').val());
					$('#premio_no_identificados').val($('#premio-no-identificados').val());
					$(this).dialog("close");
				},
				"Cancelar" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
			}
		});
		// Se limpian los textArea de premios a no ser que sean de edicion que
		// funcionaria el show information
		$('#premio-identificados').setCode('');
		$('#premio-identificados').val('');
		$('#premio-no-identificados').setCode('');
		$('#premio-identificados').val('');
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
		var hitos_necesarios = $('#hitos_necesarios').val();
		var hitos_distintos = $('#hitos_distintos').val();
		var premio_identificados = $('#premio_identificados').val();
		var premio_no_identificados = $('#premio_no_identificados').val();
		var errores = '';
		var controlarHitos = true;
		if (nombre == '') {
			errores = "- El nombre es obligatorio<br />";
		}
		if (fecha_inicio == '') {
			errores += "- La fecha de inicio es obligatoria<br />";
		}
		if (fecha_fin == '') {
			errores += "- La fecha de finalizaci&oacute;n es obligatoria<br />";
		}
		if (hitos_necesarios == ''){
			errores += "- Es necesario introducir el número de hitos necesarios<br />";
			controlarHitos = false;
		}
		if (hitos_distintos == ''){
			errores += "- Es necesario introducir el número de hitos distintos que se deben checkear para ganar el premio<br />";
			controlarHitos = false;
		}
		if (controlarHitos){
			if (hitos_necesarios > hitos.length){
				errores += "- El n&uacute;mero de hitos necesarios para encontrar el tesoro no puede superar al n&uacute;mero de hitos del tesoro<br />";
			}
			if (hitos_distintos > hitos.length){
				errores += "- El n&uacute;mero de hitos distintos para encontrar el tesoro no puede superar al n&uacute;mero de hitos del tesoro<br />";
			}
			if (hitos_distintos > hitos_necesarios){
				errores += "- El n&uacute;mero de hitos distintos no puede superar al n&uacute;mero de hitos necesarios<br />";
			}
		}
		if(premio_identificados.length == 0 || premio_no_identificados.length == 0){
			errores += "- Deben introducirse los premios correspondientes a la ruta<br />";
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
				hitosDTO : hitos,
				hitos_necesarios : hitos_necesarios,
				hitos_distintos : hitos_distintos,
				premio_identificados : premio_identificados,
				premio_no_identificados : premio_no_identificados
			};
			var entity = (id != null) ? 'ruta/' + id : 'ruta';
			generic.post(entity, data, function() {
				generic.getList('ruta');
			});
		}
	},
    'newLocation': null,
    'polygon' : null,
    'map' : null,
	'configureGoogleMaps' : function(){
		if (google.maps.BrowserIsCompatible()) {
			if (this.map == null){
	            this.map = new google.maps.Map2(document.getElementById("map_canvas"));
	            this.map.addControl(new google.maps.SmallMapControl());
	            this.map.setCenter(new google.maps.LatLng(40.4199, -3.694668), 13);
			}
			google.maps.Event.addListener(this.map, "click", function(marker,point) {
				if (ruta.newLocation != null){
					ruta.map.removeOverlay(ruta.newLocation);
					ruta.map.removeOverlay(ruta.polygon);
				}
				if (marker && marker.openInfoWindowHtml) {
					ruta.newLocation = marker;
					$('#longitud').val(marker.getPoint().lng().toString());
					$('#latitud').val(marker.getPoint().lat().toString());
					ruta.paintCircle(marker.getPoint().lat().toString(), marker.getPoint().lng().toString());
				} 
				else if (point) {
					ruta.newLocation = new google.maps.Marker(point);
					ruta.map.addOverlay(ruta.newLocation);
					$('#longitud').val(point.lng().toString());
					$('#latitud').val(point.lat().toString());
					ruta.paintCircle(point.lat().toString(), point.lng().toString());
	            }
			});
        }
	},
	'paintPoint': function(lattitude, longitude){
		ruta.newLocation = new google.maps.Marker(new GLatLng(lattitude, longitude));
		ruta.map.addOverlay(ruta.newLocation);
		ruta.map.setCenter(new google.maps.LatLng(lattitude, longitude), 13);
		
		ruta.paintCircle(lattitude, longitude);
	},
	'paintCircle' : function(lattitude, longitude){
		 // Perkins
        var center = new GLatLng(lattitude, longitude); 
        var radius = ruta.distanciaControl / 1000; 

        // convert kilometers to miles-diameter
        var radius = radius * 1.609344; 

        var latOffset = 0.01; 
        var lonOffset = 0.01; 
        var latConv = center.distanceFrom(new GLatLng(center.lat()+0.1, center.lng()))/100; 
        var lngConv = center.distanceFrom(new GLatLng(center.lat(), center.lng()+0.1))/100; 

        // nodes = number of points to create circle polygon
        var nodes = 40; 
        // Loop
        var points = []; 
        var step = parseInt(360/nodes)||10; 
        for(var i=0; i<=360; i+=step) { 
        	var pint = new GLatLng(center.lat() + (radius/latConv * Math.cos(i * Math.PI/180)), center.lng() + (radius/lngConv * Math.sin(i * Math.PI/ 180))); 
            // push pints into points array
             points.push(pint); 
        } 
        ruta.polygon = new GPolygon(points, "#f33f00", 1, 1, "#ff0000",  0.1); 
        ruta.map.addOverlay(ruta.polygon); 
	},
	'showQRCode': function(){
		var params = arguments[0];
		ruta.paintQr(params.parameter);
	},
	'paintQr' : function(image){
		$('div#containerQR').empty();
		var contextQR = $('input#contextQR').val();
		$('div#containerQR').append("<img src='" + contextQR + image.replace(/ /g, '_') + "' alt='C&oacute;digo QR generado' />");
	},
	'controlarConfiguracion' : function(info){
		ruta.distanciaControl = info.itemsConfiguracion[1].valor;
	}
};