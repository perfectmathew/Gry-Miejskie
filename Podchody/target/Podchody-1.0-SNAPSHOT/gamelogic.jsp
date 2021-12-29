<%-- 
    Document   : gamelogic
    Created on : 20 gru 2021, 08:31:20
    Author     : Perfectamthew
--%>

<%@page import="com.mycompany.podchody.GameActivity"%>
<%@page import="com.mycompany.podchody.Exams"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.podchody.Players"%>
<%@page import="com.mycompany.podchody.Players"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <head>
       <%@include file="/WEB-INF/template/header.jsp"%>
       <style><%@include file="/WEB-INF/css/main.css"%></style>
       <script><%@include file="/WEB-INF/js/functions.js"%></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Zarządzanie Grami | Gry miejskie</title>
    </head>
    <body>
        <% int gameid;
        String gamename;
        %>
        <% gameid = Integer.parseInt((String)request.getAttribute("GAMEID"));  
            gamename = (String) request.getAttribute("GAMENAME");
        %>
        <% if(request.getAttribute("gameUsers")!=null){  %>
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
            <h2><a href="g?id=<%= gameid %>"><%= gamename %> </a>/ <b>Gracze</b></h2>
        </div>
        <div class="col-sm-6">
          <a class="btn btn-success" id="addu" data-toggle="modal"><i class="fa fa-address-book-o">&#xE147;</i> <span>Dodaj nowego gracza</span></a>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Imie</th>
          <th>Nazwisko</th>
          <th>Kod Dostępu</th>
          <th>Akcja</th>
        </tr>
      </thead>
      <tbody>
          <%
        ArrayList<Players> userslist = (ArrayList<Players>) request.getAttribute("gameUsers");
             for(Players player : userslist){   %>
        <tr>
          <td><%=  player.getImie() %></td>
          <td><%=  player.getNazwisko() %></td>
                <td><%=  player.getKod() %></td>
           <td>
                  <form action="function?type=deleteplayer" method="post">
                      <input type="hidden" name="gameid" id="gameid" value="<%= gameid %>" />
            <input type="hidden" name="usrid" id="usrid" value="<%= player.getID() %>" />
            <button type="submit" class="delete" data-toggle="modal"><i class="fa fa-trash" data-toggle="tooltip" title="Delete"></i></button>
                  </form>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </div>
</div> 
        <% }else if(request.getAttribute("gameActivty")!=null){ %>
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
            <h2><a href="g?id=<%= gameid %>"><%= gamename %> </a>/ <b>Aktywnosc</b></h2>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Gracz</th>
          <th>Aktualny Test</th>
         <th>Czas rozpoczęcia</th>
          <th>AktulanaPozX</th>
          <th>AktulanaPozY</th>
        </tr>
      </thead>
      <tbody>
                 <%
        ArrayList<GameActivity> activitylist = (ArrayList<GameActivity>) request.getAttribute("gameActivty");
             for(GameActivity activity : activitylist){   %> 
        <tr>
            <td><%= activity.getImie() + " " + activity.getNazwisko() %></td>
          <td><%= activity.getNazwa() %></td>
          <td><%= activity.getData() %></td>
          <td><%= activity.getPozX() %></td>
          <td><%= activity.getPozY() %></td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </div>
</div> 
        <% }else if(request.getAttribute("gameExams")!=null){ %>
<div class="container">
  <div class="table-wrapper">
    <div class="table-title">
      <div class="row">
        <div class="col-sm-6">
            <h2><a href="g?id=<%= gameid %>"><%= gamename %></a>/ <b>Testy</b></h2>
        </div>
        <div class="col-sm-6">
          <a id="adde" class="btn btn-success" data-toggle="modal"><i class="fa fa-paperclip">&#xE147;</i> <span>Przypisz test</span></a>
        </div>
      </div>
    </div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Nazwa</th>
          <th>Próg zaliczenia</th>
          <th>Szerokość geograficzna</th>
          <th>Wysokość geograficzna</th>
          <th>Akcja</th>
        </tr>
      </thead>
      <tbody>
              <%
        ArrayList<Exams> examslist = (ArrayList<Exams>) request.getAttribute("gameExams");
             for(Exams exam : examslist){   %>
        <tr>
            <td><%= exam.getNazwa() %></td>
          <td><%= exam.getProg() %></td>
          <td><%= exam.getSzer() %></td>
          <td><%= exam.getWys() %></td>
           <td>
               <form action="e" method="get">
                   <input type="hidden" id="id" name="id" value="<%= exam.getID() %>">
               <button  class="edit" data-toggle="modal"><i class="fa fa-pencil-square" data-toggle="tooltip" title="Edit"></i></button>
               </form>
               <form action="function?type=deleteexam" method="post">
                   <input type="hidden" name="gameid" id="gameid" value="<%= gameid %>" />
               <input type="hidden" name="idtestu" id="idtestu" value="<%= exam.getID() %>"/>
               <button typ="submit"  class="delete" data-toggle="modal"><i class="fa fa-trash" data-toggle="tooltip" title="Delete"></i></button>
               </form>
               </td>
        </tr>
        <% } %> 
      </tbody>
    </table>
  </div>
</div>         
         <% }else { %>
<h2 style="text-align: center;">Zarządzanie grą</h2>
<div class="cards-list">
<a href="<%= request.getScheme() + "://" + request.getServerName() +  ":" +   request.getServerPort() + "/Podchody/g" + "?" +   request.getQueryString() + "&action=exams" %>">
<div class="card 1">
  <div class="card_image"> <img src="https://i.imgur.com/BnfX0Pm.png" /> </div>
  <div class="card_title title-white">
    <p>Testy</p>
  </div>
</div>
</a>
<a href="<%= request.getScheme() + "://" + request.getServerName() +  ":" +   request.getServerPort() + "/Podchody/g" + "?" +   request.getQueryString() + "&action=users" %>">
<div class="card 2">
  <div class="card_image">
    <img src="https://i.imgur.com/8UuTQwL.png" />
   </div>
  <div class="card_title title-white">
    <p>Gracze</p>
  </div>
</div></a>
<a href="<%= request.getScheme() + "://" + request.getServerName() +  ":" +   request.getServerPort() + "/Podchody/g" + "?" +   request.getQueryString() + "&action=activity" %>">
<div class="card 3">
  <div class="card_image">
    <img src="https://i.imgur.com/1qllhLq.png" />
   </div>
  <div class="card_title title-white">
    <p>Aktywność</p>
  </div>
</div></a>
</div>
         <% } %>
                 
<div id="addUser" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form method="post" action="function?type=adduser">
        <div class="modal-header">
          <h4 class="modal-title">Dodaj gracza</h4>
          <button type="button" class="close" data-dismiss="modal" id="closemodal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
            <input type="hidden" class="form-control" id="gameid" name="gameid" value="<%= gameid %>" required>
          <div class="form-group">
            <label>Imie</label>
            <input type="text" class="form-control" id="imie" name="imie" required>
          </div>
          <div class="form-group">
            <label>Nazwisko</label>
            <input type="text" class="form-control" id="nazwisko" name="nazwisko" required>
          </div>
          <div class="form-group">
            <label>Kod dostępu</label>
            <input type="text" class="form-control" pattern="[A-Za-z0-9]{6}" required id="code" name="code"> <button type="button" id="generate" class="btn-success"><i class="fa fa-random" aria-hidden="true"></i></button>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" id="closemodal" data-dismiss="modal" value="Anuluj">
          <input type="submit" class="btn btn-success" id="addUsr" value="Dodaj">
        </div>
      </form>
    </div>
  </div>
</div>

<div id="addExam" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form method="post" action="function?type=addexam">
        <div class="modal-header">
          <h4 class="modal-title">Dodaj test</h4>
          <button type="button" class="close" data-dismiss="modal" id="closemodal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
            <input type="hidden" class="form-control" id="gameid" name="gameid" value="<%= gameid %>" required>
          <div class="form-group">
            <label>Wybierz test z listy!</label>
    <select class="form-select" id="idtestu" name="idtestu" aria-label="Default select example">
             <%
        ArrayList<Exams> examsall = (ArrayList<Exams>) request.getAttribute("ALLEXAMS");
             for(Exams examu : examsall){   %>
             <option value="<%= examu.getID() %>"><%= examu.getNazwa() %></option>
    <% }   %>
    </select>
          </div>
          <div class="form-group">
            <label>Wysokość Geograficzna</label>
            <input type="text" class="form-control" id="wysokosc" name="wysokosc" required>
          </div>
          <div class="form-group">
            <label>Szerokość Geograficzna</label>
            <input type="text" class="form-control" required id="szerokosc" name="szerokosc"></button>
          </div>
        </div>
        <div class="modal-footer">
          <input type="button" class="btn btn-default" id="closemodal" data-dismiss="modal" value="Anuluj">
          <input type="submit" class="btn btn-success" id="addUsr" value="Dodaj">
        </div>
      </form>
    </div>
  </div>
</div>        
  <script>
$(document).on('click', '#closemodal', function(e) {
  e.preventDefault();
  $('#addUser').modal('hide'); 
  $('#addExam').modal('hide'); 
});
$(document).on('click', '#addu', function(e) {
  e.preventDefault();
  $('#addUser').modal('show');
});
$(document).on('click', '#adde', function(e) {
  e.preventDefault();
  $('#addExam').modal('show');
});
$(document).on('click', '#generate', function(e) {
  e.preventDefault();
  
  $('#code').val(codeGen(6));
});


function codeGen(length) {
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * 
 charactersLength));
   }
   return result;
}
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

<div id="deleteUser" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form  method="post" action="function?type=deleteuser">
        <div class="modal-header">
          <h4 class="modal-title">Usunięcie gracza</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Czy napewno chcesz usunąć tego gracza?</p>
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

<div id="deleteExam" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <form  method="post" action="function?type=deleteexam">
        <div class="modal-header">
          <h4 class="modal-title">Usunięcie Testu</h4>
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        </div>
        <div class="modal-body">
          <p>Czy napewno chcesz usunąć ten test?</p>
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
