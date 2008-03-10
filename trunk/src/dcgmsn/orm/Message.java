package dcgmsn.orm;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Message Unit
 * @author DongQian
 *
 */
@Entity
@Table (name="messages")
public class Message implements Serializable{

	private static final long serialVersionUID = -6253071239552256147L;

	@Id @GeneratedValue
	@Column
	private Long id;
	
	@Column (nullable=false)
	private String title;
	
	@Column (length = 1024)
	private String content;
	
	@ManyToOne
	private User from;
	
	@ManyToOne
	private User to;
	
	@Column (name="create_date")
	private Date createDate;
	
	/** begin with 1 (min) to 7 (max)*/
	@Column
	private int level;
	
	@Column (name="is_read")
	private Boolean isRead = false;

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

	public void setTo(User to) {
		this.to = to;
	}

	public User getTo() {
		return to;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public Boolean getIsRead() {
		return isRead;
	}
	
	
}
