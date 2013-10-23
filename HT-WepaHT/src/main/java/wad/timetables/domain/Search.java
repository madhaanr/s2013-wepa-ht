package wad.timetables.domain;

/* @author mhaanran */


public class Search {
  
//    @Length(min=3,max=30)
    private String stopName;
    
//    @Length(min=0,max=7)
    private String stopNumber;

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(String stopNumber) {
        this.stopNumber = stopNumber;
    }
    
}
