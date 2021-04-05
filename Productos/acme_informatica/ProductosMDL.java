package acme_informatica;

public class ProductosMDL {

	private int prod_id;
	private String prod_marca;
	private String prod_modelo;
	private String prod_caracteristicas;
	private String prod_foto;
	private double prod_precio_venta;
	private int prod_stock_teorico;
	private String prod_observaciones;
	private int prod_cat_id;

	private String cat_categoria;

	public ProductosMDL(int prod_id, String prod_marca, String prod_modelo, String prod_caracteristicas,
			String prod_foto, double prod_precio_venta, int prod_stock_teorico, String prod_observaciones,
			int prod_cat_id) {
		super();
		this.prod_id = prod_id;
		this.prod_marca = prod_marca;
		this.prod_modelo = prod_modelo;
		this.prod_caracteristicas = prod_caracteristicas;
		this.prod_foto = prod_foto;
		this.prod_precio_venta = prod_precio_venta;
		this.prod_stock_teorico = prod_stock_teorico;
		this.prod_observaciones = prod_observaciones;
		this.prod_cat_id = prod_cat_id;
	}

	public ProductosMDL(int prod_id, String prod_marca, String prod_modelo, String prod_caracteristicas,
			String prod_foto, double prod_precio_venta, int prod_stock_teorico, String prod_observaciones,
			int prod_cat_id, String cat_categoria) {
		super();
		this.prod_id = prod_id;
		this.prod_marca = prod_marca;
		this.prod_modelo = prod_modelo;
		this.prod_caracteristicas = prod_caracteristicas;
		this.prod_foto = prod_foto;
		this.prod_precio_venta = prod_precio_venta;
		this.prod_stock_teorico = prod_stock_teorico;
		this.prod_observaciones = prod_observaciones;
		this.prod_cat_id = prod_cat_id;
		this.cat_categoria = cat_categoria;
	}

	public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_marca() {
		return prod_marca;
	}

	public void setProd_marca(String prod_marca) {
		this.prod_marca = prod_marca;
	}

	public String getProd_modelo() {
		return prod_modelo;
	}

	public void setProd_modelo(String prod_modelo) {
		this.prod_modelo = prod_modelo;
	}

	public String getProd_caracteristicas() {
		return prod_caracteristicas;
	}

	public void setProd_caracteristicas(String prod_caracteristicas) {
		this.prod_caracteristicas = prod_caracteristicas;
	}

	public String getProd_foto() {
		return prod_foto;
	}

	public void setProd_foto(String prod_foto) {
		this.prod_foto = prod_foto;
	}

	public double getProd_precio_venta() {
		return prod_precio_venta;
	}

	public void setProd_precio_venta(double prod_precio_venta) {
		this.prod_precio_venta = prod_precio_venta;
	}

	public int getProd_stock_teorico() {
		return prod_stock_teorico;
	}

	public void setProd_stock_teorico(int prod_stock_teorico) {
		this.prod_stock_teorico = prod_stock_teorico;
	}

	public String getProd_observaciones() {
		return prod_observaciones;
	}

	public void setProd_observaciones(String prod_observaciones) {
		this.prod_observaciones = prod_observaciones;
	}

	public int getProd_cat_id() {
		return prod_cat_id;
	}

	public void setProd_cat_id(int prod_cat_id) {
		this.prod_cat_id = prod_cat_id;
	}

	public String getCat_categoria() {
		return cat_categoria;
	}

	public void setCat_categoria(String cat_categoria) {
		this.cat_categoria = cat_categoria;
	}

	@Override
	public String toString() {
		return prod_marca + " - " + prod_modelo;
	}
	
}
