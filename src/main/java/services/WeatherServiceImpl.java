package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;

public class WeatherServiceImpl implements WeatherService {
    @Override
    public String getMyWeather(double lon, double lat) {
        String message = "";
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather" +
                    "?lat=" + lon +
                    "&lon=" + lat +
                    "&utils=metric" +
                    "&appid=088d841dde3483519ba1b8536b79ce79");
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String row;
            StringBuilder stringBuilder = new StringBuilder();

            while ((row = bufferedReader.readLine()) != null) {
                stringBuilder.append(row);
            }
            Type typeToken = new TypeToken<Weather>() {
            }.getType();

            Gson gson = new Gson();

            Weather weather = gson.fromJson(stringBuilder.toString(), typeToken);

            message = String.valueOf(
                    "Shaxar \uD83C\uDF06: " + weather.getName() +
                    "\nTemperatura: \uD83C\uDF21" + weather.getMain().getTemp() +
                    "\nMinimal \uD83C\uDF21 ➖: Temperatura : " + weather.getMain().getTemp_min() +
                    "\nMaksimal \uD83C\uDF21 ➕: Temperatura : " + weather.getMain().getTemp_max() +
                            "\n Baza: " + weather.getBase()
            );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    @Override
    public void getWeather() {

    }
}
