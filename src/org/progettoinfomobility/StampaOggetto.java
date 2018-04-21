/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progettoinfomobility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author in5a04
 */
public class StampaOggetto {
    String fileName;
    
    public StampaOggetto(String fileName) {
        this.fileName = fileName;
    }
    
    public void write(Object o){
        try {
         FileOutputStream fileOut =
         new FileOutputStream(fileName);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(o);
         out.close();
         fileOut.close();
      } catch (IOException i) {}
    }
    
    public Object read(){
        Object o = null;
        try {
         FileInputStream fileIn = new FileInputStream(fileName);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         o = in.readObject();
         in.close();
         fileIn.close();
        } catch (IOException i) {} catch (ClassNotFoundException c) {}
        return o;
    }
    
}
