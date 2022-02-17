package com.stackroute.FavouriteService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Resturant Already Exists")
public class ResturantAlreadyExistException extends Exception{
}
