package com.github.alexwolfgoncharov.termdata.interfaces;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temperature")
public class ThermData {
	@Id
	@Column(name = "ID")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	long ID;
	@Column(name = "BASE_ID")
	String BaseID;
	@Column(name = "location")
	String location;
	@Column(name = "time")
	Timestamp time;
	@Column(name = "term1")
	float term1;
	@Column(name = "term2")
	float term2;
	@Column(name = "term3")
	float term3;
	@Column(name = "term4")
	float term4;
	@Column(name = "term5")
	float term5;
	@Column(name = "term6")
	float term6;
	@Column(name = "term7")
	float term7;
	@Column(name = "term8")
	float term8;
	@Column(name = "term9")
	float term9;
	@Column(name = "term10")
	float term10;
	@Column(name = "term11")
	float term11;
	@Column(name = "term12")
	float term12;
	@Column(name = "term13")
	float term13;
	@Column(name = "term14")
	float term14;
	@Column(name = "term15")
	float term15;
	@Column(name = "term16")
	float term16;
	@Column(name = "term17")
	float term17;
	@Column(name = "term18")
	float term18;
	@Column(name = "term19")
	float term19;
	@Column(name = "term20")
	float term20;
	@Column(name = "term21")
	float term21;
	@Column(name = "term22")
	float term22;
	@Column(name = "term23")
	float term23;
	@Column(name = "term24")
	float term24;
	@Column(name = "hum")
	float hum;
	@Column(name = "balance")
	float balance;
	@Column(name = "signal1")
	int signal1;
	@Column(name = "signal2")
	int signal2;
	@Column(name = "signal3")
	int signal3;
	@Column(name = "signal4")
	int signal4;
	@Column(name = "signal5")
	int signal5;
	@Column(name = "signal6")
	int signal6;
	@Column(name = "signal7")
	int signal7;
	@Column(name = "signal8")
	int signal8;
	@Column(name = "signal9")
	int signal9;
	@Column(name = "signal10")
	int signal10;
	@Column(name = "signal11")
	int signal11;
	@Column(name = "signal12")
	int signal12;
	@Column(name = "signal13")
	int signal13;
	@Column(name = "signal14")
	int signal14;
	@Column(name = "signal15")
	int signal15;
	@Column(name = "signal16")
	int signal16;
	@Column(name = "version")
	String version;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getBaseID() {
		return BaseID;
	}

