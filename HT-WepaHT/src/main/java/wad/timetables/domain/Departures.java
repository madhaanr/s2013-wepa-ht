package wad.timetables.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* @author mhaanran */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Departures {
    
    private String code;
    private String time;
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
