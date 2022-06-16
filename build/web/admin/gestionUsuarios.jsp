<%-- 
    Document   : gestionUsuarios
    Created on : 04-may-2022, 11:57:16
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
            <a href="admin.jsp"><i class="bi bi-puzzle-fill"></i></a>
        </div>
      </div>
    </header> 
    <section>
      <div class="mostrarUsuarios">
        <c:forEach items="${usuariosG}" var="usuarioG">
            <div class="usuarioG">
                <div class="left">
                    <div class="imgUsuario">
                        <img src="../imgUsuarios/${usuarioG.usuario}.PNG" alt="">
                    </div>
                </div>
                <div class="middle">
                    <h4><c:out value="${usuarioG.usuario}"/></h4>
                    <p><a href="ModificarUsuario?usuarioMod=${usuarioG.usuario}" class="link-info">Modificar Usuario</a></p>
                    <p><a href="EnviarEmail?usuarioEm=${usuarioG.usuario}" class="link-info">Enviar email</a></p>
                    <p><a href="EliminarUsuario?usuarioEm=${usuarioG.usuario}" class="link-info">Eliminar Usuario</a></p>
                </div>
                <div class="right">
                    <p class="comentariosp"><a href="ObtenerComentariosUsuario?usuarioEC=${usuarioG.usuario}" class="comentarios"><i class="bi bi-chat-left-text"></i></a></p>
                    <p>Monedas: <c:out value="${usuarioG.monedas}"/></p>
                    <p>Nivel: <c:out value="${usuarioG.nivel}"/></p>
                </div>
            </div>
        </c:forEach>
      </div>
       
    </section>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
