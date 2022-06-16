<%-- 
    Document   : gestionTienda
    Created on : 13-may-2022, 20:06:19
    Author     : rcane
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <c:if test="${!(empty param.error)}">
        <script>
        Swal.fire(
        'Warning!',
        '${param.error}',
        'warning'
        )
    </script>
    </c:if>
    <c:if test="${!(empty param.verifica)}">
        <script>
        Swal.fire(
        'Success',
        '${param.verifica}',
        'success'
        )
    </script>
    </c:if>
    <header>
      <div class="header">
        <div class="titulo">
          <h1>admin</h1>
        </div>
        <div class="home">
            <a href="admin.jsp"><i class="bi bi-puzzle-fill"></i></a>
        </div>
      </div>
    </header> 
    <section>
      <div class="tienda">
          <p class="tituloF">Gestión tienda</p>
          <select name="tipoProducto" id="tipoProducto" class="form-select form-select mb-3" aria-label=".form-select-lg example">
              <option selected>Seleccionar Artículo</option>
              <option value="Fondo">Fondo</option>
              <option value="Skin">Skin</option>
          </select>
          
          <form action="AgregarSkin" name="Skin" id="Skin" style="display:none" method="POST" enctype="multipart/form-data">
            <p class="tituloF">Añadir Skin</p>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="nombreProducto" placeholder="Nombre de artículo" required>
            </div>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="precio" placeholder="Precio" required>
            </div>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="width" placeholder="Width" required>
            </div>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="height" placeholder="Height" required>
            </div>
            <br>
            <p class="pImg"><input type="file" name="sprite" id="archivo" class="inputImg" accept="image/jpeg, image/png" required> </p>
            <p class="pImg"><input type="file" name="visual" id="archivo" class="inputImg2" accept="image/jpeg, image/png" required></p>
            <p class="enviarArticulo"><input type="submit" name="Skin" value="Enviar" class="btn btn-secondary"></p>
            <p class="enviarArticulo"><button class="btn btn-warning" onClick="window.location.reload();">Recargar</button></p>
          </form>
          
          <form action="AgregarFondo" name="Fondo" id="Fondo" style="display:none" method="POST" enctype="multipart/form-data">
            <p class="tituloF">Añadir Fondo</p>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="nombreProducto" placeholder="Nombre de artículo" required>
            </div>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="precio" placeholder="Precio" required>
            </div>
            <br>
            <p class="pImg"><input type="file" name="archivo" id="archivo" class="inputImg" accept="image/jpeg, image/png" required></p>
            
            <p class="enviarArticulo"><input type="submit" name="Fondo" value="Enviar" class="btn btn-secondary"></p>
            <p class="enviarArticulo"><button class="btn btn-warning" onClick="window.location.reload();">Recargar</button></p>
          </form>
      </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>

    <script>
        $("#tipoProducto").on("change", function() {
            $("#" + $(this).val()).show().siblings().hide();
        })
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
</body>
</html>
