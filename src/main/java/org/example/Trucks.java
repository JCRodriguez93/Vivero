package org.example;

import java.util.Random;

public class Trucks {

    private static StringBuilder strb = new StringBuilder(); //objeto StringBulder para ir añadiendo letras
    private static Random r = new Random(); //objeto para cosas randoms

    //atributos esenciales
    private String matricula;
    private Orders pedido;

    /**
     * Método generar matrícula.
     * consiste en generar un número entre 999 y 0 con 0 incluido.
     * y una sucesión de caracteres aleatorios en mayúsculas al cual se
     * le dará posteriormente un formato.
     * ejem: "0939 - BKT"
     * @return
     */
    public static String generarMatricula(){
        int aleatorio = (int) (Math.random() * (10000 - 0)) + 0; //genera el número aleatorio

        //genera las letras aleatorias
        for (int i = 0; i < 3; i++) {
            r = new Random();
            char tmp = (char) ('A' + r.nextInt('Z' - 'A'));
            strb.append(tmp);
        }

        //4 numeros (si es un número de 1000 a 0, obvio añade un 0 si sale 334)
        //y guión + 3 letras
        String f = String.format("%1$04d - %2$s", aleatorio ,strb);
        strb.delete(0, strb.length());
        return f;
    }
    //constructor parametizado para crear un camión con su matricula y el pedido
    public Trucks(Orders pedido) {

        this.matricula = generarMatricula();
        this.pedido = pedido;
    }

    public Trucks() { //este se supone que mal pero si no no me funciona
        //principalmente lo uso para tener un objeto con el que usar los métodos de clase
        this.matricula = generarMatricula();
        this.pedido = new Orders();
    }

    //metodos get , set y toString para el acceso y modificación de atributos y mostrar el objeto.
    @Override
    public String toString() {
        return "Camion{ matricula=" + matricula + ", pedido=" + pedido + '}';
    }

    public StringBuilder getStrb() {
        return strb;
    }

    public void setStrb(StringBuilder strb) {
        this.strb = strb;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Orders getPedido() {
        return pedido;
    }

    public void setPedido(Orders pedido) {
        this.pedido = pedido;
    }

}
