package chameleon.support.member.simplename.method;


import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.DeclarationWithParametersSignature;
import chameleon.core.lookup.LookupException;
import chameleon.core.member.HidesRelation;
import chameleon.core.member.Member;
import chameleon.core.method.Method;
import chameleon.core.method.RegularMethod;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.Type;
import chameleon.oo.type.TypeReference;


/**
 * @author Marko van Dooren
 */
public class NormalMethod<E extends RegularMethod<E,H,S,NormalMethod>, H extends DeclarationWithParametersHeader<H, S>, S extends DeclarationWithParametersSignature> extends RegularMethod<E,H,S,NormalMethod>  {

  public NormalMethod(H header, TypeReference returnType) {
    super(header, returnType);
  }
  
	public boolean sameKind(Method other) {
  	return(other instanceof NormalMethod);
  }  

	protected E cloneThis() {
    return (E) new NormalMethod(header().clone(), (TypeReference)returnTypeReference().clone());
  }
	
  public HidesRelation<? extends Member> hidesRelation() {
		return _hidesSelector;
  }
  
  private static HidesRelation<NormalMethod> _hidesSelector = new HidesRelation<NormalMethod>(NormalMethod.class) {
		
		public boolean containsBasedOnRest(NormalMethod first, NormalMethod second) throws LookupException {
			boolean result = first.isTrue(((ObjectOrientedLanguage)first.language(ObjectOrientedLanguage.class)).INSTANCE) 
			              && first.signature().sameParameterBoundsAs(second.signature()) 
			              && ((Type)first.nearestAncestor(Type.class)).subTypeOf((Type)second.nearestAncestor(Type.class));
			return result;
		}
	};

}


