package com.github.alexwolfgoncharov.termdata.interfaces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensors_base_id_demo")
public class SensorsBaseId  implements Comparable<SensorsBaseId> {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(name = "base_id")
	int baseId;
	@Column(name = "sensor_id")
	int sensorId;
	@Column(name = "sensor_type")
	String type;
	@Column(name = "sensor_name")
	String name;
	@Column(name = "sensor_min")
	float min;
	@Column(name = "sensor_max")
	float max;
	@Column(name = "used")
	boolean used;
	@Column(name = "critical_error")
	boolean criticalError;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBaseId() {
		return baseId;
	}
	public void setBaseId(int baseId) {
		this.baseId = baseId;
	}
	public int getSensorId() {
		return sensorId;
	}
	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	@Override
	public String toString() {
		return "SensorsBaseId [id=" + id + ", baseId=" + baseId + ", sensorId="
				+ sensorId + ", type=" + type + ", name=" + name + ", min="
				+ min + ", max=" + max + "]";
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	
	
	
	public int compareTo(SensorsBaseId baseIdSensor) {
		int conType = 0;
		if ("tm".equals(baseIdSensor.getType())){
			conType = -1;
		} else {
			conType = 1;
		}
		
		int conId =  this.sensorId - baseIdSensor.sensorId;
		
		
		return conType * conId;
	}
	public boolean isCriticalError() {
		return criticalError;
	}
	public void setCriticalError(boolean criticalError) {
		this.criticalError = criticalError;
	}
	
	
	
	
	

}
