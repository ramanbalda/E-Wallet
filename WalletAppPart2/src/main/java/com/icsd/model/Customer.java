package com.icsd.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer 
{
	@Id
	@GeneratedValue
	int customerId;
	String firstName;
	String lastName;
	String emailId;
	String contactNo;
	
	@OneToOne
	@JoinColumn(name="addressFk")
	Address address;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name="pwd")
	String password;
	@Transient
	String confirmPassword;
	private LocalDate registerationDate;
	
	@OneToOne
	private CustomerDocuments cstd;
	
	@OneToMany(targetEntity=Account.class,mappedBy="customer")
	private List<Account> accoutns=new ArrayList<Account>();
	
	
	
	


	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", contactNo=" + contactNo + ", address=" + address + ", gender=" + gender
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", registerationDate="
				+ registerationDate + "]";
	}






	public Customer(String firstName, String lastName, String emailId, String contactNo, Address address, Gender gender,
			String password, String confirmPassword, LocalDate registerationDate, CustomerDocuments cstd,
			List<Account> accoutns) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.address = address;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.registerationDate = registerationDate;
		this.cstd = cstd;
		this.accoutns = accoutns;
	}

}