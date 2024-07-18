package org.GenerationQR.Controller;

import com.google.zxing.WriterException;
import org.GenerationQR.Model.ManagePackage;
import org.GenerationQR.Model.QRCodeGenerator;

import org.GenerationQR.Vista.Interfaz;

import java.io.File;
import java.io.IOException;


public class Fachada {

    final private QRCodeGenerator qrCodeGenerator;
    final private ManagePackage managePackage;
    final private Interfaz interfaz;

    private String namePng;
    private int size;
    private String url;

    public Fachada () {
        managePackage = new ManagePackage();
        qrCodeGenerator = new QRCodeGenerator();
        interfaz = Interfaz.obtenerInstancia();
    }

    public void iniciar () throws IOException, WriterException {

        if (!managePackage.getFolder().exists()) {
            if (managePackage.getCreado()) {
                interfaz.imprimir("Carpeta creada exitosamente en: " + managePackage.getPath());
            } else {
                interfaz.imprimir("No se pudo crear la carpeta. Contacte con el administrador.");
            }
        }

        fillVariables();

        generarQR();


    }

    public void fillVariables(){
        this.namePng = interfaz.preguntar("Ingrese el nombre de la imagen QR");
        this.url = interfaz.preguntar("Ingrese la url a plasmar en un QR");
    }

    public void generarQR () throws IOException, WriterException {
        try {

            qrCodeGenerator.generateQRCodeImage(url, new File(managePackage.getPath() + "/" + namePng + ".png"), namePng);

            interfaz.imprimir("El código QR se creó satisfactoriamente en: " + managePackage.getPath());
        } catch (WriterException e) {
            interfaz.imprimir("No se pudo crear el QR, error: " + e.getMessage());
        } catch (IOException e) {
            interfaz.imprimir("No se pudo crear el QR, error: " + e.getMessage());
        }

    }

    public int setSize(){

        boolean bandera = false;

        int number = 0;
        do{
            try {
                // Mostrar un cuadro de diálogo para la entrada del usuario
                String input = interfaz.preguntar("Ingrese el tamaño del QR en píxeles: ");

                // Intentar convertir el string a int
                number = Integer.parseInt(input);

                bandera = false;
            } catch (NumberFormatException e) {
                bandera = true;
                // Si ocurre un error en la conversión, mostrar un mensaje de error
                interfaz.imprimir( "Error: Debes ingresar un número válido. Vuelva a intentarlo");

            }
        }while(bandera);
        return number;
    }

}
