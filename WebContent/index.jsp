<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/script.js"></script>
<title>Encontre os Pokémons</title>
</head>
<body>
	<div class="container">
		<div class="text-center">
			<a href=""><img class="my-3" height="200" width="500" src="resources\img\pokemon.png"></a>
			<h2>Encontre pokémons em todo o mundo!</h2>
			<form class="form-inline mx-auto" action="Controller" method="get">
				<div class="d-block mx-auto">
					<div class="input-group my-3">
						<input type="text" class="form-control" name="city" placeholder="Digite uma cidade">
						<div class="input-group-append">
							<button class="btn btn-success" type="submit">Localizar</button>
						</div>
					</div>
				</div>
			</form>
			<c:if test="${not empty temp}">
				<div>
					<div>
						<label><strong>Temperatura atual: </strong></label>
						<span>${temp}</span>
					</div>
					<div>
						<label><strong>Chuva no local: </strong></label>
						<span>${rain}</span>
					</div>
					<div>
						<label><strong>Pokémon: </strong></label>
						<span>${pokemon}</span>
					</div>
				</div>
			</c:if>
		</div>
	</div>
	<div class="modal fade" id="message" tabindex="" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="title">Alerta</h4>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h5 id="text">${erro}</h5>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>