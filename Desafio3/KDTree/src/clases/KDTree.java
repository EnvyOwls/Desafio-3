package clases;

import java.util.ArrayList;

public class KDTree 
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
	    public KDNode find(int id)
	    {
	    	KDNode temp;
	    	
	    	int i;
	    	
	    	for (i=0;i<List.length;i++)
	    	{
	    		temp=List[i];
	    		if (temp.id==id)
	    		{
	    			return temp;
	    		}
	    	}
	    	return null;
	    }
	    public boolean add(double[] x, int id, String track_name,String currency, String ver, int ipad,int vpp)
	    {
	    	 if (nList >= 2000000 - 1)
		            return false; // can't add more points
		 
		        if (Root == null)
		        {
		            Root = new KDNode(x, 0, x.length,id,track_name,currency,ver,ipad,vpp);
		            //Root.id = KD_id++;
		            List[nList++] = Root;
		        } else
		        {
		            KDNode pNode;
		            if ((pNode = Root.Insert(x,id,track_name,currency,ver,ipad,vpp)) != null)
		            {
		               // pNode.id = KD_id++;
		                List[nList++] = pNode;
		            }
		        }
		 
		        return true;
	    }
	 
	    public KDNode findNode(int id)
	    {
	    	KDNode next = List[0],node = null;
	    	
	    	int i = 0;

	    	do
	    	{
	             next = List[i];
	             node = next;
	             if (next.id == id && next != null)//> next.x[split])
	             {
	              	node =  next;
	               	
	             }
	             else
	             {
	               	if(next.Right != null)
	               	{
	               		next = next.Right;
	                    if (next.id == id)//> next.x[split])
	                    {
	                       	node =  next;
	                       	
	                    }
	               	}
	                else
	                {
	                   	if(next.Left != null)
	                  	{
	                   		next = next.Left;
	                   		if ((next.id == id))//> next.x[split])
	                        {
	                           	node = next;                    	
	                        }
	                    }
	                    else
	                    {
	                    	i++;
	                       	continue;
	                    }
	                 }
	                	 
	              }
	             i++;
	            
	    	}while(node.id != id);
	    	
	    	return node;
	    }
	    
	    @SuppressWarnings("unchecked")
		public ArrayList similar (int id,int cantidad)
	    {
	    	ArrayList nodos_similares = new ArrayList();
	    	ArrayList<KDNode> aux = null;
	    	NodeNearest temp = new NodeNearest();
	    	
	    	KDNode node = findNode(id),auxi = null;
	    	int n = node.x.length;
	    	KDNode padre = node.FindParent(node.x);
	    	KDNode hijoI = node.Left, hijoD = node.Right, padreI,padreD;
	    	double dist;// = node.distance(node.x, node.FindParent(node.x).x, node.x.length);
	    	
	    	for (int i = 0 ; i < List.length ; i++)
	    	{
	    		temp = new NodeNearest();
	    		if(i== 7195)
	    			System.out.println(i);
	    		i--;
	    		i++;
	    		auxi = (KDNode)List[i];
	    		if(node.equals(auxi) || auxi == null)
	    			continue;
	    		dist = node.distance2(node.x, auxi.x , n);
	    		temp.setNodo(auxi);
	    		temp.setDistancia(dist);
	    		nodos_similares.add(temp);
	    	}
	    	return nodos_similares;
	    }
	    
	    public ArrayList simil(double x[])
	    {
	    	ArrayList nodos=new ArrayList();
	    	ArrayList dist=new ArrayList();
	    	
	    	//AQUI DA IGUAL SI ENCUENTRA UN NODO IGUAL AL VECTOR INGRESADO
	    	int n=x.length;
	    	int i;
	    	//A RECORRER
	    	KDNode temp;
	    	for (i=0;i<List.length;i++)
	    	{
	    		temp=List[i];
	    		
	    		//SI TEMP NO ESTA VACIO
	    		if (temp!=null)
	    		{
	    			double distancia=temp.distance2(x, temp.x, n);
	    			//SE GUARDA EL NODO Y LA DISTANCIA
	    			nodos.add(temp);
	    			dist.add(distancia);
	    		}
	    	}
	    	//UNA VEZ QUE CALCULA LA DISTANCIA DE TODOS LOS NODOS EN COMPARACION AL INGRESADO, SE ORDENA CON EL METODO MAS FACIL QUE CONOZCO DE IMPLEMENTAR
	    	
	    	nodos=bubbleSort(dist, nodos);
	    	return nodos;
	    }
	    public ArrayList simil(int id,int cantidad)
	    {
	    	ArrayList nodos=new ArrayList();
	    	ArrayList dist=new ArrayList();
	    	
	    	KDNode temp;
	    	int i;
	    	
	    	//AQUI LOS DETALLES IMPORTANTES
	    	KDNode nodo=find(id);
	    	int n=nodo.x.length;
	    	//A RECORRER
	    	for (i=0;i<List.length;i++)
	    	{
	    		temp=List[i];
	    		
	    		//SI TEMP NO ES EL MISMO NODO
	    		if (temp!=null && !temp.equals(nodo))
	    		{
	    			double distancia=nodo.distance2(nodo.x, temp.x, n);
	    			//SE GUARDA EL NODO Y LA DISTANCIA
	    			nodos.add(temp);
	    			dist.add(distancia);
	    		}
	    		
	    	}
	    	//UNA VEZ QUE CALCULA LA DISTANCIA DE TODOS LOS NODOS EN COMPARACION AL INGRESADO, SE ORDENA CON EL METODO MAS FACIL QUE CONOZCO DE IMPLEMENTAR
	    	
	    	nodos=bubbleSort(dist, nodos);
	    	return nodos;
	    }
	    
	    public  ArrayList bubbleSort(ArrayList distancia,ArrayList nodos)
		{
			 boolean sorted=false;
			 int i;
			 double aux;
			 Object o;
			 while (!sorted)
			 {
				 sorted = true;
				 //ES UN BUBBLESORT NORMAL USANDO LA DISTANCIA COMO REFERENCIA Y APLICANDO LOS MISMOS CAMBIOS DE ESA LISTA A LA LISTA DE NODOS
				 
				 for (i=0;i<distancia.size()-1;i++)
				 {
					 //SI I>I+1 CAMBIAN DE POSICION
					 if ((double)distancia.get(i)>(double)distancia.get(i+1))
					 {
						 //SE ORDENA POR DISTANCIA
						 aux=(double)distancia.get(i);
						 distancia.set(i,distancia.get(i + 1));
						 distancia.set(i + 1,aux);
						 
						 //SE REPLICA EN LA LISTA DE NODOS
						 o=(KDNode)nodos.get(i);
						 nodos.set(i,nodos.get(i + 1));
						 nodos.set(i + 1,o);
						 sorted=false;
					 }
				 }
			 }
			 
			 return nodos;
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
