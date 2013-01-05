var generic = {
	"uri" : "http://localhost:8080/com.movember.quizz.view/",
	"getList" : function(entity, parameters) {
		var action = entity + '/form/list';
		this.executeHtml('GET', action, function() {
			generic.get(entity, parameters, generic.showInformation);
		});
	},
	"getForm" : function(entity, item) {
		if (item) {
			var action = entity + '/form/edit';
			this.executeHtml('GET', action, function() {
				generic.get(entity, item, generic.showInformation);
			});
		}
		else {
			var action = entity + '/form/new';
			this.executeHtml('GET', action, null);
		}
	},
	"get" : function(entity, item, callback) {
		var action = entity;
		if (item && item != null) {
			if (typeof item == "object") {
				action += "?";
				var count = 1;
				for( var o in item) {
					if (count > 1)
						action += "&";
					action += o + "=" + item[o];
					count++;
				}
				params = item;
			}
			else {
				action += '/' + item;
			}
		}
		this.executeJSon('GET', action, null, callback);
	},
	"post" : function(action, params, callback) {
		this.executeJSon('POST', action, params, callback);
	},
	"put" : function(action, params, callback) {
		this.executeJSon('PUT', action, params, callback);
	},
	"delete" : function(entity, id, callback) {
		var params = {
			'id' : id
		};
		this.executeJSon('DELETE', entity + '/' + id, params, callback);
	},
	"executeJSon" : function($method, action, data, callback) {
		if (data != null) {
			data = JSON.stringify(data);
		}
		$.ajaxSetup({
			headers : {
				"Content-Type" : 'application/json',
				"Accept" : 'application/json'
			}
		});
		$.ajax({
			type : $method,
			url : action,
			data : data,
			dataType : 'json',
			// contentType : content_type,
			// dataType : 'html',
			success : function(response) {
				if ($method == 'GET') {
					if (callback) {
						var param = new Array();
						param.push(response);
						callback.apply(this, param);
					}
				}
				else {
					jAlert(response.mensaje, 'Operaci&oacute;n correcta');
					if (response.correcto) {
						if (callback) {
							var param = new Array();
							param.push(response);
							callback.apply(this, param);
						}
					}
				}
			},
			error : function(e) {
				jAlert('Se ha producido un error al procesar la acción', 'Error de operaci&oacute;n');
			}
		});
	},
	"executeHtml" : function($method, action, callback) {
		$.ajaxSetup({
			headers : {
				"Content-Type" : 'text/html;charset=UTF-8',
				"Accept" : 'text/html;charset=UTF-8'
			}
		});
		$.ajax({
			type : $method,
			url : action,
			dataType : 'html',
			success : function(response) {
				if (response.indexOf('Pantalla de acceso al sistema') != -1) {
					jAlert('Ha finalizado la sesi&oacute;n. Por favor, vuelva a acceder al sistema', 'Finalizaci&oacute;n de sesi&oacute;n', function() {
						window.location.reload(true);
					});
				}
				else {
					$('#content').html(response);
					if (callback) {
						callback.apply(this, arguments);
					}
				}
			},
			error : function(e) {
				jAlert('Se ha producido un error al acceder a la p&aacute;gina', 'Error de acceso');
			}
		});
	},
	'showInformation' : function() {
		var information = arguments[0];
		showInformationIntoView(information);
	},
	'preloadNecessaryFiles' : function() {
		if (!jQuery.browser.mobile) {
			require([
					"resources/js/mobile/jquery.mobile-1.2.0.min", "resources/js/mobile/jquery.mobile.jqGrid.min"
			]);
		}
		else {
			require([
					"", "resources/js/jquery.jqGrid.min"
			]);
		}
	},
	'resizeEvent' : function() {
		$(window).resize(function() {
			generic.resizeContent();
		});
		this.resizeContent();
	},
	'resizeContent' : function() {
		var bodyHeight = $('body').height();
		$('#content').height(bodyHeight - 103);
	},
	'goHome' : function() {
		$('#content').html('<img src="resources/imgs/tesoro.png" alt="imagenes"/>');
	}
};