package com.socialmedia.modal;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class UpdateUserDetailsModal {

	@NotEmpty
	@Size(max = 30, min = 3)
	private String firstName;

	@Size(max = 30, min = 1)
	private String lastName;

	@Size(max = 100)
	private String intro;

	private int phoneNo;

	@Size(max = 7)
	private String gender;

	@Size(max = 100)
	private String homeTown;

	@Size(max = 100)
	private String currentCity;

	@Size(max = 100)
	private String eduInstitution;

	@Size(max = 100)
	private String workPlace;

	private String country;

	@Past
	@DateTimeFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	private Date birthDate;

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

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public UpdateUserDetailsModal(@NotEmpty @Size(max = 30, min = 3) String firstName,
			@Size(max = 30, min = 1) String lastName, @Size(max = 100) String intro,
			@Digits(fraction = 0, integer = 16) int phoneNo, @Size(max = 7) String gender,
			@Size(max = 100) String homeTown, @Size(max = 100) String currentCity,
			@Size(max = 100) String eduInstitution, @Size(max = 100) String workPlace, String country,
			@Past Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.intro = intro;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.homeTown = homeTown;
		this.currentCity = currentCity;
		this.eduInstitution = eduInstitution;
		this.workPlace = workPlace;
		this.country = country;
		this.birthDate = birthDate;
	}

	public UpdateUserDetailsModal() {
		super();
		// TODO Auto-generated constructor stub
	}

}
