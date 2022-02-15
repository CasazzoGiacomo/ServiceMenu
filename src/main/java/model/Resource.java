package model;

public class Resource {
	private int id;
	private String type;
	private String version;
	
	public Resource(int id, String type, String version) {
		setId(id); 
		setType(type);
		setVersion(version);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
