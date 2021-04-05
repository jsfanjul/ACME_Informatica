package acme_informatica;

public class ProveedoresMDL {
	
	private int prov_id;
	private String prov_nombre;
	private String prov_cif;
	private String prov_tipo_via;
	private String prov_localidad;
	private String prov_codigo_postal;
	private String prov_provincia;
	private String prov_pais;
	private String prov_contacto;
	private String prov_telefono;
	
	public ProveedoresMDL(int prov_id, String prov_nombre, String prov_cif, String prov_tipo_via, String prov_localidad,
			String prov_codigo_postal, String prov_provincia, String prov_pais, String prov_contacto,
			String prov_telefono) {
		super();
		this.prov_id = prov_id;
		this.prov_nombre = prov_nombre;
		this.prov_cif = prov_cif;
		this.prov_tipo_via = prov_tipo_via;
		this.prov_localidad = prov_localidad;
		this.prov_codigo_postal = prov_codigo_postal;
		this.prov_provincia = prov_provincia;
		this.prov_pais = prov_pais;
		this.prov_contacto = prov_contacto;
		this.prov_telefono = prov_telefono;
	}

	public int getProv_id() {
		return prov_id;
	}

	public void setProv_id(int prov_id) {
		this.prov_id = prov_id;
	}

	public String getProv_nombre() {
		return prov_nombre;
	}

	public void setProv_nombre(String prov_nombre) {
		this.prov_nombre = prov_nombre;
	}

	public String getProv_cif() {
		return prov_cif;
	}

	public void setProv_cif(String prov_cif) {
		this.prov_cif = prov_cif;
	}

	public String getProv_tipo_via() {
		return prov_tipo_via;
	}

	public void setProv_tipo_via(String prov_tipo_via) {
		this.prov_tipo_via = prov_tipo_via;
	}

	public String getProv_localidad() {
		return prov_localidad;
	}

	public void setProv_localidad(String prov_localidad) {
		this.prov_localidad = prov_localidad;
	}

	public String getProv_codigo_postal() {
		return prov_codigo_postal;
	}

	public void setProv_codigo_postal(String prov_codigo_postal) {
		this.prov_codigo_postal = prov_codigo_postal;
	}

	public String getProv_provincia() {
		return prov_provincia;
	}

	public void setProv_provincia(String prov_provincia) {
		this.prov_provincia = prov_provincia;
	}

	public String getProv_pais() {
		return prov_pais;
	}

	public void setProv_pais(String prov_pais) {
		this.prov_pais = prov_pais;
	}

	public String getProv_contacto() {
		return prov_contacto;
	}

	public void setProv_contacto(String prov_contacto) {
		this.prov_contacto = prov_contacto;
	}

	public String getProv_telefono() {
		return prov_telefono;
	}

	public void setProv_telefono(String prov_telefono) {
		this.prov_telefono = prov_telefono;
	}

	@Override
	public String toString() {
		return "Nombre: " + prov_nombre + "\n" + "CIF: " + prov_cif + "\n" + "Tipo de vía: " + prov_tipo_via + "\n" +
			   "Localidad: " + prov_localidad + "\n" + "Código postal: " + prov_codigo_postal + "\n" + "Provincia: " 
			   + prov_provincia + "\n" + "País: " + prov_pais + "\n" + "Contacto: " + prov_contacto + "\n" +
			   "Teléfono: " + prov_telefono + "";
	}
	
}
