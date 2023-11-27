package pantrypal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// This class ensures that Java Spring Boot uses Gson for serializing objects into JSON
// This is used in the APIs as it is  
@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder().create(); // Customize Gson as needed
    }

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }
}