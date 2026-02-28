# Examen de programación, servicios y procesos
### Curso 2021 - 2022

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

---

## 📋 Descripción

La prueba consistía en crear un Vivero para el reparto de plantones.

Tenemos por un lado la clase **Trucks** representativa de los camiones, que genera sus matrículas con este formato: **2473 - TWF**

La clase **Orders** es la encargada de generar los pedidos, estos forman el código de pedido con formato: **#540_20240108** y genera entre 5 y 25 pedidos.

La clase **ProcessingBelt** hace referencia a la cinta procesadora, es una clase que extiende de la clase Thread y su función es:
* Generar entre 5 y 10 cintas procesadoras
* Simular el reparto de pedidos
* Adquiere permiso del semáforo si está disponible

Finalmente la clase **Main** muestra una interfaz con los datos de los pedidos y utiliza un pool de hilos para la ejecución del programa.
