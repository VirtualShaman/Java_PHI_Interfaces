package com.caresoft.clinicapp;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class Physician extends User implements PHIAdminCompliant, PHICompliantUser {

	private HashSet<Patient> patients;
	private ArrayList<String> securityIncidents;
	
	public Physician(HashSet<Patient> patients, ArrayList<String> securityIncidents) {
		super();
		this.setPatients(patients);
		this.securityIncidents = securityIncidents;
	}

    
    // ... you see other existing member variables. ...
    
    // TO DO: Constructor
    
    public void prescribeRXTo(Patient patient, Integer rxNumber) {
        patient.currentPrescriptionsByRX.add(rxNumber);
    }
    
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
    // TO DO: Setters & Getters
	

	@Override
	public boolean assignPin(int pin) {
		if (pin >= 100000){
			this.setPin(pin);
			return true;
		}
		return false;
	}

	@Override
	public boolean isAuthorized(Integer confirmedAuthID) {
		if (getId() == confirmedAuthID) {
			authIncident();
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<String> reportSecurityIncidents() {
		for (String incident: securityIncidents) {
			System.out.println(incident);
		}
		return securityIncidents;
	}


	public HashSet<Patient> getPatients() {
		return patients;
	}


	public void setPatients(HashSet<Patient> patients) {
		this.patients = patients;
	}

}
