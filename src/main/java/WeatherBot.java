import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import services.WeatherService;

import java.util.ArrayList;
import java.util.List;

public class WeatherBot extends TelegramLongPollingBot {
    WeatherService weatherService;
    public WeatherBot(String botToken, WeatherService weatherService) {
        super(botToken);
        this.weatherService = weatherService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String userName = update.getMessage().getChat().getUserName();
            String text = update.getMessage().getText();

            printLogs(update);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());

            sendMessage.setText("Salom");


            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            replyKeyboardMarkup.setResizeKeyboard(true);
            List<KeyboardRow> keyboardButtons = new ArrayList<>();
            KeyboardRow keyboardRows = new KeyboardRow();
            switch (text) {
                case "/start" -> {
                    sendMessage.setText("Salom " + userName + "\n Siz Bu Telegram Bot Uzbdagi shaxarlarni obhavosini " +
                            "bilishingiz mumkin ☁️☁️☁️ ");
                    KeyboardButton keyboardButton1 = new KeyboardButton();
                    keyboardButton1.setText("Saýat");
                    KeyboardButton keyboardButton2 = new KeyboardButton();
                    keyboardButton2.setText("Abu Simbel");
                    KeyboardButton keyboardButton3 = new KeyboardButton();
                    keyboardButton3.setText("Marabba");


                    keyboardRows.add(keyboardButton1);
                    keyboardRows.add(keyboardButton2);
                    keyboardRows.add(keyboardButton3);

                    keyboardButtons.add(keyboardRows);

                    replyKeyboardMarkup.setKeyboard(keyboardButtons);
                    sendMessage.setReplyMarkup(replyKeyboardMarkup);
                }
                case "Saýat" -> {
                    sendMessage.setText(weatherService.getMyWeather(39, 64));
                }
                case "Abu Simbel" -> {
                    sendMessage.setText(weatherService.getMyWeather(22, 32));
                }
                case "Marabba" -> {
                    sendMessage.setText(weatherService.getMyWeather(12, 32));
                }
                default -> {
                    sendMessage.setText("Xato");
                }
            }

            sendMessage.setChatId(update.getMessage().getChatId());
            try {
                execute(sendMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return "xdwiki_bot";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    private void printLogs(Update update) {
        String username = update.getMessage().getChat().getUserName();
        String text = update.getMessage().getText();

        System.out.println(username + ">>>>>>>>>" + text);
    }
}
