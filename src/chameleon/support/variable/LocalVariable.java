package chameleon.support.variable;

import java.util.List;

import org.rejuse.predicate.TypePredicate;

import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.element.Element;
import chameleon.core.expression.Expression;
import chameleon.core.lookup.LookupException;
import chameleon.core.scope.Scope;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementListContainer;
import chameleon.core.statement.StatementListScope;
import chameleon.core.variable.RegularVariable;
import chameleon.core.variable.VariableContainer;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeReference;

/**
 * @author Marko van Dooren
 */
public class LocalVariable extends RegularVariable<LocalVariable,VariableContainer,LocalVariable> {

  public LocalVariable(SimpleNameSignature sig, TypeReference type, Expression init) {
    super(sig, type, init);
  }

  protected LocalVariable cloneThis() {
    Expression expr = null;
    if(getInitialization() != null) {
      expr = getInitialization().clone();
    }
    return new LocalVariable(signature().clone(), (TypeReference)getTypeReference().clone(), expr);
  }

  public Scope scope() throws LookupException {
    List ancestors = ancestors();
    new TypePredicate(StatementListContainer.class).filter(ancestors);
    return new StatementListScope((StatementListContainer)ancestors.get(ancestors.size() - 1), (Statement)parent());
  }

	public LocalVariable actualDeclaration() throws LookupException {
		return this;
	}

}
