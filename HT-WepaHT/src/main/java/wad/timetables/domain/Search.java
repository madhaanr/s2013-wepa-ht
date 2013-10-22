package wad.timetables.domain;

/* @author mhaanran */


public class Search {
  
//    @Length(min=3,max=30)
    private String stopName;
    
//    @Length(min=0,max=7)
    private Integer stopNumber;

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Integer getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(Integer stopNumber) {
        this.stopNumber = stopNumber;
    }
    
}
