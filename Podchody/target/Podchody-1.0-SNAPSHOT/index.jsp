

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
 <h2 style="text-align: center;">Zarządzanie aplikacją</h2>
<div class="cards-list">
    <a href="exams">
<div class="card 1">
  <div class="card_image"> <img src="https://i.imgur.com/HRuR4f1.png" /> </div>
  <div class="card_title title-white">
    <p>Testy</p>
  </div>
</div>
    </a>
        <a href="questions">
<div class="card 2">
  <div class="card_image"> <img src="https://i.imgur.com/HRuR4f1.png" /> </div>
  <div class="card_title title-white">
    <p>Pytania</p>
  </div>
</div>
    </a>
<a href="games">
<div class="card 3">
  <div class="card_image">
    <img src="https://i.imgur.com/YWOJ3zX.png" />
   </div>
  <div class="card_title title-white">
    <p>Gry</p>
  </div>
</div></a>
</div>

    </body>
</html>
