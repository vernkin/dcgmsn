package dcgmsn.robot;

import java.util.List;

import com.incesoft.botplatform.sdk.RobotException;
import com.incesoft.botplatform.sdk.RobotHandler;
import com.incesoft.botplatform.sdk.RobotMessage;
import com.incesoft.botplatform.sdk.RobotServer;
import com.incesoft.botplatform.sdk.RobotSession;
import com.incesoft.botplatform.sdk.RobotUser;

import dcgmsn.net.BaiduCalculator;
import dcgmsn.net.BaiduKnow;
import dcgmsn.net.BaiduNews;
import dcgmsn.net.CodeSearch;
import dcgmsn.net.InternetResource;
import dcgmsn.robot.util.MsgTypeUtil;

/**
 * @author LiBo
 */
public class XHandler implements RobotHandler {
	
	
	public final String commandList =   " 四则表达式 ------ 计算\r" +
										/*" 行程安排示例: \r" + 
										"    1. 九点五十吃饭\r" +
									    "    2. 4月5号到六月九号八点闭关 \r" +*/
									    " 只有英文字符 ----- 搜索代码使用情况.\r" +
									    " 新闻XXXX ----- 搜索新闻.\r" +
									    " 其它 ----- 提问问题.\r" +
									    " help 或 ? ------ 输出帮助.\r"; 
										
	private RobotServer server = null;
	
	InternetResource news = new BaiduNews();
	InternetResource calculator = new BaiduCalculator();
	InternetResource codeSearch = new CodeSearch();
	InternetResource know = new BaiduKnow();
	
	String[] helps = {"help","h","?","？"};
	
	public XHandler(RobotServer s) {
		server = s;
	}
	
