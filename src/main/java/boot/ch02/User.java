package boot.ch02;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "tuser")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long JDOIDX;

	@Column(name = "loginname")
	private String loginName;
	private String password;
	private String name;
	@Column(name = "createdate")
	@Temporal(TemporalType.DATE)
	private Date createDate;

	public Long getJDOIDX() {
		return JDOIDX;
	}

	public void setJDOIDX(Long jDOIDX) {
		JDOIDX = jDOIDX;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [JDOIDX=" + JDOIDX + ", loginName=" + loginName
				+ ", password=" + password + ", name=" + name + ", createDate="
				+ createDate + "]";
	}
}
