/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumaconsecutiutaskalumne;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumaConsecutiuTaskAlumne extends RecursiveTask<Long>{
  private int maxim;
  private int minim;

 public static final int LLINDAR = 10;

 public SumaConsecutiuTaskAlumne(int min, int max) {
     //Assignació  del paràmetres a implementar per l'alumne
     this.maxim = max;
     this.minim = min;
 }

 private Long SumaConsecutivaSecuencial() {
      // Algoritme recursiu adaptat a implementar per l'alumne
    long ret = 0;
    while (minim <= maxim){
        ret = ret + minim;
        minim ++ ;
    }
    return ret;
     
 }

 private Long SumaConsecutiuParallel() {
    int nummig = ((minim +  maxim)/2) + 1;  
     
    SumaConsecutiuTaskAlumne task1 = new SumaConsecutiuTaskAlumne(minim, nummig);
    SumaConsecutiuTaskAlumne task2 = new SumaConsecutiuTaskAlumne(nummig+1, maxim);
    
    task1.fork();
    task2.fork();
            
    return task1.join() + task2.join(); 
 }
 
 @Override
 protected Long compute() {      
      long resp;
      if(this.maxim - this.minim < LLINDAR) {
          // crida SumaConsecutivaSecuencial
          // i assignació del valor de resp que ha d'implementar l'alumne
          resp = SumaConsecutivaSecuencial();
          //System.out.print("Ha entrar secuencial");
      }
      else {
          // crida producteConsecutiuSecuencial
          // i assignació del valor de resp que ha d'implementar l'alumne
          resp = SumaConsecutiuParallel();
          //System.out.print("Ha entrar paralel");
      }
      return resp;
  }

  public static void main(String[] args) {
      int min = 0;
      int max = 100;
      long result = 0;
      ForkJoinPool pool = new ForkJoinPool();
      /**
       * Instanciació inicial i crida que calculi la suma consecutiva de la variable valor
       * a implementar per l'alumne.
       */
      SumaConsecutiuTaskAlumne tasca = new SumaConsecutiuTaskAlumne(min, max);
      result = pool.invoke(tasca); //AMb el mètode invoke assegurem que acabi la tasca abans de pasar a la següent instruccio
      System.out.println(result);
  }
}