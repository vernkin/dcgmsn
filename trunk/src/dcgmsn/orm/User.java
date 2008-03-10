package dcgmsn.orm;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="users") 
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
    @Column
	private Long id;
	
	/** MSN account */
	@Column (name = "account",length=80,unique = true)
	private String account;
	
	@Column (name="is_admin")
	private Boolean isAdmin = false;
	
	
	@Column (name = "email",length=50)
	private String email;
	
	@Column (name = "nickname",length=50)
	private String nickname;
	
	@Column (length=1024)
	private String description;
	
	@Column (name = "regist_date")
	private Date registDate;
	
	@Column (name = "last_login_date")
	private Date lastLoginDate;
	
	public User(){
	}
	
	public User(String account){
		setAccount(account);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}
