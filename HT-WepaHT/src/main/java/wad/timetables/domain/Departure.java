package wad.timetables.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* @author mhaanran */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Departure {

    private String code;
    private int time;
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
