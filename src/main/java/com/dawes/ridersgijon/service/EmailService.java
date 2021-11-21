package com.dawes.ridersgijon.service;

public interface EmailService {

	/**
	 * @param to
	 * @param subject
	 * @param text
	 */
	void sendSimpleMessage(String to, String subject, String text);

}