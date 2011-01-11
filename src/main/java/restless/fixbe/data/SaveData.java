//
// SaveData.java
//

package restless.fixbe.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class SaveData {

	private String activeSave;
	private byte[] data;
	private boolean gzip;

	public String getActiveSave() { return activeSave; }
	public byte[] getData() { return data; }

	public void clear() {
		activeSave = null;
		data = null;
		gzip = false;
	}

	public void open(String path, int expectedSize) throws IOException {
		activeSave = path;
    data = new byte[expectedSize];
		final File file = new File(path);
    final int size = (int) file.length();
    InputStream in = new FileInputStream(file);
    gzip = size != expectedSize;
    if (gzip) {
    	// data may be GZIP-compressed (e.g., Nestopia)
    	in = new GZIPInputStream(in);
    }
    in.read(data, 0, size);
    in.close();
	}

}
