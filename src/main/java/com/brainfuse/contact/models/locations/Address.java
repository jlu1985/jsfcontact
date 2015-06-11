package com.brainfuse.contact.models.locations;

import java.io.Serializable;

public class Address implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -729031563940347156L;
	private long addressId;
	private String addressLine1;
	private String addressLine2;
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	private String city;
	private String state;
	
	private int postcode;
	
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
	public String getDefaultFormat(){
		return String.format("%s %s,%s, %s %d",addressLine1, addressLine2,city,state,postcode);
	}
	
	@Override
	public String toString() {
		return String
				.format("Address [addressId=%s, addressLine1=%s, addressLine2=%s, city=%s, state=%s, postcode=%s]",
						addressId, addressLine1, addressLine2, city, state,
						postcode);
	}
}
