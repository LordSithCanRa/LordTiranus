 <%-- 
    Document   : index
    Created on : 11-abr-2022, 13:24:58
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
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="src/main.css">
    <link rel="shortcut icon" href="src/img/icono.png" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>    
<body>
    <header>
      <div class="header">
        <div class="titulo">
          <h1>juego de starwars</h1>
        </div>
        <div class="logIn">
           <a href=""data-bs-toggle="modal" data-bs-target="#modalLogin"><i class="bi bi-person-bounding-box"></i></a>
        </div>
      </div>
       
            <c:if test="${!(empty param.media)}">
                <script>
                Swal.fire(
                'Error',
                '${param.media}',
                'error'
                )
                </script>
            </c:if>
       
    </header> 
    <section>
          <div class="historia">
            <div class="container-fluid">
              <h1>HISTORIA DEL JUEGO</h1>
              <p>Solo viendo la historia podrás registrarte y 
              convertirte en un <strong>Jedi</strong>
              </p>
              <p>
                <a class="btn btn-secondary btn-lg" href="historia.html">Registrate ahora &raquo;</a>
              </p>
            </div>
          </div>
    </section>
    <section>
      <div class="objetivos">
        <div class="container-fluid">
          <h1>OBJETIVOS DEL JUEGO</h1>
          <p>Tu objetivo es <strong>derrotar</strong> a los imperiales usando tu bláster</p>
          <p>Vence a los imperiales, consigue puntos y canjealos para <strong>mejorar tu apariencia</strong> </p>
        </div>
      </div>
    </section>
    <section>
      <div class="controles">
        <div class="container-fluid">
          <h1>CONTROLES</h1>
          <p>Para mover a tu personaje deberás usar "WASD"</p>
          <img src="src/img/controles.png" alt="WASD">
          <br>
          <br>
          <p>En la versión móvil aparecerán los botones en la pantalla para que puedas moverte
          </p>
        </div>
      </div>
    </section>
    <section>
      <div class="competicion">
        <div class="container-fluid">
          <h1>ESTO ES UN JUEGO CLASIFICATORIO</h1>
          <p>Dependiendo de los puntos que tengas subirás en un ranking
            ese ranking sera accesible mediante el menu de la página del juego. Dependiendo
            de tu nivel de experiencia podras conseguir monedas o skins o fondos.
          </p>
          <p>SE EL MEJOR REBELDE DE LA PÁGINA</p>
        </div>
      </div>
    </section>
    <section>
      <div class="normas">
        <div class="container-fluid">
          <h1>NORMAS DE LA PÁGINA</h1>
          <div class="acc">
            <div class="accordion accordion-flush" id="accordionFlushExampleaccordionFlushExample">
              <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingOne">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                    NO ESTAN PERMITIDOS LOS HACKERS
                  </button>
                </h2>
                <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                  <div class="accordion-body">
                    Cualquier hacker será espulsado permanentemente de la página y deberá empezar de nuevo
                  </div>
                </div>
              </div>
              <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingTwo">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                    NORMAS DE COMUNIDAD
                  </button>
                </h2>
                <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                  <div class="accordion-body">
                    En nuestra comunidad debe haber paz y competitividad, no se admiten usuarios que vayan a insultar o discriminar a ningún
                    jugador de la comunidad.
                  </div>
                </div>
              </div>
              <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingThree">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                    NORMAS PARA LOS PERFILES
                  </button>
                </h2>
                <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                  <div class="accordion-body">
                    Ten cautela con lo que comentas en los distintos perfiles, puedes ser baneado.
                  </div>
                </div>
              </div>
              <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingFour">
                  <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
                    NORMAS DE IMÁGENES
                  </button>
                </h2>
                <div id="flush-collapseFour" class="accordion-collapse collapse" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample">
                  <div class="accordion-body">
                    Cualquier imagen que contenga algo obsceno o insultante conllevará un baneo de la cuenta.
                  </div>
                </div>
              </div>
            </div>
        </div>
        </div>
      </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>
    <!-- Modal Login -->
    <div class="modal  fade" id="modalLogin">
      <div class="modal-sm modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modalheader">
            <p>INICIA SESIÓN</p>
          </div>
          <div class="modal-body">
            <!-- APARTADO PARA EL LOG IN -->
            <form action="LogIn">
              <div class="form-group">
                <label for="inputusuario"><strong>Usuario:</strong></label>
                <input type="usuario"  name="usuarioLog" id="inputusuario" class="form-control" placeholder="Escriba su usuario...">
              </div>
              <div class="form-group">
                <label for="inputPassword"><strong>Contraseña:</strong> </label>
                <input type="password" name="passwordLog" id="inputPassword" class="form-control" placeholder="Escriba su contraseña"/>
              </div>
              <br>
              <button type="submit" class="btn btn-success btn-block iniciar">Iniciar sesión</button>
            </form>
          </div>
          <div class="modal-footer">
            <p><a href="">¿Has olvidado la contraseña?</a></p>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
    
</body>
</html>
