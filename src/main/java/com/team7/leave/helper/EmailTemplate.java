package com.team7.leave.helper;

import javax.mail.Message;

import com.team7.leave.model.LeaveApplication;
import com.team7.leave.model.Overtime;

public class EmailTemplate {

	public String message;
//	public String status;
//	public String type;
//	public String manager;
//	public Overtime oT;
//	public LeaveApplication lA;

	public EmailTemplate(String status, String manager, Overtime OT) {

		message = "Your request for over-time claim has been " + status + " by " + manager + ". Request details: Hours claimed: " + OT.getHours() + ", Date: " + OT.getDateTime();
	}

	public EmailTemplate(String status, String manager, LeaveApplication LA) {

		message = "Your request for your leave application has been " + status + " by " + manager + ". Request details: From: " + LA.getDateFrom() + ", To: " + LA.getDateTo() + ", Reason: " + LA.getReason() + ", Manager's Comments: " + LA.getManagerComments();
	}

}
