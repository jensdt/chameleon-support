package chameleon.support.input;

import org.antlr.runtime.RecognitionException;

import chameleon.core.compilationunit.CompilationUnit;


public interface ChameleonParser {
	
	 public Object compilationUnit() throws RecognitionException;

	 public void setCompilationUnit(CompilationUnit compilationUnit);
}
