package org.jasig.cas.client.model;

/**
 * 客户表
 * 
 * @date 2015-7-13
 * @since 1.0
 */
public class User implements java.io.Serializable {
	private static final long serialVersionUID = -2636778397956119662L;

	/**
	 * 客户编号
	 */
	private Integer uid;
	/**
	 * 登录名
	 */
	private String loginName;
	/**
	 * 名字
	 */
	private String uname;
	/**
	 * 登陆密码
	 */
	private String pwd;
	
	/**
     * 修改密码时的旧密码
     */
	private String oldPwd;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * 电话号码
	 */
	private String phoneNum;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
     * 创建时间
     */
    private String updateTime;

	/**
	 * 状态 1 有效状态 0 无效状态
	 */
	private Integer state;
	
	/**
	 * 是否超级管理员
	 */
	private Integer isAdmin;

	/** 用户所有角色(组)信息 **/
	private String roles;

    public Integer getUid()
    {
        return uid;
    }

    public void setUid(Integer uid)
    {
        this.uid = uid;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    public String getUname()
    {
        return uname;
    }

    public void setUname(String uname)
    {
        this.uname = uname;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

    public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getOldPwd()
    {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd)
    {
        this.oldPwd = oldPwd;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
