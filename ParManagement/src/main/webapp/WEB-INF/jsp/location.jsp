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
	
	<title>Location </title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<!--  Jquery CSS -->
	<link rel="stylesheet" href="static/css/jquery-ui.min.css">
	<link rel="stylesheet" href="static/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="static/css/bootstrap.css">
	<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/datatables.bootstrap4.min.css"> 
	<!-- Par Management CSS -->
	<link rel="stylesheet" href="static/css/common.css">
	<link rel="stylesheet" href="static/css/location.css">		
	
	<!-- JQuery -->
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/jquery-3.5.1.js"></script>

	<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>	
	<script type="text/javascript" src="static/js/bootstrap.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/datatables.bootstrap4.min.js"></script>	
	<!-- Par Management Java Script -->
	<script type="text/javascript" src="static/js/common.js"></script>
	<script type="text/javascript" src="static/js/location.js"></script>
</head>
<body>
		<!-- Page Header and Menu jsp -->
		<jsp:include page="header-menu.jsp" />
	
		<div class="main">
			<div >
				<h1 class="screen-title">Par Location </h1>
			</div>

		    <div class="tablediv">
		    	<table id="ParlocationTable" class="table table-striped table-hover table-bordered" style="width:100%">
				     <thead>
				         <tr>
				         	 <th>Par-Location-Id</th>
				         	 <th>Par-Location-Name</th>
				             <th>Par-Location-Active</th>
				             <th>Action </th>
				         </tr>
				     </thead>
				     <tbody>
				     	<c:forEach var="location" items="${alllocationList}">
				     		<tr>
				     			<td>${location.locationId}</td>
				     			<td>${location.locationName}</td>
				     			<td>${location.locationActive}</td>				     			
				     			<td>
				     				<button type="button" class="btn btnlocationEdit btn-link" id="locationTableEdit-btn">Edit</button>/
				     				<button type="button" class="btn btnlocationDelete btn-link" id="locationDelete-btn">Delete</button>
				     			</td>
				     		</tr>        		
				     	</c:forEach>
				     </tbody>
				</table>
		   </div>
		   <div id="messagediv">
		   </div>
		   	<div>
				<button type="button" class="btn btn-primary btnlocationAdd" id="locationadd-btn">Add New </button>
			</div>
		
		</div>
		<footer class="footer">
			<span>Copyright &copy; 2020 HTC GLOBAL SERVICES All rights reserved.</span>
		</footer>
		
		
		<!-- Delete Prescreener Message Modal -->
		<div class="modal fade" id="locationDeleteconfirmModal" tabindex="-1"
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
						<p id="locationDeleteconfirmModalBody"></p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="locationModalDelete-btn">Delete</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="locationModalDeletelocationId"/>
					</div>
				</div>
			</div>
		</div>f
		
		<!-- Edit Prescreener Message Modal -->
		<div class="modal fade" id="locationEditModal" tabindex="-1"
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
						<form class="form-horizontal">
						  <div class="form-group">
						    <label>Location-Id</label>
						    <input type="text" class="form-control" id="locationIdModal">
						  </div> 
						  <div class="form-group">
						    <label>Location-Name</label>
						    <input type="text" class="form-control" id="locationNameModal" required>
						  </div> 
						  <div class="form-group">
						    <label>Location-Active</label>
						   <select class="form-control" id="locationActiveModal">
							      <option value="Yes">Yes</option>
							      <option value="No">No</option>
							 </select>
							</div> 
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="locationModalEdit-btn">Save</button>
						<button type="button" class="btn btn-primary cancel"
							data-dismiss="modal">Cancel</button>
						<input type="hidden" id="locationModalProcess"/>
					</div>
				</div>
			</div>
		</div>
		
		<!-- Message Modal -->
		<jsp:include page="Message.jsp" />
</body>
</html>