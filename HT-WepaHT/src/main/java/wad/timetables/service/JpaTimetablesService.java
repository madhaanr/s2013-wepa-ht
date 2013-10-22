package wad.timetables.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wad.timetables.domain.SearchResults;

/* @author mhaanran */
@Service
public class JpaTimetablesService implements TimetablesService{
    
    private RestTemplate restTemplate;
    private String user="SWR159kt";
    private String pass = "RQTY9943zz5";
    private int code = 2222222;
    private int dep_limit=10;
    
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
    public String search(String stopName) {
         return restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopName}&dep_limit={dep_limit}", String.class, user, pass, stopName, dep_limit);    
    }
//    @Override
//    @Async
//    public String search(Integer stopNumber) {
//         return restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&format=txt&code={stopNumber}&dep_limit={dep_limit}&p=111111100011011", String.class, user,pass,stopNumber,dep_limit);    
//    }
    @Override
    @Async
    public SearchResults search(Integer stopNumber) throws IOException {
         ObjectMapper mapper = new ObjectMapper();
         String resu = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=11111110", String.class, user, pass, stopNumber, dep_limit);    
         List<SearchResults> results=mapper.readValue(resu, new TypeReference<List<SearchResults>>() {});
         
         System.out.println("results: "+results.get(0).getCode());
         
        
         //         List<SearchResults> results = restTemplate.getForObject("http://api.reittiopas.fi/hsl/prod/?request=stop&user={user}&pass={pass}&code={stopNumber}&dep_limit={dep_limit}&p=111111100011011&format=json", List.class, user, pass, stopNumber, dep_limit);    
        return results.get(0);
    }
}
