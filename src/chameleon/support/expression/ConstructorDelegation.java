package chameleon.support.expression;

import java.util.List;

import org.rejuse.logic.ternary.Ternary;

import chameleon.core.declaration.Signature;
import chameleon.core.expression.Invocation;
import chameleon.core.expression.InvocationTarget;
import chameleon.core.lookup.DeclarationSelector;
import chameleon.core.lookup.LookupException;
import chameleon.core.method.MethodSignature;
import chameleon.core.relation.WeakPartialOrder;
import chameleon.core.type.Type;
import chameleon.oo.language.ObjectOrientedLanguage;
import chameleon.support.member.MoreSpecificTypesOrder;
import chameleon.support.member.simplename.method.NormalMethod;

/**
 * @author Marko van Dooren
 */

public abstract class ConstructorDelegation<E extends ConstructorDelegation>
    extends Invocation<E, NormalMethod> {

  public ConstructorDelegation(InvocationTarget target) {
    super(target);
  }

  protected class NamelessConstructorSelector extends
      DeclarationSelector<NormalMethod> {
    @Override
    public boolean selectedRegardlessOfName(NormalMethod declaration)
        throws LookupException {
    	return declaration.is(language(ObjectOrientedLanguage.class).CONSTRUCTOR) == Ternary.TRUE;
    }
    
    @Override
    public boolean selectedBasedOnName(Signature signature) throws LookupException {
      boolean result = false;
      if(signature instanceof MethodSignature) {
      	MethodSignature sig = (MethodSignature)signature;
        List<Type> actuals = getActualParameterTypes();
        List<Type> formals = sig.parameterTypes();
        if (MoreSpecificTypesOrder.create().contains(actuals, formals)) {
          result = true;
        }
      }
      return result;
    }

    // @FIXME: generalize along with implementation in
    // SimpleNameMethodInvocation
    @Override
    public WeakPartialOrder<NormalMethod> order() {
      return new WeakPartialOrder<NormalMethod>() {
        @Override
        public boolean contains(NormalMethod first, NormalMethod second)
            throws LookupException {
          return MoreSpecificTypesOrder.create().contains(first.header()
              .getParameterTypes(), second.header().getParameterTypes());
        }
      };
    }

    @Override
    public Class<NormalMethod> selectedClass() {
      return NormalMethod.class;
    }

		@Override
		public String selectionName() {
			return nearestAncestor(Type.class).signature().name();
		}
  }
  
  @Override
  public DeclarationSelector<NormalMethod> createSelector() {
    return new NamelessConstructorSelector();
  }

}
