package com.iteye.wwwcomy.entity.exception;

public class HttpException extends RuntimeException {

	private static final long serialVersionUID = -3350262420867359131L;

	public HttpException() {
	}

	public HttpException(String s) {
		super(s);
	}

	public HttpException(String s, Throwable cause) {
		super(s, cause);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

}
