package jp.co.internous.cony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.cony.model.domain.MstUser;
import jp.co.internous.cony.model.form.UserForm;
import jp.co.internous.cony.model.mapper.MstUserMapper;
import jp.co.internous.cony.model.session.LoginSession;

@Controller
@RequestMapping("/cony/user")
public class UserController {
	
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	
	@RequestMapping("/")
	public String index(Model m) {
		
		m.addAttribute("loginSession",loginSession);
		
		return "register_user";
	}
    
	
	//重複確認ボタン押下時
	//エラーならユーザー名重複エラーダイアログ
	//OKならそのまま
	@ResponseBody
	@RequestMapping(value="/duplicatedUserName")
	public boolean check(@RequestBody UserForm f) {
		int count = userMapper.findCountByUserName(f.getUserName());
		
		return count>0;
	}

	@ResponseBody
	@RequestMapping("/register")
	public boolean register(@RequestBody UserForm f) {
	
		MstUser user = new MstUser();
		user.setUserName(f.getUserName());
		user.setPassword(f.getPassword());
		user.setFamilyName(f.getFamilyName());
		user.setFirstName(f.getFirstName());
		user.setFamilyNameKana(f.getFamilyNameKana());
		user.setFirstNameKana(f.getFirstNameKana());
		user.setGender(f.getGender());
		
		int count=userMapper.insert(user);	
	
		return count>0;
	}
	

}
