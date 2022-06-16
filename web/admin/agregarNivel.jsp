<%-- 
    Document   : agregarNivel
    Created on : 12-may-2022, 18:56:24
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
    <link rel="stylesheet" href="../src/admin.css">
    <link rel="stylesheet" href="../src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    
</head>    
<body>
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
      <div class="nivel">
        <form action="InsertarNivel">
            <p class="tituloF">Siguiente nivel: ${ultimoNivel}</p>
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="experienciaNecesaria" placeholder="Experiencia necesaria">
                <span class="input-group-text">Exp</span>
              </div>
            <br>
            <select class="form-select form-select mb-3" name="recompensa" aria-label=".form-select-lg example">
              <option selected>Seleccionar Recompensa</option>
              <option value="M-20">20 monedas</option>
              <option value="M-30">30 monedas</option>
              <option value="M-50">50 monedas</option>
            </select>
            <input type="hidden" name="nivel" value="${ultimoNivel}">
            <p class="enviarNivel"><input type="submit" value="Enviar" class="btn btn-secondary"></p>
            
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
