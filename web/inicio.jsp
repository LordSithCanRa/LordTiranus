<%-- 
    Document   : inicio
    Created on : 26-abr-2022, 18:47:28
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
    <link rel="stylesheet" href="src/inicio.css">
    <link rel="stylesheet" href="src/TyF.css">
    <script src="https://kit.fontawesome.com/f99d82f56d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <link rel="shortcut icon" href="src/img/icono.png" />
    
</head>    
<body>
    <c:if test="${!(empty param.error)}">
        <script>
        Swal.fire(
        'Error',
        '${param.error}',
        'error'
        )
    </script>
    </c:if>
    <c:if test="${!(empty param.confirm)}">
        <script>
        Swal.fire(
        'Success',
        '${param.confirm}',
        'success'
        )
    </script>
    </c:if>
    <c:if test="${!(empty param.info)}">
        <script>
        Swal.fire(
        'Welcome',
        '${param.info}',
        'info'
        )
    </script>
    </c:if>
    <c:if test="${!(empty param.pro)}">
        <script>
        Swal.fire(
        'Eres un PRO',
        '${param.info}',
        'info'
        )
    </script>
    </c:if>
    
    <header>
       <jsp:include page="encabezado.jsp"></jsp:include>
      <div class="header">
        <div class="titulo">
            <h1>@</h1>
        </div>
        <div class="home">
          <a href="CerrarSesion"><i class="bi bi-box-arrow-left"></i></a>
        </div>
        <div class="usuario">
           <div class="imagenUsuario">
            <img src="imgUsuarios/${usuario.usuario}.PNG" width="50px" height="50px">
           </div>
           <div class="datos">
                <p id="nombre">Nombre: <br><a href="MostrarUsuario?usuarioV=${usuario.usuario}">${usuario.usuario}</a></p>
                <p id="monedas">Monedas:  <br>${usuario.monedas}</p>
           </div>
        </div>
      </div>
    </header> 
    <nav class="movimiento">
        <div class="container-fluid">
            <a href="ObtenerClasificaciones"><i class="bi bi-file-earmark-bar-graph"></i></a>
            <a href="ObtenerTienda"><i class="bi bi-cart2"></i></a>
            <a data-bs-toggle="offcanvas" href="#offcanvasExample" role="button" aria-controls="offcanvasExample"><i class="bi bi-gear"></i></a>
            <a href="ObtenerInventario?usuarioInv=${usuario.usuario}"><i class="bi bi-boxes"></i></a>
            <c:if test="${usuario.admin}">
                 <a href="admin/admin.jsp"><i class="bi bi-puzzle-fill"></i></a>
            </c:if>
            
        </div>
    </nav>
    <section class="juego">
        <div class="anuncios">
            <div class="anuncio">
              <img src="src/anuncios/an1.PNG" alt="">
            </div>
            <div class="anuncio">
              <img src="src/anuncios/an2.PNG" alt="">
            </div>
            <div class="anuncio">
              <img src="src/anuncios/an3.PNG" alt="">
            </div>
        </div>
        <div class="juego">
          
          <canvas id="canvas1"></canvas>
        </div>
        <div class="botonesR">
          <button class="botonJuego up-arrow" id="upBttn"><i class="bi bi-caret-up-fill"></i></button>
          <button class="botonJuego left-arrow" id="leftBttn"><i class="bi bi-caret-left-fill"></i></button>
          <button class="botonJuego down-arrow" id="downBttn"><i class="bi bi-caret-down-fill"></i></button>
          <button class="botonJuego right-arrow" id="rightBttn"><i class="bi bi-caret-right-fill"></i></button>
        </div>
        <div class="top10">
            <h2 class="top10Titulo">TOP 10:</h2>
            <table class="table top10Tabla">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">Usuario</th>
                  <th scope="col">Puntos</th>
                  <th scope="col">Juegos</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${top10}" var="clasif">
                    <tr>
                      <th scope="row"><c:out value="${clasif.posicion}"/></th>
                      <td class="table-info"><a href="MostrarUsuario?usuarioV=${clasif.usuario.usuario}"><c:out value="${clasif.usuario.usuario}"/></a></td>
                      <td class="table-info"><c:out value="${clasif.usuario.recordPuntos}"/></td>
                      <td class="table-info"><c:out value="${clasif.usuario.partidasJugadas}"/></td>
                    </tr>
                </c:forEach>
              </tbody>
            </table>
        </div>
    </section>
    <footer>
      <p>Esta página esta creada por: <a href="https://www.linkedin.com/in/ram%C3%B3n-canelo-ar%C3%A9valo-b9182b18b/">Ramón Canelo Arévalo</a>&copy;</p>
      <form action="ObtenerExperiencia">
          <select name="experiencia">
              <option value="50">Obtener 50px</option>
              <option value="100">Obtener 100xp</option>
              <option value="300">Obtener 300px</option>
              <option value="500">Obtener 500px</option>
              <option value="1000">Obtener 1000xp</option>
              <option value="2000">Obtener 2000xp</option>
              <option value="5000">Obtener 5000xp</option>
              <option value="10000">Obtener 10000xp</option>
              <option value="50000">Obtener 50000xp</option>
              <option value="100000">Obtener 100000xp</option>
          </select>
          <input type="hidden" name="usuarioExp" value="${usuario.usuario}">
          <br>
          <input type="submit" value="Ganar XP">
      </form>
    </footer>

    <!-- OFFCANVAS -->
    <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasExampleLabel"><i class="bi bi-gear-fill"></i> Ajustes</h5>
          <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <div class="d-flex align-items-start">
            <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
              <button class="nav-link" id="v-pills-Perfil-tab" data-bs-toggle="pill" data-bs-target="#v-pills-Perfil" type="button" role="tab" aria-controls="v-pills-Perfil" aria-selected="false">Editar perfil</button>
              <button class="nav-link active" id="v-pills-comentarios-tab" data-bs-toggle="pill" data-bs-target="#v-pills-comentarios" type="button" role="tab" aria-controls="v-pills-comentarios" aria-selected="false">Tus comentarios</button>
            </div>
            <div class="tab-content" id="v-pills-tabContent">
              
              <div class="tab-pane fade panelEditUsuario" id="v-pills-Perfil" role="tabpanel" aria-labelledby="v-pills-Perfil-tab">
                <h2>Opciones:</h2>
                <div class="accordion" id="accordionExample">
