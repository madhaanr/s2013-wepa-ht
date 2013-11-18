package wad.timetables.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/* @author mhaanran */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResult {

        private int code;
        private String code_short;
        private String name_fi;   
        private String name_sv; 
        private String city_fi;
        private String city_sv; 
        private List<String> lines;      
        private String coords;
        private String wgs_coords;        
        private List<String> accessibility;
        private List<Departure> departures;
        private String timetable_link;    
        private String omatlahdot_link;     
        private String address_fi;  
        private String address_sv;

        private List<LineThatPassStop> linesParsed; 

    public SearchResult() {
    }
 
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCode_short() {
        return code_short;
    }

    public void setCode_short(String code_short) {
        this.code_short = code_short;
    }

    public String getName_fi() {
        return name_fi;
    }

    public void setName_fi(String name_fi) {
        this.name_fi = name_fi;
    }

    public String getName_sv() {
        return name_sv;
    }

    public void setName_sv(String name_sv) {
        this.name_sv = name_sv;
    }

    public String getCity_fi() {
        return city_fi;
    }

    public void setCity_fi(String city_fi) {
        this.city_fi = city_fi;
    }

    public String getCity_sv() {
        return city_sv;
    }

    public void setCity_sv(String city_sv) {
        this.city_sv = city_sv;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    public String getWgs_coords() {
        return wgs_coords;
    }

    public void setWgs_coords(String wgs_coords) {
        this.wgs_coords = wgs_coords;
    }

    public List<String> getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(List<String> accessibility) {
        this.accessibility = accessibility;
    }
//
    public List<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public String getTimetable_link() {
        return timetable_link;
    }

    public void setTimetable_link(String timetable_link) {
        this.timetable_link = timetable_link;
    }

    public String getOmatlahdot_link() {
        return omatlahdot_link;
    }

    public void setOmatlahdot_link(String omatlahdot_link) {
        this.omatlahdot_link = omatlahdot_link;
    }

    public String getAddress_fi() {
        return address_fi;
    }

    public void setAddress_fi(String address_fi) {
        this.address_fi = address_fi;
    }

    public String getAddress_sv() {
        return address_sv;
    }

    public void setAddress_sv(String address_sv) {
        this.address_sv = address_sv;
    }

    public List<LineThatPassStop> getLinesParsed() {
        return linesParsed;
    }

    public void setLinesParsed(List<LineThatPassStop> linesParsed) {
        this.linesParsed = linesParsed;
    }
    
    
}
