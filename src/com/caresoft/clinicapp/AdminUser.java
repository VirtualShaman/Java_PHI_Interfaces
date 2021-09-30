package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements PHIAdminCompliant, PHICompliantUser {
	
	private Integer employeeID;
	private String role;
	private ArrayList<String> securityIncidents;

	public AdminUser(Integer employeeID, String role) {
		super();
		this.employeeID = employeeID;
		this.role = role;
		this.securityIncidents = new ArrayList<>();
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

}
