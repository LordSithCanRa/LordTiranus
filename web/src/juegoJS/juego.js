const canvas = document.getElementById("canvas1");
const ctx = canvas.getContext('2d');
canvas.width = 800;
canvas.height = 500;

const keys = [];

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

const playerSprite = new Image();
playerSprite.src = "src/juegoJS/srcjuego/chewie.png";
const background = new Image();
background.src = "src/juegoJS/srcjuego/background.png";

function drawSprite(img, sX, sY, sW, sH, dX, dY, dW, dH) {

    ctx.drawImage(img, sX, sY, sW, sH, dX, dY, dW, dH);
}

window.addEventListener("keydown", function (e) {
    keys[e.key] = true;
    player.moving = true;
});
window.addEventListener("keyup", function (e) {
    delete keys[e.key];
    player.moving = false;
});
/* [ArrowRight: true]
main.js:39 [ArrowUp: true]
main.js:39 [ArrowDown: true]
main.js:39 [ArrowLeft: true] */
function movePlayer(){
    if((keys["w"] || keys["W"]) && player.y>100 /* || arriba */){
        player.y-=player.speed;
        player.frameY = 3;
        player.moving = true;

    }
    if((keys["a"] || keys["A"]) && player.x>0 /* || izquierda */){
        player.x-=player.speed;
        player.frameY = 1;
        player.moving = true;
    }
    if((keys["s"] || keys["S"]) && player.y < canvas.height - player.height /* || abajo */){
        player.y+=player.speed;
        player.frameY = 0;
        player.moving = true;
    }
    if((keys["d"] || keys["D"]) && player.x < canvas.width - player.width /* || derecha */){
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
        drawSprite(playerSprite, player.width * player.frameX, player.height * player.frameY, player.width, player.height, player.x, player.y, player.width, player.height);
        movePlayer();
        handlePlayerFrame();
        requestAnimationFrame(animate);
    }
}
starAnimating(30);

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