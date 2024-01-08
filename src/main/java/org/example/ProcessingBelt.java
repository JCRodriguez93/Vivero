package org.example;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessingBelt extends Thread{
    //atributos necesarios
    private int identificador;
    private int total_plantas;
    private ArrayList<Orders> cola;
    private static int n_cinta; //si no es static no funciona
    private Orders p = new Orders(); //objeto simplemente para hacer uso de los métodos
    private Trucks camion = new Trucks(); //poco más o meno, quizás renta ponerlos en el constructor ambos
    //no lo sé ni lo quiero averiguar porque de momento todo funciona
    private Semaphore cinta_procesadora; //semaforo para cuando le pasemos por parámetro al constructor el semáforo
    //de la clase vivero

    /**
     * Método numero de cintas. Es un método que permite crear un número
     * aleatorio entre 5 y 10 cintas.
     *
     * @return
     */
    public static int numCintas() {
        n_cinta = (int) (Math.random() * (10 - 5)) + 5;
        return n_cinta;
    }

    //constructor que le pasamos el string para el nombre de la cinta, el semáforo y el array
    public ProcessingBelt(String nombre, Semaphore s, ArrayList cola) {
        super(nombre);
        this.cinta_procesadora = s;
        this.cola = cola;

        try {
            cinta_procesadora.acquire(); //el método del adquire se lanza siempre en el constructor
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessingBelt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Cosas que hace el run().
     *      mientras la cola no esté vacia, obtiene el primer pedido de la cola (posicion 0)
     *      añade ese pedido al camión
     *      muestra los datos
     *      el total de plantas será la suma del pedido (hembras + machos)
     *      borramos el elemento que hemos cogido de la posición 0
     *      liberamos el semaforo
     *      dormimos 5 secs el hilo
     *      si está vacia, terminamos
     */
    @Override
    public void run() {
        try {

            while(!cola.isEmpty()) { //mietras la cola no esté vacía.

                p = cola.get(0); //obtenemos el objeto en la primera posición del array

                camion = new Trucks(p); //creamos el camión con su matricula y el pedido

                //obtenemos los valores de los atributos con los métodos get y los mostramos
                System.out.println("______________________________________________________________________");
                System.out.println("la " + getName() + " preparada para procesar pedidos\n");
                System.out.println("\tPlantas del pedido:\nHembras: " + p.getPlatones_hembra()
                        + "\nMachos: " + p.getPlatones_macho()
                        + "\nTotal plantas pedido: " + (p.getPlatones_hembra() + p.getPlatones_macho())
                        + "\nEl pedido: " + p.getCod_ped() + " se entregará en la dirección: " + p.getDir_entrega() + ""
                        + " por el camión: " + camion.getMatricula());

                System.out.println("preparando entrega....");
//calculamos el total de plantas que cada hilo ha gestionado
                total_plantas += (p.getPlatones_hembra() + p.getPlatones_macho());

                cola.remove(0); //una vez gestionado el primer pedido, lo borramos de la cola
                cinta_procesadora.release(); //hacemos release del semaforo para que otro hilo entre en ejecución
                sleep((long) (Math.random() * 5000)); //simulamos un tiempo de espera

            }
            if (cola.isEmpty()) { //si la cola está vacía, decimos que está libre y mostramos el total de plantas gestionado
                System.out.println("la " + getName() + " está LIBRE");
                System.out.println("la " + getName() + " ha TERMINADO su trabajo. Ha entregado: " + total_plantas + " total plantas");

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(ProcessingBelt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //MÉTODOS GET Y SET
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getTotal_plantas() {
        return total_plantas;
    }

    public void setTotal_plantas(int total_plantas) {
        this.total_plantas = total_plantas;
    }

    public ArrayList<Orders> getCola() {
        return cola;
    }

    public void setCola(ArrayList<Orders> cola) {
        this.cola = cola;
    }

    public int getN_cinta() {
        return n_cinta;
    }

    public void setN_cinta(int n_cinta) {
        this.n_cinta = n_cinta;
    }

    public Orders getP() {
        return p;
    }

    public void setP(Orders p) {
        this.p = p;
    }

    public Trucks getCamion() {
        return camion;
    }

    public void setCamion(Trucks camion) {
        this.camion = camion;
    }

    public Semaphore getCinta_procesadora() {
        return cinta_procesadora;
    }

    public void setCinta_procesadora(Semaphore cinta_procesadora) {
        this.cinta_procesadora = cinta_procesadora;
    }

}


