package dcgmsn.orm;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zkforge.json.simple.JSONObject;
import org.zkforge.timeline.data.OccurEvent;

@Entity
@Table (name="occur_events")
public class DCEvent implements Serializable{

	private static final long serialVersionUID = 5561139165046741497L;
	
	/** Id in the table */
	@Id @GeneratedValue
	@Column
	private Long tid;
	
	/** The people used */
	@ManyToOne
	private User user;
	
	@Column (name="start_date")
	private Date start;
	
	@Column (name="end_date")
	private Date end;
	
	@Column (length = 1024)
	private String text;
	
	@Column
	private Boolean duration;
	
	@Column (length = 2048)
	private String description;
	
	@Column (name = "image_url")
	private String imageUrl;
	
	@Column (name = "icon_url")
	private String iconUrl;
	
	@Column (name = "link_url")
	private String linkUrl;
	
	@Column
	private String color;
	
	@Column (name = "text_color")
	private String textColor;
	
	public DCEvent(){
		duration = true;
	}
	

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public Long getTid() {
		return tid;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setStart(java.util.Date start) {
		if(start == null){
			this.start = null;
		}else if(start instanceof Date)
			this.start = (Date)start;
		else
			this.start = new Date(start.getTime());
	}

	public java.util.Date getStart() {
		return start;
	}

	public void setEnd(java.util.Date end) {
		if(end == null){
			this.end = null;
		}else if(end instanceof Date)
			this.end = (Date)end;
		else
			this.end = new Date(end.getTime());
	}

	public java.util.Date getEnd() {
		return end;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	/** For override */
	public void setDuration(boolean duration) {
		this.duration = duration;
	}
	
	public void setDuration(Boolean duration) {
		this.duration = duration;
	}

	public Boolean getDuration() {
		return duration;
	}
	
	/** For override */
	public boolean isDuration() {
		return duration;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getTextColor() {
		return textColor;
	}
	
	/**
	 * Convert DCEvent to OccurEvent
	 * @param dce
	 * @return
	 */
	public static OccurEvent toOccurEvent(DCEvent dce){
		OccurEvent ret = new OccurEvent();
		ret.setColor(dce.getColor());
		ret.setDescription(dce.getDescription());
		ret.setDuration(dce.getDuration());
		ret.setEnd(dce.getEnd());
		ret.setIconUrl(dce.getIconUrl());
		ret.setImageUrl(dce.getImageUrl());
		ret.setLinkUrl(dce.getLinkUrl());
		ret.setStart(dce.getStart());
		ret.setText(dce.getText());
		ret.setTextColor(dce.getTextColor());	
		return ret;
	}
	
	/**
	 * Convert OccurEvent to DCEvent
	 * @param oe
	 * @param ret if not null, update the DCEvent
	 * @return
	 */
	public static DCEvent toDCEvent(OccurEvent oe,DCEvent ret){
		if(ret == null)
			ret = new DCEvent();
		ret.setColor(oe.getColor());
		ret.setDescription(oe.getDescription());
		ret.setDuration(oe.isDuration());
		if(oe.getEnd() != null)
			ret.setEnd(new java.sql.Date(oe.getEnd().getTime()));
		ret.setIconUrl(oe.getIconUrl());
		ret.setImageUrl(oe.getImageUrl());
		ret.setLinkUrl(oe.getLinkUrl());
		if(oe.getStart() != null)
			ret.setStart(new java.sql.Date(oe.getStart().getTime()));
		ret.setText(oe.getText());
		ret.setTextColor(oe.getTextColor());
		return ret;
	}
	
	
	@SuppressWarnings("unchecked")
	public String toString(){
		JSONObject json = new JSONObject();
		if (start != null)
			json.put("start", 
					org.zkforge.timeline.util.TimelineUtil.formatDateTime(start));
		if (end != null)
			json.put("end", 
					org.zkforge.timeline.util.TimelineUtil.formatDateTime(end));
		json.put("duration", Boolean.valueOf(duration));
		if (text != null && text.length() != 0)
			json.put("text", text);
		if (description != null && description.length() != 0)
			json.put("description", description);
		if (imageUrl != null && imageUrl.length() != 0)
			json.put("image", imageUrl);
		if (linkUrl != null && linkUrl.length() != 0)
			json.put("link", linkUrl);
		if (iconUrl != null && iconUrl.length() != 0)
			json.put("icon", iconUrl);

		if (color != null && color.length() != 0)
			json.put("color", color);
		if (textColor != null && textColor.length() != 0)
			json.put("textColor", textColor);
		
		return json.toString();
	}
}
