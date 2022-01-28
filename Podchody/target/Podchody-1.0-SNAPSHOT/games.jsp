
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.podchody.Games"%>
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
<div class="cards-list">
    <%
             if(request.getAttribute("listOfGames")!=null)
               {
             ArrayList<Games> gameslist = (ArrayList<Games>) request.getAttribute("listOfGames");
             for(Games game : gameslist){   %>
<a href="g?id=<%= game.getID() %>">
<div class="card <%= game.getID() %>">
  <div class="card_image"> <img src="<%= game.getObraz() %>" /> </div>
  <div class="card_title title-white">
    <p><%= game.getNazwa() %></p>
  </div>
</div>
</a>
<% }} %>
<a id="addbtn">
  <div class="card 78">
  <div class="card_image">
    <img src="https://media1.giphy.com/media/UXiJjgeI7udx1PvrxL/giphy.gif?cid=ecf05e47m8dd911rit15befw4agt1olha4hl4j208zpjt91z&rid=giphy.gif" />
    </div>
  <div class="card_title title-white">
    <p>Stwórz nową grę</p>
  </div>
  </div>
</a>
</div>
  <script>
$(document).on('click', '#closemodal', function(e) {
  e.preventDefault();
  $('#exampleModal').modal('hide'); 
});
$(document).on('click', '#addbtn', function(e) {
  e.preventDefault();
  $('#exampleModal').modal('show');
});
  </script>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Dodaj nową grę</h5>
              <button type="button" class="close" id="closemodal" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
            <form action="games&amp;action=add" method="post">
              <div class="mb-3">
                <label for="recipient-name" class="col-form-label">Nazwa:</label>
                <input type="text" class="form-control" id="nazwa" name="nazwa" required>
              </div>
              <div class="mb-3">
                <label for="recipient-name" class="col-form-label">Unikalny Kod:</label>
                <input type="text" class="form-control" id="kod" name="kod" required>
              </div>
              <div class="mb-3">
                <label for="recipient-name" class="col-form-label">Czas rozpoczecia:</label>
                <input type="datetime-local" class="form-control" id="start_time" name="start_time" required>
              </div>  
               <div class="mb-3">
                <label for="recipient-name" class="col-form-label">Czas zakonczenia:</label>
                <input type="datetime-local" class="form-control" id="end_time" name="end_time" required>
              </div> 
              <div class="mb-3">
                <label for="recipient-name" class="col-form-label">(Opcjonalne) Obraz:</label>
                <input type="text" class="form-control" name="obraz" id="obraz">
              </div>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-primary">Dodaj</button>
            </form>
              <button type="button" id="closemodal" class="btn btn-secondary" data-dismiss="modal">Anuluj</button>
            </div>
            </div>
          </div>
        </div> 
    </body>
   
</html>