<!--             <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Cambiar usuario:
                      </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                      <div class="accordion-body">
                        <form action="">
                            <label for="nombre">Nombre:</label>
                            <input type="text" name="nombre" id="nombre">
                            <br><br>
                            <input type="submit" class="btn btn-info" value="Cambiar">
                        </form>
                      </div>
                    </div>
                  </div>-->
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Cambiar contraseña:
                      </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                      <div class="accordion-body">
                        <form action="ModificarClave">
                          <label for="password">Contraseña:</label>
                          <input type="password" name="password" id="password">
                          <br><br>
                          <input type="submit" class="btn btn-info" value="Cambiar">
                        </form>
                        

                      </div>
                    </div>
                  </div>
                  <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Cambiar email:
                      </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                      <div class="accordion-body">
                        <form action="ModificarEmail">
                          <label for="email">Email:</label>
                          <input type="email" name="email" id="email">
                          <br><br>
                          <input type="submit" class="btn btn-info" value="Cambiar">
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="tab-pane fade panelComentarios     show active" id="v-pills-comentarios" role="tabpanel" aria-labelledby="v-pills-comentarios-tab">
                COMENTARIOS:
                <div class="miComents">
                  <c:forEach items="${comentariosEscritos}" var="comentariosEscritos">
                    <div class="comentario">
                      <div class="contenido">
                        <p class="destinatario">PARA: ${comentariosEscritos.usuarioDestinatario}</p>
                        <p>${comentariosEscritos.comentario}</p>
                        <p>Escrito el ${comentariosEscritos.fechaEscrito}</p>
                      </div>
                      <div class="eliminar">
                          <form action="EliminarComentario">
                            <input type="hidden" name="id" value="${comentariosEscritos.idComentario}"/>
                            <input type="hidden" name="usuarioEC" value="${usuario.usuario}"/>
                            <button type="submit" class="eliminarComentBttn" name="inicio"><i class="bi bi-trash-fill"></i></button>
                            </form>
                      </div>
                            
                      
