package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.element.Element;

/**
 * @author Marko van Dooren
 */
public class BreakStatement extends JumpStatement<BreakStatement> {

	public BreakStatement() {
		super(null);
	}
	
  public BreakStatement(String label) {
    super(label);
  }

  public BreakStatement clone() {
    return new BreakStatement(getLabel());
  }

  public List<Element> children() {
    return new ArrayList();
  }

}
