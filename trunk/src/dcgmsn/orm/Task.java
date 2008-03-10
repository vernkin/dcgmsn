package dcgmsn.orm;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="tasks")
public class Task implements Serializable{

	private static final long serialVersionUID = -6666111651837264307L;
	
	public static final int NO_REPEAT = -1;
	
	@Id @GeneratedValue
	@Column
	private Long id;
	
	@ManyToOne
	private User owner;
	
	@Column (nullable=false)
	private String name;
	
	@Column (length=1024)
	private String description;
	
	/** 
	 * Like Calendar.MONTH Calendar.Date 
	 * if NO_REPEAT,not repeat
	 */
	@Column (name="repeat_unit")
	private int repeatUnit;

	/** Every X days */
	@Column (name="repeat_num")
	private int repeatNum;
	
	@Column (name="start_time")
	private Date startTime;
	
	/** If null, not expired date */
	@Column (name="end_time")
	private Date endDate;
	
	@ManyToOne
	private Programme programe;
	
	@Column
	private String params;
	
	@Column (length=2048)
	private String log;
	
	@Column (name="last_result")
	private String lastResult;
	
	@Column (name="next_run_time")
	private Date nextRunTime;

	@ManyToOne
	private LabelType type;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getOwner() {
		return owner;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setRepeatUnit(int repeatUnit) {
		this.repeatUnit = repeatUnit;
	}

	public int getRepeatUnit() {
		return repeatUnit;
	}

	public void setRepeatNum(int repeatNum) {
		this.repeatNum = repeatNum;
	}

	public int getRepeatNum() {
		return repeatNum;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setPrograme(Programme programe) {
		this.programe = programe;
	}

	public Programme getPrograme() {
		return programe;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getParams() {
		return params;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getLog() {
		return log;
	}

	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}

	public String getLastResult() {
		return lastResult;
	}

	public void setNextRunTime(Date nextRunTime) {
		this.nextRunTime = nextRunTime;
	}

	public Date getNextRunTime() {
		return nextRunTime;
	}

	public void setType(LabelType type) {
		this.type = type;
	}

	public LabelType getType() {
		return type;
	}
	
	public boolean isRepeat(){
		return repeatUnit != NO_REPEAT;
	}
}
