package com.kobe.nucleus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.ClientDTO;

public interface CustomizedClientService {
	Page<ClientDTO> findAll(String search,TypeClient typeClient, Pageable pageable);

}
