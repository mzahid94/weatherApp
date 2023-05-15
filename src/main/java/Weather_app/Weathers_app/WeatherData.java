package Weather_app.Weathers_app;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherData {

    private String iconCode;
    private String location;
    private ZonedDateTime dateTime;
    private double maxTemp;
    private double minTemp;
    private double precip;
    private String source;
    private double temperature;
    private String description;
    private String conditions;
    private double feelsLike;
    private double humidity;
    private double windSpeed;
    private int precipitationProbability;
    private static JSONArray values;
    public boolean error = false;


    String unitGroup = "metric"; //us,metric,uk
    String apiKey = "7EZGRKSJC5ZA7DBXLELQ6FVFA";

    public WeatherData(String location) {
        error = false;
        String apiEndPoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

        this.location = location;
        this.dateTime = null;
        this.maxTemp = 0.0;
        this.minTemp = 0.0;
        this.precip = 0.0;
        this.setTemperature(0.0);
        this.setFeelsLike(0.0);
        this.setHumidity(0.0);
        this.setWindSpeed(0.0);
        this.setPrecipitationProbability(0);


        StringBuilder requestBuilder = new StringBuilder(apiEndPoint);
        location = location.replace(" ", "%20");
        System.out.println(location);
        requestBuilder.append(location);


        // Build the parameters to send via GET or POST
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(requestBuilder.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey);

        HttpGet get;
        try {
            get = new HttpGet(builder.build());

            CloseableHttpClient httpclient = HttpClients.createDefault();

            CloseableHttpResponse response = httpclient.execute(get);

            String rawResult = null;

            try {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    System.out.printf("Bad response status code:%d%n",
                            response.getStatusLine().getStatusCode());
                    error = true;
                    return;
                }
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    rawResult = EntityUtils.toString(entity, Charset.forName("utf-8"));
                }

            } finally {
                response.close();
            }

            JSONObject timelineResponse = new JSONObject(rawResult);

            ZoneId zoneId = ZoneId.of(timelineResponse.getString("timezone"));

            values = timelineResponse.getJSONArray("days");
            JSONObject firstDayValue = values.getJSONObject(0);

            System.out.println(firstDayValue);
            this.dateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(firstDayValue.getLong("datetimeEpoch")), zoneId);
            this.maxTemp = firstDayValue.getDouble("tempmax");
            this.minTemp = firstDayValue.getDouble("tempmin");
            this.precip = firstDayValue.getDouble("precip");
            this.temperature = firstDayValue.getDouble("temp");
            this.description = firstDayValue.getString("description");
            this.conditions = firstDayValue.getString("conditions");
            this.precip = firstDayValue.getDouble("precip");
            this.feelsLike = firstDayValue.getDouble("feelslike");
            this.windSpeed = firstDayValue.getDouble("windspeed");
            this.minTemp = firstDayValue.getDouble("tempmin");
            this.humidity = firstDayValue.getDouble("humidity");
            this.precipitationProbability = firstDayValue.getInt("precipprob");
            this.iconCode = firstDayValue.getString("icon");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            error = true;
        }
    }

    public String getLocation() {
        return location;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getPrecip() {
        return precip;
    }

    public String getSource() {
        return source;
    }


    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(int precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIconCode() {
        return iconCode;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }
}
