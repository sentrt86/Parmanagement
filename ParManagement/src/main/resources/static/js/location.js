$(document).ready(function() {

	$(function () {
		if ($.trim($('#ParlocationTable tbody').html())==""){
		    $('.tablediv').hide();
		    $('#messagediv').show();
		    $('#messagediv').append("<h5> No Location Data Available at this moment </h5>");
		 }
		else
			{
			/*  To display the Location datatables in the Location jsp page  */
			$('#messagediv').hide();
			$('.tablediv').show();
			$('#ParlocationTable').DataTable( {
				"scrollY":        "300px",
				"scrollCollapse": true
			});
			}
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});

	/* To get the next Location id and display in the add pop modal */

	$('#locationadd-btn').click(function(){
		$.getJSON('getNextLocationId', function (data) {
			$('#locationIdModal').prop("readonly", true);
			$('#locationIdModal').val(data);
			$("#locationNameModal").val(" ");
		});
		$('#locationIdModal').prop("readonly", true);
		$('#locationModalProcess').val("locationAdd");
		$('#locationEditModal').modal('show');
	/*	url="./getLocationId";
		$.ajax({
			type:"GET",
			dataType:"text",	
			contentType: "application/json",
			url:url,
			success:function(data){
				$('#LocationIdModal').val(data);		
			},
			error:function(req, status, error)
			{				
				console.log(req.responseText);
				console.log(status,error);
			}	
		});*/
	}); 

	
	/* To display the Location delete confirmation pop modal  */

	$("#ParlocationTable tbody").on('click', '.btnlocationDelete', function () {
		var table = $("#ParlocationTable").DataTable();
		var location = table.row($(this).closest('tr')).data();			
		$('#locationDeleteconfirmModalBody').html("Are you sure want to Delete this Location <strong> "+location[1]+" <strong> ?");
		$('#locationModalDeletelocationId').val(location[0]);
		$('#locationDeleteconfirmModal').modal('show');		

	});

	/* To display the Location update  pop modal */

	$("#ParlocationTable tbody").on('click', '.btnlocationEdit', function () {
		var table = $("#ParlocationTable").DataTable();
		var location = table.row($(this).closest('tr')).data();	
		$('#locationModalProcess').val("locationEdit");
		$('#locationIdModal').prop("readonly", true);
		$('#locationIdModal').val(location[0]);
		$('#locationNameModal').val(location[1]);
		$('#locationActiveModal').val(location[2]);
		$('#locationEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the Location informations */

	$('#locationModalEdit-btn').click(function() {
		var url;
		var locationId = $('#locationIdModal').val();
		var locationName = $('#locationNameModal').val();
		var locationActive = $('#locationActiveModal :selected').val();
		var process = $('#locationModalProcess').val();
		var data = '{"locationId":"'+locationId+'","locationName":"'+locationName+'","locationActive":"'+locationActive+'"}';

		if (process == 'locationAdd')
		{
			url = "./addLocation";
		}
		else
		{
			url="./updateLocation";
		}

		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "application/json",
			url:url,
			data: data,
			success:function(data){
				$('#locationEditModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				
				console.log(req.responseText);
				console.log(status,error);
			}	
		});

	});

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the select Location */

	$('#locationModalDelete-btn').click(function(){
		$('#locationDeleteconfirmModal').modal('hide');
		var locationId= $('#locationModalDeletelocationId').val();
		
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deleteLocation/"+locationId,
			data: "",
			success:function(data){
				$('#locationDeleteModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				console.log(req.responseText);
				console.log(status,error);
			}	
		});
	});





} );