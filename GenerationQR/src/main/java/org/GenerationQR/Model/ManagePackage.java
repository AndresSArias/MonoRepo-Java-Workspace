package org.GenerationQR.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class ManagePackage {

    private File folder;
    private boolean creado;
    public ManagePackage () {
        initFolder();
    }

    public void initFolder (){

        
        this.folder = new File("C:/Temp/GenerationQR");

        // Crear la carpeta si no existe
        if (!folder.exists()) {
            this.creado = folder.mkdirs(); // Usa mkdirs() para crear directorios intermedios si es necesario
        }
    }

    public boolean getCreado (){
        return creado;
    }

    public File getFolder(){
        return folder;
    }
    public String getPath (){
        return  folder.getAbsolutePath();
    }
}
