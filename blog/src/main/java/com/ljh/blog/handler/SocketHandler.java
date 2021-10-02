package com.ljh.blog.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	List<HashMap<String, Object>> rls = new ArrayList<>();

	// client에서 메시지가 서버로 전송댈때 실행되는 함수.
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String messagePayload = message.getPayload();
		JSONObject object = jsonToObjectParser(messagePayload);

		String rN = (String) object.get("roomNumber");
		HashMap<String, Object> temp = new HashMap<String, Object>();
		
		System.out.println(rls.size());
		if (rls.size() > 0) {
			
			for (int i = 0; i < rls.size(); i++) {
				String roomNumber = (String) rls.get(i).get("roomNumber");
				System.out.println("roomNumber: " + roomNumber);
				if (roomNumber.equals(rN)) {
					temp = rls.get(i);
					System.out.println(temp);
					break;
				}
			}

			for (String key : temp.keySet()) {
				System.out.println(key);
				if (key.equals("roomNumber")) {
					continue;
				}
				WebSocketSession webSocketSession = (WebSocketSession) temp.get(key);
				if (webSocketSession != null) {
					try {
						System.out.println(object.toJSONString());
						webSocketSession.sendMessage(new TextMessage(object.toJSONString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 세션이 생성될때 시작되는 함수
	@SuppressWarnings("unchecked")
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		boolean flag = false;
		String url = session.getUri().toString();
		String roomNumber = url.split("/chating/")[1];
		int idx = rls.size();

		if (rls.size() > 0) {
			for (int i = 0; i < rls.size(); i++) {
				String rN = (String) rls.get(i).get("roomNumber");
				if (rN.equals(roomNumber)) {
					flag = true;
					idx = i;
					break;
				}
			}
		}

		if (flag) { // 존재하는 방이라면 세션만 추가한다.
			HashMap<String, Object> map = rls.get(idx);
			map.put(session.getId(), session);
		} else { // 최초 생성하는 방이라면 방번호와 세션을 추가한다.
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("roomNumber", roomNumber);
			map.put(session.getId(), session);
			rls.add(map);
		}

		JSONObject object = new JSONObject();
		object.put("type", "getId");
		object.put("sessionId", session.getId());
	
		session.sendMessage(new TextMessage(object.toJSONString()));
	}

	// 세션이 끝날때 실행되는 함수
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

		if (rls.size() > 0) { // 소켓이 종료되면 해당 세션값들을 찾아서 지운다.
			for (int i = 0; i < rls.size(); i++) {
				rls.get(i).remove(session.getId());
			}
		}

		super.afterConnectionClosed(session, status);
	}

	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return obj;
	}
}
