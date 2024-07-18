package org.GenerationQR.Model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class QRCodeGenerator {
    private final int size = 1000;

    public QRCodeGenerator() {

    }

    public void generateQRCodeImage(String url, File filePath, String text) throws WriterException, IOException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size, size);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            Graphics2D graphics = qrImage.createGraphics();

            // Configurar el texto
            Font font = new Font("Arial", Font.BOLD, 40); // Ajusta el tamaño de la fuente según necesites
            graphics.setFont(font);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);

            // Asegurarse de que el texto se ajuste al ancho del QR
            if (textWidth > qrImage.getWidth()) {
                // Calcula el nuevo tamaño de la fuente para ajustar el texto al ancho del QR
                int newSize = (int) (font.getSize() * (qrImage.getWidth() / (double) textWidth));
                font = new Font("Arial", Font.BOLD, newSize);
                graphics.setFont(font);
                fontMetrics = graphics.getFontMetrics();
                textWidth = fontMetrics.stringWidth(text);
            }

            // Calcular la posición para el texto (centrado y en la parte inferior)
            int textPositionX = (qrImage.getWidth() - textWidth) / 2;
            int textPositionY = qrImage.getHeight() - fontMetrics.getHeight() / 2; // Ajusta esta posición según sea necesario

            // Dibujar el texto
            graphics.setColor(Color.black); // O cualquier color que contraste bien con tu QR y logo
            graphics.drawString(text, textPositionX, textPositionY);

            graphics.dispose();

            // Guardar la imagen resultante
            ImageIO.write(qrImage, "PNG", filePath);

        } catch (NullPointerException npe) {
            System.exit(0);
        }
    }
    public void generateQRCodeWithLogo(String url, InputStream logoFile, File filePath, String text) throws WriterException, IOException {

        try {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, size, size);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Cargar el logo
            BufferedImage logoImage = ImageIO.read(logoFile);
            int logoWidth = qrImage.getWidth() / 7;  // Tamaño del logo con relación al QR
            int logoHeight = qrImage.getHeight() / 7;

            // Calcular la posición para el logo (centro)
            int logoPositionX = (qrImage.getWidth() - logoWidth) / 2;
            int logoPositionY = (qrImage.getHeight() - logoHeight) / 2;

            Graphics2D graphics = qrImage.createGraphics();


            // Insertar el logo en el QR Code
            graphics.drawImage(logoImage, logoPositionX, logoPositionY, logoWidth, logoHeight, null);

            // Configurar el texto
            Font font = new Font("Arial", Font.BOLD, 40); // Ajusta el tamaño de la fuente según necesites
            graphics.setFont(font);
            FontMetrics fontMetrics = graphics.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);

            // Asegurarse de que el texto se ajuste al ancho del QR
            if (textWidth > qrImage.getWidth()) {
                // Calcula el nuevo tamaño de la fuente para ajustar el texto al ancho del QR
                int newSize = (int) (font.getSize() * (qrImage.getWidth() / (double) textWidth));
                font = new Font("Arial", Font.BOLD, newSize);
                graphics.setFont(font);
                fontMetrics = graphics.getFontMetrics();
                textWidth = fontMetrics.stringWidth(text);
            }

            // Calcular la posición para el texto (centrado y en la parte inferior)
            int textPositionX = (qrImage.getWidth() - textWidth) / 2;
            int textPositionY = qrImage.getHeight() - fontMetrics.getHeight() / 2; // Ajusta esta posición según sea necesario

            // Dibujar el texto
            graphics.setColor(Color.black); // O cualquier color que contraste bien con tu QR y logo
            graphics.drawString(text, textPositionX, textPositionY);


            graphics.dispose();

            // Guardar la imagen resultante
            ImageIO.write(qrImage, "PNG", filePath);


        } catch (NullPointerException npe) {
            System.exit(0);
        }
    }
}