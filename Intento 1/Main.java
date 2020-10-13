import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


	    public static void main(String args[]) throws IOException
	    {
	       // BufferedReader in = new BufferedReader(new FileReader("input.txt"));
	        
	    	//Cantidad de arreglos que tendrá
	    	int numpoints = 5;
	 
	        KDTree kdt = new KDTree(numpoints);
	        
	        //Se crean los datos, de momento esta comentada la lectura.
	        //Lo modifiqué para que lea arreglos de n-dimensiones
	        double x[] = new double[4];
	 
	        x[0] = 2.1;
	        x[1] = 4.3;
	        x[2] = 3.8;
	        x[3] = 6.3;
	        kdt.add(x);
	 
	        x[0] = 3.3;
	        x[1] = 1.5;
	        x[2] = 6.3;
	        x[3] = 0.3;
	        kdt.add(x);
	 
	        x[0] = 4.7;
	        x[1] = 11.1;
	        x[2] = 0.3;
	        x[3] = 1.3;
	        kdt.add(x);
	 
	        x[0] = 5.0;
	        x[1] = 12.3;
	        x[2] = 9.3;
	        x[3] = 2.3;
	        kdt.add(x);
	 
	        x[0] = 5.1;
	        x[1] = 1.2;
	        x[2] = 3.3;
	        x[3] = 3.3;
	        kdt.add(x);
	 
	        //Ejemplo para encontrar el arreglo que mas se parezca
	        System.out.println("Enter the co-ordinates of the point: (one after the other)");
	        InputStreamReader reader = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(reader);
	        double sx = 0.1;
	        double sy = 1.6;//Double.parseDouble(br.readLine());
	        double a = 3.5;
	        double b = 0.15;
	        double c = 5.3;
	        
	        double s[] = { sx, sy, a,b};
	        KDNode kdn = kdt.find_nearest(s);
	        System.out.println("The nearest neighbor is: ");
	        System.out.println("(" + kdn.x[0] + " , " + kdn.x[1] + " , "+kdn.x[2]+ " , "+kdn.x[3]+ ")");
	        //in.close();
	    }


}
