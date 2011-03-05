package chameleon.support.member.simplename.method;


import org.rejuse.logic.ternary.Ternary;

import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.DeclarationWithParametersSignature;
import chameleon.core.lookup.LookupException;
import chameleon.core.member.HidesRelation;
import chameleon.core.member.Member;
import chameleon.core.method.Method;
import chameleon.core.method.RegularMethod;
import chameleon.oo.language.ObjectOrientedLanguage;
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
	
//	   private Reference<RegularMethod,ConstructorDelegation> _invokingConstructor = new Reference<RegularMethod,ConstructorDelegation>(this);
//
//	   //STUDENTSCREWUP
//	   
//	   /**
//	    * In C# a constructor can be specified to be executed before
//	    * the statements in this constructor are executed, this can
//	    * be done by the keyword "this" or "base" followed by the 
//	    * necessary parameterlist
//	    */
//	   public void setInvokingConstructor(ConstructorDelegation literal){
//	   	_invokingConstructor.connectTo(literal.getParentLink());
//	   }
//	   
//	   public ConstructorDelegation getInvokingConstructor(){
//	   	return (ConstructorDelegation)_invokingConstructor.getOtherEnd();
//	   } 

  public HidesRelation<? extends Member> hidesRelation() {
		return _hidesSelector;
  }
  
  private static HidesRelation<NormalMethod> _hidesSelector = new HidesRelation<NormalMethod>(NormalMethod.class) {
		
		public boolean containsBasedOnRest(NormalMethod first, NormalMethod second) throws LookupException {
			boolean result = (first.is(((ObjectOrientedLanguage)first.language(ObjectOrientedLanguage.class)).INSTANCE) == Ternary.TRUE) && 
      first.signature().sameParameterBoundsAs(second.signature());
			return result;
		}
	};

}


