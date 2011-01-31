package chameleon.support.member.simplename;

import java.util.List;

import org.rejuse.logic.ternary.Ternary;

import chameleon.core.declaration.DeclarationContainer;
import chameleon.core.declaration.DeclarationWithParametersHeader;
import chameleon.core.declaration.Signature;
import chameleon.core.declaration.SimpleNameDeclarationWithParametersSignature;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.MethodInvocation;
import chameleon.core.lookup.LookupException;
import chameleon.core.lookup.SimpleNameCrossReferenceWithArgumentsSelector;
import chameleon.core.member.MoreSpecificTypesOrder;
import chameleon.core.method.Method;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.oo.type.Type;

public abstract class SimpleNameMethodInvocation<I extends SimpleNameMethodInvocation<I,D>, D extends Method> extends MethodInvocation<I,D> {

  public SimpleNameMethodInvocation(InvocationTarget target, String name) {
    super(target);
    setName(name);
  }
  
  protected Type actualType() throws LookupException {
    try {
			Method method = getElement();
			if (method != null) {
			  return method.returnType();
			}
			else {
			  getElement();
			  throw new LookupException("Could not find method of constructor invocation", this);
			}
		} catch (LookupException e) {
//			e.printStackTrace();
//			getMethod();
			throw e;
		}
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


  public abstract class SimpleNameMethodSelector extends SimpleNameCrossReferenceWithArgumentsSelector<D> {
  	
//  	private int _nameHash = SimpleNameMethodInvocation.this._methodName.hashCode();
    
  	@Override
    public boolean selectedRegardlessOfName(D declaration) throws LookupException {
  		boolean result = declaration.is(language(ObjectOrientedLanguage.class).CONSTRUCTOR) != Ternary.TRUE;
  		if(result) {
  			result = super.selectedRegardlessOfName(declaration);
  		}

  		return result;
    }
  	
  	@Override
  	public int nbActualParameters() {
  		return SimpleNameMethodInvocation.this.nbActualParameters();
  	}
  	
  	@Override
  	public List<Type> getActualParameterTypes() throws LookupException {
  		return SimpleNameMethodInvocation.this.getActualParameterTypes();
  	}
  	
  	public String name() {
  		return SimpleNameMethodInvocation.this.name();
  	}
  }
}
