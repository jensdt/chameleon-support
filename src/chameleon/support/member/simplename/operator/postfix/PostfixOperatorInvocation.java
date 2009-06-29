package chameleon.support.member.simplename.operator.postfix;




import chameleon.core.context.DeclarationSelector;
import chameleon.core.expression.InvocationTarget;
import chameleon.support.member.simplename.SimpleNameMethodInvocation;


/**
 * @author Marko van Dooren
 */
public class PostfixOperatorInvocation extends SimpleNameMethodInvocation<PostfixOperatorInvocation,PostfixOperator> {

	/**
	 * @param target
	 * @param name
	 */
 /*@
   @ public behavior
   @
   @ pre target != null; 
   @*/
	public PostfixOperatorInvocation(String name, InvocationTarget target) {
		super(target, name);
	}

  protected PostfixOperatorInvocation cloneInvocation(InvocationTarget target) {
    return new PostfixOperatorInvocation(getName(), target);
  }
  
  @Override
  public DeclarationSelector<PostfixOperator> selector() {
    return new SimpleNameMethodSelector() {
      @Override
      public Class<PostfixOperator> selectedClass() {
        return PostfixOperator.class;
      }

    };
  }

}
