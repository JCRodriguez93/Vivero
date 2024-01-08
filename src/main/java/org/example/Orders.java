package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Orders {
    //atributos
    private String cod_ped;
    private String nom_cliente;
    private String dir_entrega;
    private int platones_hembra;
    private int platones_macho;
    private int numcliente;

    /**
     * Método para generar una fecha. crea una fecha (la actual) y le da un
     * formato.
     * @return fecha en formato yyyMMdd
     */
    public static String fecha() {

        Date dat = new Date();
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMdd");

        return d.format(dat);
    }

    /**
     * Constructor de la clase Pedido.
     * Inicializa los atributos de los diferentes pedidos.
     */
    public Orders() {

        int cod = (int) ((Math.random() * (1000 - 0)) + 0); //codigo del pedido entre 999 y 0 con 0 incluido
        int hembra = (int) (286 * (Math.random() * (101 - 5) + 5)); //número de plantas hembras
        int numCli = (int) ((Math.random() * (10 - 1)) + 1); //numero de cliente
        this.cod_ped = "#" + cod + "_" + fecha();  //fecha del pedido
        this.nom_cliente = "Cliente " + numCli; //clientes
        this.dir_entrega = "Dirección Cliente " + numCli; //dirección del cliente
        this.platones_hembra = hembra;  //plantas hembra
        this.platones_macho = (hembra * 8) / 100; //plantas macho
        this.numcliente = numCli; //añadido 22/07/2022

    }

    /**
     * Método generar pedidos.
     * genera un número aleatorio entre 25 y 5 con 5 incluido de pedidos
     * @return int con un número aleatorio entre 25 y 5
     */
    public int generarPedidos() {
        return ((int) (Math.random() * (26 - 5)) + 5);
    }


    /**
     * Método toString.
     * devuelve las características de los pedidos.
     * @return String con los datos del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" + "cod_ped=" + cod_ped + ", nom_cliente=" + nom_cliente + ", dir_entrega=" + dir_entrega + ", platones_hembra=" + platones_hembra + ", platones_macho=" + platones_macho + ", numcliente=" + numcliente + "fecha: "+Orders.fecha()+'}';
    }

    public int getNumcliente() {
        return numcliente;
    }

    public void setNumcliente(int numcliente) {
        this.numcliente = numcliente;
    }

    public String getCod_ped() {
        return cod_ped;
    }

    public void setCod_ped(String cod_ped) {
        this.cod_ped = cod_ped;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }

    public String getDir_entrega() {
        return dir_entrega;
    }

    public void setDir_entrega(String dir_entrega) {
        this.dir_entrega = dir_entrega;
    }

    public int getPlatones_hembra() {
        return platones_hembra;
    }

    public void setPlatones_hembra(int platones_hembra) {
        this.platones_hembra = platones_hembra;
    }

    public int getPlatones_macho() {
        return platones_macho;
    }

    public void setPlatones_macho(int platones_macho) {
        this.platones_macho = platones_macho;
    }

}
