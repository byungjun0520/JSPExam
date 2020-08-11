package mvcMem.control;

import mvcMem.action.Action;
import mvcMem.action.IdCheckAction;
import mvcMem.action.IndexAction;
import mvcMem.action.RegFormAction;
import mvcMem.action.ZipCheckAction;


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
		
		case "regForm" :
			action = new RegFormAction();
		break;
		
		case "idCheck" :
			action = new IdCheckAction();
			break;
		case "zipCheck" :
			action = new ZipCheckAction();
			break;
		
		
		
		
		
		
		
		}
	
		return action;
	}
	
}
