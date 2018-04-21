/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progettoinfomobility;

import java.util.LinkedList;

/**
 *
 * @author Alessandro
 */

public class Coda<T> {
    private final LinkedList<T> list;
    
    public Coda() {
      list = new LinkedList<>();
    }
    
    public synchronized T pop() {
      try {
        while(list.isEmpty())
          wait();
      } catch(InterruptedException ex) {}
          return list.removeFirst();
    }
    public synchronized void push(T element) {
      list.addLast(element);
      notify();
    }
    
    public synchronized int size(){
        return list.size();
    }
}