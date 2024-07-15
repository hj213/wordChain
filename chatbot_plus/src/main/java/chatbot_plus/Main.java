package chatbot_plus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import chatbot_plus.model.Message;
import chatbot_plus.model.Update;
import chatbot_plus.service.TelegramBotService;
import chtbot_plus.dao.ChatbotDAO;
import chtbot_plus.dao.ChatbotDAOImpl;

public class Main {
	public static void main(String[] args) {
		TelegramBotService service = new TelegramBotService();
		
		ChatbotDAO dao = new ChatbotDAOImpl();
		
		long lastId = 0;
		while(true) {
			try {
				TimeUnit.SECONDS.sleep(1);
				List<Update> updates = service.getUpdates(lastId + 1);
				if(updates == null ) continue;
				for(Update update: updates) {
					String text = update.getMessage().getText();
					System.out.println(text);
					
					List<Message> data = dao.findFiveMessage();
					
					long chatId = update.getMessage().getChat().getId();
					lastId = update.getUpdateId();
					String lastText = data.get(0).getText();
					if(text.charAt(0) == lastText.charAt(lastText.length()-1)) {
						service.sendMessage(chatId, "정확한 단어입니다: " + text);
						dao.addMessage(update.getMessage());
					} else {
						for(Message m : data) {
							System.out.println("* " +  m.getText());
						}
						service.sendMessage(chatId, "잘못된 단어입니다: "+text);
						for(Message m : data) {
							service.sendMessage(chatId, "* " +  m.getText());
						}
					}
				
					for(Message m : data) {
						System.out.println("* " +  m.getText());
					}
					
//					dao.addMessage(update.getMessage());
					
//					service.sendMessage(chatId, "안녕하세요!");
				}
				
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
