package dcgmsn.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="programmes")
public class Programme implements Serializable{

	private static final long serialVersionUID = -8691618021676659835L;
	
	@Id @GeneratedValue
	@Column
	private Long id;
	
	@Column (nullable=false)
	private String name;
	
	@Column (nullable=false)
	private String path;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
	
	public String toString(){
		return name;
	}
}
