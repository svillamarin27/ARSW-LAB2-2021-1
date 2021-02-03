package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private Carril carril;
	RegistroLlegada regl;
	private boolean flag;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		flag = true;
	}

	public  void corra() throws InterruptedException {
		while (paso < carril.size()) {			
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			synchronized(this){
				while (flag){
					wait();
				}
			if (paso == carril.size()) {						
				carril.finish();
				int ubicacion;
				synchronized (regl) {
					ubicacion = regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion + 1);
				}
				System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
				if (ubicacion==1){
					regl.setGanador(this.getName());
				}
				
			}
			
			}
		}
	}

	
	@Override
	public void run() {
		
		try {
			flag = false;
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
		
	

}