    /**
     * '打开对话窗'事件.
     * @param	session 	对话
     */
    public void sessionOpened(RobotSession session) {
    	System.out.println("EVENT: sessionOpened (" + session.getUser().getClientID() + "," + session.getUser().getStatus() + ")");
        try {
            if (RobotSession.OPEN_MODE_CONV_OPEN == session.getOpenMode()) {
                session.send(commandList);		//发送欢迎语.
            } else if(RobotSession.OPEN_MODE_ROBOT == session.getOpenMode()) {
            	try {
					Thread.sleep(3000) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	session.send("i'm a bot!") ;
            }
        } catch (RobotException e) {
            e.printStackTrace();
        }
    }

    /**
     * '对话窗关闭'事件.
     * @param	session		对话
     */
    public void sessionClosed(RobotSession session) {
    	System.out.println("EVENT: sessionClosed");

    }

    /**
     * '收到消息'事件
     * @param	session		对话
     * @param	message		消息
     */
    public void messageReceived(RobotSession session, RobotMessage message) {
        
    	System.out.println("EVENT: messageReceived");
        try {
        	//取得命令.
        	String command = message.getString();
        	
        	//生成一个将要发送给用户的消息对象.
        	RobotMessage msg = session.createMessage();
            
        	String ret = null;
        	//如果是帮助信息, 则发送菜单.
            if (isHelp(command)) {
            	ret = commandList;
            }
            
            if(ret== null && command.startsWith("新闻")){
            	if(command.length() == 2){
            		ret = "请输入新闻的关键字";
            	}else{
            		ret = news.getAnswer(command.substring(2));
            	}
            }
            
            if(ret== null && MsgTypeUtil.isCalculateStr(command)){
            	ret = calculator.getAnswer(command);
            }
            
            if(ret== null && MsgTypeUtil.isCodeStr(command)){
            	ret = codeSearch.getAnswer(command);
            }
            
            if(ret == null){
            	ret = know.getAnswer(command);
            }
            
            session.send(ret);
        } catch (Exception e) {
            e.printStackTrace();
            try {
				session.send(e.getLocalizedMessage());
			} catch (RobotException e1) {}
        }
    }

    /**
     * '闪屏振动'事件.
     * @param	session		对话
     */
    public void nudgeReceived(RobotSession session) {
    	System.out.println("EVENT: nudgeReceived");
    	try {
			//session.send("nudge received!");
			session.sendActivity("http://www.baidu.com","Baidu Page");
		} catch (RobotException e) {
			e.printStackTrace();
		}	
    }

    /**
     * '活动邀请被接受'事件.
     * @param	session		对话
     */
    public void activityAccepted(RobotSession session) {
    	System.out.println("EVENT: activityAccepted");
    }

    /**
     * '活动邀请被拒绝'事件.
     * @param	session		对话
     */
    public void activityRejected(RobotSession session) {
    	System.out.println("EVENT: activityRejected");
    }
    
    /**
     * '异常'事件.
     * @param	session		对话
     * @param	cause		异常
     */
	public void exceptionCaught(RobotSession session, Throwable cause) {
		System.out.println("SERVER ERROR: " + cause.getMessage());
	}
	
	/**
	 * '用户添加机器人'事件.
	 * @param robot 机器人帐号
	 * @param user  用户帐号
	 * @deprecated Only available in enterprise version 
	 */
	public void userAdd(String robot,String user) {
		System.out.println("EVENT: userAdd (" + user + ")");
	}

	/**
	 * '用户删除机器人'事件.
	 * @param robot 机器人帐号
	 * @param user	用户帐号
	 */
	public void userRemove(String robot, String user) {
		System.out.println("EVENT: userRemove (" + user + ")");
	}
	
	/**
	 * '活动关闭'事件.
	 * @param	session		对话
	 */
	public void activityClosed(RobotSession session) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * '活动被加载'事件
	 * @param	session		对话
	 */
	public void activityLoaded(RobotSession session) {
		// TODO Auto-generated method stub
	}

	/**
	 * '活动发送消息到机器人'事件
	 * @param	session		对话
	 * @param	data		数据
	 * @deprecated Only available in enterprise version 
	 */
	public void activityReceived(RobotSession session, String data) {
	}

	/** 
	 * '背景共享被接受'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void backgroundAccepted(RobotSession session) {
	}

	/**
	 * '背景共享被拒绝'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void backgroundRejected(RobotSession session) {
	}

	/**
	 * '背景传送完毕'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void backgroundTransferEnded(RobotSession session) {
	}

	/**
	 * '文件被接受'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void fileAccepted(RobotSession session) {
	}
	
	/**
	 * '传送文件被拒绝'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void fileRejected(RobotSession session) {
	}

	/**
	 * '传送文件结束'事件.
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void fileTransferEnded(RobotSession session) {
	}

	/**
	 * '用户加入对话'事件.
	 * @param	session		对话
	 * @param	user		用户
	 * @deprecated Only available in enterprise version 
	 */
	public void userJoined(RobotSession session, RobotUser user) {	
	}

	/**
	 * '用户离开'事件.
	 * @param	session		对话
	 * @param	user		用户
	 * @deprecated Only available in enterprise version 
	 */
	public void userLeft(RobotSession session, RobotUser user) {
	}

	
	/**
	 * '用户接受视频'事件
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void webcamAccepted(RobotSession session) {
	}
	
	/**
	 * '用户拒绝视频'事件
	 * @param	session		对话
	 * @deprecated Only available in enterprise version 
	 */
	public void webcamRejected(RobotSession session) {
	}

	public void userUpdated(RobotUser user) {
		System.out.println("EVENT: userUpdated (" + user + ")");
	}

	public void personalMessageUpdated(String robot, String user,
			String personalMessage) {
		System.out.println("EVENT: personalMessageUpdated (" + robot + ", " + user + ", " + personalMessage + ")");
		
	}

	public void contactListReceived(String robot, List<RobotUser> friendList) {
		
		System.out.println("EVENT: friendListReceived(" + robot + ", list.size()=" + friendList.size() + ")") ;
		
		for(RobotUser u : friendList) {
			System.out.println(u) ;
		}
		
	}

	public boolean isHelp(String s){
		s = s.toLowerCase();
		for(String h : helps){
			if(h.equals(s))
				return true;
		}
		return false;
	}
}
