package serialization;

import fpt.com.SerializableStrategy;

public abstract class SerializableStrategyClass implements SerializableStrategy {

	public abstract String getFilename();

	public abstract void setCount(long value, long savedId);

	public abstract long getHighestId();
}
