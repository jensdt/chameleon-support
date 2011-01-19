package chameleon.support.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.antlr.runtime.RecognitionException;
import org.rejuse.association.Association;
import org.rejuse.io.DirectoryScanner;

import antlr.TokenStreamException;
import chameleon.core.compilationunit.CompilationUnit;
import chameleon.core.element.Element;
import chameleon.core.lookup.LookupException;
import chameleon.exception.ChameleonProgrammerException;
import chameleon.input.ModelFactory;
import chameleon.input.NoLocationException;
import chameleon.input.ParseException;
import chameleon.input.SourceManager;
import chameleon.oo.type.Type;
import chameleon.plugin.PluginImpl;
import chameleon.util.concurrent.CallableFactory;
import chameleon.util.concurrent.FixedThreadCallableExecutor;
import chameleon.util.concurrent.QueuePollingCallableFactory;
import chameleon.util.concurrent.UnsafeAction;

public abstract class ModelFactoryUsingANTLR extends PluginImpl implements ModelFactory {

	public void initializeBase(Collection<File> base) throws IOException, ParseException {
		addToModel(base);
		initializePredefinedElements();
	}
	
	protected abstract void initializePredefinedElements();

	/**
	 * Return the top of the metamodel when parsing the given file.
	 *
	 * @param files
	 *            A set containing all .java files that should be parsed.
	 * @pre The given set may not be null | files != null
	 * @pre The given set may only contain effective files | for all o in files: |
	 *      o instanceof File && | ! ((File)o).isDirectory()
	 * @return The result will not be null | result != null
	 * @return All given files will be parsed and inserted into the metamodel
	 *
	 * @throws TokenStreamException
	 *             Something went wrong
	 * @throws RecognitionException
	 *             Something went wrong
	 * @throws MalformedURLException
	 *             Something went wrong
	 * @throws FileNotFoundException
	 *             Something went wrong
	 * @throws RecognitionException 
	 */
	public void addToModel(File file) throws IOException, ParseException {
    // The file name is used in the lexers and parser to
    // give more informative error messages
    String fileName = file.getName();

    // The constructor throws an FileNotFoundException if for some
    // reason the file can't be read.
    InputStream fileInputStream = new FileInputStream(file);
    
		parse(fileInputStream, fileName, new CompilationUnit());
	
	}

	public void addToModel(Collection<File> files) throws IOException, ParseException {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(availableProcessors);
		final BlockingQueue<File> fileQueue = new ArrayBlockingQueue<File>(files.size(), true, files);

	  UnsafeAction<File,Exception> unsafeAction = new UnsafeAction<File,Exception>() {
		public void actuallyPerform(File file) throws IOException, ParseException {
  			  addToModel(file);
		} 
	};
	CallableFactory factory = new QueuePollingCallableFactory<File,Exception>(unsafeAction,fileQueue);
	try {
		new FixedThreadCallableExecutor<Exception>(factory).run();
	} catch (IOException e) {
		throw e;
	} catch (ParseException e) {
		throw e;
	}
	catch (Exception e) {
		e.printStackTrace();
	}
//
//		
//		
//		
//		try {
//		for(int i=0; i < availableProcessors; i++) {
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//					boolean ongoing = true;
//					while(ongoing) {
//						File file = fileQueue.poll();
//						if(file != null) {
//							try {
//								addToModel(file);
//							} catch (IOException e) {
//								throw new ChameleonProgrammerException(e); 
//							} catch (ParseException e) {
//								throw new ChameleonProgrammerException(e); 
//							}
//						} else {
//							ongoing = false;
//						}
//					}
//				}
//			});
//		}
//		} catch(ChameleonProgrammerException exc) {
//			Throwable cause = exc.getCause();
//			if(cause instanceof IOException) {
//				throw (IOException)cause;
//			} else if(cause instanceof ParseException) {
//				throw (ParseException) cause;
//			}
//			else {
//				throw exc;
//			}
//		}
//		executor.shutdown();
//		try {
//			executor.awaitTermination(100, TimeUnit.HOURS);
//		} catch (InterruptedException e) {
//			throw new ChameleonProgrammerException();
//		}
	}
	
	
//  // Single threaded
//	public void addToModel(Collection<File> files) throws IOException, ParseException {
////	  int count = 0;
//	  for (File file : files) {
////	    System.out.println(++count + " Parsing "+ file.getAbsolutePath());
//	  	addToModel(file);
//	  }
//	
//	}

	public void addToModel(String source, CompilationUnit cu) throws ParseException {
	    String name = "document";
	    InputStream inputStream = new StringBufferInputStream(source);
	    try {
				parse(inputStream, name, cu);
			} catch (IOException e) {
				// cannot happen if we work with a String
				throw new ChameleonProgrammerException("IOException while parsing a String.", e);
			}
	}

	private void parse(InputStream inputStream, String fileName, CompilationUnit cu) throws IOException, ParseException {
		try {
			ChameleonParser parser = getParser(inputStream, fileName);
			cu.disconnectChildren();
			parser.setCompilationUnit(cu);
			parser.compilationUnit();
		} catch (RecognitionException e) {
			throw new ParseException(e,cu);
		}
	}
	
	public abstract ChameleonParser getParser(InputStream inputStream, String fileName) throws IOException;

	/**
	 * @param pathList		The directories to from where to load the cs-files
	 * @param extension     Only files with this extension will be loaded
	 * @param recursive	    Wether or not to also load cs-files from all sub directories
	 * @return A set with all the cs-files in the given path
	 */
	public static Set<File> sourceFiles(List<String> pathList, String extension, boolean recursive) {
	    Set<File> result = new HashSet();
	    for(String path: pathList) {
	      result.addAll(new DirectoryScanner().scan(path, extension, recursive));
	    }
	    return result;
	}

	public <P extends Element> void reParse(Element<?,P> element) throws ParseException {
		CompilationUnit compilationUnit = element.nearestAncestor(CompilationUnit.class);
		boolean done = false;
		while((element != null) && (! done)){
			try {
		    SourceManager manager = language().plugin(SourceManager.class);
		    String text = manager.text(element);
		    Element newElement = parse(element, text);
		    if(newElement != null) {
		      // Use raw type here, we can't really type check this.
		      Association childLink = element.parentLink().getOtherRelation();
		      childLink.replace(element.parentLink(), newElement.parentLink());
		      done = true;
		    }
			} catch(ParseException exc) {
			} catch (NoLocationException e) {
			}
			Element<?,?> old = element;
			element = element.parent();
			if(element == null) {
				throw new ParseException(old.nearestAncestor(CompilationUnit.class));
			}

		}
	}
	
	protected abstract <P extends Element> Element parse(Element<?,P> element, String text) throws ParseException;


}
