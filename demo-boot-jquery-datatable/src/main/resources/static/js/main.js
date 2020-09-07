$(document).ready(function() {
	moment.locale("pt-br");
	
	var table = $("#pessoaTable").DataTable( {	
					"dom":' <"search"f><"top"l>rt<"bottom"ip><"clear">',
					"language" : {
						"url" : "../docs/portuguese.json"
					},
			        "columns": [
			        	{ "data" : null},
						{ "data" : "sequencial" }, 
						{ "data" : "cpf" }, 
						{ "data" : "nome" }, 
						{ "data" : "endereco" }, 
						{"data" : "cidade" }, 
						{ "data" : "estado" }, 
						{ "data" : "cep" }, 
						{"data" : "datNasc", 
									render: function(datNasc){ 
												return moment(datNasc).format("L"); 
											} 
						}, 
						{"data" : "foneRes"}, 
						{"data" : "salario", render: $.fn.dataTable.render.number( '.', ',', 2, 'R$' )}
			        ],
					"columnDefs": [{
                		"className": "small",
                		"targets": "_all"
            		}] //columnDefs for align text to center
				});

	//para limpiar los campos antes de dar de Alta una Persona
	$("#btnNuevo").click(function(){
	    $("#formulario-pessoa").trigger("reset");


	    $(".modal-header").css( "background-color", "#17a2b8");
	    $(".modal-header").css( "color", "white" );
	    $(".modal-title").text("Criar Pessoa");
	    $('#modalCRUD').modal('show');	    
	});
	
    $('#btn-pesquisar').on('click',function(){
		$('#pessoaTable').DataTable( {
			 "destroy" : true,
			"scrollY" : "300px",
			"scrollX" : true,
			"select" : true,
            "serverSide" : false,
			"sAjaxDataProp" : "data",
			"scrollCollapse" : true,
			"info" : false,
			"language" : {
				"decimal" : ",",
				"thousands" : "."
			},
			"pagingType" : "full_numbers",
			"language" : {
				"url" : "../docs/portuguese.json"
			},
			"sPaginationType" : "bootstrap",
			"contentType" : "application/json; charset=utf-8",
			"aLengthMenu" : [ [ 5, 10, 20, 100, -1 ],
					[ 5, 10, 20, 100, "Todos" ] ],
			"pageLength": 5,
			"lengthChange" : true,
			"processing" : true,
			"serverSide" : false,
	        "ajax": {
                    "url": "../pessoa/api",
                    "type": "GET",
                    "data": {
                        'cpf': $('#cpf-busca').val(),
                        'nome': $('#nome-busca').val()
                    },
				"dataType": "json"                               
            },
	        "columns": [
	        	{"defaultContent":"<div class='text-center'>"+
	        						"<div class='btn-group'>"+
	        							"<button class='btn btn-light btn-sm btnEditar'><i class='material-icons'>edit</i></button>&nbsp;"+
	        							"<button class='btn btn-light btn-sm btnBorrar'><i class='material-icons'>delete</i></button>"+
	        						"</div>"+
	        					   "</div>"}, 
				{ "data" : "sequencial" }, 
				{ "data" : "cpf" }, 
				{ "data" : "nome" }, 
				{ "data" : "endereco" }, 
				{"data" : "cidade" }, 
				{ "data" : "estado" }, 
				{ "data" : "cep" }, 
				{"data" : "datNasc", 
							render: function(datNasc){ 
										return moment(datNasc).format("L"); 
									} 
				}, 
				{"data" : "foneRes"}, 
				{"data" : "salario", render: $.fn.dataTable.render.number( '.', ',', 2, 'R$' )}
	         ],
		"columnDefs": [{
    		"className": "small",
    		"targets": "_all"
	        }  ],
	        select: true,
	        colReorder: true,
	    } );
		
    });

	//Editar        
	$(document).on("click", ".btnEditar", function(){
		var pessoaReturn;		        
	    fila = $(this).closest("tr");	        
	    sequencial = parseInt(fila.find('td:eq(1)').text()); //capturo el ID
	    
        $.ajax({
          url: "../pessoa/api",
          type: "GET",
          datatype:"json",    
          data:  {sequencial : sequencial} ,    
          success: function(response) {
				$('#sequencial').val(response.data[0].sequencial);
				$('#cpf').val(response.data[0].cpf);
				$('#nome').val(response.data[0].nome);
				$('#endereco').val(response.data[0].endereco);
				$('#cidade').val(response.data[0].cidade);
				$('#estado').val(response.data[0].estado);
				$('#cep').val(response.data[0].cep);
				$('#foneRes').val( response.data[0].foneRes );
				$('#datNasc').val(response.data[0].datNasc);
				$('#salario').val(response.data[0].salario);
				$(".modal-header").css("background-color", "#007bff");
				$(".modal-header").css("color", "white" );
				$(".modal-title").text("Editar Pessoa");		
           }
        });	
	    
	    $('#modalCRUD').modal('show');
	});


	// Incluir e Alterar
	$('#formulario-pessoa').submit(function(e){                         
	    e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
	    
	    
		var _method;
		var _params;
			    
		var sequencial = $('#sequencial').val();
        var cpf = $('#cpf').val();
        var nome = $('#nome').val();
        var endereco = $('#endereco').val();
        var cidade = $('#cidade').val();
        var estado = $('#estado').val();
        var cep = $('#cep').val();
        var foneRes = $('#foneRes').val();
        var datNasc = $('#datNasc').val();
        var salario = $('#salario').val();

		if (sequencial !== ''){
			_params = { url : '../pessoa/api/' + $('#sequencial').val(),
						type : 'PUT',
						datatype : 'json',
						data : { sequencial	: $('#sequencial').val(),
									cpf 		: cpf,
									nome 		: nome,
									endereco 	: endereco,
									cidade 		: cidade,
									estado 		: estado,
									cep 		: cep,
									foneRes 	: foneRes,
									datNasc 	: datNasc,
									salario 	: salario 
								},
						success : function( data ){
									$('#pessoaTable').DataTable().ajax.reload(null, false);
								},
						error : function( error ){
							alert(error);
						}
					};
		} else {
			_params = { url : '../pessoa/api',
						type : 'POST',
						datatype : 'json',
						data : { 	sequencial	: null,
									cpf 		: cpf,
									nome 		: nome,
									endereco 	: endereco,
									cidade 		: cidade,
									estado 		: estado,
									cep 		: cep,
									foneRes 	: foneRes,
									datNasc 	: datNasc,
									salario 	: salario 
						},
						success : function( data ){
							$('#cpf-busca').val( cpf );
							$('#nome-busca').val('');
							$('#btn-pesquisar').click();
						},
						error : function( error ){
							alert(error);
						}
					 };
		}
	                            
        $.ajax(	_params );			        

	    $('#modalCRUD').modal('hide');											     			
	});

	// Deletar
	$(document).on("click", ".btnBorrar", function(){
	    fila = $(this);           
	    user_id = parseInt($(this).closest('tr').find('td:eq(0)').text()) ;		
	    opcion = 3; //eliminar        

	    var fila = $(this).closest("tr");	        
	    var sequencial = parseInt(fila.find('td:eq(1)').text()); //capturo el ID
	    var cpf = fila.find('td:eq(2)').text();
	    var nome = fila.find('td:eq(3)').text();


	    var resposta = confirm("Confirma a exclusão do CPF " + cpf + " - " + nome); 
	                   
	    if (resposta) {
	        $.ajax({
	          url: '../pessoa/api/' + sequencial,
	          type: "DELETE",
	          datatype:"json",    
	          success: function() {
					$('#pessoaTable').DataTable().ajax.reload(null, false);
					alert('Eliminação concluída com sucesso');
	           },
				error : function( error, exception ){
					alert(error.status + " - " + exception);
				}
	           
	        });	
	    }
	 });



});
/*
				        	
				        	
				        	"<button id='btn-abrir' class='btn btn-default btn-sm'>A</button>&nbsp;" +
				           				  "<button id='btn-eliminar' class='btn btn-default btn-sm' >D</button>"}
				           				  
							{ "data": null, render: function ( data, type, row ) {
				                // Combine the first and last names into a single table field
				                return data.cpf+' '+data.nome;
				            } }










	var user_id, opcion;
	opcion = 4;
	    
	tablaUsuarios = $('#tablaUsuarios').DataTable({  
	    "ajax":{            
	        "url": "bd/crud.php", 
	        "method": 'POST', //usamos el metodo POST
	        "data":{opcion:opcion}, //enviamos opcion 4 para que haga un SELECT
	        "dataSrc":""
	    },
	    "columns":[
	        {"data": "user_id"},
	        {"data": "username"},
	        {"data": "first_name"},
	        {"data": "last_name"},
	        {"data": "gender"},
	        {"data": "password"},
	        {"data": "status"},
	        {"defaultContent": "<div class='text-center'><div class='btn-group'><button class='btn btn-primary btn-sm btnEditar'><i class='material-icons'>edit</i></button><button class='btn btn-danger btn-sm btnBorrar'><i class='material-icons'>delete</i></button></div></div>"}
	    ]
	});     
	
	var fila; //captura la fila, para editar o eliminar
	//submit para el Alta y Actualización
	$('#formUsuarios').submit(function(e){                         
	    e.preventDefault(); //evita el comportambiento normal del submit, es decir, recarga total de la página
	    username = $.trim($('#username').val());    
	    first_name = $.trim($('#first_name').val());
	    last_name = $.trim($('#last_name').val());    
	    gender = $.trim($('#gender').val());    
	    password = $.trim($('#password').val());
	    status = $.trim($('#status').val());                            
	        $.ajax({
	          url: "bd/crud.php",
	          type: "POST",
	          datatype:"json",    
	          data:  {user_id:user_id, username:username, first_name:first_name, last_name:last_name, gender:gender, password:password ,status:status ,opcion:opcion},    
	          success: function(data) {
	            tablaUsuarios.ajax.reload(null, false);
	           }
	        });			        
	    $('#modalCRUD').modal('hide');											     			
	});
	        
	 
	
	//para limpiar los campos antes de dar de Alta una Persona
	$("#btnNuevo").click(function(){
	    opcion = 1; //alta           
	    user_id=null;
	    $("#formUsuarios").trigger("reset");
	    $(".modal-header").css( "background-color", "#17a2b8");
	    $(".modal-header").css( "color", "white" );
	    $(".modal-title").text("Alta de Usuario");
	    $('#modalCRUD').modal('show');	    
	});
	
	//Editar        
	$(document).on("click", ".btnEditar", function(){		        
	    opcion = 2;//editar
	    fila = $(this).closest("tr");	        
	    user_id = parseInt(fila.find('td:eq(0)').text()); //capturo el ID		            
	    username = fila.find('td:eq(1)').text();
	    first_name = fila.find('td:eq(2)').text();
	    last_name = fila.find('td:eq(3)').text();
	    gender = fila.find('td:eq(4)').text();
	    password = fila.find('td:eq(5)').text();
	    status = fila.find('td:eq(6)').text();
	    $("#username").val(username);
	    $("#first_name").val(first_name);
	    $("#last_name").val(last_name);
	    $("#gender").val(gender);
	    $("#password").val(password);
	    $("#status").val(status);
	    $(".modal-header").css("background-color", "#007bff");
	    $(".modal-header").css("color", "white" );
	    $(".modal-title").text("Editar Usuario");		
	    $('#modalCRUD').modal('show');		   
	});
	
	//Borrar
	$(document).on("click", ".btnBorrar", function(){
	    fila = $(this);           
	    user_id = parseInt($(this).closest('tr').find('td:eq(0)').text()) ;		
	    opcion = 3; //eliminar        
	    var respuesta = confirm("¿Está seguro de borrar el registro "+user_id+"?");                
	    if (respuesta) {            
	        $.ajax({
	          url: "bd/crud.php",
	          type: "POST",
	          datatype:"json",    
	          data:  {opcion:opcion, user_id:user_id},    
	          success: function() {
	              tablaUsuarios.row(fila.parents('tr')).remove().draw();                  
	           }
	        });	
	    }
	 });
});    
*/