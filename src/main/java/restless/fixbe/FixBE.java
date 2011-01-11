//
// FixBE.java
//

package restless.fixbe;

import restless.fixbe.view.EditorFrame;

public class FixBE {

  public static void main(String[] args) {
    EditorFrame editor = new EditorFrame();
    editor.loadDef("defs/dw1.def");
    editor.open("data/dw1.sav");
    editor.setVisible(true);
  }

}
