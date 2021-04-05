package acme_informatica;

public class Albdet_MDL {
	// albd_id	albd_alb_id	albd_prod_id	albd_cantidad	albd_precio_entrada
	private int albd_id;
	private int albd_alb_id;
	private int albd_prod_id;
	private int albd_cantidad;
	private double albd_precio_entrada;
	private String prod_modelo;
	private String prod_marca;
	
	public Albdet_MDL(int albd_id, int albd_alb_id, int albd_prod_id, int albd_cantidad, double albd_precio_entrada,
			String prod_modelo, String prod_marca) {
		super();
		this.albd_id = albd_id;
		this.albd_alb_id = albd_alb_id;
		this.albd_prod_id = albd_prod_id;
		this.albd_cantidad = albd_cantidad;
		this.albd_precio_entrada = albd_precio_entrada;
		this.prod_modelo = prod_modelo;
		this.prod_marca = prod_marca;
	}
	public Albdet_MDL(int albd_id, int albd_alb_id, int albd_prod_id, int albd_cantidad, double albd_precio_entrada) {
		super();
		this.albd_id = albd_id;
		this.albd_alb_id = albd_alb_id;
		this.albd_prod_id = albd_prod_id;
		this.albd_cantidad = albd_cantidad;
		this.albd_precio_entrada = albd_precio_entrada;
	}
	public int getAlbd_id() {
		return albd_id;
	}
	public void setAlbd_id(int albd_id) {
		this.albd_id = albd_id;
	}
	public int getAlbd_alb_id() {
		return albd_alb_id;
	}
	public void setAlbd_alb_id(int albd_alb_id) {
		this.albd_alb_id = albd_alb_id;
	}
	public int getAlbd_prod_id() {
		return albd_prod_id;
	}
	public void setAlbd_prod_id(int albd_prod_id) {
		this.albd_prod_id = albd_prod_id;
	}
	public int getAlbd_cantidad() {
		return albd_cantidad;
	}
	public void setAlbd_cantidad(int albd_cantidad) {
		this.albd_cantidad = albd_cantidad;
	}
	public double getAlbd_precio_entrada() {
		return albd_precio_entrada;
	}
	public void setAlbd_precio_entrada(double albd_precio_entrada) {
		this.albd_precio_entrada = albd_precio_entrada;
	}
	public String getProd_modelo() {
		return prod_modelo;
	}
	public void setProd_modelo(String prod_modelo) {
		this.prod_modelo = prod_modelo;
	}
	public String getProd_marca() {
		return prod_marca;
	}
	public void setProd_marca(String prod_marca) {
		this.prod_marca = prod_marca;
	}
	
}
