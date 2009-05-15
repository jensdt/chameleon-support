package chameleon.support.expression;

import chameleon.core.MetamodelException;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.scope.Scope;
import chameleon.core.type.Type;
import chameleon.core.type.TypeReference;

/**
 * @author Marko van Dooren
 * @author Tim Laeremans
 */
public class ThisLiteral extends LiteralWithTypeReference<ThisLiteral> {

  public ThisLiteral() {
    super("this");
  }

  public Type getType() throws MetamodelException {
    if (getTypeReference() == null) {
      return getNearestType();
    }
    else {
      return getTypeReference().getType();
    }
  }

  public boolean superOf(InvocationTarget target) throws MetamodelException {
    return (target instanceof ThisLiteral) && ((ThisLiteral)target).getType().assignableTo(getType());
  }

  public ThisLiteral clone() {
    ThisLiteral result = new ThisLiteral();
    result.setTypeReference((TypeReference)getTypeReference().clone());
    return result;
  }

//  public AccessibilityDomain getAccessibilityDomain() throws MetamodelException {
//    return getType().getTypeAccessibilityDomain();
//  }
}
