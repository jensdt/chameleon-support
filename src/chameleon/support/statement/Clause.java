package chameleon.support.statement;

import org.rejuse.association.Reference;

import chameleon.core.lookup.LookupException;
import chameleon.core.namespacepart.NamespaceElementImpl;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;

/**
 * @author Marko van Dooren
 */
public abstract class Clause<E extends Clause> extends NamespaceElementImpl<E,TryStatement> implements ExceptionSource<E,TryStatement> {

  public Clause(Statement statement) {
    setStatement(statement);
  }

	/**
	 * Statement
	 */
	private Reference<Clause,Statement> _statement = new Reference<Clause,Statement>(this);

  public void setStatement(Statement statement) {
    _statement.connectTo(statement.parentLink());
  }

  public void removeStatement() {
    _statement.connectTo(null);
  }

  public Statement getStatement() {
    return _statement.getOtherEnd();
  }

  public CheckedExceptionList getCEL() throws LookupException {
    CheckedExceptionList result = new CheckedExceptionList();
    if(getStatement() != null) {
      result.absorb(getStatement().getCEL());
    }
    return result;
  }

  public CheckedExceptionList getAbsCEL() throws LookupException {
    CheckedExceptionList result = new CheckedExceptionList();
    if(getStatement() != null) {
      result.absorb(getStatement().getAbsCEL());
    }
    return result;
  }

}
