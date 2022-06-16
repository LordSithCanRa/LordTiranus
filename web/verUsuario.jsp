<%-- 
    Document   : verUsuario
    Created on : 27-abr-2022, 23:38:17
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    <link rel="stylesheet" href="src/TyF.css">
    <link rel="stylesheet" href="src/verUsuario.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="shortcut icon" href="src/img/icono.png" />
</head>    
<body>
    <script>
            <c:if test="${!(empty error)}">
                alert("${error}");
            </c:if>
       </script>    
    <header>
        <jsp:include page="encabezado.jsp"></jsp:include>
        <div class="header">
            <div class="titulo">
            <a href="ObtenerInicio"><h1><i class="bi bi-box-arrow-left"></i> inicio</h1></a>
            </div>
            <div class="usuario">
                <div class="imagenUsuario">
                <img src="imgUsuarios/${usuario.usuario}.PNG" width="50px" height="50px">
                </div>
                <div class="datos">
                    <p id="nombre">Nombre: <br>${usuario.usuario}</p>
                <p id="monedas">Monedas:  <br>${usuario.monedas}</p>
                </div>
            </div>
        </div>
    </header> 
    <section >
        <div class="verUsuario">
            <div class="zonaUsuario">
                <h2 class="nombreU">${uMostrado.usuario}</h2>
                <div class="imagenUsuarioP">
                    <img src="imgUsuarios/${uMostrado.usuario}.PNG" alt="">
                </div>
                <div class="stats">
                    <div class="max-points">
                        <p>Máxima Puntuación:</p>
                        <p>${uMostrado.recordPuntos}</p>
                    </div>
                    <div class="plays">
                        <p>Partidas Jugadas:</p>
                        <p>${uMostrado.partidasJugadas}</p>
                    </div>
                    <div class="max-points">
                        <p>Nivel:</p>
                        <p>${uMostrado.nivel}<p>
                    </div>
                    <div class="plays">
                        <p>Experiencia:</p>
                        <p>${uMostrado.experienciaAct}xp</p>
                    </div>
                </div>
                <div class="trofeos">
                    <h3 class="trofTitl">Trofeos:</h3>
                    <div class="cajaTrofeos">
                        <c:forEach items="${trofeos}" var="trofeo">
                            <div class="trofeo1">
                               <img src="src/img/trofeo.PNG" alt="" data-toggle="tooltip" title="${trofeo.nombre}">
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="comentarios">
                    <!-- SOLO SI NO ES NUESTRO PERFFIL -->
                    <c:if test="${uMostrado.usuario != usuario.usuario}">
                        <div class="comentar">
                            <button type="button" class="btn btn-info" id="liveToastBtn">Comentar</button><!-- alert -->
                        </div>
                        <div class="d-flex justify-content-center align-items-center w-100 sectionComentar" aria-live="polite" aria-atomic="true" style="z-index: 11">
                            <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                              <div class="toast-header">
                                  <img src="src/iconos/luke.png" class="rounded me-2 imgComent" alt="...">
                                <strong class="me-auto">COMENTAR</strong>
                                <small>Para ${uMostrado.usuario}</small>
                                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                              </div>
                              <div class="toast-body">
                                  <form action="InsertarComentario" class="formComentario">
                                      <textarea name="comentario"></textarea>
                                      <input type="hidden" name="usuarioDestinatario" value="${uMostrado.usuario}"/>
                                      <input type="hidden" name="usuarioRemitente" value="${usuario.usuario}"/>
                                      <br>
                                      <input type="submit" name="enviarC" class="btn btn-light"/>
                                  </form>
                              </div>
                            </div>
                        </div>
                        <script>
                            var toastTrigger = document.getElementById('liveToastBtn')
                            var toastLiveExample = document.getElementById('liveToast')
                            if (toastTrigger) {
                              toastTrigger.addEventListener('click', function () {
                                var toast = new bootstrap.Toast(toastLiveExample)

                                toast.show()
                              })
                            }
                        </script>
                    </c:if>
                    <c:forEach items="${comentarios}" var="comentario">
                        <div class="container">
                            <div class="row">
                                <div class="col-sm-8 col-sm-offset-2">
                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="media">
                                                <div class="media-left">
                                                    <img src="imgUsuarios/${comentario.usuarioRemitente}.PNG" width="64" height="64" alt="Foto de perfil" class="media-object rounded-circle">
                                                </div>
                                                <div class="media-body">
                                                    <h4 class="media-heading"><c:out value="${comentario.usuarioRemitente}"/><span class="espacioNombreHora"></span><small class="text-muted"><c:out value="${comentario.fechaEscrito}"/></small></h4>
                                                    <p><c:out value="${comentario.comentario}"/></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </section>
   <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/    ">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>
   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function(){
          $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</body>
</html>
