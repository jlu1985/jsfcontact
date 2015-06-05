package com.brainfuse.contact.models;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Contact implements Serializable{
	private static final Logger logger = LoggerFactory.getLogger(Contact.class);
	private long contactId;
	private String personName;
	private String email;
	private String phoneNumber;

	public long getContactId() {
		return contactId;
	}

	public String getPersonName() {
		return personName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return String
				.format("Contact [contactId=%s, personName=%s, email=%s, phoneNumber=%s]",
						contactId, personName, email, phoneNumber);
	}
	
	@PostConstruct
	public void init(){
		logger.debug("{} is created by the container",this);
	}
	
	public boolean isNewContact(){
			if (getContactId() <= 0)
				return true;
			else
				return false;
	}
}

