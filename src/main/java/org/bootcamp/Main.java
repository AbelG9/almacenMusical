package org.bootcamp;

import org.bootcamp.dao.impl.AlmacenArticuloDaoImpl;
import org.bootcamp.dao.impl.PrestamoDaoImpl;
import org.bootcamp.dao.impl.UserDaoImpl;
import org.bootcamp.model.*;
import org.bootcamp.service.AlmacenArticuloService;
import org.bootcamp.service.PrestamoService;
import org.bootcamp.service.UserService;
import org.bootcamp.service.impl.AlmacenArticuloServiceImpl;
import org.bootcamp.service.impl.PrestamoServiceImpl;
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
        UserService userService = new UserServiceImpl(new UserDaoImpl(connection));
        PrestamoService prestamoService = new PrestamoServiceImpl(new PrestamoDaoImpl(connection));

        Scanner sc = new Scanner(System.in);
        int optionSystem = 0;
        //mostrando opciones del menu
        do {
            System.out.println("------------------------------------------------------");
            System.out.println("           Bienvenido al Sistema de Almacen ");
            System.out.println("------------------------------------------------------");
            System.out.println("Ingrese 1 para gestionar los usuarios");
            System.out.println("Ingrese 2 para gestionar los articulos");
            System.out.println("Ingrese 3 para gestionar los prestamos y devoluciones");
            System.out.println("------------------------------------------------------");

            int option = sc.nextInt();
            int idArt;
            int idUser;
            int internalOption;
            switch (option) {
                case 1:
                    System.out.println("--------------------------------------------");
                    System.out.println("             Gestion de Usuarios ");
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese 1 para ver todos los usuarios");
                    System.out.println("Ingrese 2 para ver la informacion de un usuario");
                    System.out.println("Ingrese 3 para agregar un nuevo usuario");
                    System.out.println("Ingrese 4 para modificar un usuario");
                    System.out.println("Ingrese 5 para eliminar un usuario");
                    System.out.println("--------------------------------------------");
                    internalOption = sc.nextInt();
                    switch (internalOption){
                        case 1:
                            List<User> users = userService.showAllUsers();
                            for (User user: users){
                                user.showUserDetails();
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el id del usuario");
                            idUser = sc.nextInt();
                            User user = userService.findUserById(idUser);
                            if (user.getUserID() != 0) {
                                user.showUserDetails();
                            } else {
                                System.out.println("No existe el usuario solicitado");
                            }
                            break;
                        case 3:
                            User userSave = new User();
                            System.out.println("Ingrese el nombre del usuario");
                            String nombre = sc.next();

                            userSave.setNombre(nombre);
                            userService.addUser(userSave);
                            break;
                        case 4:
                            System.out.println("Ingrese el id del usuario");
                            idUser = sc.nextInt();

                            User userEdit = userService.findUserById(idUser);
                            if (userEdit == null || userEdit.getUserID() == 0){
                                System.out.println("Ingrese un usuario valido");
                                break;
                            }
                            userEdit.showUserDetails();
                            System.out.println("--------------------------------------------");
                            System.out.println("     Ingrese los nuevos datos del usuario");
                            System.out.println("--------------------------------------------");
                            System.out.println("Ingrese el nombre del usuario");
                            String newUserNombre = sc.next();

                            userEdit.setNombre(newUserNombre);
                            userService.editUser(userEdit);
                            break;
                        case 5:
                            System.out.println("Ingrese el id del usuario");
                            idUser = sc.nextInt();

                            User userDelete = userService.findUserById(idUser);
                            if (userDelete == null || userDelete.getUserID() == 0){
                                System.out.println("Ingrese un usuario valido");
                                break;
                            }
                            List<Prestamo> prestamoRevisionUser = prestamoService.getLoansByUserId(userDelete.getUserID());
                            if (!prestamoRevisionUser.isEmpty()){
                                System.out.println("El usuario tiene articulos prestados, primero debe efectuar la devolucion");
                                break;
                            }
                            userService.deleteUser(userDelete.getUserID());
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("--------------------------------------------");
                    System.out.println("          Gestion de Articulos ");
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese 1 para ver los articulos disponibles");
                    System.out.println("Ingrese 2 para ver la informacion de un articulo");
                    System.out.println("Ingrese 3 para agregar un nuevo artículo");
                    System.out.println("Ingrese 4 para modificar un articulo");
                    System.out.println("Ingrese 5 para eliminar un articulo");
                    System.out.println("--------------------------------------------");
                    internalOption = sc.nextInt();
                    switch (internalOption) {
                        case 1:
                            List<AlmacenArticulo> articuloList = almacenArticuloService.showAllArt();
                            for(AlmacenArticulo articulo: articuloList){
                                articulo.showDetails();
                            }
                            break;
                        case 2:
                            System.out.println("Ingrese el id del articulo");
                            idArt = sc.nextInt();
                            AlmacenArticulo articulo = almacenArticuloService.findArtById(idArt);
                            if (articulo.getArticuloID() != 0) {
                                articulo.showDetails();
                            } else {
                                System.out.println("No existe el articulo solicitado");
                            }
                            break;
                        case 3:
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
                                    instrumento.setTipoArticulo("Instrumento");
                                    almacenArticuloService.addArt(instrumento);
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
                                    partitura.setTipoArticulo("Partitura");
                                    almacenArticuloService.addArt(partitura);
                                    break;
                            }
                            break;
                        case 4:
                            System.out.println("Ingrese el id del articulo a modificar");
                            idArt = sc.nextInt();

                            AlmacenArticulo articuloEdit = almacenArticuloService.findArtById(idArt);
                            if (articuloEdit == null || articuloEdit.getArticuloID() == 0){
                                System.out.println("Ingrese un id de articulo valido");
                                break;
                            }
                            articuloEdit.showDetails();

                            System.out.println("--------------------------------------------");
                            System.out.println("    Ingrese los nuevos datos del articulo");
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
                        case 5:
                            System.out.println("Ingrese el id del articulo");
                            idArt = sc.nextInt();
                            AlmacenArticulo articuloDelete = almacenArticuloService.findArtById(idArt);
                            if (articuloDelete == null || articuloDelete.getArticuloID() == 0){
                                System.out.println("Ingrese un articulo valido");
                                break;
                            }
                            List<Prestamo> prestamoRevision = prestamoService.getLoansByArtId(articuloDelete.getArticuloID());
                            if (!prestamoRevision.isEmpty()){
                                System.out.println("El articulo esta prestado, primero efectue la devolucion por favor");
                                break;
                            }
                            almacenArticuloService.deleteArt(articuloDelete.getArticuloID());
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("--------------------------------------------");
                    System.out.println("          Gestion de Prestamos ");
                    System.out.println("--------------------------------------------");
                    System.out.println("Ingrese 1 para prestar un articulo");
                    System.out.println("Ingrese 2 para devolver un articulo");
                    System.out.println("Ingrese 3 para ver el historial de prestamos por usuario");
                    System.out.println("Ingrese 4 para ver el historial de prestamos por articulo");
                    System.out.println("--------------------------------------------");
                    internalOption = sc.nextInt();
                    switch (internalOption) {
                        case 1:
                            System.out.println("Ingrese el id del usuario");
                            idUser = sc.nextInt();

                            User userFind = userService.findUserById(idUser);
                            if (userFind == null || userFind.getUserID() == 0){
                                System.out.println("Ingrese un usuario valido");
                                break;
                            }

                            System.out.println("Ingrese el id del articulo");
                            idArt = sc.nextInt();

                            AlmacenArticulo articuloFind = almacenArticuloService.findArtById(idArt);
                            if (articuloFind == null || articuloFind.getArticuloID() == 0){
                                System.out.println("Ingrese un articulo valido");
                                break;
                            }
                            if (articuloFind.getIsLoaned()){
                                System.out.println("El articulo ya esta prestado");
                                break;
                            }
                            prestamoService.loanArt(idArt, idUser);
                            break;
                        case 2:
                            System.out.println("Ingrese el id del usuario");
                            idUser = sc.nextInt();

                            User userFound = userService.findUserById(idUser);
                            if (userFound == null || userFound.getUserID() == 0){
                                System.out.println("Ingrese un usuario valido");
                                break;
                            }
                            if(userFound.getArticuloList().isEmpty()){
                                System.out.println("El usuario no tiene articulos prestados");
                                break;
                            }

                            System.out.println("Ingrese el id del articulo");
                            idArt = sc.nextInt();

                            AlmacenArticulo articuloFound = almacenArticuloService.findArtById(idArt);
                            if (articuloFound == null || articuloFound.getArticuloID() == 0){
                                System.out.println("Ingrese un articulo valido");
                                break;
                            }
                            boolean loaned = articuloFound.getIsLoaned();
                            if (!loaned){
                                System.out.println("El articulo no esta prestado");
                                break;
                            }
                            prestamoService.returnArt(idArt, idUser);
                            break;
                        case 3:
                            System.out.println("Ingrese el id del usuario");
                            idUser = sc.nextInt();

                            User userHistory = userService.findUserById(idUser);
                            if (userHistory == null || userHistory.getUserID() == 0){
                                System.out.println("Ingrese un usuario valido");
                                break;
                            }

                            List<Prestamo> allPrestamos = prestamoService.getAllLoansByUserId(idUser);
                            if (allPrestamos.isEmpty()){
                                System.out.println("El usuario no tiene historial de prestamos");
                                break;
                            }
                            for (Prestamo prestamo : allPrestamos) {
                                prestamo.showPrestamoDetails();
                            }
                            break;
                        case 4:
                            System.out.println("Ingrese el id del articulo");
                            idArt = sc.nextInt();

                            AlmacenArticulo articuloHistory = almacenArticuloService.findArtById(idArt);
                            if (articuloHistory == null || articuloHistory.getArticuloID() == 0){
                                System.out.println("Ingrese un articulo valido");
                                break;
                            }
                            List<Prestamo> allHistoryPrestamos = prestamoService.getAllLoansByUserId(idArt);
                            if (allHistoryPrestamos.isEmpty()){
                                System.out.println("El articulo no tiene historial de prestamos");
                                break;
                            }
                            for (Prestamo prestamo : allHistoryPrestamos) {
                                prestamo.showPrestamoDetails();
                            }
                            break;
                        default:
                            System.out.println("Ingrese una opcion valida");
                            break;
                    }
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
            System.out.println("--------------------------------------------");
            System.out.println("Desea realizar otra operacion? 1: Si, 0: No");
            optionSystem = sc.nextInt();
        } while (optionSystem == 1);
        System.out.println("Gracias por utilizar este sistema, vuelva pronto!");
    }
}