package org.progettoinfomobility;

public class DataFrame {

   

    double freq; // frequenza
    double[] array; // numeri asse delle x

    public DataFrame(double freq, double[] array){
        this.freq = freq;
        this.array = array;
    }

  

   public double getFreq() {
       return(freq);
   }

  

   public double[] getArray() {
       return(array);
    }

}