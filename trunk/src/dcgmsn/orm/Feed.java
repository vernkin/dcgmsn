package dcgmsn.orm;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="feeds")
public class Feed implements Serializable{

	private static final long serialVersionUID = -3967926228255574587L;
	
	@Id @GeneratedValue
	@Column
	private Long id;
	
	@Column (nullable=false)
	private String title;
	
	@Column (length = 1024)
	private String content;
	
	@ManyToOne
	private User from;
	
	@Column (name="create_date")
	private Date createDate;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getFrom() {
		return from;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}
	
	
}
