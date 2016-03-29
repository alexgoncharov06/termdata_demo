package com.github.alexwolfgoncharov.termdata.interfaces;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "history_errorcodes")
public class ErrorHistory {
	@Id
	@Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	
	
	@ManyToOne
    @JoinColumn(name="error_id", 
                insertable=true, updatable=true, 
                nullable=true)
    private ErrorCodes error;
	
	@Column(name = "BASE_ID")
	String baseId;
	@Column(name = "status")
	String status;
	@Column(name = "text_status")
	String textStatus;
	@Column(name = "time_change")
	Timestamp timeChange;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ErrorCodes getError() {
		return error;
	}
	public void setError(ErrorCodes error) {
		this.error = error;
	}
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTextStatus() {
		return textStatus;
	}
	public void setTextStatus(String textStatus) {
		this.textStatus = textStatus;
	}
	public Timestamp getTimeChange() {
		return timeChange;
	}
	public void setTimeChange(Timestamp timeChange) {
		this.timeChange = timeChange;
	}
	public ErrorHistory(ErrorCodes error) {
		super();
		this.baseId = error.getBaseId();
		long timeLong = new Date().getTime();
		this.timeChange = new Timestamp(timeLong);
		this.error = error;
	}
	
	public ErrorHistory() {
	
	}
	@Override
	public String toString() {
		return "ErrorHistory [ID=" + ID + ", error=" + error + ", baseId="
				+ baseId + ", status=" + status + ", textStatus=" + textStatus
				+ ", timeChange=" + timeChange + "]";
	}

}
