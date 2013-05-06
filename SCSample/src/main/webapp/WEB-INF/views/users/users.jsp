<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<c:url value="/users/records" var="recordsUrl"/>
<c:url value="/users/create" var="addUrl"/>
<c:url value="/users/update" var="editUrl"/>
<c:url value="/users/delete" var="deleteUrl"/>
<html>
<head>

	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/pepper-grinder/jquery-ui.css"/>'/>
	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/js/jqGrid-4.4.5/css/ui.jqgrid.css"/>'/>
<%-- 	<link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/style.css"/>'/> --%>
	
	<script type='text/javascript' src='<c:url value="/js/jquery-1.9.1.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/js/ui/jquery-ui.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/js/jqGrid-4.4.5/js/i18n/grid.locale-en.js"/>'></script>
	<script type='text/javascript' src='<c:url value="/js/jqGrid-4.4.5/js/jquery.jqGrid.min.js"/>'></script>
<%-- 	<script type='text/javascript' src='<c:url value="/js/custom.js"/>'></script> --%>
	
<title>Insert title here</title>

<script type='text/javascript'>
	$(function() {
		$("#grid").jqGrid({
		   	url:'${recordsUrl}',
			datatype: 'json',
			mtype: 'GET',
		   	colNames:['Id', 'Username', 'Password', 'First Name', 'Last Name'],
		   	colModel:[
		   		{name:'id',index:'id', width:55, editable:false, editoptions:{readonly:true, size:10}, hidden:true},
		   		{name:'username',index:'username', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'password',index:'password', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}, edittype:'password', hidden:true},
		   		{name:'firstName',index:'firstName', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}},
		   		{name:'lastName',index:'lastName', width:100, editable:true, editrules:{required:true}, editoptions:{size:10}}
		   		
		   	],
		   	postData: {},
			rowNum:10,
		   	rowList:[10,20,40,60],
		   	height: 240,
		   	autowidth: true,
			rownumbers: true,
		   	pager: '#pager',
		   	sortname: 'id',
		    viewrecords: true,
		    sortorder: "asc",
		    caption:"Records",
		    emptyrecords: "Empty records",
		    loadonce: false,
		    loadComplete: function() {},
		    jsonReader : {
		        root: "rows",
		        page: "page",
		        total: "total",
		        records: "records",
		        repeatitems: false,
		        cell: "cell",
		        id: "id"
		    }
		});
 
		$("#grid").jqGrid('navGrid','#pager',
				{edit:false, add:false, del:false, search:true},
				{}, {}, {}, 
				{ 	// search
					sopt:['cn', 'eq', 'ne', 'lt', 'gt', 'bw', 'ew'],
					closeOnEscape: true, 
					multipleSearch: true, 
					closeAfterSearch: true
				}
		);
		
		$("#grid").navButtonAdd('#pager',
				{ 	caption:"Add", 
					buttonicon:"ui-icon-plus", 
					onClickButton: addRow,
					position: "last", 
					title:"", 
					cursor: "pointer"
				} 
		);
		
// 		$("#grid").navButtonAdd('#pager',
// 				{ 	caption:"Edit", 
// 					buttonicon:"ui-icon-pencil", 
// 					onClickButton: editRow,
// 					position: "last", 
// 					title:"", 
// 					cursor: "pointer"
// 				} 
// 		);
		
// 		$("#grid").navButtonAdd('#pager',
// 			{ 	caption:"Delete", 
// 				buttonicon:"ui-icon-trash", 
// 				onClickButton: deleteRow,
// 				position: "last", 
// 				title:"", 
// 				cursor: "pointer"
// 			} 
// 		);
 
		// Toolbar Search
		$("#grid").jqGrid('filterToolbar',{stringResult: true,searchOnEnter : true, defaultSearch:"cn"});
	});
	
	function addRow() {
   		$("#grid").jqGrid('setColProp', 'username', {editoptions:{readonly:false, size:10}});
   		$("#grid").jqGrid('setColProp', 'password', {hidden: false});
   		$("#grid").jqGrid('setColProp', 'password', {editrules:{required:true}});
   		
		// Get the currently selected row
		$('#grid').jqGrid('editGridRow','new',
	    		{ 	url: '${recordsUrl}', 					
					editData: {},
	                serializeEditData: function(data){ 
	                    data.id = 0; 
	                    return $.param(data);
	                },
				    recreateForm: true,
				    beforeShowForm: function(form) {
			            $('#pData').hide();  
			            $('#nData').hide();
			            $('#password',form).addClass('ui-widget-content').addClass('ui-corner-all');
				    },
					beforeInitData: function(form) {},
					closeAfterAdd: true,
					reloadAfterSubmit:true,
					afterSubmit : function(response, postdata) 
					{ 
				        var result = eval('(' + response.responseText + ')');
						var errors = "";
						
				        if (result.success == false) {
							for (var i = 0; i < result.message.length; i++) {
								errors +=  result.message[i] + "<br/>";
							}
				        }  else {
				        	$('#msgbox').text('Entry has been added successfully');
							$('#msgbox').dialog( 
									{	title: 'Success',
										modal: true,
										buttons: {"Ok": function()  {
											$(this).dialog("close");} 
										}
									});
		                }
				    	// only used for adding new records
				    	var newId = null;
				    	
						return [result.success, errors, newId];
					}
	    		});
 
   		$("#grid").jqGrid('setColProp', 'password', {hidden: true});
	} // end of addRow
	
	</script>
</head>
<body>
	<h1 id='banner'>System Records</h1>
	
	<div id='jqgrid'>
		<table id='grid'></table>
		<div id='pager'></div>
	</div>
	
	<div id='msgbox' title='' style='display:none'></div>
</body>
</html>