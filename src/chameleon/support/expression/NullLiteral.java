package chameleon.support.expression;

import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.Literal;
import chameleon.core.lookup.LookupException;
import chameleon.core.scope.Scope;
import chameleon.core.scope.UniversalScope;
import chameleon.core.validation.Valid;
import chameleon.core.validation.VerificationResult;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.Type;

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
