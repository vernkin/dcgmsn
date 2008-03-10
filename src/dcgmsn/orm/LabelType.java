package dcgmsn.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="label_types") 
public class LabelType implements Serializable{

	private static final long serialVersionUID = 2450881513516463329L;
	
	@Id @GeneratedValue
    @Column
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Column
	private String name;
	
	@Column (name="bg_color")
	private String bgColor;
	
	@Column (name="text_color")
	private String textColor;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getTextColor() {
		return textColor;
	}
}
