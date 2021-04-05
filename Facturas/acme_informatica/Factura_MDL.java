package acme_informatica;

import java.sql.Date;

public class Factura_MDL {

	private int fac_id;
	private String fac_fecha_venta;
	private int fac_cli_id;
	private String fac_fecha_salida;
	private String cli_nombre;

	public Factura_MDL(int fac_id, String fac_fecha_venta, int fac_cli_id, String fac_fecha_salida) {
		super();
		this.fac_id = fac_id;
		this.fac_fecha_venta = fac_fecha_venta;
		this.fac_cli_id = fac_cli_id;
		this.fac_fecha_salida = fac_fecha_salida;
	}

	public Factura_MDL(int fac_id, String fac_fecha_venta, int fac_cli_id, String fac_fecha_salida, String cli_nombre) {
		super();
		this.fac_id = fac_id;
		this.fac_fecha_venta = fac_fecha_venta;
		this.fac_cli_id = fac_cli_id;
		this.fac_fecha_salida = fac_fecha_salida;
		this.cli_nombre = cli_nombre;
	}

	public int getFac_id() {
		return fac_id;
	}

	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}

	public String getFac_fecha_venta() {
		return fac_fecha_venta;
	}

	public void setFac_fecha_venta(String fac_fecha_venta) {
		this.fac_fecha_venta = fac_fecha_venta;
	}

	public int getFac_cli_id() {
		return fac_cli_id;
	}

	public void setFac_cli_id(int fac_cli_id) {
		this.fac_cli_id = fac_cli_id;
	}

	public String getFac_fecha_salida() {
		return fac_fecha_salida;
	}

	public void setFac_fecha_salida(String fac_fecha_salida) {
		this.fac_fecha_salida = fac_fecha_salida;
	}

	public String getCli_nombre() {
		return cli_nombre;
	}

	public void setCli_nombre(String cli_nombre) {
		this.cli_nombre = cli_nombre;
	}

	public static void main(String[] args) {

	}

}
