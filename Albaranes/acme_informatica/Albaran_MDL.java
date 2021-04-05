package acme_informatica;

import java.sql.Date;

public class Albaran_MDL {
	private int alb_id;
	private String alb_fecha_prov;
	private int alb_prov_id;
	private String alb_fecha_entrada;
	private String prov_nombre;

	
	public Albaran_MDL(int alb_id, String alb_fecha_prov, int alb_prov_id, String alb_fecha_entrada) {
		super();
		this.alb_id = alb_id;
		this.alb_fecha_prov = alb_fecha_prov;
		this.alb_prov_id = alb_prov_id;
		this.alb_fecha_entrada = alb_fecha_entrada;
	}
	public Albaran_MDL(int alb_id, String alb_fecha_prov, int alb_prov_id, String alb_fecha_entrada,
			String prov_nombre) {
		super();
		this.alb_id = alb_id;
		this.alb_fecha_prov = alb_fecha_prov;
		this.alb_prov_id = alb_prov_id;
		this.alb_fecha_entrada = alb_fecha_entrada;
		this.prov_nombre = prov_nombre;
	}
	public int getAlb_id() {
		return alb_id;
	}
	public void setAlb_id(int alb_id) {
		this.alb_id = alb_id;
	}
	public String getAlb_fecha_prov() {
		return alb_fecha_prov;
	}
	public void setAlb_fecha_prov(String alb_fecha_prov) {
		this.alb_fecha_prov = alb_fecha_prov;
	}
	public int getAlb_prov_id() {
		return alb_prov_id;
	}
	public void setAlb_prov_id(int alb_prov_id) {
		this.alb_prov_id = alb_prov_id;
	}
	public String getAlb_fecha_entrada() {
		return alb_fecha_entrada;
	}
	public void setAlb_fecha_entrada(String alb_fecha_entrada) {
		this.alb_fecha_entrada = alb_fecha_entrada;
	}
	public String getProv_nombre() {
		return prov_nombre;
	}
	public void setProv_nombre(String prov_nombre) {
		this.prov_nombre = prov_nombre;
	}
	
}
