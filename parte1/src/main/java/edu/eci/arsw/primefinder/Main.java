package edu.eci.arsw.primefinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<PrimeFinderThread> hilos= new ArrayList<PrimeFinderThread>();
	public static void main(String[] args) throws InterruptedException  {
		int inicio = 0;
		int valor=10000000;
		int fin = valor;
		for(int i=1;i<4;i++) {
			PrimeFinderThread pft=new PrimeFinderThread(inicio, fin);
			inicio = fin+1;
			fin=valor*(i+1);
			hilos.add(pft);
		}
		for(int i = 0;i<hilos.size();i++) {
			if(i==0) {
				hilos.get(i).start();
				Thread.sleep(5000);
				hilos.get(i).pausarhilo();
				pressAnyKeyToContinue();
				hilos.get(i).reanudarhilo();
			}else {
				hilos.get(i).start();
			}
			
		}
		
		
	}	
	 static public void pressAnyKeyToContinue()
	    {
	        String seguir;
	        Scanner next = new Scanner(System.in);
	        System.out.println("Press Enter key to continue with the program");
	        try
	        {
	            seguir = next.nextLine();
	        }
	        catch(Exception e)
	        {}
	    }
	
}
