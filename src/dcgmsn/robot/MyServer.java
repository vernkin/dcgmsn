/*
 * Created on 2006-3-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package dcgmsn.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.incesoft.botplatform.sdk.RobotException;
import com.incesoft.botplatform.sdk.RobotServer;
import com.incesoft.botplatform.sdk.RobotServerFactory;

/**
 * @author LiBo
 */
public class MyServer {
    
    private RobotServer server;

    
    /**
     * 启动机器人
     */
    public void start() {
    	Properties prop = new Properties() ;
    	
        try {
        	//加载配置文件
        	prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties")) ;
        	
        	System.out.println(prop.getProperty("host"));
        	
        	//生成对象,参数1表示服务器地址,参数2表示端口.
            server = RobotServerFactory.getInstance().createRobotServer(prop.getProperty("host"), 
            		Integer.parseInt(prop.getProperty("port")));
            
            //连接到机器人平台
            server.setReconnectedSupport(true);
            
            //设置一个消息侦听器
            server.setRobotHandler(new XHandler(server));
        	
            //登录,参数1表示spid, 参数2表示密码.
            server.login(prop.getProperty("spid"), prop.getProperty("sppwd"));
        } catch (RobotException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 关闭机器人
     */
    public void stop() {
        server.logout();	//关闭连接
    }
    
    public static void main(String[] args) {
    	try{
    	MyServer s = new MyServer();	//生成一个机器人实例
       
        s.start();	//启动
		BufferedReader reader = 
				new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			reader.readLine();
			Thread.sleep(500) ;
		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
//		s.stop();	//关闭
        
    }

}
