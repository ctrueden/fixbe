//
// Alphabet.java
//

package restless.fixbe.data;

/** A mapping from integer values to characters. */
public class Alphabet {

  public static final Alphabet ASCII = new Alphabet("ascii",
  	"??????????????????????????????? !\"#$%&'()*+,-./0123456789:;<=>?" +
  	"@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~");

	private String label;
	private String chars;

  public Alphabet(String label, String chars) {
  	this.label = label;
  	this.chars = chars;
  }

  public String getLabel() { return label; }

  public char getChar(int value) {
  	if (value < 0 || value >= chars.length()) return '?';
  	return chars.charAt(value);
  }

}
