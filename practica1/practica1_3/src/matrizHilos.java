public class matrizHilos extends Thread{
	private static int N = 3;
	private int acu = 0;
	private int id = 0;
	static int m[][] = new int[N][N];
	public matrizHilos (int id, int acu) {
		this.acu = acu;
		this.id = id;
	}
	
	public void run() {
		
		for (int j = 0; j < N; j++) {
			this.acu+=m[j][id-1]*m[j][id-1];
			//System.out.println("Suma de Fila: "+acu);
		}
		
		System.out.println("Suma de Fila: "+acu);
	}
	/*
	|1|2|3|
	|4|5|6|
	|7|8|9|*/
	public static void crearMatriz() {
		int num =1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				m[i][j] = num;
				num++;
			}
		}
		/*for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(m[i][j]);
				
			}
			System.out.println();
		}*/
	}
	
	public static void main(String[] args) {

		matrizHilos[] vecHilos = new matrizHilos[N];
		crearMatriz();

		for (int i = 0; i < vecHilos.length; i++) {
			vecHilos[i] = new matrizHilos(i + 1, 0);
			vecHilos[i].start();
		}

		try {
			for (int i = 0; i < vecHilos.length; i++) {

				vecHilos[i].join();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		System.out.println("Se ha terminado ");
	}
	

}
