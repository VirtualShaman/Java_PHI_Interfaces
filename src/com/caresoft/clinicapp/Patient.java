package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class Patient extends User implements PHICompliantUser {

	
	private String firstName;
	private String lastName;
	// .. other personal identifying information .. //
	
	Physician primaryCarePhysician;
	ArrayList<Integer> currentPrescriptionsByRX;
	int providerCode;
	int memberNumber;
	PatientRecord medicalHistory;
	
	public Patient(String firstName, String lastName, Physician primaryCarePhysician,
			ArrayList<Integer> currentPrescriptionsByRX, int providerCode, int memberNumber,
			PatientRecord medicalHistory) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryCarePhysician = primaryCarePhysician;
		this.currentPrescriptionsByRX = currentPrescriptionsByRX;
		this.providerCode = providerCode;
		this.memberNumber = memberNumber;
		this.medicalHistory = medicalHistory;
	}
	
    
    public boolean requestAppointment(Date date, Physician doctor) {
        boolean successfullyAdded = false;
        // you see existing code to find and schedule an appointment
    	// (Leave as is for the assignment, no need to implement anything here.)
        return successfullyAdded;
    }
    void addChartNotes(String notes) {
        this.medicalHistory.getCharts().add(notes);
    }
    
	@Override
	public boolean assignPin(int pin) {
		if (pin < 10000 && pin > 999 && pin != 1234 && pin != 4321){
			this.setPin(pin);
			return true;
		}
		return false;
	}

	@Override
	public boolean isAuthorized(Integer confirmedAuthID) {
		if (getId() == confirmedAuthID) {
			return true;
		}
		return false;
	}
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

}
