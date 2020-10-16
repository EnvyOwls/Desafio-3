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
}
