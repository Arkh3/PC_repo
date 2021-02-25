public class hilos extends Thread {

	private int id;
	private static int N = 100;
	private static int n = 0; // contador
	private static int M = 100; // M procesos

	public hilos(int id) {
		this.id = id;
	}

	public void run() {
		for (int i = 0; i < M; i++) {
			n++;
			n--;
		}
	}

	public static void main(String[] args) {

		hilos[] vecHilos = new hilos[N];

		for (int i = 0; i < vecHilos.length; i++) {
			vecHilos[i] = new hilos(i + 1);
			vecHilos[i].start();
		}

		try {
			for (int i = 0; i < vecHilos.length; i++) {

				vecHilos[i].join();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Se ha terminado con un n: " +n);
	}
}