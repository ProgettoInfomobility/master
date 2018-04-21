/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progettoinfomobility;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 *
 * @author in5a13
 */
public class Server extends Thread{
    
    int HEADER_SIZE;
    Coda<DataFrame> coda = new Coda<>();
    int porta;
    
    public Server(int header_size, int porta, Coda<DataFrame> coda){
        this.HEADER_SIZE = header_size;
        this.porta = porta;
        this.coda = coda;
    }
    
    @Override
    public void run(){
        try{
            String ia = InetAddress.getLocalHost().getHostAddress();
            ServerSocket serverS = new ServerSocket();
            serverS.bind(new InetSocketAddress(ia, this.porta));
            
            Socket s = serverS.accept();
            InputStream in = s.getInputStream();

            byte[] array = new byte[HEADER_SIZE];
            in.read(array);

            String stringa = new String(array);
            
            String[] pairs = stringa.split(",");

            int BlockSize = Integer.parseInt(pairs[1].split(":")[1]);
            int NumSample = Integer.parseInt(pairs[2].split(":")[1]);
            int FreqSample = Integer.parseInt(pairs[3].split(":")[1]); 
            int FattMult = Integer.parseInt(pairs[4].split(":")[1]);
            
            while(true){
                byte[] data = new byte[BlockSize];
                if (in.read(data) == -1) break;

                double[] values = new double[NumSample];
                
                int index = 0;
                for(int i=0;i<BlockSize;i+=4)
                {
                    byte[] vet = new byte[4];
                    vet[3] = data[i];
                    vet[2] = data[i+1];
                    vet[1] = data[i+2];
                    vet[0] = data[i+3];
                    ByteBuffer bb = ByteBuffer.wrap(vet);

                    double valore = (double) bb.getInt();
                    valore = valore / FattMult; //da aggiungere al vettore
                    
                    values[index] = valore;
                    index++;
                }
                
                DataFrame df = new DataFrame(FreqSample, values);
                coda.push(df);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
}
