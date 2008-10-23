package chameleon.support.expression;

import chameleon.core.MetamodelException;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.type.Type;
import chameleon.support.member.simplename.method.RegularMethod;

/**
 * @author Marko van Dooren
 */
public class ThisConstructorDelegation extends ConstructorDelegation<ThisConstructorDelegation> {

  public ThisConstructorDelegation(){
    super(null);
  }

  protected String getName() {
    return "this";
  }

  public Type getType() throws MetamodelException {
    return language().voidType();
  }

  public RegularMethod getMethod() throws MetamodelException {
	   return ((Type)getNearestType()).lexicalContext().lookUp(selector());
  }

  public boolean superOf(InvocationTarget target) {
    return false;
  }

  protected ThisConstructorDelegation cloneInvocation(InvocationTarget target) {
    return new ThisConstructorDelegation();
  }

}
