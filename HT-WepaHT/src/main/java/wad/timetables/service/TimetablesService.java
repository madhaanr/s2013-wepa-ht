package wad.timetables.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.timetables.domain.Search;

/* @author mhaanran */
@Service
public class TimetablesService {

    private RestTemplate restTemplate = new RestTemplate();
    private String user="SWR159kt";
    private String pass = "RQTY9943zz5";
    private int code = 2222222;
    
    public String search() { 
        
        String search = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&format=txt&code={code}", null, String.class, user,pass,code);
        
        return search;
    }
}
