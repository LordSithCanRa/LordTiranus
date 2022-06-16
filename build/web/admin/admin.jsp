<%-- 
    Document   : admin
    Created on : 04-may-2022, 11:06:05
    Author     : rcane
--%>

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
    <link rel="stylesheet" href="../src/TyF.css">
    <link rel="stylesheet" href="../src/admin.css">
    
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    
</head>    
    <body>
        <header>
            <jsp:include page="encabezadoAdmin.jsp"></jsp:include>
          <div class="header">
            <div class="titulo">
              <h1>admin</h1>
            </div>
            <div class="home">
                <a href="../ObtenerInicio"><i class="bi bi-house-door"></i></a>
            </div>
          </div>
        </header> 
        <section>
          <div class="menu">
            <div>
              <a class="menuA" href="ObtenerUsuarios">Gestión Usuarios</a>
            </div>
            <div>
                <a class="menuA" href="gestionTienda.jsp">Añadir Producto</a>
            </div>
            <div>
                <a class="menuA" href="ObtenerUltimoNivel">Añadir Nivel</a>
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
