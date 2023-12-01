package org.bootcamp;

import org.bootcamp.dao.impl.AlmacenArticuloDaoImpl;
import org.bootcamp.dao.impl.InstrumentoDaoImpl;
import org.bootcamp.dao.impl.PartituraDaoImpl;
import org.bootcamp.dao.impl.UserDaoImpl;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.model.Instrumento;
import org.bootcamp.model.Partitura;
import org.bootcamp.model.User;
import org.bootcamp.service.AlmacenArticuloService;
import org.bootcamp.service.InstrumentoService;
import org.bootcamp.service.PartituraService;
import org.bootcamp.service.UserService;
import org.bootcamp.service.impl.AlmacenArticuloServiceImpl;
import org.bootcamp.service.impl.InstrumentoServiceImpl;
import org.bootcamp.service.impl.PartituraServiceImpl;
import org.bootcamp.service.impl.UserServiceImpl;
import org.bootcamp.utils.DbConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();

        AlmacenArticuloService almacenArticuloService = new AlmacenArticuloServiceImpl(new AlmacenArticuloDaoImpl(connection));
        InstrumentoService instrumentoService = new InstrumentoServiceImpl(new InstrumentoDaoImpl(connection));
        PartituraService partituraService = new PartituraServiceImpl(new PartituraDaoImpl(connection));
        UserService userService = new UserServiceImpl(new UserDaoImpl(connection));

        Scanner sc = new Scanner(System.in);

        //mostrando opciones del menu
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Bienvenido al Sistema de Almacen ");
            System.out.println("--------------------------------------------");
            System.out.println("Ingrese 1 para ver los articulos disponibles");
            System.out.println("Ingrese 2 para ver los usuarios disponibles");
            System.out.println("Ingrese 3 para ver la informacion de un articulo");
            System.out.println("Ingrese 4 para ver la informacion de un usuario");
            System.out.println("Ingrese 5 para agregar un nuevo artículo");
            System.out.println("Ingrese 6 para agregar un nuevo usuario");
            System.out.println("Ingrese 7 para prestar un articulo");
            System.out.println("Ingrese 8 para devolver un articulo");
            System.out.println("Ingrese 9 para modificar un articulo");
            System.out.println("Ingrese 10 para modificar un usuario");
            System.out.println("--------------------------------------------");

            int option = sc.nextInt();
            int idArt;
            int idUser;
            switch (option) {
                case 1:
                    List<AlmacenArticulo> articuloList = almacenArticuloService.showAllArt();
                    for(AlmacenArticulo articulo: articuloList){
                        articulo.showDetails();
                    }
                    break;
                case 2:
                    List<User> users = userService.showAllUsers();
                    for (User user: users){
                        user.showUserDetails();
                    }
                    break;
                case 3:
                    System.out.println("Ingrese el id del articulo");
                    idArt = sc.nextInt();
                    AlmacenArticulo articulo = almacenArticuloService.findArtById(idArt);
                    if (articulo.getArticuloID() != 0) {
                        articulo.showDetails();
                    } else {
                        System.out.println("No existe el articulo solicitado");
                    }
                    break;
                case 4:
                    System.out.println("Ingrese el id del usuario");
                    idUser = sc.nextInt();
                    User user = userService.findUserById(idUser);
                    if (user.getUserID() != 0) {
                        user.showUserDetails();
                    } else {
                        System.out.println("No existe el usuario solicitado");
                    }
                    break;
                case 5:
                    System.out.println("Ingrese el tipo de articulo (1 para Instrumento, 2 para Partitura)");
                    int tipo = sc.nextInt();
                    switch (tipo){
                        case 1:
                            Instrumento instrumento = new Instrumento();
                            System.out.println("Ingrese el nombre del instrumento");
                            String nombre = sc.next();
                            System.out.println("Ingrese el nombre del dueño");
                            String dueño = sc.next();
                            instrumento.setNombreArticulo(nombre);
                            instrumento.setIsLoaned(false);
                            instrumento.setDesDueño(dueño);
                            instrumentoService.addInstrumento(instrumento);
                            break;
                        case 2:
                            Partitura partitura = new Partitura();
                            System.out.println("Ingrese el nombre de la partitura");
                            String nombreArticulo = sc.next();
                            System.out.println("Ingrese el nombre del autor");
                            String autor = sc.next();
                            System.out.println("Ingrese la duracion de la partitura");
                            int duracion = sc.nextInt();
                            partitura.setNombreArticulo(nombreArticulo);
                            partitura.setIsLoaned(false);
                            partitura.setAutor(autor);
                            partitura.setDuration(duracion);
                            partituraService.addPartitura(partitura);
                            break;
                    }
                    break;
                case 6:
                    User userSave = new User();
                    System.out.println("Ingrese el nombre del usuario");
                    String nombre = sc.next();

                    userSave.setNombre(nombre);
                    userService.addUser(userSave);
                    break;
                case 7:
                    System.out.println("Ingrese el id del usuario");
                    idUser = sc.nextInt();

                    User userFind = userService.findUserById(idUser);
                    if (userFind.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }

                    System.out.println("Ingrese el id del articulo");
                    idArt = sc.nextInt();

                    AlmacenArticulo articuloFind = almacenArticuloService.findArtById(idArt);
                    if (articuloFind.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        break;
                    }
                    if (articuloFind.getIsLoaned()){
                        System.out.println("El articulo ya esta prestado a otro usuario");
                        break;
                    }
                    almacenArticuloService.loanArt(idArt, idUser);
                    break;
                case 8:
                    System.out.println("Ingrese el id del usuario");
                    idUser = sc.nextInt();

                    User userFound = userService.findUserById(idUser);
                    if (userFound.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }

                    System.out.println("Ingrese el id del articulo");
                    idArt = sc.nextInt();

                    AlmacenArticulo articuloFound = almacenArticuloService.findArtById(idArt);
                    if (articuloFound.getArticuloID() == 0){
                        System.out.println("Ingrese un articulo valido");
                        break;
                    }
                    boolean loaned = articuloFound.getIsLoaned();
                    if (!loaned){
                        System.out.println("El articulo no esta prestado");
                        break;
                    }
                    almacenArticuloService.returnArt(idArt, idUser);
                    break;
                case 9:
                    System.out.println("Ingrese el id del articulo a modificar");
                    idArt = sc.nextInt();

                    AlmacenArticulo articuloEdit = almacenArticuloService.findArtById(idArt);
                    if (articuloEdit == null || articuloEdit.getArticuloID() == 0){
                        System.out.println("Ingrese un id de articulo valido");
                        break;
                    }
                    articuloEdit.showDetails();

                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese los nuevos datos del articulo");
                    System.out.println("--------------------------------------------");
                    String tipoArt = articuloEdit.getTipoArticulo();
                    switch (tipoArt){
                        case "Instrumento":
                            Instrumento instrumento = (Instrumento) articuloEdit;
                            System.out.println("Ingrese el nuevo nombre del instrumento");
                            String nombreArt = sc.next();
                            System.out.println("Ingrese el nuevo nombre del dueño");
                            String dueño = sc.next();
                            instrumento.setNombreArticulo(nombreArt);
                            instrumento.setDesDueño(dueño);
                            almacenArticuloService.updateArt(instrumento);
                            break;
                        case "Partitura":
                            Partitura partitura = (Partitura) articuloEdit;
                            System.out.println("Ingrese el nuevo nombre de la partitura");
                            String nombreArticulo = sc.next();
                            System.out.println("Ingrese el nuevo nombre del autor");
                            String autor = sc.next();
                            System.out.println("Ingrese la nueva duracion de la partitura");
                            int duracion = sc.nextInt();
                            partitura.setNombreArticulo(nombreArticulo);
                            partitura.setAutor(autor);
                            partitura.setDuration(duracion);
                            almacenArticuloService.updateArt(partitura);
                            break;
                    }
                    break;
                case 10:
                    System.out.println("Ingrese el id del usuario");
                    idUser = sc.nextInt();

                    User userEdit = userService.findUserById(idUser);
                    if (userEdit.getUserID() == 0){
                        System.out.println("Ingrese un usuario valido");
                        break;
                    }
                    userEdit.showUserDetails();
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese los nuevos datos del usuario");
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese el nombre del usuario");
                    String newUserNombre = sc.next();

                    userEdit.setNombre(newUserNombre);

                    userService.editUser(userEdit);
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
        }
    }
}