//This is a java program to find nearest neighbor using KD Tree implementation
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
class KDNode
{
    int axis;
    double[] x;
    int id;
    boolean checked;
    boolean orientation;
 
    KDNode Parent;
    KDNode Left;
    KDNode Right;
 
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
 
class KDTree
{
    KDNode Root;
 
    int TimeStart, TimeFinish;
    int CounterFreq;
 
    double d_min;
    KDNode nearest_neighbour;
 
    int KD_id;
 
    int nList;
 
    KDNode CheckedNodes[];
    int checked_nodes;
    KDNode List[];
 
    double x_min[], x_max[];
    boolean max_boundary[], min_boundary[];
    int n_boundary;
 
    public KDTree(int i)
    {
        Root = null;
        KD_id = 1;
        nList = 0;
        List = new KDNode[i];
        CheckedNodes = new KDNode[i];
        max_boundary = new boolean[i];
        min_boundary = new boolean[i];
        x_min = new double[i];
        x_max = new double[i];
    }
 
    public boolean add(double[] x)
    {
        if (nList >= 2000000 - 1)
            return false; // can't add more points
 
        if (Root == null)
        {
            Root = new KDNode(x, 0, x.length);
            Root.id = KD_id++;
            List[nList++] = Root;
        } else
        {
            KDNode pNode;
            if ((pNode = Root.Insert(x)) != null)
            {
                pNode.id = KD_id++;
                List[nList++] = pNode;
            }
        }
 
        return true;
    }
 
    public KDNode findNode(Double id)
    {
    	KDNode node = null;
    	
    	int i = 0;
    	KDNode next = List[i];
    	node = next;
    	do
    	{
             node = next;
             if (next.x[0] == id && next != null)//> next.x[split])
             {
              	node =  next;
               	
             }
             else
             {
               	if(next.Right != null)
               	{
               		next = next.Right;
                    if (next.x[0] == id)//> next.x[split])
                    {
                       	node =  next;
                       	
                    }
               	}
                else
                {
                   	if(next.Left != null)
                  	{
                   		next = next.Left;
                   		if ((next.x[0] == id))//> next.x[split])
                        {
                           	node = next;                    	
                        }
                    }
                    else
                    {
                       	continue;
                    }
                 }
                	 
              }
            i++;
            
    	}while(node.x[0] != id);
    	
    	return node;
    }
    
    public ArrayList similar (double id,int cantidad)
    {
    	ArrayList nodos_similares = new ArrayList();
    	int n = List.length;
    	KDNode[] temp = List;
    	KDNode aux;
    	for(int i = 0; i < n ; i++)
    	{	
    		aux = (KDNode)temp[i];
    		if(aux.x[0] == id)
    		{
    			nodos_similares.add(aux);
    			temp = (KDNode[]) remove(temp,i);
    			n-=1;
    		}
    	}
    	
    	return nodos_similares;
    }
    
    public  static Object[]  remove(KDNode[] arrayObjetos, int i) {
        KDNode[] nuevoArray = new KDNode[arrayObjetos.length - 1];
         if (i > 0){
               System.arraycopy(arrayObjetos, 0, nuevoArray, 0, i);
         }
         if (nuevoArray.length > i){
          System.arraycopy(arrayObjetos, i + 1, nuevoArray, i, nuevoArray.length - i);
         }
         return nuevoArray;
       }
    public KDNode find_nearest(double[] x, int dim)
    {
        if (Root == null)
            return null;
 
        checked_nodes = 0;
        KDNode parent = Root.FindParent(x);
        nearest_neighbour = parent;
        d_min = Root.distance2(x, parent.x, dim);
        
 
        if (parent.equal(x, parent.x, dim))
            return nearest_neighbour;
 
        search_parent(parent, x, dim);
        uncheck();
 
        return nearest_neighbour;
    }
 
    public void check_subtree(KDNode node, double[] x)
    {
        if ((node == null) || node.checked)
            return;
 
        CheckedNodes[checked_nodes++] = node;
        node.checked = true;
        set_bounding_cube(node, x);
 
        int dim = node.axis;
        double d = node.x[dim] - x[dim];
 
        if (d * d > d_min)
        {
            if (node.x[dim] > x[dim])
                check_subtree(node.Left, x);
            else
                check_subtree(node.Right, x);
        } else
        {
            check_subtree(node.Left, x);
            check_subtree(node.Right, x);
        }
    }
 
    public void set_bounding_cube(KDNode node, double[] x)
    {
        if (node == null)
            return;
        int d = 0;
        double dx;
        for (int k = 0; k < x.length; k++)
        {
            dx = node.x[k] - x[k];
            if (dx > 0)
            {
                dx *= dx;
                if (!max_boundary[k])
                {
                    if (dx > x_max[k])
                        x_max[k] = dx;
                    if (x_max[k] > d_min)
                    {
                        max_boundary[k] = true;
                        n_boundary++;
                    }
                }
            } else
            {
                dx *= dx;
                if (!min_boundary[k])
                {
                    if (dx > x_min[k])
                        x_min[k] = dx;
                    if (x_min[k] > d_min)
                    {
                        min_boundary[k] = true;
                        n_boundary++;
                    }
                }
            }
            d += dx;
            if (d > d_min)
                return;
 
        }
 
        if (d < d_min)
        {
            d_min = d;
            nearest_neighbour = node;
        }
    }
 
    public KDNode search_parent(KDNode parent, double[] x, int dim)
    {
        for (int k = 0; k < x.length; k++)
        {
            x_min[k] = x_max[k] = 0;
            max_boundary[k] = min_boundary[k] = false; //
        }
        n_boundary = 0;
 
        KDNode search_root = parent;
        while (parent != null && (n_boundary != dim * dim))
        {
            check_subtree(parent, x);
            search_root = parent;
            parent = parent.Parent;
        }
 
        return search_root;
    }
 
    public void uncheck()
    {
        for (int n = 0; n < checked_nodes; n++)
            CheckedNodes[n].checked = false;
    }
 
}
 
