<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<div><h2>!!Welcome ${name}!!</h2></div>
	<hr>
	<h2>Your Todos</h2>
	<table class="table">
		<thead>
			<tr>
				<th>Description</th>
				<th>TargetDate</th>
				<th>Is Done?</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a href="deletetodo?id=${todo.id}" class="btn btn-warning">DELETE</td>
						<td><a href="updatetodo?id=${todo.id}" class="btn btn-success">UPDATE</td>
					</tr>
				</c:forEach>
		</tbody>
	</table>
	<a href="/addtodo" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf" %>
