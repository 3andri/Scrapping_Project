package com.rumah.btn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rumah.btn.model.BTN_rumah;
import com.rumah.btn.repo.rumahRepo;

@Controller
public class index {
	@Autowired
	rumahRepo rumahRepo;

	@GetMapping("/")
	String index(Model model) {
		List<BTN_rumah> rumah = (List<BTN_rumah>) rumahRepo.Custom("tangerang",false);
		rumah.forEach(data -> {
			System.out.println(data.getBtn_url());
		});
		model.addAttribute("rumah",rumah);
		return "index";
	}

}
