
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import services.WeatherService;
import services.WeatherServiceImpl;

public class Main {
    public static void main(String[] args) {
        WeatherService weatherService;
        try {
            weatherService = new WeatherServiceImpl();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new WeatherBot("6004153279:AAGQGEEWovMn6jsi_-cH2ZshsufSC4zMFZ0", weatherService));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
