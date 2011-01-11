//
// SimpleRule.java
//

package restless.fixbe.data;

/** A simple rule (e.g., string, integer, boolean). */
public abstract class SimpleRule extends Rule {

  enum ByteOrder { LITTLE, BIG }
  private ByteOrder byteOrder = ByteOrder.LITTLE;

  private int bytes = 1;

  public ByteOrder getByteOrder() { return byteOrder; }

  public int getByteCount() { return bytes; }

	/** Helper method for decoding words. */
	protected int decodeWord(byte[] b, int offset, int count) {
    int value = 0;
    if (byteOrder == ByteOrder.BIG) {
    	for (int i=0; i<count; i++) {
    		value <<= 8;
    		value |= (0xff & b[offset + i]);
    	}
    }
    else { // byteOrder == ByteOrder.LITTLE
    	for (int i=count-1; i>=0; i--) {
    		value <<= 8;
    		value |= (0xff & b[offset + i]);
    	}
    }
		return value;
  }

}
