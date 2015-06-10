package com.brainfuse.contact.models;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.brainfuse.contact.service.ModelContants;

public class Relationship implements Serializable {

	private long relationshipId = ModelContants.DEFAULT_ID;
	private String type;
	private long toContactId;
	private long ownerId;
	
	public Relationship() {
		type=StringUtils.EMPTY;
		toContactId=ModelContants.DEFAULT_ID;
		ownerId = ModelContants.DEFAULT_ID;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ownerId ^ (ownerId >>> 32));
		result = prime * result
				+ (int) (toContactId ^ (toContactId >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relationship other = (Relationship) obj;
		if (ownerId != other.ownerId)
			return false;
		if (toContactId != other.toContactId)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	public long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public long getToContactId() {
		return toContactId;
	}

	public void setToContactId(long toContactId) {
		this.toContactId = toContactId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "Relationship [relationshipId=" + relationshipId + ", "
				+ (type != null ? "type=" + type + ", " : "") + "toContactId="
				+ toContactId + ", ownerId=" + ownerId + "]";
	}

	public boolean isNew(){
		return (relationshipId <= 0)? true:false;
	}
}
