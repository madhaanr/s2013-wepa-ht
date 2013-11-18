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
import wad.timetables.domain.Departure;
import wad.timetables.domain.JsonStop;
import wad.timetables.domain.LineThatPassStop;
import wad.timetables.domain.SearchResult;

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

    @Override
    @Async
    public List<SearchResult> searchByStopName(String stopName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String searchResultsString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopName}&p=111000001001010&format=json", String.class, user, pass, stopName, dep_limit);
        if (searchResultsString == null) {
            return null;
        }
        List<SearchResult> results = mapper.readValue(searchResultsString, new TypeReference<List<SearchResult>>() {
        });
        for (int i = 0; i < results.size(); i++) {
            fixWgsCoords(results, i);
        }
        return results;
    }

    @Override
    @Async
    public SearchResult searchByStopNumber(String stopNumber) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String searchResultsString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=111111111001111", String.class, user, pass, stopNumber, dep_limit);
        if (searchResultsString == null) {
            return null;
        }
        List<SearchResult> results = mapper.readValue(searchResultsString, new TypeReference<List<SearchResult>>() {
        });

        String departuresString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=000000000010000", String.class, user, pass, stopNumber, dep_limit);
        departuresString = departuresString.substring(15, departuresString.length() - 2);
        List<Departure> departuresResults = mapper.readValue(departuresString, new TypeReference<List<Departure>>() {
        });

        results.get(0).setLinesParsed(linesParsingToLineAndDestination(results));

        lineCodeParsing(departuresResults);
        timeParsing(departuresResults);
        results.get(0).setDepartures(departuresResults);

        fixWgsCoords(results, 0);
        return results.get(0);
    }

    @Override
    @Async
    public JsonStop jsonStop(String stopNumber) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String searchResultsString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&p=00100010", String.class, user, pass, stopNumber, dep_limit);
        if (searchResultsString == null) {
            return null;
        }
        List<JsonStop> results = mapper.readValue(searchResultsString, new TypeReference<List<JsonStop>>() {
        });

//        String departuresString = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=000000000010000", String.class, user, pass, stopNumber, dep_limit);
//        departuresString = departuresString.substring(15, departuresString.length() - 2);
//        List<Departure> departuresResults = mapper.readValue(departuresString, new TypeReference<List<Departure>>() {
//        });
//        results.get(0).setLinesParsed(linesParsingToLineAndDestination2(results));

//        lineCodeParsing(departuresResults);           
//        results.get(0).setDepartures(departuresResults); 

        return results.get(0);
    }

    private void lineCodeParsing(List<Departure> departuresResults) {
        if (departuresResults != null) {
            for (int i = 0; i < departuresResults.size(); i++) {
                String lineCode = departuresResults.get(i).getCode();
                lineCode = lineCode.substring(1, lineCode.length() - 1);
                departuresResults.get(i).setCode(lineCode);
            }
        }
    }

    private void timeParsing(List<Departure> departuresResults) {
        if (departuresResults != null) {
            for (int i = 0; i < departuresResults.size(); i++) {
                int time = departuresResults.get(i).getTime();
                System.out.println("time"+time);
                DateFormat df = new SimpleDateFormat("HHmm");
                String currentTimeString = df.format(System.currentTimeMillis());
                int currentTimeInt = Integer.parseInt(currentTimeString);
                if(time>=2400) {
                    currentTimeInt+=2400;
                }
                time = time - currentTimeInt;
                if (time < 0) {
                    time = 0;
                }
                departuresResults.get(i).setTime(time);
            }
        }
    }

    private List<LineThatPassStop> linesParsingToLineAndDestination(List<SearchResult> results) {
        List<LineThatPassStop> list = new ArrayList();
        for (int i = 0; i < results.get(0).getLines().size(); i++) {
            String lineAndDestination = results.get(0).getLines().get(i);
            String line = lineAndDestination.substring(1, 5);
            String destination = lineAndDestination.substring(8);
            LineThatPassStop combined = new LineThatPassStop();
            combined.setDestination(destination);
            combined.setLine(line);
            list.add(combined);
        }
        return list;
    }

//    private List<LineThatPassStop> linesParsingToLineAndDestination2(List<JsonStop> results) {
//        List<LineThatPassStop> list = new ArrayList();
//        for (int i = 0; i < results.get(0).getLines().size(); i++) {      
//            String lineAndDestination=results.get(0).getLines().get(i);
//            String line = lineAndDestination.substring(1,4);
//            String destination = lineAndDestination.substring(8);
//            LineThatPassStop compined = new LineThatPassStop();
//            compined.setDestination(destination);
//            compined.setLine(line);
//            list.add(compined);
//        }
//        return list;
//    }
    private void fixWgsCoords(List<SearchResult> results, int i) {
        String wgs_coords = results.get(i).getWgs_coords();
        String wgs_coordsStart = wgs_coords.substring(0, wgs_coords.indexOf(','));
        String wgs_coordsEnd = wgs_coords.substring(wgs_coords.indexOf(',') + 1);
        results.get(i).setWgs_coords(wgs_coordsEnd.concat("," + wgs_coordsStart));
    }
}
