package org.bootcamp;

import org.bootcamp.dao.AlmacenArticuloDao;
import org.bootcamp.dao.impl.AlmacenArticuloDaoImpl;
import org.bootcamp.dao.impl.InstrumentoDaoImpl;
import org.bootcamp.dao.impl.PartituraDaoImpl;
import org.bootcamp.model.AlmacenArticulo;
import org.bootcamp.model.Instrumento;
import org.bootcamp.model.Partitura;
import org.bootcamp.service.AlmacenArticuloService;
import org.bootcamp.service.InstrumentoService;
import org.bootcamp.service.PartituraService;
import org.bootcamp.service.impl.AlmacenArticuloServiceImpl;
import org.bootcamp.service.impl.InstrumentoServiceImpl;
import org.bootcamp.service.impl.PartituraServiceImpl;
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

        Scanner sc = new Scanner(System.in);

        //mostrando opciones del menu
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("Bienvenido al Sistema de Almacen ");
            System.out.println("--------------------------------------------");
            System.out.println("Ingrese 1 para ver los articulos disponibles");
            System.out.println("Ingrese 2 para añadir nuevos artículos al almacen");
            System.out.println("Ingrese 3 para prestar un articulo");
            System.out.println("--------------------------------------------");

            int option = sc.nextInt();
            switch (option) {
                case 1:
                    List<AlmacenArticulo> articuloList = almacenArticuloService.showAllArticulos();
                    for(AlmacenArticulo articulo: articuloList){
                        articulo.showDetails();
                    }
                    break;
                case 2:
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
                case 3:
                    System.out.println("Ingrese el id del usuario");
                    int idUsuario = sc.nextInt();
                    System.out.println("Ingrese el id del articulo");
                    int idArticulo = sc.nextInt();
                    almacenArticuloService.loanArt(idArticulo, idUsuario);
                default:
                    System.out.println("Ingrese una opcion correcta");
                    break;
            }
        }
    }
}