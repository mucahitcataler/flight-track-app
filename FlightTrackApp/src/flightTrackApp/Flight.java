package flightTrackApp;

public class Flight implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	public int ID;
    public Capital from;
    public Capital to;
    public int minute;
    public int hour;
    public int day;
    public int month;
    public int year;
    public int duration;
    public int delay;
    public int aMinute;
    public int aHour;
    public int aDay;
    public int aMonth;
    public int aYear;
    public boolean landed;

	public Flight() {
		super();
	}

	public Flight(int iD, Capital from, Capital to, int minute, int hour, int day, int month, int year) {
		ID = iD;
		this.from = from;
		this.to = to;
		this.minute = minute;
		this.hour = hour;
		this.day = day;
		this.month = month;
		this.year = year;
		delay = 0;
		landed = false;
		aMinute = minute;
		aHour = hour;
		aDay = day;
		aMonth = month;
		aYear = year;
		duration = (int)((float)Math.sqrt( Math.pow( from.getX()-to.getX() , 2 ) + Math.pow( from.getY()-to.getY() , 2 ) ) * 45 );
		for ( int i=0 ; i< duration ; i++) {
			aMinute++;
			if(aMinute>59) {
				aMinute=0;
				aHour++;
				if(aHour>23) {
					aHour=0;
					aDay++;
					if (aDay>30) {
						aDay=1;
						aMonth++;
						if (aMonth > 12) {
							aMonth=1;
							aYear++;
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

    public void setFrom(Capital from) {
        this.from = from;
    }

    public Capital getTo() {
        return to;
    }

    public void setTo(Capital to) {
        this.to = to;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {      
		for ( int i=0 ; i < delay-this.delay ; i++) {
			setMinute(getMinute()+1);
			if(getMinute()>59) {
				setMinute(0);
				setHour(getHour()+1);
				if(getHour()>23) {
					setHour(0);
					setDay(getDay()+1);
					if (getDay()>30) {
						setDay(1);
						setMonth(getMonth()+1);
						if (getMonth() > 12) {
							setMonth(1);
							setYear(getYear()+1);
						}
					}
				}
			}
			aMinute++;
			if(aMinute>59) {
				aMinute=0;
				aHour++;
				if(aHour>23) {
					aHour=0;
					aDay++;
					if (aDay>30) {
						aDay=1;
						aMonth++;
						if (aMonth > 12) {
							aMonth=1;
							aYear++;
						}
					}
				}
			}
		}		
		this.delay = delay;
    }

	public int getDuration() {
		return duration;
	}

	public int getaMinute() {
		return aMinute;
	}

	public int getaHour() {
		return aHour;
	}

	public int getaDay() {
		return aDay;
	}

	public int getaMonth() {
		return aMonth;
	}

	public int getaYear() {
		return aYear;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public boolean isLanded() {
		return landed;
	}

	public void setLanded(boolean landed) {
		this.landed = landed;
	}
    

}
