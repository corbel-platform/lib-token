package com.bqreaders.lib.token;

import com.bqreaders.lib.token.model.TokenType;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Cristian del Cerro
 */
public class TokenInfoTest {

	@Test
	public void testDeserialize() {
		String serialized = TokenInfo.newBuilder().setType(TokenType.TOKEN).setUserId("User").setClientId("Client")
				.setOneUseToken(false).build().serialize();

		TokenInfo tokenInfoTest = TokenInfo.deserialize(serialized);

		assertThat(tokenInfoTest.getTokenType()).isEqualTo(TokenType.TOKEN);
		assertThat(tokenInfoTest.getUserId()).isEqualTo("User");
		assertThat(tokenInfoTest.getClientId()).isEqualTo("Client");
		assertThat(tokenInfoTest.isOneUseToken()).isEqualTo(false);
	}

	@Test
	public void testOneTimeTokenDeserializer() {
		String serialized = TokenInfo.newBuilder().setType(TokenType.CODE).setUserId("User").setClientId("Client")
				.setOneUseToken(true).build().serialize();

		TokenInfo tokenInfoTest = TokenInfo.deserialize(serialized);

		assertThat(tokenInfoTest.getTokenType()).isEqualTo(TokenType.CODE);
		assertThat(tokenInfoTest.getUserId()).isEqualTo("User");
		assertThat(tokenInfoTest.getClientId()).isEqualTo("Client");
		assertThat(tokenInfoTest.isOneUseToken()).isEqualTo(true);
	}

}
