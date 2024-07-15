package chtbot_plus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chabot_plus.util.DBUtil;
import chatbot_plus.model.Message;

public class ChatbotDAOImpl implements ChatbotDAO {

	@Override
	public List<Message> findFiveMessage() throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM chatbot ORDER BY id DESC LIMIT 5";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Message> messages = new ArrayList<>();
		while(rs.next()) {
			Message m = new Message();
			m.setText(rs.getString("text"));
			messages.add(m);
		}
		return messages;
	}

	@Override
	public void addMessage(Message message) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "INSERT INTO chatbot (text, chat_id) VALUES (?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, message.getText());
		ps.setLong(2, message.getChat().getId());
		ps.executeUpdate();
	}
	
}
