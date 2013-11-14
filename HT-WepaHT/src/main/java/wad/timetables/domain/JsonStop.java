/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.timetables.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 *
 * @author Marko
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonStop {
    
    private String name_fi;
    
    private List<String> lines;
 
//    private List<Departure> departures;

//    private List<LineThatPassStop> linesParsed;
    
    public String getName_fi() {
        return name_fi;
    }

    public void setName_fi(String name_fi) {
        this.name_fi = name_fi;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

//    public List<Departure> getDepartures() {
//        return departures;
//    }
//
//    public void setDepartures(List<Departure> departures) {
//        this.departures = departures;
//    }

//     public List<LineThatPassStop> getLinesParsed() {
//        return linesParsed;
//    }
//
//    public void setLinesParsed(List<LineThatPassStop> linesParsed) {
//        this.linesParsed = linesParsed;
//    }
    
}
