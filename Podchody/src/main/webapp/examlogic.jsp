

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
            <h2><a href="exams">Testy </a> / <b>Pytania</b></h2>
        </div>
        <div class="col-sm-6">
          <a id="conex" class="btn btn-success" data-toggle="modal"><i class="fa fa-link" aria-hidden="true"></i> <span>Przypisz pytanie</span></a>
          <a id="addex" class="btn btn-success" data-toggle="modal"><i class="fa fa-plus" aria-hidden="true"></i> <span>Dodaj nowe pytanie</span></a>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Treść</th>
          <th>Obraz</th>
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
          <td><%= task.getObraz() %></td>
          <td><%= task.getOdpA() %></td>
          <td><%= task.getOdpB() %></td>
          <td><%= task.getOdpC() %></td>
          <td><%= task.getOdpD() %></td>
          <td><%= task.getPoprawanaOdp() %></td>
          <td>
              <button type="button" class="edit" id="updateTask" data-toggle="modal"><i class="fa fa-pencil-square" data-toggle="tooltip" title="Edit"></i></button>
              <form method="post" action="efunction?type=deletequestion">
            <input type="hidden" name="examid" id="examid" value = "<%= examid %>">
            <input type="hidden" name="taskid" id="taskid" value="<%= task.getID() %>">
            <button type="submit"  class="delete" data-toggle="modal"><i class="fa fa-trash" id="delTask" data-toggle="tooltip" title="Usuń"></i></button>
              </form>
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
            <label>Link do obrazu</label>
            <input type="text" class="form-control" name="obraz" id="obraz">
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

<div id="conExam" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <form method="post" action="efunction?type=conquestion">
        <div class="modal-header">
          <h4 class="modal-title">Przypisz pytanie</h4>
          <button type="button" id="closemodal" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
            <input type="hidden" name="examid" id="examid" value="<%= examid %>">
            <div class="form-group">
            <label>Lista pytań</label>
            <select  name="idpytania" id="idpytania" class="form-control">
                           <%
        ArrayList<Tasks> alltasklist = (ArrayList<Tasks>) request.getAttribute("alltasklist");
             for(Tasks tasku : alltasklist)
{   %>
                <option value="<%= tasku.getID() %>"><%= tasku.getTresc() %></option>
              <% }  %>
            </select>
          </div> 
        </div>
        <div class="modal-footer">
          <input type="button" id="closemodal" class="btn btn-default" data-dismiss="modal" value="Anuluj">
          <input type="submit" class="btn btn-success" value="Przypisz">
        </div>
      </form>
    </div>
  </div>
</div>          
          
<script>
$(document).on('click', '#closemodal', function(e) {
  e.preventDefault();
  $('#addExam').modal('hide'); 
    $('#editTask').modal('hide'); 
     $('#conExam').modal('hide'); 
});
$(document).on('click', '#addex', function(e) {
  e.preventDefault();

  $('#addExam').modal('show');
});
$(document).on('click', '#conex', function(e) {
  e.preventDefault();

  $('#conExam').modal('show');
});
$(document).on('click', '#updateTask', function(e) {
  e.preventDefault();
  $('#editTask').modal('show'); 
  $tr = $(this).closest('tr');
 var data = $tr.children('td').map(function(){
  return $(this).text();
 }).get();
 console.log(data);
 $('#tresce').val(data[0]);
 $('#obraze').val(data[1]);
 $('#OdpAe').val(data[2]);
 $('#OdpBe').val(data[3]);
 $('#OdpCe').val(data[4]);
 $('#OdpDe').val(data[5]);
});

 </script>
<div id="editTask" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
        <form method="post" action="efunction?type=editquestion">
        <div class="modal-header">
          <h4 class="modal-title">Edytuj pytanie</h4>
          <button type="button" class="close" id="closemodal" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
   <div class="modal-body">
            <input type="hidden" name="examid" id="examid" value="<%= examid %>">
          <div class="form-group">
            <label>Tresc</label>
            <input type="text" class="form-control" name="tresce" id="tresce" required>
          </div>
          <div class="form-group">
            <label>Link do obrazu</label>
            <input type="text" class="form-control" name="obraze" id="obraze">
          </div>
          <div class="form-group">
            <label>Odpowiedź A</label>
            <input type="text" name="OdpAe" id="OdpAe" class="form-control" required>
          </div>
           <div class="form-group">
            <label>Odpowiedź B</label>
            <input type="text" name="OdpBe" id="OdpBe" class="form-control" required>
          </div>
              <div class="form-group">
            <label>Odpowiedź C</label>
            <input type="text" name="OdpCe" id="OdpCe" class="form-control" required>
          </div>
           <div class="form-group">
            <label>Odpowiedź D</label>
            <input type="text" name="OdpDe" id="OdpDe" class="form-control" required>
          </div>
            <div class="form-group">
            <label>Poprwana odpowiedź</label>
            <select  name="PoprawnaOdpe" id="PoprawnaOdpe" class="form-control">
                <option value="A">A</option>
                <option value="B">B</option>
                <option value="C">C</option>
                <option value="D">D</option>
            </select>
          </div> 
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" id="closemodal" data-dismiss="modal" value="Anuluj">
          <input type="submit" class="btn btn-info" value="Zapisz">
        </div>
      </form>
    </div>
  </div>
</div>
    </body>
</html>
