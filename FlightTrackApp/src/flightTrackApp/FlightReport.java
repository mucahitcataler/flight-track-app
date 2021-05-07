package flightTrackApp;

public class FlightReport implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int ID;
    public Capital from;
    public Capital to;
    public int tMinute;
    public int tHour;
    public int tDay;
    public int tMonth;
    public int tYear;
    public int sdMinute;
    public int sdHour;
    public int sdDay;
    public int sdMonth;
    public int sdYear;
    public int duration;
    public int delay;
    public int lMinute;
    public int lHour;
    public int lDay;
    public int lMonth;
    public int lYear;
    public int saMinute;
    public int saHour;
    public int saDay;
    public int saMonth;
    public int saYear;
    
      
	public FlightReport(int iD, Capital from, Capital to, int tMinute, int tHour, int tDay, int tMonth, int tYear,
			int duration, int delay, int lMinute, int lHour, int lDay, int lMonth, int lYear) {
		super();
		ID = iD;
		this.from = from;
		this.to = to;
		this.tMinute = tMinute;
		this.tHour = tHour;
		this.tDay = tDay;
		this.tMonth = tMonth;
		this.tYear = tYear;
		sdMinute = tMinute;
		sdHour = tHour;
		sdDay = tDay;
		sdMonth = tMonth;
		sdYear = tYear;
		this.duration = duration;
		this.delay = delay;
		this.lMinute = lMinute;
		this.lHour = lHour;
		this.lDay = lDay;
		this.lMonth = lMonth;
		this.lYear = lYear;
		saMinute = lMinute;
		saHour = lHour;
		saDay = lDay;
		saMonth = lMonth;
		saYear = lYear;
		
		for (int i = 0 ; i < delay ; i++) {
			saMinute--;
			if(saMinute<0) {
				saMinute=59;
				saHour--;
				if(saHour<0) {
					saHour=23;
					saDay--;
					if (saDay<1) {
						saDay=30;
						saMonth--;
						if (saMonth < 1) {
							saMonth=12;
							saYear--;
						}
					}
				}
			}
		}
		
		for (int i = 0 ; i < delay ; i++) {
			sdMinute--;
			if(sdMinute<0) {
				sdMinute=59;
				sdHour--;
				if(sdHour<0) {
					sdHour=23;
					sdDay--;
					if (sdDay<1) {
						sdDay=30;
						sdMonth--;
						if (sdMonth < 1) {
							sdMonth=12;
							sdYear--;
						}
					}
				}
			}
		}
		
	}


	public int getID() {
		return ID;
	}


	public Capital getFrom() {
		return from;
	}


	public Capital getTo() {
		return to;
	}


	public int gettMinute() {
		return tMinute;
	}


	public int gettHour() {
		return tHour;
	}


	public int gettDay() {
		return tDay;
	}


	public int gettMonth() {
		return tMonth;
	}


	public int gettYear() {
		return tYear;
	}


	public int getSdMinute() {
		return sdMinute;
	}


	public int getSdHour() {
		return sdHour;
	}


	public int getSdDay() {
		return sdDay;
	}


	public int getSdMonth() {
		return sdMonth;
	}


	public int getSdYear() {
		return sdYear;
	}


	public int getDuration() {
		return duration;
	}


	public int getDelay() {
		return delay;
	}


	public int getlMinute() {
		return lMinute;
	}


	public int getlHour() {
		return lHour;
	}


	public int getlDay() {
		return lDay;
	}


	public int getlMonth() {
		return lMonth;
	}


	public int getlYear() {
		return lYear;
	}


	public int getSaMinute() {
		return saMinute;
	}


	public int getSaHour() {
		return saHour;
	}


	public int getSaDay() {
		return saDay;
	}


	public int getSaMonth() {
		return saMonth;
	}


	public int getSaYear() {
		return saYear;
	}
	
	
    
    
    
    

}
