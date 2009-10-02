package chameleon.support.statement;

import org.rejuse.association.SingleAssociation;

import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.core.namespace.NamespaceElementImpl;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.ExceptionSource;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementImpl;

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
	private SingleAssociation<Clause,Statement> _statement = new SingleAssociation<Clause,Statement>(this);

  public void setStatement(Statement statement) {
    _statement.connectTo(statement.parentLink());
  }

  public void removeStatement() {
    _statement.connectTo(null);
  }

  public Statement statement() {
    return _statement.getOtherEnd();
  }

  public CheckedExceptionList getCEL() throws LookupException {
    CheckedExceptionList result = new CheckedExceptionList();
    if(statement() != null) {
      result.absorb(statement().getCEL());
    }
    return result;
  }

  public CheckedExceptionList getAbsCEL() throws LookupException {
    CheckedExceptionList result = new CheckedExceptionList();
    if(statement() != null) {
      result.absorb(statement().getAbsCEL());
    }
    return result;
  }

}
