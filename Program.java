package weixin;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
public class Program {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//
		// 第三方回复公众平台
		//

		// 需要加密的明文
		String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
		String token = "pamtest";
		String timestamp = "1409304348";
		String nonce = "xxxxxx";
		String appId = "wxb11529c136998cb6";
		String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
		System.out.println("加密后: " + mingwen);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(mingwen);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);

		Element root = document.getDocumentElement();
		NodeList nodelist1 = root.getElementsByTagName("Encrypt");
		NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

		String encrypt = nodelist1.item(0).getTextContent();
		String msgSignature = nodelist2.item(0).getTextContent();

		String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
		String fromXML = String.format(format, encrypt);

		System.out.println("解密format明文: " + format);
		System.out.println("解密fromXML后明文: " + fromXML);
		//
		// 公众平台发送消息给第三方，第三方处理
		//

		// 第三方收到公众号平台发送的消息
		String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
		System.out.println("解密后明文: " + result2);
		
		//pc.verifyUrl(null, null, null, null);
		
		token = "jszhongqu";
		encodingAesKey = "3eHOXP34P5hnrm2PXd1fAfFHdKcKhGJ52bwscBxM8ky";
		appId = "wxf205d57bec948723";
		msgSignature = "2e543bf6261776793fce3cd96fc46841a143b6de";
		timestamp = "1471918062";
		nonce = "105231497";
		encrypt = "kIPKkcObfyrg7nlpuYeykJ0s7hGCw0TEA0CaW+lZp3OMkb1LvDfJg4RGJ8ychxSddnYKEVRl1C6Z3E+xVCdEBFLWE/HXaEUpT7zpjs1jqBpb+WEPPB3Em1sGEC4JfdmvSvyGKX/hNhIuqYuOdBaglYpDtPI3v/msyK9ihIq0rzKSMB7g3ysqVJmLJsA0pfqwMlEhD7FvqJfgDDLhXJx6CLWTRfeOB7YAKGnPXfFGkq9mAP8XbSbmhA41LkH5YOK9nXFUjHoMn1H/PHJaduGo3Ve/1HHzr2mm/auIed6zMVeL5+ifCr62PWXElsxV8aYW3iifRlz3l/f/WiUwtF7ReF6aVrmV1Cvfkhfjom7m8Erqr+waazp59aJq4stq/jtGhlaBnNTojPaQp6rjh8TenjnAJ2qWG45lG7C2sqPf3ko=";
		encrypt = "esWidSF/nm9SvIUrtylrGqAb4BEeNthni9AfDQm9MqZomKfh8+egiguTsNDc8mY30CWa3T57kvEC0GiYg/w+WBKZmpQn79utGfroqbTipkbVvaqbUJHpa5wcvTT6lrbzqDjjAtpf5cVbdLGIgGOR0b0nDv1G6YD+ONw9FYMY43UJyhfC+1Y8Ek7SXX0R446gUttnRXsDUMVcVD9dqWCrIbPzhqrWPCc+4SHDWaDPJ3hzGStonlAO3i4Iczf8W8l9PuSrW9GtL/IyLsoUBSeODEc6vi/5eB+DQUOh9zlM7iMgLI4/AKMF35mchjGe6pdZedbmfnF8YY0Mtw9hEXf+ZwntyOvlCjkAG4n9TYmNlwdQkvZ8XxMZIfuH9HeuPX0zi5PLaeQgJvcCeZCDNntHPAnEXjlFDksuR6dRjyIcNHE=";	
		fromXML = String.format(format, encrypt);
		
		WXBizMsgCrypt pcc = new WXBizMsgCrypt(token, "3eHOXP34P5hnrm2PXd1fAfFHdKcKhGJ52bwscBxM8ky", "wxf205d57bec948723");
		 System.out.println("SSSS" + pcc.decrypt(encrypt) );
		 
		 //System.out.println("解密fromXML : " + fromXML);
		 //result2 =  pcc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
		
		 //System.out.println("解密后明文2: " + result2);
		
		
		
		
		
		
		
		
		
		
	}

}
