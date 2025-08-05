package com.pmdesigns.jvc;
import org.checkerframework.dataflow.qual.SideEffectFree;


/**
 * This exception can be thrown during request processing to
 * cause an non-standard (ie not HTTP_OK) response to be returned.
 * For example, this is how you can cause a redirect response.
 */
public final class NonStandardResponseException extends RuntimeException {

	public final int httpCode;
	public final String arg;
	
	@SideEffectFree
	public NonStandardResponseException(int httpCode, String arg) {
		this.httpCode = httpCode;
		this.arg = arg;
	}
}
