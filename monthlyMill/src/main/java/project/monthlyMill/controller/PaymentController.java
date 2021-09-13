package project.monthlyMill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/payment")
public class PaymentController {
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
}
