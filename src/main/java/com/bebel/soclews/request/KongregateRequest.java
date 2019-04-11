package com.bebel.soclews.request;

/**
 * requete kongregate contenant le username et le code secret
 *
 */
public class KongregateRequest extends GeneralRequest {
	private String username;
	private String secretPass;

	public String getUsername() {
		return username;
	}
	public void setUsername(final String username) {
		this.username = username;
	}

	public String getSecretPass() {
		return secretPass;
	}
	public void setSecretPass(final String secretPass) {
		this.secretPass = secretPass;
	}
}
