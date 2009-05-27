package chameleon.support.statement;

import org.rejuse.association.Reference;

import chameleon.core.element.Element;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;

/**
 * @author Marko van Dooren
 */

public interface ForInit<E extends ForInit, P extends Element> extends ExceptionSource<E,P> {

	public Reference parentLink();

  /**
   * @param statement
   * @return
   */
  public int getIndexOf(Statement statement);

  /**
   * @return
   */
  public int getNbStatements();
    
}
