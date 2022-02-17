package com.stackroute.FoodieService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "directory already exists")
public class DirectoryAlreadyExistException extends Exception{
}
