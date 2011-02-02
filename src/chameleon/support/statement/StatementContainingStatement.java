package chameleon.support.statement;

import org.rejuse.association.SingleAssociation;

import chameleon.core.element.Element;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementImpl;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;

/**
 * @author Marko van Dooren
 */
public abstract class StatementContainingStatement<E extends StatementContainingStatement> extends StatementImpl<E> {
  
  public StatementContainingStatement(Statement<E> statement) {
    setStatement(statement);
  }

	/**
	 * STATEMENT
	 */
  
	private SingleAssociation<StatementContainingStatement,StatementImpl> _statement = new SingleAssociation<StatementContainingStatement,StatementImpl>(this);

  
  public void setStatement(Statement<E> statement) {
    _statement.connectTo(statement.parentLink());
  }
  
  public void removeStatement() {
    _statement.connectTo(null);
  }
  
  public Statement<E> getStatement() {
    return _statement.getOtherEnd();
  }
  
  @Override
  public VerificationResult verifySelf() {
		return checkNull(getStatement(), "Statement is missing", Valid.create());
  }
}
