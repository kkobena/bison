package com.kobe.nucleus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.service.dto.VenteDTO;

public interface CustomizedClientService {
	List<ClientDTO> findAll(String search, TypeClient typeClient, Status status);

	Integer encoursClient(long clientId);

	ClientDTO save(ClientDTO client);

	ClientDTO update(ClientDTO client);

	Page<VenteDTO> findVenteByClientId(long clientId,Pageable pageable);
}
