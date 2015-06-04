package com.brainfuse.contact.models;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Contact{

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
}

