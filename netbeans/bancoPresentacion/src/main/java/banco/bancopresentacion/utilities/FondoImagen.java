/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancopresentacion.utilities;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

/**
 *
 * @author luiis
 */
public class FondoImagen implements Border{
     private BufferedImage img=null;
    
    public FondoImagen(BufferedImage img){
        this.img=img;
    }
    
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
            int width, int height){
        if(img!=null)
            g.drawImage(img, 0, 0, width, height, null);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
