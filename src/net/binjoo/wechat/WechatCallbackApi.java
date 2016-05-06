package net.binjoo.wechat;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.binjoo.utils.SHA1;

@SuppressWarnings("serial")
public class WechatCallbackApi extends HttpServlet {
	// 自定�?token
	private String TOKEN = "123456";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 随机字符�
		String echostr = request.getParameter("echostr");
		// 时间�?
		String timestamp = request.getParameter("timestamp");
		// 随机�?
		String nonce = request.getParameter("nonce");

		try{
		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排�?
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		
		// 确认请求来至微信
		if (digest.equals(signature)) {
			System.out.println(TOKEN);
			System.out.println("chenggong");
			response.getWriter().print(echostr);
		}else{
			System.out.println("ʧ��");
			response.getWriter().print(echostr);
		}
		}catch(Exception e ){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
