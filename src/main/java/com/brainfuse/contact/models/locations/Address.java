package com.brainfuse.contact.models.locations;

public class Address {
	
	private long addressId;
	private String addressLine1;
	private String addressList2;
	private City city;
	private State state;
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
	public String getAddressList2() {
		return addressList2;
	}
	public void setAddressList2(String addressList2) {
		this.addressList2 = addressList2;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	
	public String getDefaultFormat(){
		return String.format("%s %s,%s, %s %d",addressLine1, addressList2,city.getName(),state.getShortName(),postcode);
	}
	
	@Override
	public String toString() {
		return String
				.format("Address [addressId=%s, addressLine1=%s, addressList2=%s, city=%s, state=%s, postcode=%s]",
						addressId, addressLine1, addressList2, city, state,
						postcode);
	}
}
