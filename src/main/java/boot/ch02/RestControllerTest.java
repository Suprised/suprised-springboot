package boot.ch02;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTest {

	@Autowired
	private UserDao userDao;

	@RequestMapping("/rest")
	@ResponseBody
	public String home(String id) {
		if (StringUtils.isEmpty(id)) {
			Iterator<User> users = userDao.findAll().iterator();
			StringBuffer sb = new StringBuffer();
			if (users != null) {
				while(users.hasNext()) {
					sb.append(users.next().toString()).append(",");
				}
			}
			return sb.toString();
		} else {
			return userDao.findOne(Long.valueOf(id)).toString();
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RestControllerTest.class, args);
	}
}
