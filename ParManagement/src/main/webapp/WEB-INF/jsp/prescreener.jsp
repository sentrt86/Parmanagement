<!-- <!DOCTYPE html> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" %>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/> 
	
	<title>PreScreener </title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	<link rel="stylesheet" href="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>

	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/prescreener.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.14.0/jquery.validate.min.js"></script>


	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>
	<script type="text/javascript" src="//cdn.jsdelivr.net/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/prescreener.js"></script>
</head>
<body>
		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
		<div class="main">
			<div >
				<h1 class="screen-title">PreScreener</h1>
			</div>

		    <div class="tablediv">
		    	<table id="preScreenerTable" class="table table-striped table-hover table-bordered" style="width:100%">
				     <thead>
				         <tr>
				         	 <th>Prescreeer  Id</th>
				         	 <th>Prescreener Name</th>
				             <th>Prescreener Email-Id</th>
				             <th>Prescreener Contact No</th>
				             <th>Prescreener Active</th>
				             <th>Action </th>
				         </tr>
				     </thead>
				     <tbody>
				     	<c:forEach var="preScreener" items="${allPrescreenerList}">
				     		<tr>
				     			<td>${preScreener.preScreenerId}</td>
				     			<td>${preScreener.preScreenerName}</td>
				     			<td>${preScreener.preScreenerEmailId}</td>
				     			<td>${preScreener.preScreenercontactNo}</td>
				     			<td>${preScreener.preScreenerActive}</td>
				     			<td>
				     				<button type="button" class="btn btnpreScreenerEdit btn-link" id="preScreenerTableEdit-btn">Edit</button>/
				     				<button type="button" class="btn btnpreScreenerDelete btn-link" id="preScreenerDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
				     </tbody>
				</table>
		   </div>
		   <div id="messagediv">
		   </div>
		   
		   <div>
				<button type="button" class="btn btn-primary btnpreScreenerAdd" id="preScreeneradd-btn">Add New </button>
		   </div>
		
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		
		<!-- Delete Prescreener Message Modal -->
		<div class="modal fade" id="preScreenerDeleteconfirmModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Delete
							Confirmation</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="preScreenerDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="preScreenerModalDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="preScreenerModalDeletepreScreenerId"/>
					</div>
				</div>
			</div>
		</div>f
		
		<!-- Edit Prescreener Message Modal -->
		<div class="modal fade" id="preScreenerEditModal" tabindex="-1"
			role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="confirmModalLabel">Add / Update Prescreener</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form role="form" id="form-horizontal" class="needs-validation" novalidate>
						  
						  <div class="form-group">
						    <label>Id</label>
						    <input type="text" class="form-control" id="preScreenerIdModal">
						  </div> 
						  
						  <div class="form-group">
						    <label>Name</label>
						    <input type="text" class="form-control required" id="preScreenerNameModal" name="preScreenerNameModal" required/>
						  </div>
						  
						  <div class="form-group">
						    <label>Email-Id</label>
							    <input type="text" class="form-control" id="preScreenerEmailModal" name="preScreenerEmailModal">
						  </div>
						  
						  <div class="form-group">
						    <label>ContactNo</label>
						    <input type="text" class="form-control" id="preScreenerContactNoModal" name="preScreenerContactNoModal">
						  </div> 
						  
						  <div class="form-group">
						    <label>Active</label>
						    <select class="form-control" id="preScreenerActiveModal">
							      <option value="Yes">Yes</option>
							      <option value="No">No</option>
							 </select>
						  </div> 
						  
						  <div class="form-group">
        						<div class=form-control">
            						<ul id="errors"></ul>
        						</div>
    					  </div>
					
						  <div class="modal-footer">
								<button type="submit" class="btn btn-primary" id="preScreenerModalEdit-btn">Save</button>
								<button type="button" class="btn btn-primary cancel"
									data-dismiss="modal">Cancel</button>
								<input type="hidden" id="preScreenerModalProcess"/>
						  </div>
						</form>	
					</div>
				</div>
			</div>
		</div>
		
		<!-- Message Modal -->
		<jsp:include page="Message.jsp" />
</body>
</html>