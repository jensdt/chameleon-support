package chameleon.support.expression;

import java.util.ArrayList;
import java.util.List;

import chameleon.core.expression.InvocationTargetContainer;

/**
 * @author Tim Laeremans
 *
 * An empty array index is of the form [] or [,]
 * When getDimension() == 1 it corresponds to []
 *                     == 2 it corresponds to [,]
 *                     == 2 it corresponds to [,,]
 *                     ...
 * 
 */
public class EmptyArrayIndex extends ArrayIndex<EmptyArrayIndex>{
	
	
	public EmptyArrayIndex(int dimensions){
		setDimension(dimensions);
	}
	
	private int _dimensions = 0;
	
	public void addDimension(){
		_dimensions++;
	}
	
	public void removeDimension(){
		_dimensions--;
	}

	public void setDimension(int dimension){
		this._dimensions = dimension;
	}
	public int getDimension(){
		return _dimensions;
	}
	
	public List children() {
		return new ArrayList();
	}
	
	public boolean equals(Object other){
		return (other instanceof EmptyArrayIndex) &&
				((EmptyArrayIndex)other).getDimension() == getDimension();
	}
	
	public EmptyArrayIndex clone(){
		return new EmptyArrayIndex(getDimension());
	}
	
	public String toString(){
	  	StringBuffer result = new StringBuffer();
	  	int dimension = getDimension();
	  	result.append("[");
	  	while(dimension > 1){
	  		result.append(",");
	  		dimension--;
	  	}
	  	result.append("]");
	  	return result.toString();
	}

}
