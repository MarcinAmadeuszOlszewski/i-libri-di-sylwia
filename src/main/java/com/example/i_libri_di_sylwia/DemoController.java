package com.example.i_libri_di_sylwia;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DemoController {

	private static final String WORK_MESSAGE = "Its alive";

	@GetMapping(value = "/work", produces = MediaType.TEXT_PLAIN_VALUE)
	public String work() {
		return WORK_MESSAGE;
	}

}
