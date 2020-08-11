package mvcMem.control;

import mvcMem.action.Action;
import mvcMem.action.IndexAction;


public class ActionFactory {

	
private static ActionFactory factory;
	
	private ActionFactory() {	}
	
	
	public static synchronized ActionFactory getInstance() {
		if( factory == null) {
			factory = new ActionFactory();
		}
	return factory;	
	}
	
	public Action getAction(String cmd) {
		Action action = null;
		
		switch(cmd) {
		
		case "index" :
			action = new IndexAction();
		break;
		
		
		
		
		}
	
		return action;
	}
	
}
