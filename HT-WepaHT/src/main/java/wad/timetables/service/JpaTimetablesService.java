package wad.timetables.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.timetables.domain.Departures;
import wad.timetables.domain.SearchResults;

/* @author mhaanran */
@Service
public class JpaTimetablesService implements TimetablesService {

    private RestTemplate restTemplate;
    private String user = "SWR159kt";
    private String pass = "RQTY9943zz5";
    private int code = 2222222;
    private int dep_limit = 10;

    @PostConstruct
    private void init() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public String search() {
        return restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={code}&dep_limit={dep_limit}", String.class, user, pass, code, dep_limit);
    }

    @Override
    @Async
    public String searchByStopName(String stopName) {
        return restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopName}&dep_limit={dep_limit}&p=111000001001010", String.class, user, pass, stopName, dep_limit);
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
        lineCodeParsing(departuresResults);        
        timeParsing(departuresResults);  
        
        String wgs_coords = results.get(0).getWgs_coords();
        String wgs_coordsStart = wgs_coords.substring(0,wgs_coords.indexOf(','));
        String wgs_coordsEnd = wgs_coords.substring(wgs_coords.indexOf(',')+1);
      
        results.get(0).setWgs_coords(wgs_coordsEnd.concat(","+wgs_coordsStart));
        results.get(0).setDepartures(departuresResults);
        return results.get(0);
    }

    private void lineCodeParsing(List<Departures> departuresResults) {
        for (int i = 0; i < 10; i++) {
            String lineCode = departuresResults.get(i).getCode();
            lineCode = lineCode.substring(1, lineCode.length() - 1);
            departuresResults.get(i).setCode(lineCode);
        }
    }
    private void timeParsing(List<Departures> departuresResults) {
        for (int i = 0; i < 10; i++) {
            int time = departuresResults.get(i).getTime();
            DateFormat df = new SimpleDateFormat("HHmm");
            String currentTimeString = df.format(System.currentTimeMillis());
            int currentTimeInt = Integer.parseInt(currentTimeString);
            time=time-currentTimeInt;
            departuresResults.get(i).setTime(time);
            System.out.println("time: " + time);
        }
    }
}
