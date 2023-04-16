package kr.or.ddit.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.validate.DeleteGroup;

public class BuyerVOTest {
	private static Validator validator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(
		                        new PlatformResourceBundleLocator( "kr/or/ddit/msg/ErrorMessages" )
		                )
		        ).buildValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void test() {
		BuyerVO buyer = new BuyerVO();
//		buyer.setBuyerName("삼성");
		buyer.setBuyerMail("el");
		Set<ConstraintViolation<BuyerVO>> violations = validator.validate(buyer, DeleteGroup.class, Default.class);
		Map<String, List<String>> errors = new LinkedHashMap<>();
		if(violations.isEmpty()) {
			System.out.println("검증 통과");
		}else {
			for( ConstraintViolation<BuyerVO> single : violations) {
				String propertyName = single.getPropertyPath().toString();
				String message =  single.getMessage();
				List<String> already = errors.get(propertyName);
				if(already==null) {
					already = new ArrayList<>();
				}
				already.add(message);
				errors.put(propertyName, already);
			}
			errors.forEach((k,v)->{
				System.out.printf("%s : %s\n", k, v);
			});
		}
	}

}



















