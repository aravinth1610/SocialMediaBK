package com.socialmedia.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicEntity {

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	@Column(name = "createon", length = 24)
	private Date createOn;
	
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
	@Column(name = "updateon", length = 24)
	private Date updateOn;
	
	@Column(name = "createdby", length = 185)
	private String createBy;
	
	@Column(name = "updateby", length = 185)
	private String updateBy;

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public Date getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(Date updateOn) {
		this.updateOn = updateOn;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public BasicEntity(Date createOn, Date updateOn, String createBy, String updateBy) {
		super();
		this.createOn = createOn;
		this.updateOn = updateOn;
		this.createBy = createBy;
		this.updateBy = updateBy;
	}

	public BasicEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
