package chameleon.support.member.simplename.operator;


import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.DeclarationWithParametersSignature;
import chameleon.core.method.Method;
import chameleon.core.method.RegularMethod;
import chameleon.oo.type.TypeReference;

/**
 * @author Marko van Dooren
 */
public abstract class Operator<E extends Operator<E,H,S>, H extends DeclarationWithParametersHeader<H,S>, S extends DeclarationWithParametersSignature> extends RegularMethod<E,H,S> {

  public Operator(H header, TypeReference returnType) {
    super(header, returnType);
  }

}
