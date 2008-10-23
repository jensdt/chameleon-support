package chameleon.support.expression;

import java.util.HashSet;
import java.util.Set;

import chameleon.core.MetamodelException;
import chameleon.core.accessibility.AccessibilityDomain;
import chameleon.core.context.TargetContext;
import chameleon.core.expression.Expression;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.InvocationTargetContainer;
import chameleon.core.expression.InvocationTargetWithTarget;
import chameleon.core.expression.NamedTarget;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.type.Type;
import chameleon.support.property.accessibility.All;

/**
 * @author Marko van Dooren
 */
public class SuperTarget extends InvocationTargetWithTarget<SuperTarget> implements InvocationTargetContainer<SuperTarget, InvocationTargetContainer> {

  public SuperTarget() {
		
	}

public boolean compatibleWith(InvocationTarget target) throws MetamodelException {
    return superOf(target) || target.subOf(this);
  }

  public boolean subOf(InvocationTarget target) throws MetamodelException {
    return false;
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
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

  public void substituteParameter(String name, Expression expr) throws MetamodelException {
    // Do nothing.
  }

  public void prefixRecursive(InvocationTarget target) throws MetamodelException {
    prefix(target);
  }

  public Set<Type> getDirectExceptions() throws MetamodelException {
    return new HashSet<Type>();
  }

  public Set<Type> getExceptions() throws MetamodelException {
    return new HashSet<Type>();
  }

  public CheckedExceptionList getCEL() {
    return new CheckedExceptionList(language());
  }

  public CheckedExceptionList getAbsCEL() {
    return new CheckedExceptionList(language());
  }

  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
    return new All();
  }

  private Type getType() throws MetamodelException {
    if(getTarget() != null) {
      return (Type)((NamedTarget)getTarget()).getElement();
    } else {
      return getNearestType();
    }
  }

  public TargetContext targetContext() throws MetamodelException {
    return getType().targetContext();
  }

}
