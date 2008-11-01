package chameleon.support.member.simplename;

import java.util.List;

import org.rejuse.logic.ternary.Ternary;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.expression.NonConstructorInvocation;
import chameleon.core.method.Method;
import chameleon.core.method.MethodSignature;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.core.type.Type;
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

  public String getName() {
    return _methodName;
  }

  public void setName(String method) {
    _methodName = method;
  }

//  public D getMethod() throws MetamodelException {
//	    D result = lexicalContext().lookUp(selector());
//	    if(result == null) {
//	      throw new MetamodelException();
//	    }
//	    return result;
//  }


  public abstract class SimpleNameMethodSelector extends DeclarationSelector<D> {
    
    public D filter(Declaration declaration) throws MetamodelException {
      D result = null;
      if(selectedClass().isInstance(declaration)) {
        D decl = (D)declaration;
        List<Type> actuals = getActualParameterTypes();
        List<Type> formals = ((MethodSignature)decl.signature()).getParameterTypes();
        if((decl.is(language().CONSTRUCTOR) != Ternary.TRUE) &&
        	 new MoreSpecificTypesOrder().contains(actuals,formals) && 
           ((SimpleNameMethodSignature)decl.signature()).getName().equals(getName())) {
          result = decl;
        }
      }
      return result;
    }

    @Override
    public WeakPartialOrder<D> order() {
      return new WeakPartialOrder<D>() {
        @Override
        public boolean contains(D first, D second)
            throws MetamodelException {
          return new MoreSpecificTypesOrder().contains(((MethodSignature) first.signature()).getParameterTypes(), ((MethodSignature) second.signature()).getParameterTypes());
        }
      };
    }
  }
}
