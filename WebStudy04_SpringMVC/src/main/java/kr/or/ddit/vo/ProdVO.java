package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * DataMapper 를 이용해 2개의 테이블을 조인하는 방법.
 * Relation -> has 관계 반영.
 * 1:1 -> has A
 * 1:N -> has Many
 * 주의 ! 메인테이블을 1 로 놓고 관계 판단.
 * 
 * ex) PROD BUYER (1:1) - 상품의 기본정보와 해당 거래처의 정보 조회
 * 	HAS A : ProdVO has a BuyerVO
 * 		PROD MEMBER (1:N) - 상품의 기본 정보와 구매자목록 정보 조회
 *  HAS MANY : ProdVO has many MemberVO
 * 
 * 
 * 1. 각 테이블로부터 데이터를 바인딩할 VO 설계
 * 2. 테이블간의 관계 파악
 * 3. 테이블간의 관계를 VO 반영.
 * 4. resultType -> resultMap 사용.
 * 	1:1(has a) - assocation 
 *  1:N(has many) - collection (중복 제거를 위한 id 설정 필요)
 * 
 */
@Data
@EqualsAndHashCode(of = "prodId")
@ToString(exclude = "prodDetail")
public class ProdVO implements Serializable{
	private int rnum;
	@NotBlank(groups = UpdateGroup.class)
	@Size(max = 10, groups = UpdateGroup.class)
	private String prodId;
	@NotBlank
	@Size(max = 40)
	private String prodName;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 4, groups = InsertGroup.class)
	private String prodLgu;
	private String lprodNm;
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 6, groups = InsertGroup.class)
	private String prodBuyer;
	
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	@Size(max = 100)
	private String prodOutline;
	private String prodDetail;
	
	@NotBlank(groups = InsertGroup.class)
	@Size(max = 40, groups = InsertGroup.class)
	private String prodImg;
	
	private MultipartFile prodImage;
	public void setProdImage(MultipartFile prodImage) {
		if(prodImage==null || prodImage.isEmpty()) return;
		this.prodImage = prodImage;
		this.prodImg = UUID.randomUUID().toString();
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(StringUtils.isNotBlank(prodImg)) {
			prodImage.transferTo(new File(saveFolder, prodImg));
		}
	}
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;	
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate prodInsdate;
	
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	@Size(max = 20)
	private String prodSize;
	@Size(max = 20)
	private String prodColor;
	@Size(max = 255)
	private String prodDelivery;
	@Size(max = 6)
	private String prodUnit;
	@Min(0)
	private Integer prodQtyin;
	@Min(0)
	private Integer prodQtysale;
	@Min(0)
	private Integer prodMileage;
	
	private int memCount;
	
	private BuyerVO buyer; // has a - 1:1
	
	private Set<MemberVO> memberList; // has many - 1:N
}

















