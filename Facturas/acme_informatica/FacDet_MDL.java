package acme_informatica;

public class FacDet_MDL {
	//facd_id, facd_fac_id, facd_prod_id, facd_cantidad, facd_precio_venta
	private int facd_id;
	private int facd_fac_id;
	private int facd_prod_id;
	private int facd_cantidad;
	private double facd_precio_venta;
	
	private String cli_nombre;

	public FacDet_MDL(int facd_id, int facd_fac_id, int facd_prod_id, int facd_cantidad, double facd_precio_venta) {
		super();
		this.facd_id = facd_id;
		this.facd_fac_id = facd_fac_id;
		this.facd_prod_id = facd_prod_id;
		this.facd_cantidad = facd_cantidad;
		this.facd_precio_venta = facd_precio_venta;
	}

	public int getFacd_id() {
		return facd_id;
	}

	public void setFacd_id(int facd_id) {
		this.facd_id = facd_id;
	}

	public int getFacd_fac_id() {
		return facd_fac_id;
	}

	public void setFacd_fac_id(int facd_fac_id) {
		this.facd_fac_id = facd_fac_id;
	}

	public int getFacd_prod_id() {
		return facd_prod_id;
	}

	public void setFacd_prod_id(int facd_prod_id) {
		this.facd_prod_id = facd_prod_id;
	}

	public int getFacd_cantidad() {
		return facd_cantidad;
	}

	public void setFacd_cantidad(int facd_cantidad) {
		this.facd_cantidad = facd_cantidad;
	}

	public double getFacd_precio_venta() {
		return facd_precio_venta;
	}

	public void setFacd_precio_venta(double facd_precio_venta) {
		this.facd_precio_venta = facd_precio_venta;
	}

	public String getCli_nombre() {
		return cli_nombre;
	}

	public void setCli_nombre(String cli_nombre) {
		this.cli_nombre = cli_nombre;
	}

		
}
