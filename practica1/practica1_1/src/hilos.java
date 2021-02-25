public class hilos extends Thread{
	
	private int id;
	private static int N = 5;
	private int T = 100;
	
	public hilos (int id) {
		this.id = id;
	}
	
	public void run() {
		try {
			sleep(T);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Soy el hilo:"+id);
	}
	
	public static void main(String[] args) {
		
		hilos[] vecHilos = new hilos[N];
		
		for (int i = 0; i< vecHilos.length; i++) {
			vecHilos[i] = new hilos(i+1);
			vecHilos[i].start();
		}
		
		try {
			for (int i = 0; i< vecHilos.length; i++) {
				
				vecHilos[i].join();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Se ha terminado");
	}
}