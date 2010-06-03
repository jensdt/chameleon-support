package chameleon.support.member.simplename;

import java.util.List;

import org.rejuse.logic.ternary.Ternary;

import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.declaration.Signature;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.NonConstructorInvocation;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.TwoPhaseDeclarationSelector;
import chameleon.core.method.Method;
import chameleon.core.method.MethodHeader;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.Type;
import chameleon.support.member.MoreSpecificTypesOrder;

public abstract class SimpleNameMethodInvocation<I extends SimpleNameMethodInvocation, D extends Method> extends NonConstructorInvocation<I,D> {

  public SimpleNameMethodInvocation(InvocationTarget target, String name) {
    super(target);
    setName(name);
  }
  
  
  
  /********
   * NAME *
   ********/

  private String _methodName;

  public String name() {
    return _methodName;
  }

  public void setName(String method) {
    _methodName = method;
  }

//  public D getMethod() throws LookupException {
//	    D result = lexicalContext().lookUp(selector());
//	    if(result == null) {
//	      throw new LookupException();
//	    }
//	    return result;
//  }


  public abstract class SimpleNameMethodSelector extends TwoPhaseDeclarationSelector<D> {
  	
//  	private int _nameHash = SimpleNameMethodInvocation.this._methodName.hashCode();
    
  	@Override
    public boolean selectedRegardlessOfName(D declaration) throws LookupException {
  		boolean result = declaration.is(language(ObjectOrientedLanguage.class).CONSTRUCTOR) != Ternary.TRUE;
  		if(result) {
  			Signature signature = declaration.signature();
  			if(signature instanceof SimpleNameMethodSignature) {
  				SimpleNameMethodSignature sig = (SimpleNameMethodSignature)signature;
  				if(sig.nbTypeReferences() == nbActualParameters()) {
  					List<Type> actuals = getActualParameterTypes();
  					List<Type> formals = sig.parameterTypes();
  					result = MoreSpecificTypesOrder.create().contains(actuals,formals);
  				} else {
  					result = false;
  				}
  			}
  		}
  		return result;
    }
  	
  	@Override
  	public String selectionName(DeclarationContainer container) {
  		return name();
  	}
    
  	@Override
    public boolean selectedBasedOnName(Signature signature) throws LookupException {
  		boolean result = false;
  		if(signature instanceof SimpleNameMethodSignature) {
  			SimpleNameMethodSignature sig = (SimpleNameMethodSignature)signature;
  			result = sig.name().equals(name()); // (_nameHash == sig.nameHash()) && 
  		}
  		return result;
    }

    @Override
    public WeakPartialOrder<D> order() {
      return new WeakPartialOrder<D>() {
        @Override
        public boolean contains(D first, D second)
            throws LookupException {
          return MoreSpecificTypesOrder.create().contains(((MethodHeader) first.header()).formalParameterTypes(), ((MethodHeader) second.header()).formalParameterTypes());
        }
      };
    }
  }
}
