package chameleon.support.expression;

import java.util.HashSet;
import java.util.Set;

import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.InvocationTargetWithTarget;
import chameleon.core.expression.NamedTarget;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.LookupStrategy;
import chameleon.core.scope.Scope;
import chameleon.core.scope.UniversalScope;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.type.Type;

/**
 * @author Marko van Dooren
 */
public class SuperTarget extends InvocationTargetWithTarget<SuperTarget> {

  public SuperTarget() {
		
	}

public boolean compatibleWith(InvocationTarget target) throws LookupException {
    return superOf(target) || target.subOf(this);
  }

  public boolean subOf(InvocationTarget target) throws LookupException {
    return false;
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    return target instanceof SuperTarget;
  }

  public SuperTarget clone() {
    SuperTarget result = new SuperTarget();
    if(getTarget() != null) {
      result.setTarget(getTarget().clone());
    }
    return result;
  }

  public void prefix(InvocationTarget target) {
    // Do nothing.
  }

  public void substituteParameter(String name, Expression expr) throws LookupException {
    // Do nothing.
  }

  public void prefixRecursive(InvocationTarget target) throws LookupException {
    prefix(target);
  }

  public Set<Type> getDirectExceptions() throws LookupException {
    return new HashSet<Type>();
  }

  public Set<Type> getExceptions() throws LookupException {
    return new HashSet<Type>();
  }

  public CheckedExceptionList getCEL() {
    return new CheckedExceptionList();
  }

  public CheckedExceptionList getAbsCEL() {
    return new CheckedExceptionList();
  }

  public Scope getAccessibilityDomain() throws LookupException {
    return new UniversalScope();
  }

  private Type getType() throws LookupException {
    if(getTarget() != null) {
      return (Type)((NamedTarget)getTarget()).getElement();
    } else {
      return nearestAncestor(Type.class);
    }
  }

  public LookupStrategy targetContext() throws LookupException {
    return getType().targetContext();
  }

}
