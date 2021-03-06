package chameleon.support.expression;

import chameleon.core.declaration.Declaration;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.ClassBody;
import chameleon.oo.type.Type;
import chameleon.support.member.simplename.method.NormalMethod;

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

  protected Type actualType() throws LookupException {
    return language(ObjectOrientedLanguage.class).voidType();
  }

//  public NormalMethod getMethod() throws LookupException {
//	   return nearestAncestor(Type.class).lexicalLookupStrategy().lookUp(selector());
//  }

  public <X extends Declaration> X getElement(DeclarationSelector<X> selector) throws LookupException {
	   return nearestAncestor(ClassBody.class).lexicalLookupStrategy().lookUp(selector);
  }

  protected ThisConstructorDelegation cloneInvocation(InvocationTarget target) {
    return new ThisConstructorDelegation();
  }

}