	public void setBaseID(String baseID) {
		BaseID = baseID;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ThermData [ID=" + ID + ", BaseID=" + BaseID + ", location="
				+ location + ", time=" + time + ", term1=" + term1 + ", term2="
				+ term2 + ", term3=" + term3 + ", term4=" + term4 + ", term5="
				+ term5 + ", term6=" + term6 + ", term7=" + term7 + ", term8="
				+ term8 + ", term9=" + term9 + ", term10=" + term10
				+ ", term11=" + term11 + ", term12=" + term12 + ", term13="
				+ term13 + ", term14=" + term14 + ", term15=" + term15
				+ ", term16=" + term16 + ", term17=" + term17 + ", term18="
				+ term18 + ", term19=" + term19 + ", term20=" + term20
				+ ", term21=" + term21 + ", term22=" + term22 + ", term23="
				+ term23 + ", term24=" + term24 + ", hum=" + hum + ", balance="
				+ balance + ", signal1=" + signal1 + ", signal2=" + signal2
				+ ", signal3=" + signal3 + ", signal4=" + signal4
				+ ", signal5=" + signal5 + ", signal6=" + signal6
				+ ", signal7=" + signal7 + ", signal8=" + signal8
				+ ", signal9=" + signal9 + ", signal10=" + signal10
				+ ", signal11=" + signal11 + ", signal12=" + signal12
				+ ", signal13=" + signal13 + ", signal14=" + signal14
				+ ", signal15=" + signal15 + ", signal16=" + signal16 + ", version=" + version + "]";
	}

	public float getTerm1() {
		return term1;
	}

	public void setTerm1(float term1) {
		this.term1 = term1;
	}

	public float getTerm2() {
		return term2;
	}

	public void setTerm2(float term2) {
		this.term2 = term2;
	}

	public float getTerm3() {
		return term3;
	}

	public void setTerm3(float term3) {
		this.term3 = term3;
	}

	public int compareTo(ThermData o) {
		int result;
		result = BaseID.compareTo(o.BaseID);
		if (result != 0)
			return result;

		result = (int) (o.ID - this.ID);
		if (result != 0) {
			return (int) result / Math.abs(result);
		}
		return 0;

	}

	public float getHum() {
		return hum;
	}

	public void setHum(float hum) {
		this.hum = hum;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getTerm4() {
		return term4;
	}

	public void setTerm4(float term4) {
		this.term4 = term4;
	}

	public float getTerm5() {
		return term5;
	}

	public void setTerm5(float term5) {
		this.term5 = term5;
	}

	public float getTerm6() {
		return term6;
	}

	public void setTerm6(float term6) {
		this.term6 = term6;
	}

	public float getTerm7() {
		return term7;
	}

	public void setTerm7(float term7) {
		this.term7 = term7;
	}

	public float getTerm8() {
		return term8;
	}

	public void setTerm8(float term8) {
		this.term8 = term8;
	}

	public float getTerm9() {
		return term9;
	}

	public void setTerm9(float term9) {
		this.term9 = term9;
	}

	public float getTerm10() {
		return term10;
	}

	public void setTerm10(float term10) {
		this.term10 = term10;
	}

	public int getSignal1() {
		return signal1;
	}

	public void setSignal1(int signal1) {
		this.signal1 = signal1;
	}

	public int getSignal2() {
		return signal2;
	}

	public void setSignal2(int signal2) {
		this.signal2 = signal2;
	}

	public int getSignal3() {
		return signal3;
	}

	public void setSignal3(int signal3) {
		this.signal3 = signal3;
	}

	public int getSignal4() {
		return signal4;
	}

	public void setSignal4(int signal4) {
		this.signal4 = signal4;
	}

	public int getSignal5() {
		return signal5;
	}

	public void setSignal5(int signal5) {
		this.signal5 = signal5;
	}

	public int getSignal6() {
		return signal6;
	}

	public void setSignal6(int signal6) {
		this.signal6 = signal6;
	}

	public int getSignal7() {
		return signal7;
	}

	public void setSignal7(int signal7) {
		this.signal7 = signal7;
	}

	public int getSignal8() {
		return signal8;
	}

	public void setSignal8(int signal8) {
		this.signal8 = signal8;
	}

	public int getSignal9() {
		return signal9;
	}

	public void setSignal9(int signal9) {
		this.signal9 = signal9;
	}

	public int getSignal10() {
		return signal10;
	}

	public void setSignal10(int signal10) {
		this.signal10 = signal10;
	}

	public int getSignal11() {
		return signal11;
	}

	public void setSignal11(int signal11) {
		this.signal11 = signal11;
	}

	public int getSignal12() {
		return signal12;
	}

	public void setSignal12(int signal12) {
		this.signal12 = signal12;
	}

	public int getSignal13() {
		return signal13;
	}

	public void setSignal13(int signal13) {
		this.signal13 = signal13;
	}

	public int getSignal14() {
		return signal14;
	}

	public void setSignal14(int signal14) {
		this.signal14 = signal14;
	}

	public int getSignal15() {
		return signal15;
	}

	public void setSignal15(int signal15) {
		this.signal15 = signal15;
	}

	public int getSignal16() {
		return signal16;
	}

	public void setSignal16(int signal16) {
		this.signal16 = signal16;
	}

	public float getTerm11() {
		return term11;
	}

	public void setTerm11(float term11) {
		this.term11 = term11;
	}

	public float getTerm12() {
		return term12;
	}

	public void setTerm12(float term12) {
		this.term12 = term12;
	}

	public float getTerm13() {
		return term13;
	}

	public void setTerm13(float term13) {
		this.term13 = term13;
	}

	public float getTerm14() {
		return term14;
	}

	public void setTerm14(float term14) {
		this.term14 = term14;
	}

	public float getTerm15() {
		return term15;
	}

	public void setTerm15(float term15) {
		this.term15 = term15;
	}

	public float getTerm16() {
		return term16;
	}

	public void setTerm16(float term16) {
		this.term16 = term16;
	}

	public float getTerm17() {
		return term17;
	}

	public void setTerm17(float term17) {
		this.term17 = term17;
	}

	public float getTerm18() {
		return term18;
	}

	public void setTerm18(float term18) {
		this.term18 = term18;
	}

	public float getTerm19() {
		return term19;
	}

	public void setTerm19(float term19) {
		this.term19 = term19;
	}

	public float getTerm20() {
		return term20;
	}

	public void setTerm20(float term20) {
		this.term20 = term20;
	}

	public float getTerm21() {
		return term21;
	}

	public void setTerm21(float term21) {
		this.term21 = term21;
	}

	public float getTerm22() {
		return term22;
	}

	public void setTerm22(float term22) {
		this.term22 = term22;
	}

	public float getTerm23() {
		return term23;
	}

	public void setTerm23(float term23) {
		this.term23 = term23;
	}

	public float getTerm24() {
		return term24;
	}

	public void setTerm24(float term24) {
		this.term24 = term24;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ThermData() {
	}

	public ThermData(OnlineData data) {

				this.BaseID = data.getBaseID();
				this.balance = data.getBalance();
				this.time = data.getTime();
				this.term1 = data.getTerm1();
				this.term2 = data.getTerm2();
				this.term3 = data.getTerm3();
				this.term4 = data.getTerm4();
				this.term5 = data.getTerm5();
				this.term6 = data.getTerm6();
				this.term7 = data.getTerm7();
				this.term8 = data.getTerm8();
				this.term9 = data.getTerm9();
				this.term10 = data.getTerm10();
				this.term11 = data.getTerm11();
				this.term12 = data.getTerm12();
				this.term13 = data.getTerm13();
				this.term14 = data.getTerm14();
				this.term15 = data.getTerm15();
				this.term16 = data.getTerm16();
				this.term17 = data.getTerm17();
				this.term18 = data.getTerm18();
				this.term19 = data.getTerm19();
				this.term20 = data.getTerm20();
				this.term21 = data.getTerm21();
				this.term22 = data.getTerm22();
				this.term23 = data.getTerm23();
				this.term24 = data.getTerm24();
				this.signal1 = data.getSignal1();
				this.signal2 = data.getSignal2();
				this.signal3 = data.getSignal3();
				this.signal4 = data.getSignal4();
				this.signal5 = data.getSignal5();
				this.signal6 = data.getSignal6();
				this.signal7 = data.getSignal7();
				this.signal8 = data.getSignal8();
				this.signal9 = data.getSignal9();
				this.signal10 = data.getSignal10();
				this.signal11 = data.getSignal11();
				this.signal12 = data.getSignal12();
				this.signal13 = data.getSignal13();
				this.signal14 = data.getSignal14();
				this.signal15 = data.getSignal15();
				this.signal16 = data.getSignal16();
				this.location = data.getLocation();
				this.version = data.getVersion();
			}

		}




