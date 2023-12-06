package com.socialmedia.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "smusers")
public class Users extends BasicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "userid", nullable = false)
	private String userId;

	@Column(length = 73, nullable = false)
	private String email;

	@Column(length = 256, nullable = false)
	@JsonIgnore
	private String password;

	@Column(name = "firstname", length = 73, nullable = false)
	private String firstName;

	@Column(name = "lastname", length = 73, nullable = false)
	private String lastName;

	@Column(length = 103)
	private String intro;

	@Column(length = 8)
	private String gender;

	@Column(length = 16)
	private int phoneNo;

	@Column(name = "hometown", length = 159)
	private String homeTown;

	@Column(name = "currentcity", length = 159)
	private String currentCity;

	@Column(name = "eduinstitution", length = 139)
	private String eduInstitution;

	@Column(name = "workplace", length = 139)
	private String workPlace;

	@Column(name = "profilephoto", length = 256)
	private String profilePhoto;

	@Column(name = "coverphoto", length = 256)
	private String coverPhoto;

	@Column(length = 32, nullable = false)
	private String role;

	@Column(name = "followercount", length = 299)
	private Integer followerCount;

	@Column(name = "followingcount", length = 299)
	private Integer followingCount;

	@Column(length = 299)
	private Boolean enabled;

	@Column(name = "accountverified", length = 8)
	private Boolean accountVerified;

	@Column(name = "emailverified", length = 8)
	private Boolean emailVerified;

	@Column(length = 299)
	private String country;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	@Column(name = "birthdate", length = 24)
	private Date birthDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "followuser", joinColumns = @JoinColumn(name = "followedid"), inverseJoinColumns = @JoinColumn(name = "followerid"))
	private List<Users> followUser;

	@Override
	public int hashCode() {
		return Objects.hash(email, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(userId, other.userId);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getEduInstitution() {
		return eduInstitution;
	}

	public void setEduInstitution(String eduInstitution) {
		this.eduInstitution = eduInstitution;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getCoverPhoto() {
		return coverPhoto;
	}

	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getFollowerCount() {
		return followerCount;
	}

	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}

	public Integer getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(Integer followingCount) {
		this.followingCount = followingCount;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountVerified() {
		return accountVerified;
	}

	public void setAccountVerified(Boolean accountVerified) {
		this.accountVerified = accountVerified;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Users> getFollowUser() {
		return followUser;
	}

	public void setFollowUser(List<Users> followUser) {
		this.followUser = followUser;
	}

	public Users(Date createOn, Date updateOn, String createBy, String updateBy, Integer id, String userId,
			String email, String password, String firstName, String lastName, String intro, String gender, int phoneNo,
			String homeTown, String currentCity, String eduInstitution, String workPlace, String profilePhoto,
			String coverPhoto, String role, Integer followerCount, Integer followingCount, Boolean enabled,
			Boolean accountVerified, Boolean emailVerified, String country, Date birthDate, List<Users> followUser) {
		super(createOn, updateOn, createBy, updateBy);
		this.id = id;
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.intro = intro;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.homeTown = homeTown;
		this.currentCity = currentCity;
		this.eduInstitution = eduInstitution;
		this.workPlace = workPlace;
		this.profilePhoto = profilePhoto;
		this.coverPhoto = coverPhoto;
		this.role = role;
		this.followerCount = followerCount;
		this.followingCount = followingCount;
		this.enabled = enabled;
		this.accountVerified = accountVerified;
		this.emailVerified = emailVerified;
		this.country = country;
		this.birthDate = birthDate;
		this.followUser = followUser;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(Date createOn, Date updateOn, String createBy, String updateBy) {
		super(createOn, updateOn, createBy, updateBy);
		// TODO Auto-generated constructor stub
	}

}
