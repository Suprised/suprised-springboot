package boot.ch04;

import java.util.Date;

public class CreditBillBean {

	private Long JDOIDX;

	private String loginName;
	private String password;
	private String name;
	private Date createDate;
	private String createDateStr;

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

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	@Override
	public String toString() {
		return "CreditBillBean [JDOIDX=" + JDOIDX + ", loginName=" + loginName
				+ ", password=" + password + ", name=" + name + ", createDate="
				+ createDate + ", createDateStr=" + createDateStr + "]";
	}
}
