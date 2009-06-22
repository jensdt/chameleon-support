package chameleon.support.statement;

import chameleon.core.type.Type;
import chameleon.core.type.TypeDescendantImpl;

/**
 * An abstract class of labels for a switch statement.
 * 
 * @author Marko van Dooren
 */

public abstract class SwitchLabel<E extends SwitchLabel> extends TypeDescendantImpl<E,SwitchCase> {

  public SwitchLabel() {
	}

  public Type getNearestType() {
    return parent().getNearestType();
  }
  
  public abstract E clone();

}
