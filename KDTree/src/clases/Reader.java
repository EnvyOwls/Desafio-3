package clases;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.apache.commons.codec.binary.StringUtils;

import com.opencsv.CSVReader;

public class Reader
{
	private String file;
	public Reader(String file)
	{
		this.file=file;
	}
	
	public KDTree read(int dim) throws IOException
	{
		KDTree tree=new KDTree(dim);
		//ABRE EL ARCHIVO
		FileReader filereader = new FileReader(file); 
		CSVReader csvReader = new CSVReader(filereader);
		//AQUI SE GUARDARAN LOS DATOS DE MOMENTO
	    String[] nextRecord; 
	    

		int id;
		String track_name;
		String size_bytes;
		String currency;
		double price;
		int rating_count_tot;
		int rating_count_ver;
		double user_rating;
		double user_rating_ver;
		String ver;
		String cont_rating;
		String prime_genre;
		int sup_devices_num;
		int ipadSc_urls_num;
		int lang_num;
		int vpp_lic;
		int cont=0;
	    //BASICAMENTE, MIENTRAS EL DOCUMENTO TENGA LINEAS PENDIENTES POR LEER
	    while ((nextRecord = csvReader.readNext()) != null)
	    {
	    	if (cont==0)
	    	{
	    		cont++;
	    	}
	    	else
	    	{
	    		id=Integer.parseInt(nextRecord[1]);
		    	track_name=nextRecord[2];
		    	size_bytes=nextRecord[3];
		    	currency=nextRecord[4];
		    	price=Double.parseDouble(nextRecord[5]);
		    	rating_count_tot=Integer.parseInt(nextRecord[6]);
		    	rating_count_ver=Integer.parseInt(nextRecord[7]);
		    	user_rating=Double.parseDouble(nextRecord[8]);
		    	user_rating_ver=Double.parseDouble(nextRecord[9]);
		    	ver=nextRecord[10];
		    	cont_rating=nextRecord[11];
		    	prime_genre=nextRecord[12];
		    	sup_devices_num=Integer.parseInt(nextRecord[13]);
		    	ipadSc_urls_num=Integer.parseInt(nextRecord[14]);
		    	lang_num=Integer.parseInt(nextRecord[15]);
		    	vpp_lic=Integer.parseInt(nextRecord[16]);
		    	
		    	
		    	//AQUI SE SUPONE QUE DEBERIA INGRESARSE A NUESTRO MANEJADOR DE DATOS QUE LO CORRELACIONE A ALGUN LUGAR

		    	
		    	//se crea el vector del elemento actual
		    	double[] x=vectorizar(size_bytes,price,rating_count_tot,rating_count_ver,user_rating,user_rating_ver,cont_rating,prime_genre,sup_devices_num,lang_num);
		    	//se agrega al arbol el nuevo nodo
		    	tree.add(x, id, track_name, currency, ver, ipadSc_urls_num, vpp_lic);
		    	
	    	}
	    	
	    }
	     
	    return tree;
	}
	
	public double[] vectorizar(String sizeBytes,double price,int rating_count_tot,int rating_count_ver,double user_rating, double user_rating_ver, 
			String cont_rating, String prime_genre,int sup_devices_num,int lang_num )
	{
		double vector[]=new double[10];
		
		vector[0]=new Double(sizeBytes);
		vector[1]=price;
		vector[2]=(rating_count_tot);
		vector[3]=(rating_count_ver);
		vector[4]=user_rating;
		vector[5]=user_rating_ver;
		vector[6]=new Double(cont_rating);
		vector[7]=genero(prime_genre);
		vector[8]=new Double (sup_devices_num);
		vector[9]=new Double(lang_num);
		
		return vector;
	}
	
	public double genero(String s)
	{
		/*
		Business
		Catalogs
		Education
		Entertainment
		Finance
		Food & Drink
		Games
		Health & Fitness
		Lifestyle
		Medical
		Music
		Navigation
		News
		Photo & Video
		Book
		Productivity
		Reference
		Shopping
		Social Networking
		Sports
		Travel
		Utilities
		Weather
		 */
		switch (s) {
		case "Business":
			return 1;

		case "Catalogs":
			return 2;
			
		case "Education":
			return 3;
			
		case "Entertainment":
			return 4;
			
		case "Finance":
			return 5;

		case "Food & Drink":
			return 6;
			
		case "Games":
			return 7;
			
		case "Health & Fitness":
			return 8;
			
		case "Lifestyle":
			return 9;
			
		case "Medical":
			return 10;
			
		case "Music":
			return 11;
			
		case "Navigation":
			return 12;
			
		case "News":
			return 13;
			
		case "Photo & Video":
			return 14;
			
		case "Book":
			return 15;
			
		case "Productivity":
			return 16;
			
		case "Reference":
			return 17;
			
		case "Shopping":
			return 18;
			
		case "Social Networking":
			return 19;
			
		case "Sports":
			return 20;
			
		case "Travel":
			return 21;
			
		case "Utilities":
			return 22;
			
		case "Weather":
			return 23;
		}
		//CASO QUE NO DEBE PASAR PERO ASI NO ME TIRA ERROR EL IDE
		return 50;
	}
	
}
