package com.brainfuse.playground;

import java.io.Serializable;

public class Icecube implements Serializable {
	
	private String engravedText;

	private long id;

	private double volume;
	public Icecube() {
	}
	public Icecube(long id, double volume, String text){
		this.id = id; this.volume = volume; this.engravedText=text;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Icecube other = (Icecube) obj;
		if (engravedText == null) {
			if (other.engravedText != null)
				return false;
		} else if (!engravedText.equals(other.engravedText))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String getEngravedText() {
		return engravedText;
	}

	public long getId() {
		return id;
	}

	public double getVolume() {
		return volume;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((engravedText == null) ? 0 : engravedText.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	public void setEngravedText(String engravedText) {
		this.engravedText = engravedText;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	@Override
	public String toString() {
		return "Icecube ["
				+ (engravedText != null ? "engravedText=" + engravedText + ", "
						: "") + "id=" + id + ", volume=" + volume + "]";
	}
}