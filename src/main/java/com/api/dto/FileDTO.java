package com.api.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Edmilson
 *
 */
@Getter
@Setter
public class FileDTO {

	private Long id;

	private String name;

	private String type;

	private byte[] file;

}
