package chameleon.support.variable;

import java.util.List;

import org.rejuse.predicate.TypePredicate;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.SimpleNameSignature;
import chameleon.core.expression.Expression;
import chameleon.core.scope.Scope;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementListContainer;
import chameleon.core.statement.StatementListScope;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;
import chameleon.core.variable.RegularVariable;
import chameleon.core.variable.VariableContainer;

/**
 * @author Marko van Dooren
 */
public class LocalVariable extends RegularVariable<LocalVariable,VariableContainer> {

  public LocalVariable(SimpleNameSignature sig, TypeReference type, Expression init) {
    super(sig, type, init);
  }

  public Type getNearestType() {
    return parent().getNearestType();
  }

  protected LocalVariable cloneThis() {
    Expression expr = null;
    if(getInitialization() != null) {
      expr = getInitialization().clone();
    }
    return new LocalVariable(signature().clone(), (TypeReference)getTypeReference().clone(), expr);
  }

  public Scope scope() throws MetamodelException {
    List ancestors = ancestors();
    new TypePredicate(StatementListContainer.class).filter(ancestors);
    return new StatementListScope((StatementListContainer)ancestors.get(ancestors.size() - 1), (Statement)parent());
  }
	
}
