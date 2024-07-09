package com.example.rentACar;

import com.example.rentACar.common.utilities.exceptions.BusinessException;
import com.example.rentACar.common.utilities.exceptions.ProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}

	// Bu metot bir exception handlerdır.
	// Yani business exception oluşunca olduğu gibi UI'ya vermemeliyiz bir filtreleme uygulanarak vermeliyiz.
	// ModelMapper Ioc'e eklenecek bir nesnesini üretmez bizim üretip koymamız gerekir.


	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(BusinessException businessException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());

		return problemDetails;
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage("VALIDATION.EXCEPTION");

		return problemDetails;
	}


	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	// Son durum: API'de car üzerinde işlem yapamama sorununu çözdüm. Yeni metotlar ekledim.
	// ToDo: Daha efektif şekilde tasarlanabilir mi bunu düşün. örneğin günlük üzret ve model yılının default 0 olmaması gibi.


}
