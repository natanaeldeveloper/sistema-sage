package br.com.projeto.estoque.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Criptografar {
	public static String encriptografar(String senha) {
		String retorno = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
			retorno = hash.toString(16);
		} catch (Exception e) {
		}
		return retorno;
	}
}
