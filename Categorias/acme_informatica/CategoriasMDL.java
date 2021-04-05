package acme_informatica;

public class CategoriasMDL {

	private int cat_id;
	private String cat_categoria;

	public CategoriasMDL(String cat_categoria) {
		super();
		this.cat_categoria = cat_categoria;
	}

	public CategoriasMDL(int cat_id, String cat_categoria) {
		super();
		this.cat_id = cat_id;
		this.cat_categoria = cat_categoria;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_categoria() {
		return cat_categoria;
	}

	public void setCat_categoria(String cat_categoria) {
		this.cat_categoria = cat_categoria;
	}

}
