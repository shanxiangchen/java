package com.base;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

public interface Externalizable extends Serializable {
	
	void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;

	void writeExternal(ObjectOutput out) throws IOException;

}
