package clases;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {


	    @SuppressWarnings("unused")
		public static void main(String args[]) throws IOException
	    {
	    	System.out.println(args[0]);
	    	String file="Desafio3.csv";
	    	Reader lector=new Reader(file);
	    	KDTree tree;
	    	tree=lector.read(7400);
	    	
	    	int len=args.length;

	    	
	  
	    	
	    	
	        
	       
	    	switch (len) {
			case 1:
				if (!args[0].toString().equals("-help"))
				{
					System.out.println("Por favor ingrese un comando permitido, para mas informacion ingrese -help");
				}
				else
				{
					System.out.println("Comandos: "
							+ "\n Para ver informacion sobre una aplicacion especifica, ingresar: 'find [id]'"
							+ "\n Para ver las aplicaciones mas parecidas a una, ingresar: 'similar [id]' "
							+ "\n Para mostrar las 10 aplicaciones mas similares al vector de atributos, ingresar el vector con el siguiente formato:"
							+ " similar [size_bytes] [price] [rating_count_tot] [rating_count_ver] [user_rating] [user_rating_ver] [cont_rating] [prime_genre]"
							+ " [sup_devices_num] [lang_num]");
				}
				break;
			case 2:
				if (args[0].toString().equals("find"))
				{
					//SE BUSCA LA INFO DESEADA
					KDNode temp=tree.findNode(Integer.parseInt(args[1]));
					if (temp!=null)
					{
						//SHOWTIME
						System.out.println("Id:"+temp.id+"\nNombre:  "+temp.track_name);
						break;
					}
					else
					{
						System.out.println("No se ha encontrado ninguna coincidencia");
						break;
					}
				}
				else
				{
					if (args[0].toString().equals("similar"))
					{
						//AQUI FUNCION DE SIMILARITUD Y UN PRINT
						//ASUMIENDO QUE ES EN UNA LISTA
						
						KDNode temp=tree.findNode(Integer.parseInt(args[1]));
						//SI EXISTE EL ID BUSCADO
						if (temp!=null)
						{
							ArrayList list=tree.simil(Integer.parseInt(args[1]), 10);
							int i=0;
							for (i=0;i<10;i++)
							{
								System.out.println((i+1)+")");
								temp=(KDNode)list.get(i);
								System.out.println("Id:"+temp.id+"\nNombre:  "+temp.track_name);
								System.out.println("\n");
							}
							break;
						}
						else
						{
							//SI NO EXISTE
							System.out.println("No se ha encontrado ninguna coincidencia con el id ingresado");
							break;
						}
					}
				}
				
				System.out.println("Por favor ingrese un comando permitido, para mas informacion ingrese -help");
				break;
			case 11:
				if (args[0].toString().equals("similar"))
				{
					
			    	
					String size_bytes=args[1];
			    	double price=Double.parseDouble(args[2]);
			    	int rating_count_tot=Integer.parseInt(args[3]);
			    	int rating_count_ver=Integer.parseInt(args[4]);
			    	double user_rating=Double.parseDouble(args[5]);
			    	double user_rating_ver=Double.parseDouble(args[6]);
			    	String cont_rating=args[7];
			    	String prime_genre=args[8];
			    	int sup_devices_num=Integer.parseInt(args[9]);
			    	int lang_num=Integer.parseInt(args[10]);
			    	
			    	double x[]=lector.vectorizar(size_bytes, price, rating_count_tot, rating_count_ver, user_rating, user_rating_ver, cont_rating, prime_genre, sup_devices_num, lang_num);
			    	
			    	//FUNCION PARA SIMILAR
			    	
					//ASUMIENDO QUE ES EN UNA LISTA
					
					ArrayList list=tree.simil(x);
					KDNode temp;
					int i=0;
					for (i=0;i<10;i++)
					{
						System.out.println(i+")");
						temp=(KDNode)list.get(i);
						System.out.println("Id:"+temp.id+"\nNombre:  "+temp.track_name);
						System.out.println("\n");
					}
					break;
				}
					
	
				else
				{
					System.out.println("Por favor ingrese un comando permitido, para mas informacion ingrese -help");
				}
				break;

			default:
				System.out.println("Por favor ingrese un comando permitido, para mas informacion ingrese -help");
				break;
			}
	  
	    }


}
