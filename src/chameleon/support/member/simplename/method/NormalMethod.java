package chameleon.support.member.simplename.method;


import chameleon.core.method.Method;
import chameleon.core.method.MethodHeader;
import chameleon.core.method.MethodSignature;
import chameleon.core.method.RegularMethod;
import chameleon.core.type.TypeReference;
import chameleon.support.member.simplename.SimpleNameMethodSignature;


/**
 * @author Marko van Dooren
 */
public class NormalMethod<E extends RegularMethod<E,H,S>, H extends MethodHeader<H, E, S>, S extends MethodSignature> extends RegularMethod<E,H,S>  {

  public NormalMethod(H header, TypeReference returnType) {
    super(header, returnType);
  }


  public boolean sameKind(Method other) {
  	return(other instanceof NormalMethod);
  }  

  protected E cloneThis() {
    return (E) new NormalMethod(header().clone(), (TypeReference)getReturnTypeReference().clone());
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

}


