$(document).ready(function() {
	$('#form-horizontal')
    .bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	preScreenerNameModal: {
                validators: {
                    notEmpty: {
                        message: 'The Name is required and cannot be empty'
                    }
                }
            },
            preScreenerEmailModal: {
                validators: {
                    notEmpty: {
                        message: 'The email address is required and cannot be empty'
                    },
                    emailAddress: {
                        message: 'The email address is not valid'
                    }
                }
            },
            preScreenerContactNoModal: {
                validators: {
                    notEmpty: {
                        message: 'The ContactNo is required and cannot be empty'
                    }
                }
            }
        }
    })

    .on('success.form.bv', function(e) {
        // Reset the message element when the form is valid
        $('#errors').html('');
        submitFormdata();
    })

    .on('error.field.bv', function(e, data) {
        // data.bv      --> The BootstrapValidator instance
        // data.field   --> The field name
        // data.element --> The field element

        // Get the messages of field
        var messages = data.bv.getMessages(data.element);

        // Remove the field messages if they're already available
        $('#errors').find('li[data-field="' + data.field + '"]').remove();

        // Loop over the messages
        for (var i in messages) {
            // Create new 'li' element to show the message
            $('<li/>')
                .attr('data-field', data.field)
                .wrapInner(
                    $('<a/>')
                        .attr('href', 'javascript: void(0);')
                        .html(messages[i])
                        .on('click', function(e) {
                            // Focus on the invalid field
                            data.element.focus();
                        })
                )
                .appendTo('#errors');
        }

        // Hide the default message
        // $field.data('bv.messages') returns the default element containing the messages
        data.element
            .data('bv.messages')
            .find('.help-block[data-bv-for="' + data.field + '"]')
            .hide();
    })

    .on('success.field.bv', function(e, data) {
        // Remove the field messages
        $('#errors').find('li[data-field="' + data.field + '"]').remove();
    });
	
		
	 
	$(function () {
		if ($.trim($('#preScreenerTable tbody').html())==""){
		    $('.tablediv').hide();
		    $('#messagediv').show();
		    $('#messagediv').append("<h5> No Prescreeners Data Available at this moment </h5>");
		 }
		else
			{
			/*  To display the PreScreener datatables in the prescreener jsp page  */
			$('#messagediv').hide();
			$('.tablediv').show();
			$('#preScreenerTable').DataTable( {
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

	/* To get the next PreScreener id and display in the add pop modal */

	$('#preScreeneradd-btn').click(function(){
		$.getJSON('getNextPrescreenerId', function (data) {
			$('#preScreenerIdModal').prop("readonly", true);
			$('#preScreenerIdModal').val(data);
			$("#preScreenerNameModal").val(" ");
			$('#preScreenerEmailModal').val(" ");
			$("#preScreenerContactNoModal").val(" ");
			
		});
		$('#preScreenerIdModal').prop("readonly", true);
		$('#preScreenerModalProcess').val("preScreenerAdd");
		$('#preScreenerEditModal').modal({backdrop: 'static', keyboard: false});
		$('#preScreenerEditModal').modal('show');
		
	/*	url="./getPrescreenerId";
		$.ajax({
			type:"GET",
			dataType:"text",	
			contentType: "application/json",
			url:url,
			success:function(data){
				$('#preScreenerIdModal').val(data);		
			},
			error:function(req, status, error)
			{				
				console.log(req.responseText);
				console.log(status,error);
			}	
		});*/
	}); 

	
	/* To display the PreScreener delete confirmation pop modal  */

	$("#preScreenerTable tbody").on('click', '.btnpreScreenerDelete', function () {
		var table = $("#preScreenerTable").DataTable();
		var preScreener = table.row($(this).closest('tr')).data();			
		$('#preScreenerDeleteconfirmModalBody').html("Are you sure want to Delete this Prescreener <strong> "+preScreener[1]+" <strong> ?");
		$('#preScreenerModalDeletepreScreenerId	').val(preScreener[0]);
		$('#preScreenerDeleteconfirmModal').modal({backdrop: 'static', keyboard: false});
		$('#preScreenerDeleteconfirmModal').modal('show');		

	});

	/* To display the PreScreener update  pop modal */

	$("#preScreenerTable tbody").on('click', '.btnpreScreenerEdit', function () {
		var table = $("#preScreenerTable").DataTable();
		var preScreener = table.row($(this).closest('tr')).data();	
		$('#preScreenerModalProcess').val("preScreenerEdit");
		$('#preScreenerModal').prop("readonly", true);
		$('#preScreenerIdModal').val(preScreener[0]);
		$('#preScreenerNameModal').val(preScreener[1]);
		$('#preScreenerEmailModal').val(preScreener[2]);
		$('#preScreenerContactNoModal').val(preScreener[3]);
		$('#preScreenerActiveModal').val(preScreener[4]);
		$('#preScreenerEditModal').modal({backdrop: 'static', keyboard: false});
		$('#preScreenerEditModal').modal('show');		

	});

	/* Performs the functionality of adding or updating the preScreener informations */
	
	
	
	
	function submitFormdata(){
	/*$('#preScreenerModalEdit-btn').click(function(e) {*/
		var url;
		var preScreenerId = $('#preScreenerIdModal').val();
		var preScreenerName = $('#preScreenerNameModal').val();
		var preScreenerEmailId = $('#preScreenerEmailModal').val();
		var preScreenercontactNo = $('#preScreenerContactNoModal').val();
		var preScreenerActive = $('#preScreenerActiveModal :selected').val();
		var process = $('#preScreenerModalProcess').val();
		var data = '{"preScreenerId":"'+preScreenerId+'","preScreenerName":"'+preScreenerName+'","preScreenerEmailId":"'+preScreenerEmailId+'","preScreenercontactNo":"'+preScreenercontactNo+'","preScreenerActive":"'+preScreenerActive+'"}';		
		var firstName = $('#preScreenerNameModal');
		
		

	   
		if (process == 'preScreenerAdd')
		{
			url = "./addPrescreener";
		}
		else
		{
			url="./updatePrescreener";
		}

		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "application/json",
			url:url,
			data: data,
			success:function(data){
				$('#preScreenerEditModal').modal('hide');	
				$('#messageModalBody').html(data);
				$('#messageModal').modal('show');  				
			},
			error:function(req, status, error)
			{
				
				console.log(req.responseText);
				console.log(status,error);
			}	
		});

	}

	/* Reload the page after the message modal is closed */

	$('#messageClose-btn').click(function(){
		location.reload(); 
	});

	/* Performs the functionality of deleting the select Prescreener */

	$('#preScreenerModalDelete-btn').click(function(){
		$('#preScreenerDeleteconfirmModal').modal('hide');
		var preScreenerId= $('#preScreenerModalDeletepreScreenerId').val();
		
		$.ajax({
			type:"POST",
			dataType:"text",
			contentType: "text/plain",
			url:"./deletePrescreener/"+preScreenerId,
			data: "",
			success:function(data){
				$('#preScreenerDeleteModal').modal('hide');	
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