<%-- 
    Document   : clasificaciones
    Created on : 27-abr-2022, 20:38:05
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
    <link rel="stylesheet" href="src/clasif.css">
    <link rel="stylesheet" href="src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="src/img/icono.png" />
</head>    
<body>
    <header>
        <jsp:include page="encabezado.jsp"></jsp:include>
      <div class="header">
        <div class="titulo tituloRes">
          <a href="ObtenerInicio"><h1><i class="bi bi-box-arrow-left"></i> inicio</h1></a>
        </div>
        <div class="titulo titulo1">
          <h1>Clasificaciones</h1>
        </div>
        <div class="home">
            <a href="ObtenerInicio"><i class="bi bi-house-door"></i></a>
        </div>
        <div class="usuario">
           <div class="imagenUsuario">
            <img src="imgUsuarios/${usuario.usuario}.PNG" width="50px" height="50px">
           </div>
           <div class="datos">
                <p id="nombre">Nombre: <br><a href="MostrarUsuario?usuarioV=${usuario.usuario}">${usuario.usuario}</a></p>
                <p id="monedas">Monedas:  <br>${usuario.monedas}</p>
           </div>
        </div>
      </div>
    </header> 
    <section>
        <div class="clasificaciones">
            <table class="table table-dark">
                <thead>
                  <tr>
                    <th scope="col">Posición</th>
                    <th scope="col">Usuario</th>
                    <th scope="col">Puntos</th>
                    <th scope="col">Ronda</th>
                  </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clasificaciones}" var="clasif">
                        <tr>
                          <th scope="row"><c:out value="${clasif.posicion}"/></th>
                          <td><a href="MostrarUsuario?usuarioV=${clasif.usuario.usuario}">${clasif.usuario.usuario}</a></td>
                          <td><c:out value="${clasif.usuario.recordPuntos}"/></td>
                          <td><c:out value="${clasif.usuario.partidasJugadas}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
              </table>
        </div>
        <!--  -->
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
