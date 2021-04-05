package acme_informatica;

public class ClientesMDL {
	
	private int cli_id;
	private String cli_nombre;
	private String cli_cif;
	private String cli_tipo_via;
	private String cli_localidad;
	private String cli_codigo_postal;
	private String cli_provincia;
	private String cli_pais;
	private String cli_contacto;
	private String cli_telefono;
	
	public ClientesMDL(int cli_id, String cli_nombre, String cli_cif, String cli_tipo_via, String cli_localidad,
			String cli_codigo_postal, String cli_provincia, String cli_pais, String cli_contacto, String cli_telefono) {
		super();
		this.cli_id = cli_id;
		this.cli_nombre = cli_nombre;
		this.cli_cif = cli_cif;
		this.cli_tipo_via = cli_tipo_via;
		this.cli_localidad = cli_localidad;
		this.cli_codigo_postal = cli_codigo_postal;
		this.cli_provincia = cli_provincia;
		this.cli_pais = cli_pais;
		this.cli_contacto = cli_contacto;
		this.cli_telefono = cli_telefono;
	}

	public int getCli_id() {
		return cli_id;
	}

	public void setCli_id(int cli_id) {
		this.cli_id = cli_id;
	}

	public String getCli_nombre() {
		return cli_nombre;
	}

	public void setCli_nombre(String cli_nombre) {
		this.cli_nombre = cli_nombre;
	}

	public String getCli_cif() {
		return cli_cif;
	}

	public void setCli_cif(String cli_cif) {
		this.cli_cif = cli_cif;
	}

	public String getCli_tipo_via() {
		return cli_tipo_via;
	}

	public void setCli_tipo_via(String cli_tipo_via) {
		this.cli_tipo_via = cli_tipo_via;
	}

	public String getCli_localidad() {
		return cli_localidad;
	}

	public void setCli_localidad(String cli_localidad) {
		this.cli_localidad = cli_localidad;
	}

	public String getCli_codigo_postal() {
		return cli_codigo_postal;
	}

	public void setCli_codigo_postal(String cli_codigo_postal) {
		this.cli_codigo_postal = cli_codigo_postal;
	}

	public String getCli_provincia() {
		return cli_provincia;
	}

	public void setCli_provincia(String cli_provincia) {
		this.cli_provincia = cli_provincia;
	}

	public String getCli_pais() {
		return cli_pais;
	}

	public void setCli_pais(String cli_pais) {
		this.cli_pais = cli_pais;
	}

	public String getCli_contacto() {
		return cli_contacto;
	}

	public void setCli_contacto(String cli_contacto) {
		this.cli_contacto = cli_contacto;
	}

	public String getCli_telefono() {
		return cli_telefono;
	}

	public void setCli_telefono(String cli_telefono) {
		this.cli_telefono = cli_telefono;
	}

	@Override
	public String toString() {
		 return "Nombre: " + cli_nombre + "\n" + "CIF: " + cli_cif + "\n" + "Tipo de vía: " + cli_tipo_via + "\n" +
				"Localidad: " + cli_localidad + "\n" + "Código postal: " + cli_codigo_postal + "\n" + "Provincia: " 
				+ cli_provincia + "\n" + "País: " + cli_pais + "\n" + "Contacto: " + cli_contacto + "\n" +
				"Teléfono: " + cli_telefono + "";
	}
	
}
