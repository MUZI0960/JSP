package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of="buyerId")
public class BuyerVO implements Serializable {
	private int rnum;
	@NotBlank(groups = {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank
	private String buyerName;
	
	@NotBlank
	private String buyerLgu;
	private String lprodNm;
	
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	@Size(min=3, max = 20)
	@Email
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private List<ProdVO> prodList; // has many - 1:N
	
	private int prodCount;
	
	// 파일 업로드 처리 추가
	private byte[] buyerImg;
	private MultipartFile buyerImage;
	public void setBuyerImage(MultipartFile buyerImage) throws IOException {
		if(buyerImage==null || buyerImage.isEmpty()) return;
		this.buyerImage = buyerImage;
		this.buyerImg = buyerImage.getBytes();
	}
	public String getBase64BuyerImg() {
		if(buyerImg==null) return null;
		else return Base64.getEncoder().encodeToString(buyerImg);
	}
	
	private Integer buyerContract;
	private MultipartFile contractFile;
	private AttatchFileVO atchContract;
	public void setContractFile(MultipartFile contractFile) {
		if(contractFile==null || contractFile.isEmpty()) return;
		this.contractFile = contractFile;
		atchContract = new AttatchFileVO(contractFile);
	}
	
}















