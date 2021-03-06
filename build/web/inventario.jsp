<%-- 
    Document   : inventario
    Created on : 27-may-2022, 9:57:21
    Author     : rcane
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- OFFCANVAS -->
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>LordTiranus</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="src/inv.css">
    <link rel="stylesheet" href="src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="shortcut icon" href="src/img/icono.png" />
    
</head>    
<body>
    <header>
        <jsp:include page="encabezado.jsp"></jsp:include>
      <div class="header">
        <div class="titulo">
          <h1>inventario</h1>
        </div>
        <div class="home">
          <a href="ObtenerInicio"><i class="bi bi-box-arrow-left"></i></a>
        </div>
        <div class="usuario">
           <div class="imagenUsuario">
            <img src="imgUsuarios/${usuario.usuario}.PNG" width="50px" height="50px">
           </div>
           <div class="datos">
                <p id="nombre">Nombre: <br><a href="MostrarUsuario?usuarioV=${usuario.usuario}">${usuario.usuario}</a></p>
           </div>
        </div>
      </div>
    </header> 
    <section class="tienda">
      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
        <li class="nav-item" role="presentation">
          <button class="nav-link active" id="pills-skins-tab" data-bs-toggle="pill" data-bs-target="#pills-skins" type="button" role="tab" aria-controls="pills-skins" aria-selected="true">Skins</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="pills-fondos-tab" data-bs-toggle="pill" data-bs-target="#pills-Fondos" type="button" role="tab" aria-controls="pills-Fondos" aria-selected="false">Fondos</button>
        </li>
      </ul>
      <div class="tab-content" id="pills-tabContent">
      <div class="tab-pane fade show active" id="pills-skins" role="tabpanel" aria-labelledby="pills-skins-tab">
        <c:forEach items="${skins}" var="skin">
          <div class="buySkin">
            <div class="imgSkin">
              <img src="src/juegoJS/srcjuego/SHOWPLAYERS/${skin.src}" alt="">
            </div>
            <div>
              <p>Nombre: ${skin.nombre}</p>
              <form action="Equipar">
                  <input type="hidden" name="idSkin" value="${skin.id}">
                  <input class="btn btn-warning" style="border: 1px solid black" type="submit" value="Equipar" name="Skin">
              </form>
            </div>
          </div>
        </c:forEach>
        </div>
        <div class="tab-pane fade" id="pills-Fondos" role="tabpanel" aria-labelledby="pills-Fondos-tab">
        <c:forEach items="${fondos}" var="fondo">    
          <div class="buyBackground">
            <div class="imgBack">
              <img src="src/juegoJS/srcjuego/BACKGROUNDS/${fondo.src}" alt="">
            </div>
            <div>
              <p>Nombre: ${fondo.nombre}</p>
              <form action="Equipar">
                  <input type="hidden" name="idFondo" value="${fondo.id}">
                  <input class="btn btn-warning" style="border: 1px solid black" type="submit" value="Equipar" name="Fondo">
              </form>
            </div>
          </div>
        </c:forEach> 
          
        </div>
      </div>
    </section>
    <footer>
      <p>Esta p??gina esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ram??n Canelo Ar??valo</a>&copy;</p>

    </footer>
    <script>
        //Script para que se oculten los distintos paneles
            
        $("#pills-Fondos").hide();
        $("#pills-skins").show();
        $("#pills-fondos-tab").on("click", function(){
           
            $("#pills-Fondos").show();
            $("#pills-skins").hide();
        });
        $("#pills-skins-tab").on("click", function(){
            
            $("#pills-Fondos").hide();
            $("#pills-skins").show();
        });
        
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
