package com.LIM.web.vo;

public class MemberVO {
	private String id, pw, name, address;
	private int age;

	public MemberVO(String id, String pw, String name, String address, int age) throws Exception {
		this(id,pw,name);
		setAddress(address);
		setAge(age);
	}

	public MemberVO(String id, String pw, String name) throws Exception {
		super();
		setId(id);
		setPw(pw);
		setName(name);
	}

	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws Exception {
		if(id != null) {
			this.id = id;
		} else {
			this.id = null;
			//throw new Exception("Check Input ID");
		}
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) throws Exception {
		if(pw != null) {
			this.pw = pw;
		} else {
			this.pw = null;
			//throw new Exception("Check Input Password, Default Password = 0000");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		if(name != null) {
			this.name = name;
		} else {
			this.name = null;
			//throw new Exception("Check Input Name");
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws Exception {
		if(address != null) {
			this.address = address;
		} else {
			this.address = null;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) throws Exception {
		if(age>0 && age<200) {
			this.age = age;
		} else {
			this.age = 0;
		}
		
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", age=" + age + "]";
	}

}
