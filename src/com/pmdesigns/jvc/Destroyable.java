package com.pmdesigns.jvc;
import org.checkerframework.dataflow.qual.SideEffectFree;

public interface Destroyable {

	@SideEffectFree
	void destroy();
}
