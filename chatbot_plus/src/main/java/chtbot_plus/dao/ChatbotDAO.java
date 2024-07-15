package chtbot_plus.dao;

import java.sql.SQLException;
import java.util.List;

import chatbot_plus.model.Message;

public interface ChatbotDAO {
	List<Message> findFiveMessage() throws SQLException;
	void addMessage(Message message) throws SQLException;
}
