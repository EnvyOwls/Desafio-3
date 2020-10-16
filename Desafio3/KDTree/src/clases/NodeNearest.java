package clases;

public class NodeNearest {

	private KDNode nodo;
	private double distancia;
	
	
	public NodeNearest()
	{
		
	}
	
	public NodeNearest(KDNode nodo, double distancia)
	{
		this.nodo=nodo;
		this.distancia = distancia;
	}
	
	public KDNode getNodo() {
		return nodo;
	}
	public void setNodo(KDNode nodo) {
		this.nodo = nodo;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	
}