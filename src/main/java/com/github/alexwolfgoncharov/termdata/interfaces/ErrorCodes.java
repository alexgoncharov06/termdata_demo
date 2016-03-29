package com.github.alexwolfgoncharov.termdata.interfaces;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "errorcodes")
public class ErrorCodes {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	@Column(name = "BASE_ID")
	String baseId;
	@Column(name = "id_temperature")
	long idOfThermData;
	@Column(name = "code_error")
	String codeerror;
	@Column(name = "error_text")
	String errorText;
	@Column(name = "time")
	Timestamp time;
	@OneToMany(cascade =  CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "error_id")
	private List<ErrorHistory> error_history;
	@Column(name = "Closed")
	boolean closed;
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "errorcodes_temperature", 
			joinColumns = {@JoinColumn(name = "error_id", referencedColumnName="ID")}, 
			inverseJoinColumns = {@JoinColumn(name = "temper_id", referencedColumnName="ID")})
	private Set<ThermData> termData;
	
	

	public ErrorCodes() {

	}

	public ErrorCodes(String baseId, String codeerror, long idOfThermData) {
		super();
		this.idOfThermData = idOfThermData;
		this.baseId = baseId;
		this.codeerror = codeerror;
		this.error_history = new ArrayList<ErrorHistory>();
		this.closed = false;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBaseId() {
		return baseId;
	}

	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}

	public String getCodeerror() {
		return codeerror;
	}

	public void setCodeerror(String codeerror) {
		this.codeerror = codeerror;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public List<ErrorHistory> getError_history() {
		return error_history;
	}

	public void setError_history(List<ErrorHistory> error_history) {
		this.error_history = error_history;
	}

	@Override
	public String toString() {
		return "ErrorCodes [ID=" + ID + ", baseId=" + baseId
				+ ", idOfThermData=" + idOfThermData + ", codeerror="
				+ codeerror + ", errorText=" + errorText + ", time=" + time
				+ ", closed=" + closed
				+ ", termData=" + termData + "]";
	}

	public boolean equals(ErrorCodes obj) {
		if ((this.baseId.equals(obj.getBaseId()))
				&& (this.codeerror.equals(obj.getCodeerror()))
				&& this.closed == obj.isClosed()) {
			return true;
		} else
			return false;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public long getIdOfThermData() {
		return idOfThermData;
	}

	public void setIdOfThermData(long idOfThermData) {
		this.idOfThermData = idOfThermData;
	}

	public void setTermData(ThermData termD) {
		if (termData == null) {
			Set<ThermData> setTerm = new HashSet<ThermData>();
			this.termData = setTerm;
		}
		this.termData.add(termD);
	}

	public Set<ThermData> getTermData() {
		return termData;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}

}
