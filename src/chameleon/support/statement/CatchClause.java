package chameleon.support.statement;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rejuse.association.Reference;
import org.rejuse.predicate.PrimitivePredicate;

import chameleon.core.MetamodelException;
import chameleon.core.declaration.Declaration;
import chameleon.core.declaration.DeclarationSelector;
import chameleon.core.namespace.NamespaceElement;
import chameleon.core.statement.CheckedExceptionList;
import chameleon.core.statement.Statement;
import chameleon.core.statement.StatementContainer;
import chameleon.core.type.Type;
import chameleon.core.variable.FormalParameter;
import chameleon.core.variable.VariableContainer;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class CatchClause extends Clause<CatchClause> implements VariableContainer<CatchClause,TryStatement>, StatementContainer<CatchClause,TryStatement> {
  
  public CatchClause(FormalParameter exc, Statement statement) {
    super(statement);
    setException(exc);
  }
  
  /*************
   * EXCEPTION * 
   *************/
  
	private Reference<CatchClause,FormalParameter> _exceptionLink = new Reference<CatchClause,FormalParameter>(this);

	public Reference getExceptionLink() {
    return _exceptionLink;
  }
  
  public void setException(FormalParameter exc) {
    _exceptionLink.connectTo(exc.parentLink());
  }
  
  public FormalParameter getExceptionParameter() {
    return _exceptionLink.getOtherEnd();
  }

//  /***********
//   * Context *
//   ***********/
//  
//	private Reference<CatchClause,StaticContext> _context = new Reference<CatchClause,StaticContext>(this);
//
//	public Context getContext(Element element) {
//    return _context.getOtherEnd();
//  }
//
//  public void setContext(StaticContext context) {
//  	if(context != null) {
//  		_context.connectTo(context.getParentLink());
//  	} else {
//  		_context.connectTo(null);
//  	}
//  }

  public CatchClause clone() {
    return new CatchClause((FormalParameter)getExceptionParameter().clone(), getStatement().clone());
  }

  /**
   * @return
   */
  public boolean isValid() throws MetamodelException {
    try {
      CheckedExceptionList cel = parent().getStatement().getCEL();
      Collection checkedExceptionTypes = cel.getExceptions();
      return new PrimitivePredicate() {
        public boolean eval(Object o) throws MetamodelException {
          return ((Type)o).assignableTo(getExceptionParameter().getType());
        }
      }.exists(checkedExceptionTypes);
    }
    catch (MetamodelException e) {
      throw e;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Error();
    }
  }

 /*@
   @ also public behavior
   @
   @ post getStatement() != null ==> \result.contains(getStatement());
   @ post getExceptionParameter() != null ==> \result.contains(getExceptionParameter());
   @*/
  public List children() {
    List result = Util.createNonNullList(getExceptionParameter());
    Util.addNonNull(getStatement(), result);
    return result;
  }
  
  public Set<Declaration> declarations() {
    Set<Declaration> result = new HashSet<Declaration>();
    result.add(getExceptionParameter());
    return result;
  }
  
  //COPIED FROM chameleon.core.type.Type
  public <T extends Declaration> Set<T> declarations(DeclarationSelector<T> selector) throws MetamodelException {
    Set<Declaration> tmp = declarations();
    Set<T> result = selector.selection(tmp);
    return result;
  }

	public NamespaceElement variableScopeElement() {
		return this;
	}

}
