package acme_informatica;

public class ProveedoresComboMDL {

	private int prov_id;
	private String prov_nombre;
	
	public ProveedoresComboMDL(int prov_id, String prov_nombre) {
		super();
		this.prov_id = prov_id;
		this.prov_nombre = prov_nombre;
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

	@Override
	public String toString() {
		return prov_nombre;
	}
	
}
