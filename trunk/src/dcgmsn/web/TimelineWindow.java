package dcgmsn.web;

import java.util.List;

import org.zkforge.timeline.Bandinfo;
import org.zkforge.timeline.Timeline;
import org.zkforge.timeline.data.OccurEvent;
import org.zkoss.zk.ui.Component;

import dcgmsn.orm.DCEvent;
import dcgmsn.orm.User;
import dcgmsn.service.TimelineService;
import dcgmsn.service.UserService;
import dcgmsn.util.TimelineUtil;

public class TimelineWindow extends BaseWindow {

	private static final long serialVersionUID = -8955155239026401343L;
	
	private UserService userService;
	private TimelineService tlService;
	
	public TimelineWindow(){
		userService = (UserService)DCBeanFactory.getBean("UserService");
		tlService = (TimelineService)DCBeanFactory.getBean("TimelineService");
	}
	
	public void load(){
		//User user = (User)getDesktop().getSession().getAttribute("user");
		User user = userService.sureGetUser("test@test.com");
		Component hb = getComponent("/timelineWnd/firstHbox");
		Component timeline = TimelineUtil.createTimeLine(user);
		hb.appendChild(timeline);
		
		List<OccurEvent> oes = (List<OccurEvent>)timeline.getAttribute("oes");
		for(Object obj : timeline.getChildren()){
			if(obj instanceof Bandinfo){
				((Bandinfo)obj).addManyOccurEvents(oes.iterator());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addEvent(OccurEvent ooe){
		DCEvent oe = DCEvent.toDCEvent(ooe, null);
		Component hb = getComponent("/timelineWnd/firstHbox");
		Timeline tl = (Timeline)hb.getChildren().get(0);
		
		User user = (User)tl.getAttribute("user");
		oe.setUser(user);
		tlService.addDCEvent(oe);
		
		//add event to the list
		((List<DCEvent>)tl.getAttribute("events")).add(oe);
	}
	
	@SuppressWarnings("unchecked")
	public void removeEvent(DCEvent oe){
		Component hb = getComponent("/timelineWnd/firstHbox");
		Timeline tl = (Timeline)hb.getChildren().get(0);
		//add event to the list
		((List<DCEvent>)tl.getAttribute("events")).remove(oe);		
		tlService.removeDCEvent(oe);
	}

}
