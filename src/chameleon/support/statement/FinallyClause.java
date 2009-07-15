package chameleon.support.statement;

import java.util.List;

import chameleon.core.statement.Statement;
import chameleon.util.Util;

/**
 * @author Marko van Dooren
 */
public class FinallyClause extends Clause<FinallyClause> {

  public FinallyClause(Statement statement) {
    super(statement);
  }


  /**
   * @return
   */
  public FinallyClause clone() {
    return new FinallyClause(getStatement().clone());
  }

 /*@
   @ also public behavior
   @
   @ post getExpression() != null ==> \result.contains(getExpression());
   @ post \result.size() == 1;
   @*/
  public List children() {
    return Util.createNonNullList(getStatement());
  }
}
