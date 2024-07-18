package org.GenerationQR.Vista;

import javax.swing.*;

public class Interfaz {
    // La única instancia de la clase
    private static Interfaz instanciaUnica;

    // Constructor privado para evitar la instanciación directa
    private Interfaz() {
    }

    // Método público estático para obtener la única instancia
    public static synchronized Interfaz obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Interfaz();
        }
        return instanciaUnica;
    }

    public void imprimir (String text){

        JOptionPane.showMessageDialog(null, text);

    }

    public String preguntar (String text){
        return  JOptionPane.showInputDialog(text);
    }
}
