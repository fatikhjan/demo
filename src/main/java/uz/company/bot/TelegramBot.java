package uz.company.bot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.company.config.BotConfig;

@Component
public class TelegramBot extends TelegramLongPollingBot {


    final BotConfig botConfig;

    public TelegramBot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    /**
     * Return username of this bot
     */
    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    /**
     * Returns the token of the bot to be able to perform Telegram Api Requests
     *
     * @return Token of the bot
     */
    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    /**
     * This method is called when receiving updates via GetUpdates method
     *
     * @param update Update received
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(update.getMessage().getChat()));
            sendMessage.setText(update.getMessage().getText());
            try {
                execute(sendMessage);
            } catch (Exception e) {

            }

        }
    }
}
