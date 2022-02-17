package com.stackroute.FavouriteService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Customer Already Exists")
public class CustomerAlreadyExistException extends Exception{
}
