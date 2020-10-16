package clases;

public class KDNode 
{
	int axis;
    double[] x;
    int id;
    boolean checked;
    boolean orientation;
    String track_name;
    String currency;
    String ver;
    int ipadSc_urls_num;
    int vpp_lic;
    
    
    
    KDNode Parent;
    KDNode Left;
    KDNode Right;
    
    public KDNode(double[] x0,int axis0,int dim,int id, String track_name,String currency, String ver, int ipad,int vpp)
    {
    	 x = new double[dim];
         axis = axis0;
         for (int k = 0; k < dim; k++)
             x[k] = x0[k];
  
         Left = Right = Parent = null;
         checked = false;
         this.id=id;
         this.track_name=track_name;
         this.currency=currency;
         this.ver=ver;
         this.ipadSc_urls_num=ipad;
         this.vpp_lic=vpp;
    }
    public KDNode(double[] x0, int axis0, int dim)
    {
        x = new double[dim];
        axis = axis0;
        for (int k = 0; k < dim; k++)
            x[k] = x0[k];
 
        Left = Right = Parent = null;
        checked = false;
        id = 0;
    }
    
    public void showTime()
    {
    	/*	- "id" : App ID
			- "track_name": Nombre de la App
			- "size_bytes": Tamaño (en Bytes)
			- "currency": Moneda
			- "price": Precio en la moneda
			- "ratingcounttot": Cantidad de reseñas (Para todas las versiones)
			- "ratingcountver": Cantidad de reseñas (Para la versión actual)
			- "user_rating" : Promedio de puntaje de las reseñas (Para todas las versiones)
			- "userratingver": Promedio de puntaje de las reseñas (Para la versión actual)
			- "ver" : Ultima version
			- "cont_rating": Rating de contenido
			- "prime_genre": Género principal
			- "sup_devices.num": Cantidad de dispositivos soportados
			- "ipadSc_urls.num": Cantidad de capturas de pantallas por dispositivos
			- "lang.num": Cantidad de lenguajes soportados
			- "vpp_lic": Licencia Vpp activada
    	 */
    	System.out.println("ID: "+id);
    	System.out.println("Nombre: "+track_name);
    	System.out.println("Size Bytes: "+x[0]);
    	System.out.println("Currency: "+currency);
    	System.out.println("Price: "+x[1]);
    	System.out.println("Rating Count Tot: "+x[2]);
    	System.out.println("Rating Count Ver: "+x[3]);
    	System.out.println("User Rating: "+x[4]);
    	System.out.println("User Rating Ver:" +x[5]);
    	System.out.println("Ver: "+ver);
    	System.out.println("Cont Rating: "+x[6]);
    	System.out.println("Prime Genre: "+reverse((int)x[7]));
    	System.out.println("Sup Devices Num: " +x[8]);
    	System.out.println("IpadSC_Urls Num: "+ipadSc_urls_num);
    	System.out.println("Lang num: "+x[9]);
    	System.out.println("Vpp Lic: "+vpp_lic);
    	//size_bytes x[0]
    	//price x[1]
    	//rating_count_tot x[2]
    	//rating_count_ver x[3]
    	//user_rating x[4]
    	//user_rating_ver x[5]
    	//cont_rating x[6]
    	//prime_genre x[7]
    	//sup_devices_num x[8]
    	//lang_num x[9]
    }
 
    public KDNode() {
		// TODO Auto-generated constructor stub
	}
    public int getVpp_lic() {
		return vpp_lic;
	}
	public void setVpp_lic(int vpp_lic) {
		this.vpp_lic = vpp_lic;
	}
	public int getIpadSc_urls_num() {
		return ipadSc_urls_num;
	}
	public void setIpadSc_urls_num(int ipadSc_urls_num) {
		this.ipadSc_urls_num = ipadSc_urls_num;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTrack_name() {
		return track_name;
	}
	public void setTrack_name(String track_name) {
		this.track_name = track_name;
	}
	public KDNode FindParent(double[] x0)
    {
        KDNode parent = null;
        KDNode next = this;
        int split;
        while (next != null)
        {
            split = next.axis;
            parent = next;
            if (x0[split] > next.x[split])
                next = next.Right;
            else
                next = next.Left;
        }
        return parent;
    }
 
    public KDNode Insert(double[] p,int id, String track_name,String currency, String ver, int ipad,int vpp)
    {
        //x = new double[2];
        KDNode parent = FindParent(p);
        if (equal(p, parent.x, p.length))
            return null;
 
        KDNode newNode = new KDNode(p, parent.axis + 1 < p.length ? parent.axis + 1: 0, p.length,id,track_name,currency,ver,ipad,vpp);
        newNode.Parent = parent;
 
        if (p[parent.axis] > parent.x[parent.axis])
        {
            parent.Right = newNode;
            newNode.orientation = true; //
        } else
        {
            parent.Left = newNode;
            newNode.orientation = false; //
        }
 
        return newNode;
    }
    
    public KDNode Insert(double[] p)
    {
        //x = new double[2];
        KDNode parent = FindParent(p);
        if (equal(p, parent.x, p.length))
            return null;
 
        KDNode newNode = new KDNode(p, parent.axis + 1 < p.length ? parent.axis + 1: 0, p.length);
        newNode.Parent = parent;
 
        if (p[parent.axis] > parent.x[parent.axis])
        {
            parent.Right = newNode;
            newNode.orientation = true; //
        } else
        {
            parent.Left = newNode;
            newNode.orientation = false; //
        }
 
        return newNode;
    }
    boolean equal(double[] x1, double[] x2, int dim)
    {
        for (int k = 0; k < dim; k++)
        {
            if (x1[k] != x2[k])
                return false;
        }
 
        return true;
    }
 
    double distance2(double[] x1, double[] x2, int dim)
    {
        double S = 0;
        for (int k = 0; k < dim; k++)
            S += (x1[k] - x2[k]) * (x1[k] - x2[k]);
        return S;
    }
    
    public String reverse(int n)
    {
    	switch (n) {
		case 1:
			return "Business";
		case 2:
			
			return "Catalogs";
		case 3:
			
			return "Education";
		case 4:
			
			return "Entertainment";
		case 5:
			
			return "Finance";
		case 6:
			
			return "Food & Drink";
		case 7:
			
			return "Games";
		case 8:
			
			return "Health & Fitness";
		case 9:
			
			return "Lifestyle";
		case 10:
			
			return "Medical";
		case 11:
			
			return "Music";
		case 12:
			
			return "Navigation";
		case 13:
			
			return "News";
		case 14:
			
			return "Photo & Video";
		case 15:
			
			return "Book";
		case 16:
			
			return "Productivity";
		case 17:
			
			return "Reference";
		case 18:
			
			return "Shopping";
		case 19:
			
			return "Social Networking";
		case 20:
			
			return "Sports";
		case 21:
			
			return "Travel";
		case 22:
			
			return "Utilities";
		case 23:
			
			return "Weather";
	

		default:
			break;
		}
    	return "undefined";
    }
    
}
