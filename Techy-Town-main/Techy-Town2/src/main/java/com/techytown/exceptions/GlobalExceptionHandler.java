package com.techytown.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


import javax.persistence.RollbackException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<MyErrorDetails> sqIntegrityHandler(SQLIntegrityConstraintViolationException ce,WebRequest wr){
		MyErrorDetails err = new MyErrorDetails(ce.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(CardException.class)
	public ResponseEntity<MyErrorDetails> cardExceptionHandler(CardException ce,WebRequest wr){
		MyErrorDetails err = new MyErrorDetails(ce.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> orderException(OrderException oe,WebRequest wr){
		MyErrorDetails err = new MyErrorDetails(oe.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> cartException(CartException ce,WebRequest wr){
		MyErrorDetails err = new MyErrorDetails(ce.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> productException(ProductException pe,WebRequest wr){
		MyErrorDetails err = new MyErrorDetails(pe.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(CategoryException.class)
	public ResponseEntity<MyErrorDetails> categoryException(CategoryException ce,WebRequest wr){
		MyErrorDetails err = new MyErrorDetails(ce.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<MyErrorDetails> userExceptionHandler(UsernameNotFoundException ue,WebRequest wr) {
		MyErrorDetails err = new MyErrorDetails(ue.getMessage(), wr.getDescription(true),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException manv,
			WebRequest wr) {
		String message = manv.getBindingResult().getFieldError().getDefaultMessage();
		MyErrorDetails err = new MyErrorDetails(message, wr.getDescription(false),LocalDateTime.now() );
		return new ResponseEntity<>(err, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExpHandlerMain(Exception ie, WebRequest wr) {
		MyErrorDetails err = new MyErrorDetails(ie.getMessage(), wr.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}
	
	//ConversionFailedException
	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<MyErrorDetails> myIllegalHandler(ConversionFailedException me, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(me.getMessage(), req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	//IllegalArgumentException
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyErrorDetails> IllegalArgumentExceptionHandler(IllegalArgumentException me, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails( me.getMessage(), req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<MyErrorDetails> handleRollbackException(Exception exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails("Improper arguments passed in jason. Validation failed", req.getDescription(false),LocalDateTime.now());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> noExceptionFoundHandler(NoHandlerFoundException nfe, WebRequest req) {
		System.out.println("Inside No Handler Found Exception. Exception is being handled");
		MyErrorDetails err = new MyErrorDetails( nfe.getMessage(), req.getDescription(false),LocalDateTime.now());
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

	}
	
}
