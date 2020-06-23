package com.proj.trelloproj.web.entity;

public class MemberDetail {

	private int id;
	private int memberId;
	private int age;
	private String gender;
	private String birthday;
	private String profilePicture;
	
	public MemberDetail() {
	
	}

	public MemberDetail(int id, int memberId, int age, String gender, String birthday, String profilePicture) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.age = age;
		this.gender = gender;
		this.birthday = birthday;
		this.profilePicture = profilePicture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return "MemberDetail [id=" + id + ", memberId=" + memberId + ", age=" + age + ", gender=" + gender
				+ ", birthday=" + birthday + ", profilePicture=" + profilePicture + "]";
	}

}
