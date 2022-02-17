package com.stackroute.FavouriteService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Cusine Already Exists")
public class CusineAlreadyExistException extends Exception{
}