<!--                      <div class="eliminar">
                        <p><i class="bi bi-trash"></i></p>
                      </div>-->
                    </div>
                  </c:forEach>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script>
        var canvas = document.getElementById("canvas1");
        const ctx = canvas.getContext('2d');
        canvas.width = 800;
        canvas.height = 500;

        const keys = [];

        const player ={
            x: 200,//200
            y: 300,//300,
            width: ${skinInicial.width},
            height: ${skinInicial.height},
            frameX: 0,
            frameY: 0,
            speed: 6.363,//Vector de movimiento normalizado (velocidad 9)
            moving: false
        };
        /* var arrEnemies = new Array();//Array donde guardaremos los enemigos
        class Enemie{
            constructor(x, y){
                this.x = x;
                this.y = y;
                this.width = 40;
                this.height = 72;
                this.frameX = 0;
                this.frameY = 0;
                this.speed = 7;
                moving = false;
            }

        } */
        //DEPRECATED
        const enemie ={
            x: player.x+250,
            y: player.y+250,
            width: 33,
            height: 47,
            frameX: 0,
            frameY: 0,
            speed: 2.828,//Vector de movimiento normalizado (velocidad 4)
            moving: true
        }
        //NEW
        function EnemieC(posX, posY){
            this.x = posX;//player.x+250
            this.y = posY;//player.y+250
            this.width = 33;
            this.height = 47;
            this.frameX = 0;
            this.frameY = 0;
            this.speed = 2.828;//Vector de movimiento normalizado (velocidad 4)
            this.moving = true

            this.movingEnemie = function (){//Función de mover al enemigo
            }
        }
        //NEW
        function deployEnemies(enemieNum){
            arrEnemies[enemieNum];
            for (let i = 0; i < enemieNum; i++) {
                posX = Math.floor(Math.random() * (400 - 250 + 1) + 250);//random para la x
                posY = Math.floor(Math.random() * (400 - 250 + 1) + 250);//random para la y
                arrEnemies[i] = EnemieC(posX, posY); 
            }

        }


        const enemieSprite = new Image();//NEW
        enemieSprite.src = "src/juegoJS/srcjuego/ENEMIES/darthmaul.png";//NEW

        const playerSprite = new Image();
        playerSprite.src = "src/juegoJS/srcjuego/PLAYERS/${skinInicial.nombre}.png";
        const background = new Image();
        background.src = "src/juegoJS/srcjuego/BACKGROUNDS/${fondoInicial.nombre}";

        function drawSprite(img, sX, sY, sW, sH, dX, dY, dW, dH) {

            ctx.drawImage(img, sX, sY, sW, sH, dX, dY, dW, dH);
        }
        

        
        //NEW FUNCT PARA CREAR MAS ENEMIGOS

        function moveEnemie(){
            if(enemie.moving){//SIEMPRE QUE SE ESTE MOVIENDO
                    if(player.x < enemie.x){//El enemigo esta a la derecha
                        enemie.x-=enemie.speed;//Se mueve a la izquierda
                        
                        enemie.moving = true;
                    }
                    if(player.x > enemie.x){//El enemigo esta a la izquierda
                        enemie.x+=enemie.speed;//Nos movemos hacia la derecha

                        enemie.moving = true;
                    }
                    if(player.y < enemie.y){//El enemigo esta arriba
                        enemie.y-=enemie.speed;//Nos movemos hacia abajo

                        enemie.moving = true;
                    }
                    if(player.y > enemie.y){//El enemigo estaria abajo
                        enemie.y+=enemie.speed;//Nos movemos hacia arriba

                        enemie.moving = true;
                    }
            }
        }
        function colision(playerX, playerY, enemieX, enemieY){
            if((playerX + (player.width/2)) == enemieX){//Si viene de frente
                enemie.moving = false;
            }
            if((playerY + (player.height/2)) == enemieY){//Si viene por arriba
                enemie.moving = false;
            }
            if((playerX - (player.width/2)) == enemieX){//Si viene por la izquierda
                enemie.moving = false;
            }
            if((playerY - (player.height/2)) == enemieY){//Si viene de abajo
                enemie.moving = false;
            }
        }
        function handleEnemieFrame(){
            if(enemie.frameX < 3 && enemie.moving){
                enemie.frameX++;
            }else{
                enemie.frameX = 0;
            }
        }
        //Para los controles por teclado
        window.addEventListener("keydown", function (e) {
            keys[e.key] = true;
            player.moving = true;
        });
        window.addEventListener("keyup", function (e) {
            delete keys[e.key];
            player.moving = false;
        });
        //APARTADO PARA LOS BOTONES
        var upMo = false;
        var downMo = false;
        var rightMo = false;
        var leftMo = false;
        $("#upBttn").on("click", function(){
            upMo = true;
            downMo = false;
            rightMo = false;
            leftMo = false;
        });
        $("#rightBttn").on("click", function(){
            upMo = false;
            downMo = false;
            rightMo = true;
            leftMo = false;
            
        });
        $("#leftBttn").on("click", function(){
            upMo = false;
            downMo = false;
            rightMo = false;
            leftMo = true;
            
        });
        $("#downBttn").on("click", function(){
            upMo = false;
            downMo = true;
            rightMo = false;
            leftMo = false;
            
        });
        
        function movePlayer(){  
            if((upMo || keys["w"] || keys["W"]) && player.y>100 /* || arriba */){
                player.y-=player.speed;
                player.frameY = 3;
                player.moving = true;

            }
            if((leftMo || keys["a"] || keys["A"]) && player.x>0 /* || izquierda */){
                player.x-=player.speed;
                player.frameY = 1;
                player.moving = true;
            }
            if((downMo || keys["s"] || keys["S"]) && player.y < canvas.height - player.height /* || abajo */){
                player.y+=player.speed;
                player.frameY = 0;
                player.moving = true;
            }
            if((rightMo || keys["d"] || keys["D"])&& player.x < canvas.width - player.width /* || derecha */){
                player.x+=player.speed;
                player.frameY = 2;
                player.moving = true;
            }
            
        }
        function handlePlayerFrame() {
            if(player.frameX < 3 && player.moving){
               player.frameX++;
            }else {
                player.frameX = 0;
            }
        }
        /* function animate(){
            ctx.clearRect(0,0,canvas.width,canvas.height);
            ctx.drawImage(background, 0,0,canvas.width,canvas.height);
            drawSprite(playerSprite, player.width * player.frameX, player.height * player.frameY, player.width, player.height, player.x, player.y, player.width, player.height);
            movePlayer();
            handlePlayerFrame();
            requestAnimationFrame(animate);
        }
        animate(); */

        //PARA EDITAR LA VELOCIDAD DEL JUEGO PODEMOS USAR:
        /* setInterval(function(){
            ctx.clearRect(0,0,canvas.width,canvas.height);
            ctx.drawImage(background, 0,0,canvas.width,canvas.height);
            drawSprite(playerSprite, player.width * player.frameX, player.height * player.frameY, player.width, player.height, player.x, player.y, player.width, player.height);
            movePlayer();
            handlePlayerFrame();
            requestAnimationFrame(animate);
        }, 300); */

        let fps, fpsInterval, starTime, now, then, elapsed;

        function starAnimating(fps){
            fpsInterval = 1000/fps;
            then = Date.now();
            starTime = then;
            animate();
        }
        function animate() {
            requestAnimationFrame(animate);
            now = Date.now();
            elapsed = now - then;
            if(elapsed > fpsInterval){
                then = now - (elapsed % fpsInterval);
                ctx.clearRect(0,0,canvas.width,canvas.height);
                ctx.drawImage(background, 0,0,canvas.width,canvas.height);
                drawSprite(enemieSprite, enemie.width * enemie.frameX, enemie.height * enemie.frameY, enemie.width, enemie.height, enemie.x, enemie.y, enemie.width, enemie.height);
                /* console.log(enemie); */
                moveEnemie();
                handleEnemieFrame();
                drawSprite(playerSprite, player.width * player.frameX, player.height * player.frameY, player.width, player.height, player.x, player.y, player.width, player.height);
                /* console.log(player); */
                movePlayer();
                handlePlayerFrame();

                /* colision(player.x, player.y, enemie.x, enemie.y); */
                requestAnimationFrame(animate);
            }
        }
        starAnimating(30);//Juego arrancado a 30fps

        /* 
        const player ={
            x: 200,
            y: 300,
            width: 40,
            height: 72,
            frameX: 0,
            frameY: 0,
            speed: 9,
            moving: false
        };
            Clase enemigo:
                x: un poquito fuera del canvas;
                y: random(dentro del canvas);
                width: 40;
                height: 72;
                frameX: 0;
                frameY: 0;
                speed: 8;
                moving: false;

            Niveles del juego:

            1 lvl: 
                20 enemigos;
                15 amigos;
            X lvl:
                ampliar en 5 enemigos cada nivel;
                ampliar en 5 amigos;

        */
    </script>
</body>
</html>
