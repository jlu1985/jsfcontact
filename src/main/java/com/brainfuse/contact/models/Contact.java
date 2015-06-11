package com.brainfuse.contact.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.models.locations.Address;
import com.brainfuse.contact.service.ModelContants;

public class Contact implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Contact.class);

	private long contactId;
	private String email;
	private String personName;
	private String phoneNumber;

	private List<Address> addresses;
	private List<Relationship> relationships;
	
	public Contact() {
		contactId = ModelContants.DEFAULT_ID;
		addresses = new ArrayList<Address>();
		relationships = new ArrayList<Relationship>();
	}
	public void addAddress(Address addr) {
		addresses.add(addr);
	}
	
	public void removeLastAddress(){
		int size = addresses.size();
		if(size>0){
			addresses.remove(size-1);
		}
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public long getContactId() {
		return contactId;
	}

	public String getEmail() {
		return email;
	}

	public String getPersonName() {
		return personName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@PostConstruct
	public void init() {
		logger.debug("{} is created by the container", this);
	}

	public boolean isNewContact() {
		if (getContactId() <= 0)
			return true;
		else
			return false;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}

	public void addRelationship(){
		Relationship r = new Relationship();
		r.setOwnerId(getContactId());
		relationships.add(r);
	}

	public void removeRelationship(Relationship rel){
			relationships.remove(rel);
	}
	
	public void removeAddress(Address addr){
		logger.debug("removing address {}", addr);
		logger.debug("addresses before removal {}", addresses);
		addresses.remove(addr);
		logger.debug("addresses after removal {}", addresses);
	}
	@Override
	public String toString() {
		return "Contact [contactId="
				+ contactId
				+ ", "
				+ (email != null ? "email=" + email + ", " : "")
				+ (personName != null ? "personName=" + personName + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", "
						: "")
				+ (addresses != null ? "addresses=" + addresses + ", " : "")
				+ (relationships != null ? "relationships=" + relationships
						: "") + "]";
	}
}