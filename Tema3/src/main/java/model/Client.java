package model;


/**
 * Clasa Client cu atributele idClient, nume , email ,varsta
 */
public class Client {
	private int idClient;
	private String nume;
	private String email;
	private int varsta;

	/**
	 * @param idClient id-ul clientului
	 * @param nume numele clientului
	 * @param email emailul clientului
	 * @param varsta varsta clientului
	 */
	public Client(int idClient, String nume, String email, int varsta) {
		super();
		this.idClient = idClient;
		this.nume = nume;
		this.email = email;
		this.varsta = varsta;
	}

	public Client(String nume, String email, int varsta) {
		super();
		this.nume = nume;
		this.email = email;
		this.varsta = varsta;
	}
	public Client(int idClient) {
		super();
		this.idClient = idClient;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVarsta() {
		return varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}

	/**
	 * @return afisare "prietenoasa" pentru un client
	 */
	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", name=" + nume  + ", email=" + email + ", age=" + varsta
				+ "]";
	}

}
