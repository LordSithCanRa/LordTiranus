/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import modelo.Clasificacion;
import modelo.Comentarios;
import modelo.Fondos;
import modelo.Nivel;
import modelo.Skin;
import modelo.Usuario;

/**
 *
 * @author rcane
 */
public class daoSistema {
    public static Connection conectarBD() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mijuego?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root", "");
    }
    
    public static void desconectarBD(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }
    
    /*
    PARTE DEL USUARIO
    */
    
    public static void insertarUsuario(Usuario u) throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into usuarios (usuario, clave, gmail, admin, experienciaAct, monedas, nivel, recordPuntos, partidasJugadas) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        consulta.setString(1, u.getUsuario());
        consulta.setString(2, u.getClave());
        consulta.setString(3, u.getEmail());
        consulta.setBoolean(4, u.isAdmin());
        consulta.setInt(5, u.getExperienciaAct());
        consulta.setInt(6, u.getMonedas());
        consulta.setInt(7, u.getNivel());
        consulta.setInt(8, u.getRecordPuntos());
        consulta.setInt(9, u.getPartidasJugadas());
        consulta.execute();
        
        desconectarBD(con);
    }
    public static Usuario obtenerUsuario(String usuario)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("select * from usuarios where usuario=?");
        consulta.setString(1, usuario);
        ResultSet rs = consulta.executeQuery();
        Usuario u = null;
        if(rs.next()){
            String clave = rs.getString("clave");
            String email = rs.getString("gmail");
            boolean admin = rs.getBoolean("admin");
            int experienciaAct = rs.getInt("experienciaAct");
            int monedas = rs.getInt("monedas");
            int nivel = rs.getInt("nivel");
            int recordPuntos = rs.getInt("recordPuntos");
            int partidasJugadas = rs.getInt("partidasJugadas");
            u = new Usuario(usuario, clave, email, admin, experienciaAct, monedas, nivel, recordPuntos, partidasJugadas);
        }
        desconectarBD(con);
        return u;
    }
    public static void eliminarUsuario(String u)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("delete from usuarios where usuario=?");
        consulta.setString(1, u);
        consulta.executeUpdate();
        desconectarBD(con);
    }
    public static void modificarUsuario(Usuario u)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("update usuarios set nivel=?, recordPuntos=?, monedas=?, gmail=?, clave=?, admin=? where usuario=?");
        consulta.setInt(1, u.getNivel());
        consulta.setInt(2, u.getRecordPuntos());
        consulta.setInt(3, u.getMonedas());
        consulta.setString(4, u.getEmail());
        consulta.setString(5, u.getClave());
        consulta.setBoolean(6, u.isAdmin());
        consulta.setString(7, u.getUsuario());
        consulta.executeUpdate();
        desconectarBD(con);
    
    }
    public static void modificarClave(String clave, Usuario u)throws SQLException{
    Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("update usuarios set clave=? where usuario=?");
        consulta.setString(1, clave);
        consulta.setString(2, u.getUsuario());
        consulta.executeUpdate();
        desconectarBD(con);
    }
    public static void modificarEmail(String email, Usuario u)throws SQLException{
    Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("update usuarios set gmail=? where usuario=?");
        consulta.setString(1, email);
        consulta.setString(2, u.getUsuario());
        consulta.executeUpdate();
        desconectarBD(con);
    }
    
    public static List<Usuario> obtenerTodosUsuarios() throws SQLException{
        List<Usuario> usuarios = new LinkedList();
        Connection con = conectarBD();
         PreparedStatement consulta = con.prepareStatement(
                "select * from usuarios");
         ResultSet rs = consulta.executeQuery();
        while (rs.next()) {
            String usuario = rs.getString("usuario");
            String clave = rs.getString("clave");
            String email = rs.getString("gmail");
            boolean admin = rs.getBoolean("admin");
            int experienciaAct = rs.getInt("experienciaAct");
            int monedas = rs.getInt("monedas");
            int nivel = rs.getInt("nivel");
            int recordPuntos = rs.getInt("recordPuntos");
            int partidasJugadas = rs.getInt("partidasJugadas");
            Usuario u = new Usuario(usuario, clave, email, admin, experienciaAct, monedas, nivel, recordPuntos, partidasJugadas);
            usuarios.add(u);
        }
      desconectarBD(con);
         return usuarios;
    }
    public static boolean quitarMonedas(Usuario u, int monedas)throws SQLException{
        Connection con = conectarBD();
        
        int monedasF = u.getMonedas() - monedas;
        if(monedasF>= 0){
            PreparedStatement consulta = con.prepareStatement(
                "update usuarios set monedas=? where usuario=?");
            consulta.setInt(1, monedasF);
            consulta.setString(2, u.getUsuario());
            consulta.execute();
            desconectarBD(con);
            return true; //Todo ha ido bien
        }else{
            
            desconectarBD(con);
            return false; //No tiene monedas suficientes
        }

    }
    public static void agregarMonedas(Usuario u, int monedas)throws SQLException{
    Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "update usuarios set monedas=? where usuario=?");
        consulta.setInt(1, u.getMonedas()+monedas);
        consulta.setString(2, u.getUsuario());
        consulta.execute();
    desconectarBD(con);
    
    }
    /*
        APARTADO Inventario
    */
    public static void agregarSkinInventario(Usuario u, Skin s) throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into inventarioskins (usuario, idSkin, fechaObtenido) values (?, ?, ?)");
        consulta.setString(1, u.getUsuario());
        consulta.setInt(2, s.getId());
        Timestamp fechaObten;
        Long datetime = System.currentTimeMillis();
        fechaObten = new Timestamp(datetime);
        consulta.setTimestamp(3, fechaObten);
        consulta.execute();
        desconectarBD(con);
    }
    public static List<Skin> obtenerInventarioSkins(Usuario u)throws SQLException{
    List<Skin> skins = new LinkedList();
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
        "SELECT * FROM skins INNER JOIN inventarioskins ON skins.id =inventarioskins.idSkin where usuario=?");//Select que vaya directamente a la tabla de fondos
        consulta.setString(1, u.getUsuario());
         ResultSet rs = consulta.executeQuery();
        
         while (rs.next()) {
            int id = rs.getInt("id");
            String src = rs.getString("src");
            String nombre = rs.getString("nombre");
            int precio = rs.getInt("precio");
            int width = rs.getInt("width");
            int height = rs.getInt("height");
            Skin s = new Skin(id, src, nombre, width, height, precio);
            skins.add(s);
            
        }
        desconectarBD(con);
        return skins;
    } 
    
    //Prueba insert into inventariofondos (usuario, idFondo, fechaObtenido) values ("usuario", 1, '2022-04-27 23:34:01')
    public static void agregarFondoInventario(Usuario u, Fondos f) throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into inventariofondos (usuario, idFondo, fechaObtenido) values (?, ?, ?)");
        consulta.setString(1, u.getUsuario());
        consulta.setInt(2, f.getId());
        Timestamp fechaObten;
        Long datetime = System.currentTimeMillis();
        fechaObten = new Timestamp(datetime);
        consulta.setTimestamp(3, fechaObten);
        consulta.execute();
        desconectarBD(con);
    }
    public static List<Fondos> obtenerInventarioFondos(Usuario u)throws SQLException{
    List<Fondos> fondos = new LinkedList();
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
        "SELECT * FROM fondos INNER JOIN inventariofondos ON fondos.id =inventariofondos.idFondo where usuario=?");//Select que vaya directamente a la tabla de fondos
        consulta.setString(1, u.getUsuario());
         ResultSet rs = consulta.executeQuery();
        
         while (rs.next()) {
            int id = rs.getInt("id");
            String src = rs.getString("src");
            String nombre = rs.getString("nombre");
            int precio = rs.getInt("precio");
            
            Fondos f = new Fondos(id, src, nombre, precio);
            fondos.add(f);
            
        }
        desconectarBD(con);
        return fondos;
    } 
    /*
        APARTADO CLASIFICACIONES
    */
    
    public static List<Clasificacion> obtenerTop10()throws SQLException{
        List<Clasificacion> clasif = new LinkedList();
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
        "SELECT * FROM `usuarios` ORDER BY recordPuntos DESC LIMIT 10");
         ResultSet rs = consulta.executeQuery();
         int posicion = 1;
         while (rs.next()) {
            String usuario = rs.getString("usuario");
            String clave = rs.getString("clave");
            String email = rs.getString("gmail");
            boolean admin = rs.getBoolean("admin");
            int experienciaAct = rs.getInt("experienciaAct");
            int monedas = rs.getInt("monedas");
            int nivel = rs.getInt("nivel");
            int recordPuntos = rs.getInt("recordPuntos");
            int partidasJugadas = rs.getInt("partidasJugadas");
            Usuario u = new Usuario(usuario, clave, email, admin, experienciaAct, monedas, nivel, recordPuntos, partidasJugadas);
            Clasificacion c = new Clasificacion(posicion, u);
            clasif.add(c);
            posicion++;
        }
        desconectarBD(con);
        return clasif;
    }
    
    public static List<Clasificacion> clasificaciones()throws SQLException{
        List<Clasificacion> clasif = new LinkedList();
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
        "SELECT * FROM `usuarios` ORDER BY recordPuntos DESC");
         ResultSet rs = consulta.executeQuery();
         int posicion = 1;
         while (rs.next()) {
            String usuario = rs.getString("usuario");
            String clave = rs.getString("clave");
            String email = rs.getString("gmail");
            boolean admin = rs.getBoolean("admin");
            int experienciaAct = rs.getInt("experienciaAct");
            int monedas = rs.getInt("monedas");
            int nivel = rs.getInt("nivel");
            int recordPuntos = rs.getInt("recordPuntos");
            int partidasJugadas = rs.getInt("partidasJugadas");
            Usuario u = new Usuario(usuario, clave, email, admin, experienciaAct, monedas, nivel, recordPuntos, partidasJugadas);
            Clasificacion c = new Clasificacion(posicion, u);
            clasif.add(c);
            posicion++;
        }
        desconectarBD(con);
        return clasif;
    }
    
    /*
        Parte del comentarios
    */
    public static void insertarComentario(Comentarios c)throws SQLException{//Usuario al que escribimos el comentario
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into comentarios (usuarioRemitente, usuarioDestinatario, fecha, comentario) values (?, ?, ?, ?)");
        consulta.setString(1, c.getUsuarioRemitente());
        consulta.setString(2, c.getUsuarioDestinatario());
        consulta.setTimestamp(3, c.getFechaEscrito());
        consulta.setString(4, c.getComentario());
        consulta.execute();
        desconectarBD(con);
        
    }
    //Estos son los comentarios que han hecho otros usuarios al usuario pasado
    public static List<Comentarios> obtenerComentariosUsuario(Usuario u)throws SQLException{
        List<Comentarios> comentariosUsuario = new LinkedList();
        Connection con = conectarBD();
            PreparedStatement consulta = con.prepareStatement(
                "select * from comentarios where usuarioDestinatario = ?");
        consulta.setString(1, u.getUsuario());
        ResultSet rs = consulta.executeQuery();
        while(rs.next()){
            String usuarioRe = rs.getString("usuarioRemitente");
            String usuarioDes = rs.getString("usuarioDestinatario");
            int idComent = rs.getInt("idComentario");
            Timestamp fecha = rs.getTimestamp("fecha");
            String comentario = rs.getString("comentario");
            Comentarios c = new Comentarios(usuarioRe, usuarioDes, idComent, fecha, idComent, comentario);
            comentariosUsuario.add(c);
        }
        desconectarBD(con);
        return comentariosUsuario;
    }
    //Estos son los comentarios que ha hecho el usuario enviado
    public static List<Comentarios> obtenerComentariosUsuario2(Usuario u)throws SQLException{
        List<Comentarios> comentariosUsuario = new LinkedList();
        Connection con = conectarBD();
            PreparedStatement consulta = con.prepareStatement(
                "select * from comentarios where usuarioRemitente = ?");
        consulta.setString(1, u.getUsuario());
        ResultSet rs = consulta.executeQuery();
        while(rs.next()){
            String usuarioRe = rs.getString("usuarioRemitente");
            String usuarioDes = rs.getString("usuarioDestinatario");
            int idComent = rs.getInt("idComentario");
            Timestamp fecha = rs.getTimestamp("fecha");
            String comentario = rs.getString("comentario");
            Comentarios c = new Comentarios(usuarioRe, usuarioDes, idComent, fecha, idComent, comentario);
            comentariosUsuario.add(c);
        }
        desconectarBD(con);
        return comentariosUsuario;
    }
    public static void eliminarComentario(int id)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("delete from comentarios where idComentario= ?");
        consulta.setInt(1, id);
        consulta.executeUpdate();
        desconectarBD(con);
    }
    
    /* 
        APARTADO PARA LOS FONDOS
    */
    public static void insertarFondo(Fondos f)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into fondos (src, nombre, precio) values (?, ?, ?)");
        consulta.setString(1, f.getSrc());
        consulta.setString(2, f.getNombre());
        consulta.setInt(3, f.getPrecio());
        consulta.execute();
        desconectarBD(con);
    }
    
    public static Fondos obtenerFondo(int id)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("select * from fondos where id=?");
        consulta.setInt(1, id);
        ResultSet rs = consulta.executeQuery();
        Fondos f = null;
        if(rs.next()){
            String nombre = rs.getString("nombre");
            String src = rs.getString("src");
            int precio = rs.getInt("precio");
            f = new Fondos(id, nombre, src, precio);
        }
        desconectarBD(con);
        return f;
    
    }
    
    public static List<Fondos> obtenerFondos()throws SQLException{
        List<Fondos> fondos = new LinkedList();
        Connection con = conectarBD();
            PreparedStatement consulta = con.prepareStatement(
                "select * from fondos ORDER BY precio DESC");
        ResultSet rs = consulta.executeQuery();
        while(rs.next()){
            
            int id = rs.getInt("id");
            String src = rs.getString("src");
            String nombre = rs.getString("nombre");
            int precio = rs.getInt("precio");
            Fondos f = new Fondos(id, src, nombre, precio);
            fondos.add(f);
        }
        desconectarBD(con);
        return fondos;
    
    }
    /*
        APARTADO PARA LAS SKINS
    */
    public static void insertarSkin(Skin s)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "insert into skins (src, nombre, width, height, precio) values (?, ?, ?, ?, ?)");
        consulta.setString(1, s.getSrc());
        consulta.setString(2, s.getNombre());
        consulta.setDouble(3, s.getWidth());
        consulta.setDouble(4, s.getHeight());
        consulta.setInt(5, s.getPrecio());
        consulta.execute();
        desconectarBD(con);
    }
    public static List<Skin> obtenerSkins()throws SQLException{
        List<Skin> skins = new LinkedList();
        Connection con = conectarBD();
            PreparedStatement consulta = con.prepareStatement(
                "SELECT * FROM skins ORDER BY precio DESC");
        ResultSet rs = consulta.executeQuery();
        while(rs.next()){
            
            int id = rs.getInt("id");
            String src = rs.getString("src");
            String nombre = rs.getString("nombre");
            double width = rs.getDouble("width");
            double height = rs.getDouble("height");
            int precio = rs.getInt("precio");
            Skin s = new Skin(id, src, nombre, width, height, precio);
            skins.add(s);
        }
        desconectarBD(con);
        return skins;
    }
    public static Skin obtenerSkin(int id)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement("select * from skins where id=?");
        consulta.setInt(1, id);
        ResultSet rs = consulta.executeQuery();
        Skin skin = null;
        if(rs.next()){
            String nombre = rs.getString("nombre");
            String src = rs.getString("src");
            double width = rs.getDouble("width");
            double height = rs.getDouble("height");
            int precio = rs.getInt("precio");
            skin = new Skin(id, src, nombre, width, height, precio);
        }
        desconectarBD(con);
        return skin;
    }
    /*
        Apartado Niveles
    */
    public static int obtenerUltimoNivel() throws SQLException{
    int nivel = 0;
    Connection con = conectarBD();
            PreparedStatement consulta = con.prepareStatement(
                "SELECT * FROM `sistemaniveles` ORDER BY nivel ASC");
    ResultSet rs = consulta.executeQuery();
    while(rs.next()){
           nivel = rs.getInt("nivel");
        }
    desconectarBD(con);
    nivel = nivel +1;
    return nivel;
    }
    
    public static void insertarNivel(int nivel, int experienciaNecesaria, String recompensa)throws SQLException{
    Connection con = conectarBD();
    PreparedStatement consulta = con.prepareStatement(
            "insert into sistemaniveles (nivel, experienciaNecesaria, recompensa) values (?, ?, ?)");
    consulta.setInt(1, nivel);
    consulta.setInt(2, experienciaNecesaria);
    consulta.setString(3, recompensa);
    consulta.execute();
    desconectarBD(con);
    
    }
    
    public static Nivel obtenerNivel(int nivel)throws SQLException{
    Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
            "SELECT * FROM sistemaniveles where nivel = ?");
        consulta.setInt(1, nivel);
        ResultSet rs = consulta.executeQuery();
        Nivel n = null;
        if(rs.next()){
            String recompensa = rs.getString("recompensa");
            int experienciaNecesaria = rs.getInt("experienciaNecesaria");
            n = new Nivel(nivel, recompensa, experienciaNecesaria);
        }
        
    desconectarBD(con);
    return n;
    }
    
    public static void agregarExperiencia(Usuario u, int exp)throws SQLException{
    Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "update usuarios set experienciaAct=? where usuario=?");
        consulta.setInt(1, u.getExperienciaAct()+exp);
        consulta.setString(2, u.getUsuario());
        consulta.execute();
    desconectarBD(con);
    
    }
    
    public static void subirNivelUsuario(Usuario u, int exp)throws SQLException{
    Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "update usuarios set nivel=?, experienciaAct=? where usuario=?");
        consulta.setInt(1, u.getNivel()+1);
        consulta.setInt(2, exp);
        consulta.setString(3, u.getUsuario());
        consulta.execute();
    desconectarBD(con);
    }
    
    
    //Funcion final para dar experiencia a usuario COMPROBAR ESTE MÉTODO
    public static String comprobarSubidaNivel(Usuario u, int exp)throws SQLException{
        String mensaje = null;
        if(u.getNivel()==(obtenerUltimoNivel()-1)){//Si el usuario ya llegó al nivel máximo nos salimos
                mensaje = "Salida";
                return mensaje;
        }
        int proxNiv = u.getNivel()+1;
        Nivel nivelSiguiente = obtenerNivel(proxNiv);
        int experienciaTotal = u.getExperienciaAct() + exp;//Experiencia total en este instante

        if(nivelSiguiente.getExperienciaNecesaria()>experienciaTotal){//Sumamos la experiencia al usuario
            agregarExperiencia(u, exp);
            
        }else{//Subimos de nivel
            int experienciaFinal = experienciaTotal - nivelSiguiente.getExperienciaNecesaria();//La experiencia sobrante
            subirNivelUsuario(u, experienciaFinal);
            //Switch para obtener las recompensas
            String[] recompensas = nivelSiguiente.getRecompensa().split("-");
            switch (recompensas[0]) {
                case "M":
                    //Recompensa son Monedas
                    agregarMonedas(u, Integer.parseInt(recompensas[1]));
                    break;
                case "S":
                    //Recompensa es una Skin
                    Skin s = obtenerSkin(Integer.parseInt(recompensas[1]));
                    agregarSkinInventario(u, s);
                    break;
                case "F":
                    //Recompensa es un Fondo
                    Fondos f = obtenerFondo(Integer.parseInt(recompensas[1]));
                    agregarFondoInventario(u, f);
                    break;
                default:
                    break;
            }
            //Comprobamos si vuelve a subir de nivel
            Usuario usuarioActualizado = obtenerUsuario(u.getUsuario());
            comprobarSubidaNivel(usuarioActualizado, 0);
        }  
        mensaje = "Has subido "+exp+" de experiencia!";//Mensaje que mandamos para confirmación
        return mensaje;
    }
    
    //Ponemos la experiencia del usuario a 0
    public static void experienciaFinal(Usuario u)throws SQLException{
        Connection con = conectarBD();
        PreparedStatement consulta = con.prepareStatement(
                "update usuarios set experienciaAct=? where usuario=?");
        consulta.setInt(1, 0);
        consulta.setString(2, u.getUsuario());
        consulta.execute();
        desconectarBD(con);
    }
}
