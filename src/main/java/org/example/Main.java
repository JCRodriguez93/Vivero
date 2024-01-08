package org.example;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    String nombre_vivero;
    Year anio;
    ArrayList<Orders> cola;

    //creamos el constructor con los atributos e inicializamos el arrayList
    public Main(String nombre_vivero, Year anio, ArrayList<Orders> lista) {
        this.nombre_vivero = nombre_vivero;
        this.anio = anio;
        this.cola = new ArrayList();
    }



    public static void main(String[] args) {

        Orders p = new Orders(); //creamos un objeto Orders para acceder a los parámetros
        ArrayList cola = new ArrayList(); //creamos la cola donde insertaremos los pedidos
        int cintas = ProcessingBelt.numCintas(); //le pasamos el número de cintas a una variable
        int num_ped = p.generarPedidos(); //generamos el número de pedidos

        interfaz(num_ped);

        for (int i = 1; i <= num_ped; i++) { //creamos los pedidos y los añadimos a la cola
            Orders ped = new Orders();

            cola.add(ped);
            System.out.println("pedido nº: " + i + ":" + ped);

        }

        System.out.println("\ncomenzamos a entregar los pedidos del año 2021");
        System.out.println("cintas procesadoras: "+cintas);


        try {

            Semaphore filaUnica = new Semaphore(1, true); //creamos el semáforo de la fila
            //el parámetro "1" es el número de hilos que accederán a vez al recurso compartido (en clase será así siempre)
            //el parámetro "true" es que concede a otro hilo el permiso para ejecutarse

            ExecutorService executor = Executors.newCachedThreadPool(); //creamos un pool de hilos

            for (int i = 1; i <= cintas; i++) { //ejecutaremos submit por cada nº de cintas

                executor.submit(new ProcessingBelt("cinta " + i, filaUnica, cola));

            }

            executor.shutdown();

            executor.awaitTermination(2, TimeUnit.MINUTES); //2 es la cantidad y TimeUnit es la unidad de tiempo
            //que va a esperar a que acaben los hilos
            System.out.println("trabajo terminado SE CIERRAN LAS CAJAS");


        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    private static void interfaz(int num_ped) {
        System.out.println("BIENVENIDOS AL ROBLE PERENNE\n"
                + "Vivero especializado en plantón de pistacho\n"
                + "Año: 2021\n"
                + "********************************************");
        System.out.println("-------------------------------------");
        System.out.println("Simulando cola de pedidos. Crearemos un número aleatorio de pedidos entre 5 y 25");
        System.out.println("-------------------------------------");

        System.out.println("Resumen de pedidos del Vivero");
        System.out.println("Total de pedidos " + num_ped);
    }
}