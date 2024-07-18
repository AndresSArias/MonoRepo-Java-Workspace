package org.GenerationQR;

import com.google.zxing.WriterException;
import org.GenerationQR.Controller.Fachada;

import java.io.IOException;

public class Main {

    public static void main (String[] args) throws IOException, WriterException {

        Fachada app = new Fachada ();
        app.iniciar();

    }

}