<%-- 
    Document   : gestionComentarios
    Created on : 09-may-2022, 19:14:58
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
    <link rel="shortcut icon" href="src/img/icono.png" />
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="../src/admin.css">
    <link rel="stylesheet" href="../src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    
</head>    
<body>
    <header>
      <div class="header">
        <div class="titulo">
          <h1>gestion usuarios</h1>
        </div>
        <div class="home">
            <a href="ObtenerUsuarios"><i class="bi bi-person-lines-fill"></i></a>
        </div>
      </div>
    </header> 
    <section>
        <div class="gestionComentarios">
            <p class="tituloF">Comentarios de: ${usuarioEC}</p>
            <div class="displayComents">
                <c:forEach items="${comentarios}" var="comentario">
                <div class="comentario">
                    <div class="contenidoComent">
                       <p>${comentario.comentario}</p> 
                    </div>
                    <div class="eliminarComentario">
                        <form action="../EliminarComentario">
                            <input type="hidden" name="id" value="${comentario.idComentario}"/>
                            <input type="hidden" name="usuarioEC" value="${usuarioEC}"/>
                            <button type="submit" name="admin"><i class="bi bi-trash-fill"></i></button>
                        </form>
                  
                    </div>
                </div>
                </c:forEach>
            </div>
            

        </div>
    
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
