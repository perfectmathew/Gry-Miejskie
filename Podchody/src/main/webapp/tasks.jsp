
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.podchody.Exams"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file="/WEB-INF/template/header.jsp"%>
       <style><%@include file="/WEB-INF/css/main.css"%></style>
       <script><%@include file="/WEB-INF/js/functions.js"%></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gry miejskie</title>
    </head>
    <body>
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
          <h2>Zarządzaj <b>Testami</b></h2>
        </div>
        <div class="col-sm-6">
          <a id="adde"  class="btn btn-success" data-toggle="modal"><i class="fa fa-plus" aria-hidden="true"></i> <span>Dodaj nowy test</span></a>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Nazwa</th>
          <th>Ilość pytań</th>
          <th>Próg zdawalności</th>
          <th>Akcje</th>
        </tr>
      </thead>
      <tbody>
           <%
        ArrayList<Exams> examlist = (ArrayList<Exams>) request.getAttribute("listexams");
             for(Exams exam : examlist){   %>
        <tr>
          <td><%= exam.getNazwa() %></td>
          <td><%= exam.getIlosc() %></td>
          <td><%= exam.getProg() %></td>
          <td>
              <a href="e?id=<%= exam.getID() %>" class="edit" data-toggle="modal"><i class="fa fa-pencil-square" data-toggle="tooltip" title="Edit"></i></a>
            <a  class="delete" data-toggle="modal"><i class="fa fa-trash" data-toggle="tooltip" title="Delete"></i></a>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </div>
</div>

<div id="addExam" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <form method="post" action="efunction?type=addexam">
        <div class="modal-header">
          <h4 class="modal-title">Stwórz nowy egzamin</h4>
          <button type="button" id="closemodal" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>Nazwa</label>
            <input type="text" class="form-control" name="name" id="name" required>
          </div>
          <div class="form-group">
            <label>Próg zaliczenia</label>
            <input type="number" name="prog" id="prog" min="0.00" step="0.10" max="100.00" class="form-control" required>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" id="closemodal" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-success" value="Add">
        </div>
      </form>
    </div>
  </div>
</div>

<!-- Edit Modal HTML -->
<script>
$(document).on('click', '#closemodal', function(e) {
  e.preventDefault();
  $('#addExam').modal('hide'); 
});
$(document).on('click', '#adde', function(e) {
  e.preventDefault();
  $('#addExam').modal('show');
});
 </script>
<div id="editEmployeeModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form>
        <div class="modal-header">
          <h4 class="modal-title">Edit Employee</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" required>
          </div>
          <div class="form-group">
            <label>Address</label>
            <textarea class="form-control" required></textarea>
          </div>
          <div class="form-group">
            <label>Phone</label>
            <input type="text" class="form-control" required>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-info" value="Save">
        </div>
      </form>
    </div>
  </div>
</div>
<!-- Delete Modal HTML -->
<div id="deleteEmployeeModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form>
        <div class="modal-header">
          <h4 class="modal-title">Usunięcie zadania</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Czy napewno chcesz usunąć to zadanie?</p>
          <p class="text-warning"><small>Ta akcja nie będzie możliwa do cofnięcia.</small></p>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-danger" value="Delete">
        </div>
      </form>
    </div>
  </div>
</div>
    </body>
</html>
