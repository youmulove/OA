package com.thinkgem.jeesite.modules.oa.entity;

import java.io.Serializable;

public class ApprovalRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String approvalId;
	private String approvalType;
	private String approvalProcess;
	private String approvalCompany;
	private String approvalRank;
	private String approvalPerson;
	private String approvalRole;
	private String approvalStart;
	private String approvalEnd;
	private String approvalYuliu1;
	private String approvalYuliu2;
	private String approvalYuliu3;
	private String approvalYuliu4;
	private String approvalYuliu5;

	public String getApprovaleId() {
		return approvalId;
	}

	public void setApprovaleId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalProcess() {
		return approvalProcess;
	}

	public void setApprovalProcess(String approvalProcess) {
		this.approvalProcess = approvalProcess;
	}

	public String getApprovalCompany() {
		return approvalCompany;
	}

	public void setApprovalCompany(String approvalCompany) {
		this.approvalCompany = approvalCompany;
	}

	public String getApprovalRank() {
		return approvalRank;
	}

	public void setApprovalRank(String approvalRank) {
		this.approvalRank = approvalRank;
	}

	public String getApprovalPerson() {
		return approvalPerson;
	}

	public void setApprovalPerson(String approvalPerson) {
		this.approvalPerson = approvalPerson;
	}

	public String getApprovalRole() {
		return approvalRole;
	}

	public void setApprovalRole(String approvalRole) {
		this.approvalRole = approvalRole;
	}

	public String getApprovalStart() {
		return approvalStart;
	}

	public void setApprovalStart(String approvalStart) {
		this.approvalStart = approvalStart;
	}

	public String getApprovalEnd() {
		return approvalEnd;
	}

	public void setApprovalEnd(String approvalEnd) {
		this.approvalEnd = approvalEnd;
	}

	public String getApprovalYuliu1() {
		return approvalYuliu1;
	}

	public void setApprovalYuliu1(String approvalYuliu1) {
		this.approvalYuliu1 = approvalYuliu1;
	}

	public String getApprovalYuliu2() {
		return approvalYuliu2;
	}

	public void setApprovalYuliu2(String approvalYuliu2) {
		this.approvalYuliu2 = approvalYuliu2;
	}

	public String getApprovalYuliu3() {
		return approvalYuliu3;
	}

	public void setApprovalYuliu3(String approvalYuliu3) {
		this.approvalYuliu3 = approvalYuliu3;
	}

	public String getApprovalYuliu4() {
		return approvalYuliu4;
	}

	public void setApprovalYuliu4(String approvalYuliu4) {
		this.approvalYuliu4 = approvalYuliu4;
	}

	public String getApprovalYuliu5() {
		return approvalYuliu5;
	}

	public void setApprovalYuliu5(String approvalYuliu5) {
		this.approvalYuliu5 = approvalYuliu5;
	}

	public ApprovalRule() {
		super();
	}

	public ApprovalRule(String approvalId, String approvalType,
			String approvalProcess, String approvalCompany,
			String approvalRank, String approvalPerson, String approvalRole,
			String approvalStart, String approvalEnd, String approvalYuliu1,
			String approvalYuliu2, String approvalYuliu3,
			String approvalYuliu4, String approvalYuliu5) {
		super();
		this.approvalId = approvalId;
		this.approvalType = approvalType;
		this.approvalProcess = approvalProcess;
		this.approvalCompany = approvalCompany;
		this.approvalRank = approvalRank;
		this.approvalPerson = approvalPerson;
		this.approvalRole = approvalRole;
		this.approvalStart = approvalStart;
		this.approvalEnd = approvalEnd;
		this.approvalYuliu1 = approvalYuliu1;
		this.approvalYuliu2 = approvalYuliu2;
		this.approvalYuliu3 = approvalYuliu3;
		this.approvalYuliu4 = approvalYuliu4;
		this.approvalYuliu5 = approvalYuliu5;
	}

	@Override
	public String toString() {
		return "ApprovalRule [approvalId=" + approvalId + ", approvalType="
				+ approvalType + ", approvalProcess=" + approvalProcess
				+ ", approvalCompany=" + approvalCompany + ", approvalRank="
				+ approvalRank + ", approvalPerson=" + approvalPerson
				+ ", approvalRole=" + approvalRole + ", approvalStart="
				+ approvalStart + ", approvalEnd=" + approvalEnd
				+ ", approvalYuliu1=" + approvalYuliu1 + ", approvalYuliu2="
				+ approvalYuliu2 + ", approvalYuliu3=" + approvalYuliu3
				+ ", approvalYuliu4=" + approvalYuliu4 + ", approvalYuliu5="
				+ approvalYuliu5 + "]";
	}

}
