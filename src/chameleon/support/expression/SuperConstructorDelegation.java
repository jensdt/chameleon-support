package chameleon.support.expression;

import java.util.List;

import org.rejuse.logic.ternary.Ternary;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.method.Method;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.core.type.Type;
import chameleon.support.member.MoreSpecificTypesOrder;
import chameleon.support.member.simplename.method.NormalMethod;

/**
 * @author Marko van Dooren
 */
public class SuperConstructorDelegation extends ConstructorDelegation<SuperConstructorDelegation> {

  public SuperConstructorDelegation(){
    super(null);
  }

  public Type getType() throws MetamodelException {
    return language().voidType();
  }
  
  // @FIXME: does not work with multiple inheritance. Call is ambiguous.
  public NormalMethod getMethod() throws MetamodelException {
	    return getNearestType().getDirectSuperTypes().get(0).lexicalContext().lookUp(selector());
  }
  
  public boolean superOf(InvocationTarget target) {
    return false;
  }

  protected SuperConstructorDelegation cloneInvocation(InvocationTarget target) {
    return new SuperConstructorDelegation();
  }

}
