package org.amix.serializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

import org.amix.dto.UserDto;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * user反序列化器
 * @author Administrator
 *
 */
public class UserDeserializer implements Deserializer<UserDto> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDto deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		UserDto userDto = null;
		ByteArrayInputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			inputStream = new ByteArrayInputStream(data);
			objectInputStream = new ObjectInputStream(inputStream);
			userDto = (UserDto) objectInputStream.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return userDto;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
