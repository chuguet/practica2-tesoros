<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="titlePage">Listado de Encuestas</div>
<ol data-role="listview" id="listadoEncuestas" style="padding-top:10px;margin:10px;">
	
</ol>



<script type="text/javascript">
function showInformationIntoView(information){
	rellenarEncuesta.createList(information);
};
</script>