package serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fpt.com.Product;
import fpt.com.SerializableStrategy;

public abstract class SerializableStrategyClass implements SerializableStrategy {

	public abstract String getFilename();

}
