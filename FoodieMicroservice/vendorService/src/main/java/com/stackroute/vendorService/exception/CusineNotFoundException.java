package com.stackroute.vendorService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cusine not found")
public class CusineNotFoundException extends Exception{
}
