package flightTrackApp;

public class Capital implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	public String name;
    public int x;
    public int y;
    
    

    public Capital() {
		super();
	}


	public Capital(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
