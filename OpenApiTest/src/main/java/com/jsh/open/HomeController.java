package com.jsh.open;

import java.io.File;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page. 
 */
@Controller
public class HomeController {
	@Inject
	private ServletContext context;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}
	// 두 가지 url 방식으로 접근이 가능 그에따른 location.href의 결과가 다름 주의 
	//'http://localhost:8080/open/javascript' 
	//'http://localhost:8080/open/javascript/'
	@RequestMapping(value = "/javascript", method = RequestMethod.GET)
	public String open(Locale locale, Model model) {
		//logger.info("redirect:open/home.html", locale);
		//return "redirect:open/home.html";
		/*파일 경로에 있는 파일 가져오기*/

		File filesForder = new File(context.getRealPath("/resources/javascript")); 
		//logger.info(filesForder.getAbsolutePath(), locale);
		/*파일 경로에 있는 파일 리스트 fileList[] 에 넣기*/
		File []filesList = filesForder.listFiles();
//		System.out.println(filesForder.getAbsolutePath());
//		System.out.println(filesList.length);
		Arrays.sort(filesList);;
		JSONObject fileNameList = new JSONObject();
		//Map<String, String> fileNameList = new HashMap<String, String>();
		/*fileList에 있는거 for 문 돌려서 출력*/
		for(File file : filesList) {
			if(file.isFile() && file.getName().endsWith(".html") && file.getName().contains("_")) {
				int Idx = file.getName() .lastIndexOf(".");
				String fileName = file.getName().substring(0, Idx);
				String signal = fileName.substring(0,fileName.lastIndexOf("_"));
				String signal_ko = fileName.substring(fileName.lastIndexOf("_")+1);
				fileNameList.put(signal, signal_ko);
//				System.out.println("signal : " + signal);
//				System.out.println("signal_ko : " + signal_ko);
			}

		}
		model.addAttribute("fileNameList", fileNameList);
		logger.info("javaScript_home", locale);
		return "javaScript_home";
	}

}
