$(document).ready(function () {
	moment.locale('pt-BR');
	console.log("<<<<<<<< Albuns.js >>>>>>>>>>>")
    var table = $('#table-albuns').DataTable({
    	searching: true,
    	order: [[ 1, "asc" ]],
    	lengthMenu: [5, 10],
        processing: true,
        serverSide: true,
        responsive: true,
        ajax: {
            url: '/albuns/datatables/server',
            data: 'data'
        },
        columns: [
			{data: 'id'},
            {data: 'nome'},
            {data: 'gravadora'},
            {data: 'interprete.nome'},
            {orderable: false, 
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-success btn-sm btn-block" href="/albuns/editar/'+ 
                    	id +'" role="button"><i class="fas fa-edit"></i></a>';
                }
            },
            {orderable: false,
             data: 'id',
                "render": function(id) {
                    return '<a class="btn btn-danger btn-sm btn-block" href="/albuns/excluir/'+ 
                    	id +'" role="button" data-toggle="modal" data-target="#confirm-modal"><i class="fas fa-times-circle"></i></a>';
                }               
            }
        ]
    });
});    

