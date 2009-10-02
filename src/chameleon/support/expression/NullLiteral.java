package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.Literal;
import chameleon.core.language.ObjectOrientedLanguage;
import chameleon.core.lookup.LookupException;
import chameleon.core.scope.Scope;
import chameleon.core.scope.UniversalScope;
import chameleon.core.type.Type;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;

/**
 * @author Marko van Dooren
 */
public class NullLiteral extends Literal<NullLiteral> {

  public NullLiteral(){
    super("null");
  }

  protected Type actualType() throws LookupException {
	  return language(ObjectOrientedLanguage.class).getNullType();
  }

  public boolean superOf(InvocationTarget target) throws LookupException {
    return target instanceof NullLiteral;
  }

  public NullLiteral clone() {
    return new NullLiteral();
  }

  public Scope getAccessibilityDomain() throws LookupException {
    return new UniversalScope();
  }

	@Override
	public VerificationResult verifySelf() {
		return Valid.create();
	}

}
