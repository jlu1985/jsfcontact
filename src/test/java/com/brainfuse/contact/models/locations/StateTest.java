package com.brainfuse.contact.models.locations;

import static org.junit.Assert.*;

import org.junit.Test;

import com.brainfuse.contact.models.locations.State.StateBuilder;


public class StateTest {
	@Test
	public void equals_a_and_b(){
		State ny1 = buildNewYorkState();
		State ny2 = buildNewYorkState();
		assertTrue(ny1.equals(ny2));
	}
	
	@Test
	public void equals_a_and_b_inverse(){
		
		State ny1 = buildNewYorkState();
		State ny2 = buildNewYorkState();
		assertTrue(ny1.equals(ny2));
	}
	
	private State buildNewYorkState(){
		return  StateBuilder.getInstance().name("New York").shortName("NY").build();
	}
		
}
