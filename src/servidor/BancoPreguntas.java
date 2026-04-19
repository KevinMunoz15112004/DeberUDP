package servidor;

import java.util.ArrayList;
import java.util.List;

public class BancoPreguntas {

    public static List<Cuestionario> obtenerPreguntas() {

        List<Cuestionario> preguntas = new ArrayList<>();

        preguntas.add(new Cuestionario("¿Qué es la ingeniería de software?",
                "Conjunto de actividades para desarrollar software de forma sistemática",
                "Un lenguaje de programación",
                "Una base de datos",
                "Un sistema operativo",
                'A'));

        preguntas.add(new Cuestionario("¿Qué es UML?",
                "Lenguaje de programación",
                "Lenguaje de modelado",
                "Herramienta de base de datos",
                "Servidor web",
                'B'));

        preguntas.add(new Cuestionario("¿Qué representa el patrón MVC?",
                "Modelo-Ventana-Cliente",
                "Memoria-Variable-CPU",
                "Master-Version-Control",
                "Modelo-Vista-Controlador",
                'D'));

        preguntas.add(new Cuestionario("¿Qué es un sprint en Scrum?",
                "Un periodo fijo de trabajo para entregar incrementos",
                "Una herramienta de compilación",
                "Un tipo de testing",
                "Una base de datos",
                'A'));

        preguntas.add(new Cuestionario("¿Cuál es la finalidad del testing de software?",
                "Reducir el tamaño del ejecutable",
                "Acelerar la compilación",
                "Verificar y validar que el software cumple requisitos",
                "Optimizar imágenes",
                'C'));

        preguntas.add(new Cuestionario("¿Qué es refactorizar?",
                "Formatear el disco",
                "Añadir nuevas funcionalidades",
                "Eliminar comentarios",
                "Cambiar diseño del código sin alterar su comportamiento",
                'D'));

        preguntas.add(new Cuestionario("¿Qué es una prueba unitaria?",
                "Prueba de integración de sistemas completos",
                "Prueba que verifica una pequeña parte del código aislada",
                "Prueba de rendimiento",
                "Prueba de seguridad",
                'B'));

        preguntas.add(new Cuestionario("¿Qué es un repositorio Git?",
                "Un motor de base de datos",
                "Un servidor web",
                "Lugar donde se almacena código y su historial",
                "Un IDE",
                'C'));

        preguntas.add(new Cuestionario("¿Qué significa 'continuous integration'?",
                "Eliminar ramas",
                "Integrar solo al final del proyecto",
                "Realizar integraciones manuales",
                "Integrar cambios frecuentemente y automatizar builds",
                'D'));

        preguntas.add(new Cuestionario("¿Qué documento describe los requisitos de un sistema?",
                "Especificación de requisitos",
                "Manual de usuario",
                "Contrato de hosting",
                "Diseño de base de datos",
                'A'));

        return preguntas;
    }
}