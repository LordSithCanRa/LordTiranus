<%-- 
    Document   : modificarUsuario
    Created on : 07-may-2022, 18:32:09
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>    
<body>
    <c:if test="${!(empty media)}">
        <script>
        Swal.fire(
        'Warning',
        '${media}',
        'warning'
        )
    </script>
    </c:if>
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
      <div class="modificarUsuario">
          <p class="tituloF">Modificar Usuario</p>
          
          <form action="ModificarUsuarioForm" class="modificarUsuarioF">
            <div class="input-group modificacion">
                <div class="imgUsuarioM">
                    <img src="../imgUsuarios/${uMod.usuario}.PNG" alt="">
                </div>
            </div>
            <div class="input-group modificacion ">
                <p><label for="nombreUsuarioM" class="modLbl">Usuario:</label>
                <br>
                <input type="text" class="form-floating form-control modI" id="nombreUsuarioM" value="${uMod.usuario}" disabled ></p>
            </div>
            <input type="hidden" name="usuarioMod" value="${uMod.usuario}">
            <div class="input-group modificacion ">
                <p> <label for="email" class="modLbl">Email:</label>
                <br>
                <input type="text" class="form-floating form-control modI" id="email" value="${uMod.email}" name="email"> </p>
            </div>    
            
            <div class="input-group modificacion">
                <p><label for="monedas" class="modLbl">Monedas:</label>
                <br>
                <input type="text" class="form-control modI" id="monedas" value="${uMod.monedas}" name="monedas"></p>
            </div>
            
            <div class="input-group modificacion">
                <p><label for="nivel" class="modLbl">Nivel:</label>
                <br>
                <input type="text" class="form-control modI" id="nivel" value="${uMod.nivel}" name="nivel"></p>
            </div>
            
            <div class="input-group modificacion">
                <p><label for="recordPuntos" class="modLbl">Record Puntos:</label>
                <br>
                <input type="text" class="form-control modI" id="recordPuntos" value="${uMod.recordPuntos}" name="recordPuntos"></p>
            </div>
            
            <div class="input-group modificacion">
                <p><label for="clave" class="modLbl">Clave:</label>
                <br>
                <input type="text" class="form-control modI" id="clave" value="${uMod.clave}" name="clave"></p>
            </div>
            
            <div class="input-group modificacion">
                <p><label for="clave" class="modLbl">Admin:</label>
                <input type="checkbox" ${uMod.admin?"checked":""} value="${uMod.admin}" name="administrador"></p>
            </div>
            
            <p class="enviarArticulo"><input type="submit" value="Enviar" class="btn btn-secondary"></p>
          </form>
      </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
