package com.prueba.client.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Client {

	@Id
	@NotEmpty
    @Column(name = "sharedKey")
    private String sharedKey; 
	
	@NotEmpty
	@Column(name = "businessId")
    private String businessId;
	
	@NotEmpty
	@Column(name = "email")
    private String email; 
	
	@NotEmpty
	@Column(name = "phone")
    private String phone; 
	
	@CreatedDate
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

}
