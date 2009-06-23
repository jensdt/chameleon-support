package chameleon.support.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;

import chameleon.core.MetamodelException;
import chameleon.core.expression.Assignable;
import chameleon.core.expression.Expression;
import chameleon.core.expression.ExpressionContainer;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.scope.Scope;
import chameleon.core.type.Type;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class AssignmentExpression extends Expression<AssignmentExpression> implements ExpressionContainer<AssignmentExpression,ExpressionContainer> {

  /**
   * @param first
   * @param second
   */
  public AssignmentExpression(Assignable var, Expression value) {
    
	  setVariable(var);
    setValue(value);
  }

	/**
	 * VARIABLE
	 */
	private Reference<AssignmentExpression,Assignable> _variable = new Reference<AssignmentExpression,Assignable>(this);


  public Assignable getVariable() {
    return _variable.getOtherEnd();
  }

  public void setVariable(Assignable var) {
  	if(var != null) {
      _variable.connectTo(var.parentLink());
  	}
  	else {
  		_variable.connectTo(null);
  	}
  }

	/**
	 * VALUE
	 */
	private Reference<AssignmentExpression,Expression> _value = new Reference<AssignmentExpression,Expression>(this);

  public Expression getValue() {
    return (Expression)_value.getOtherEnd();
  }

  public void setValue(Expression expression) {
    _value.connectTo(expression.parentLink());
  }

  public Type getType() throws MetamodelException {
    return getVariable().getType();
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    return (target instanceof AssignmentExpression) &&
           ((Expression)getVariable()).compatibleWith((Expression)((AssignmentExpression)target).getVariable()) &&
           getValue().compatibleWith(((AssignmentExpression)target).getValue());
  }

  public AssignmentExpression clone() {
    return new AssignmentExpression(getVariable().clone(), ((Expression<? extends Expression>)getValue()).clone());
  }

  public List children() {
    List result = Util.createNonNullList(getVariable());
    Util.addNonNull(getValue(), result);
    return result;
  }

  public Set getDirectExceptions() throws MetamodelException {
    return new HashSet();
  }

//  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
//    return getVariable().getAccessibilityDomain().intersect(getValue().getAccessibilityDomain());
//  }

  
}
