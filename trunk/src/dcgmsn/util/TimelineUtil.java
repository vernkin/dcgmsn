package dcgmsn.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.zkforge.timeline.Bandinfo;
import org.zkforge.timeline.Hotzone;
import org.zkforge.timeline.Timeline;
import org.zkforge.timeline.data.OccurEvent;

import dcgmsn.orm.DCEvent;
import dcgmsn.orm.User;
import dcgmsn.service.TimelineService;
import dcgmsn.web.DCBeanFactory;

/**
 * Create the timeline
 */
public class TimelineUtil {
	public static final TimeZone GMT8_ZONE =TimeZone.getTimeZone("GMT+08");
	
	private static TimelineService tlService = 
		(TimelineService)DCBeanFactory.getBean("TimelineService");
	
	public static Timeline createTimeLine(User user){
		Timeline timeline = new Timeline();
		timeline.setId("timeline");
		timeline.setHeight("300px");
		timeline.setWidth("100%");
		
		timeline.setAttribute("user", user);
		
		Date current = Calendar.getInstance().getTime();		
		Bandinfo monthinfo = new Bandinfo();
		
		/*<bandinfo width="70%" id="b1" intervalUnit="month"
			intervalPixels="100" eventSourceUrl="../data/example2.xml"
			timeZone="${zone}" date="${current}">*/
		monthinfo.setTimeZone(GMT8_ZONE);
		monthinfo.setWidth("70%");
		monthinfo.setId("monthinfo");
		monthinfo.setIntervalUnit("month");
		monthinfo.setIntervalPixels(100);
		monthinfo.setDate(current);
		//monthinfo.setEventSourceUrl("../data/example2.xml");
		
		Bandinfo yearinfo = new Bandinfo();
		/*<bandinfo id="b2" timeZone="${zone}" date="${current}"
			width="30%" intervalUnit="year" intervalPixels="200" syncWith="b1"
			eventSourceUrl="../data/example2.xml" trackHeight="0.5" trackGap="0.2"
			showEventText="false">*/
		yearinfo.setTimeZone(GMT8_ZONE);
		yearinfo.setWidth("30%");
		yearinfo.setId("yearinfo");
		yearinfo.setIntervalUnit("year");
		yearinfo.setIntervalPixels(200);
		yearinfo.setDate(current);
		yearinfo.setSyncWith("monthinfo");
		yearinfo.setTrackHeight((float)0.5);
		yearinfo.setTrackGap((float)0.2);
		yearinfo.setShowEventText(false);
		//yearinfo.setEventSourceUrl("../data/example2.xml");
		
		List<DCEvent> events = tlService.getDCEventByUser(user);
		if(events == null)
			events = new ArrayList<DCEvent>();
		
		List<OccurEvent> oes = new ArrayList<OccurEvent>();
		//FIXME not load the data from the library
		for(DCEvent evt : events){
			oes.add(DCEvent.toOccurEvent(evt));
		}
		
		timeline.setAttribute("events", events);
		timeline.setAttribute("oes", oes);
		
		timeline.appendChild(monthinfo);
		timeline.appendChild(yearinfo);
		
		return timeline;
	}
	
	
	public static Hotzone createHotzone(Date begin,Date end){
		//Adjust the begin and end date
		if(begin.after(end)){
			Date tmp = begin;
			begin = end;
			end = tmp;
		}
		
		Hotzone zone = new Hotzone();
		zone.setStart(begin);
		zone.setEnd(end);
		
		//the minutes
		long mins = (long)((end.getTime()-begin.getTime()) / 60000);
		if(mins < 120){ // 2 hours
			zone.setMagnify(5);
			zone.setUnit("hour");
		}else if(mins < 2880){ // 2 days
			zone.setMagnify(7);
			zone.setUnit("day");
		}else{
			zone.setMagnify(10);
			zone.setUnit("week");
		}
		
		return zone;
	}
	
}
