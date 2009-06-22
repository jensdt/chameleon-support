package chameleon.support.statement;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class ContinueStatement extends JumpStatement<ContinueStatement> {

	public ContinueStatement() {
		super(null);
	}
	
  public ContinueStatement(String label) {
    super(label);
  }

  public ContinueStatement clone() {
    return new ContinueStatement(getLabel());
  }

  public List children() {
    return new ArrayList();
  }
}
