package wad.timetables.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.timetables.domain.Departures;
import wad.timetables.domain.LineThatPassStop;
import wad.timetables.domain.SearchResults;

/* @author mhaanran */
@Service
public class JpaTimetablesService implements TimetablesService {

    private RestTemplate restTemplate;
    private String user = "SWR159kt";
    private String pass = "RQTY9943zz5";
    private int dep_limit = 10;

    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

//    @Override
//    @Async
//    public String searchByStopName(String stopName) {
//        return restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopName}&dep_limit={dep_limit}&p=111000001001010&format=json", String.class, user, pass, stopName, dep_limit);
//    }
    @Override
    @Async
    public List<SearchResults> searchByStopName(String stopName) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        String searchResultsString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopName}&p=111000001001010&format=json", String.class, user, pass, stopName, dep_limit);
        List<SearchResults> results = mapper.readValue(searchResultsString, new TypeReference<List<SearchResults>>() {
        });
//        System.out.println("results: "+ results.get(1).getName_fi());
        return results;
    }

    @Override
    @Async
    public SearchResults searchByStopNumber(String stopNumber) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String searchResultsString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=111111111001111", String.class, user, pass, stopNumber, dep_limit);
        List<SearchResults> results = mapper.readValue(searchResultsString, new TypeReference<List<SearchResults>>() {
        });
        
        String departuresString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=000000000010000", String.class, user, pass, stopNumber, dep_limit);
        departuresString = departuresString.substring(15, departuresString.length() - 2);
        List<Departures> departuresResults = mapper.readValue(departuresString, new TypeReference<List<Departures>>() {
        });
        
        
        results.get(0).setLinesParsed(linesParsingToLineAndDestination(results));
        lineCodeParsing(departuresResults);        
        timeParsing(departuresResults);
        results.get(0).setDepartures(departuresResults);
        
        String wgs_coords = results.get(0).getWgs_coords();
        String wgs_coordsStart = wgs_coords.substring(0,wgs_coords.indexOf(','));
        String wgs_coordsEnd = wgs_coords.substring(wgs_coords.indexOf(',')+1);   
        results.get(0).setWgs_coords(wgs_coordsEnd.concat(","+wgs_coordsStart));
//        System.out.println("lines: "+results.get(0).getLines().get(0));
        return results.get(0);
    }

    private void lineCodeParsing(List<Departures> departuresResults) {
        for (int i = 0; i < departuresResults.size(); i++) {
            String lineCode = departuresResults.get(i).getCode();
//            System.out.println("lineCode: "+lineCode);
            lineCode = lineCode.substring(1, lineCode.length() - 1);
            departuresResults.get(i).setCode(lineCode);
        }
    }
    private void timeParsing(List<Departures> departuresResults) {
        for (int i = 0; i < departuresResults.size(); i++) {
            int time = departuresResults.get(i).getTime();
            DateFormat df = new SimpleDateFormat("HHmm");
            String currentTimeString = df.format(System.currentTimeMillis());
            int currentTimeInt = Integer.parseInt(currentTimeString);
            time=time-currentTimeInt;
            if(time<0) {
                time=0;
            }
            departuresResults.get(i).setTime(time);
            System.out.println("time: " + time);
        }
    }

    private List<LineThatPassStop> linesParsingToLineAndDestination(List<SearchResults> results) {
        List<LineThatPassStop> list = new ArrayList();
        for (int i = 0; i < results.get(0).getLines().size(); i++) {      
            String lineAndDestination=results.get(0).getLines().get(i);
            String line = lineAndDestination.substring(1,4);
            String destination = lineAndDestination.substring(6);
            LineThatPassStop compined = new LineThatPassStop();
            compined.setDestination(destination);
            compined.setLine(line);
            list.add(compined);
        }
        return list;
    }
}
