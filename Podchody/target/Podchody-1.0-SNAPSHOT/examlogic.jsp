

<%@page import="com.mycompany.podchody.Tasks"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <head>
       <%@include file="/WEB-INF/template/header.jsp"%>
       <style><%@include file="/WEB-INF/css/main.css"%></style>
       <script><%@include file="/WEB-INF/js/functions.js"%></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zarządzanie Testami | Gry miejskie</title>
    </head>
    <body>
        <% String examid;
        examid = (String)request.getAttribute("IDOFEXAM");
            %>
        <div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
          <h2>TEST / <b>Pytania</b></h2>
        </div>
        <div class="col-sm-6">
          <a id="adde"  class="btn btn-success" data-toggle="modal"><i class="fa fa-plus" aria-hidden="true"></i> <span>Dodaj nowe pytanie</span></a>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Treść</th>
          <th>Odpowiedź A</th>
          <th>Odpowiedź B</th>
          <th>Odpowiedź C</th>
          <th>Odpowiedź D</th>
          <th>Poprawna odpowiedź</th>
          <th>Akcje</th>
        </tr>
      </thead>
      <tbody>
           <%
        ArrayList<Tasks> tasklist = (ArrayList<Tasks>) request.getAttribute("taskslist");
             for(Tasks task : tasklist){   %>
        <tr>
          <td><%= task.getTresc() %></td>
          <td><%= task.getOdpA() %></td>
          <td><%= task.getOdpB() %></td>
          <td><%= task.getOdpC() %></td>
          <td><%= task.getOdpD() %></td>
          <td><%= task.getPoprawanaOdp() %></td>
          <td>
              <a class="edit" data-toggle="modal"><i class="fa fa-pencil-square" data-toggle="tooltip" title="Edit"></i></a>
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
        <form method="post" action="efunction?type=addquestion">
        <div class="modal-header">
          <h4 class="modal-title">Dodaj pytanie</h4>
          <button type="button" id="closemodal" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
            <input type="hidden" name="examid" id="examid" value="<%= examid %>">
          <div class="form-group">
            <label>Tresc</label>
            <input type="text" class="form-control" name="tresc" id="tresc" required>
          </div>
          <div class="form-group">
            <label>Odpowiedź A</label>
            <input type="text" name="OdpA" id="OdpA" class="form-control" required>
          </div>
           <div class="form-group">
            <label>Odpowiedź B</label>
            <input type="text" name="OdpB" id="OdpB" class="form-control" required>
          </div>
              <div class="form-group">
            <label>Odpowiedź C</label>
            <input type="text" name="OdpC" id="OdpC" class="form-control" required>
          </div>
           <div class="form-group">
            <label>Odpowiedź D</label>
            <input type="text" name="OdpD" id="OdpD" class="form-control" required>
          </div>
            <div class="form-group">
            <label>Poprwana odpowiedź</label>
            <select  name="PoprawnaOdp" id="PoprawnaOdp" class="form-control">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>
          </div> 
        </div>
        <div class="modal-footer">
          <input type="button" id="closemodal" class="btn btn-default" data-dismiss="modal" value="Cancel">
          <input type="submit" class="btn btn-success" value="Dodaj">
        </div>
      </form>
    </div>
  </div>
</div>

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
