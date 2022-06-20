<%-- 
    Document   : enviarEmail
    Created on : 17-jun-2022, 17:28:41
    Author     : rcane
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Juego</title>
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
    <header>
        <c:if test="${!(empty param.mensaje)}">
            <script>
            Swal.fire(
            'Success',
            '${param.mensaje}',
            'success'
            )
            </script>
        </c:if>
        <c:if test="${!(empty param.error)}">
            <script>
            Swal.fire(
            'Error',
            '${param.error}',
            'error'
            )
            </script>
        </c:if>
      <div class="header">
        <div class="titulo">
          <h1>enviar email</h1>
        </div>
        <div class="home">
            <a href="ObtenerUsuarios"><i class="bi bi-person-lines-fill"></i></a>
        </div>
      </div>
    </header> 
    <section>
      <div class="enviarEmail" action="EnviarEmail">
          <p class="tituloF">Enviar email a : ${uEm.usuario}</p>
          
          <form action="">
            
                
            
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="subject" placeholder="Asunto">
            </div>
            <br>
            <div class="input-group mb-3">
                <textarea class="form-control" name="text" placeholder="Mensaje..."></textarea>
            </div>
            <div class="input-group mb-3">
                <input type="hidden" name="usuario" class="form-control" value="${uEm.usuario}">
                <input type="hidden" class="form-control" name="emailAdm" value="lordtiranus2002@gmail.com">
                <input type="hidden" class="form-control" name="passwordAdm" value="lzbanavxlpyupwoq">
            </div>
            <p class="enviarArticulo"><input type="submit" value="Enviar" class="btn btn-secondary"></p>
          </form>
      </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="linkedin">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
