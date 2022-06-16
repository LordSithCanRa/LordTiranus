<%-- 
    Document   : tienda
    Created on : 10-may-2022, 19:14:52
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
    <link rel="stylesheet" href="src/tienda.css">
    <link rel="stylesheet" href="src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="shortcut icon" href="src/img/icono.png" />
</head>    
<body>
    <c:if test="${!(empty error)}">
        <script>
        Swal.fire(
        'Error en la tienda',
        '${error}',
        'warning'
        )
        </script>
    </c:if>
    <c:if test="${!(empty confirm)}">
        <script>
        Swal.fire(
        'Success',
        '${confirm}',
        'success'
        )
    </script>
    </c:if>
    <header>
        <jsp:include page="encabezado.jsp"></jsp:include>
      <div class="header">
        <div class="titulo">
          <h1>Comercio JAWA</h1>
        </div>
        <div class="home">
            <a href="ObtenerInicio"><i class="bi bi-house-door"></i></a>
        </div>
        <div class="usuario">
           <div class="imagenUsuario">
            <img src="imgUsuarios/${usuario.usuario}.PNG" width="50px" height="50px">
           </div>
           <div class="datos">
                <p id="monedas">Monedas:  <br>${usuario.monedas}</p>
           </div>
        </div>
      </div>
    </header> 
    <section class="tienda">
      <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
        <li class="nav-item" role="presentation">
          <button class="nav-link active" id="pills-skins-tab" data-bs-toggle="pill" data-bs-target="#pills-skins" type="button" role="tab" aria-controls="pills-skins" aria-selected="true">Skins</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="pills-fondos-tab" data-bs-toggle="pill" data-bs-target="#pills-Fondos" type="button" role="tab" aria-controls="pills-Fondos" aria-selected="false">Fondos</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="pills-monedas-tab" data-bs-toggle="pill" data-bs-target="#pills-monedas" type="button" role="tab" aria-controls="pills-monedas" aria-selected="false">Monedas</button>
        </li>
      </ul>
      <div class="tab-content" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-skins" role="tabpanel" aria-labelledby="pills-skins-tab">
        <c:forEach items="${skins}" var="skin">
          <div class="buySkin">
            <div class="imgSkin">
              <img src="src/juegoJS/srcjuego/SHOWPLAYERS/${skin.src}" alt="">
            </div>
            <div>
              <p>Nombre: ${skin.nombre}</p>
              <c:if test="${skin.precio != 0}"><!--No mostramos los que valen 0 porque se pueden obtener o con logros o porque es la skinInical-->
              <p>Precio: ${skin.precio}</p>
              <form action="Comprar">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="idSkin" value="${skin.id}">
                  <input type="submit" value="Comprar" name="Skin" class="btn btn-secondary" >
              </form>
              </c:if>
            </div>
          </div>
        </c:forEach>  
        </div>
        <div class="tab-pane fade" id="pills-Fondos" role="tabpanel" aria-labelledby="pills-Fondos-tab">
        <c:forEach items="${fondos}" var="fondo">    
          <div class="buyBackground">
            <div class="imgBack">
              <img src="src/juegoJS/srcjuego/BACKGROUNDS/${fondo.src}" alt="">
            </div>
            <div>
                <p>Nombre: ${fondo.nombre}</p>
              <c:if test="${fondo.precio != 0}">
              <p>Precio: ${fondo.precio}</p>
              <form action="Comprar">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="idFondo" value="${fondo.id}">
                  <input type="submit" value="Comprar" name="Fondo" class="btn btn-secondary">
              </form>
              </c:if>
            </div>
          </div>
        </c:forEach>  
        </div>
        <div class="tab-pane fade" id="pills-monedas" role="tabpanel" aria-labelledby="pills-monedas-tab">
          <div class="buyMonedas">
            <div class="imgMon">
              <img src="src/img/creditos.jpg" alt="">
            </div>
            <div>
              <p>Monedas: 80</p>
              <p>Precio: 0.99€</p>
              <form action="ComprarMonedas">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="monedas" value="80">
                  <input type="submit" value="Comprar" class="btn btn-secondary">
              </form>
            </div>
          </div>
          <div class="buyMonedas">
            <div class="imgMon">
              <img src="src/img/creditos.jpg" alt="">
            </div>
            <div>
              <p>Monedas: 500</p>
              <p>Precio: 4.99€</p>
              <form action="ComprarMonedas">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="monedas" value="500">
                  <input type="submit" value="Comprar" class="btn btn-secondary">
              </form>
            </div>
          </div>
          <div class="buyMonedas">
            <div class="imgMon">
              <img src="src/img/creditos.jpg" alt="">
            </div>
            <div>
              <p>Monedas: 1200</p>
              <p>Precio: 9.99€</p>
              <form action="ComprarMonedas">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="monedas" value="1200">
                  <input type="submit" value="Comprar" class="btn btn-secondary">
              </form>
            </div>
          </div>
          <div class="buyMonedas">
            <div class="imgMon">
              <img src="src/img/creditos.jpg" alt="">
            </div>
            <div>
              <p>Monedas: 2500</p>
              <p>Precio: 19.99€</p>
              <form action="ComprarMonedas">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="monedas" value="2500">
                  <input type="submit" value="Comprar" class="btn btn-secondary">
              </form>
            </div>
          </div>
          <div class="buyMonedas">
            <div class="imgMon">
              <img src="src/img/creditos.jpg" alt="">
            </div>
            <div>
              <p>Monedas: 6500</p>
              <p>Precio: 49.99€</p>
              <form action="ComprarMonedas">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="monedas" value="6500">
                  <input type="submit" value="Comprar" class="btn btn-secondary">
              </form>
            </div>
          </div>
          <div class="buyMonedas">
            <div class="imgMon">
              <img src="src/img/creditos.jpg" alt="" class="btn btn-secondary">
            </div>
            <div>
              <p>Monedas: 14000</p>
              <p>Precio: 99.99€</p>
              <form action="ComprarMonedas">
                  <input type="hidden" name="usuario" value="${usuario.usuario}">
                  <input type="hidden" name="monedas" value="14000">
                  <input type="submit" value="Comprar" class="btn btn-secondary">
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>

    </footer>

    <script>
        //Script para que se oculten los distintos paneles
        $("#pills-monedas").hide();
        $("#pills-Fondos").hide();
        $("#pills-skins").show();
        $("#pills-fondos-tab").on("click", function(){
            $("#pills-monedas").hide();
            $("#pills-Fondos").show();
            $("#pills-skins").hide();
        });
        $("#pills-skins-tab").on("click", function(){
            $("#pills-monedas").hide();
            $("#pills-Fondos").hide();
            $("#pills-skins").show();
        });
        $("#pills-monedas-tab").on("click", function(){
            $("#pills-monedas").show();
            $("#pills-Fondos").hide();
            $("#pills-skins").hide();
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src='src/juegoJS/juego.js'></script>
</body>
</html>
