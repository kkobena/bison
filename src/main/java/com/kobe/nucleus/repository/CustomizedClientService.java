package com.kobe.nucleus.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kobe.nucleus.domain.AyantDroit;
import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.domain.CompteClient;
import com.kobe.nucleus.domain.Tierspayant;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.domain.enumeration.TypeClient;
import com.kobe.nucleus.service.dto.AyantDroitDTO;
import com.kobe.nucleus.service.dto.ClientDTO;
import com.kobe.nucleus.service.dto.CompteClientDTO;
import com.kobe.nucleus.service.dto.VenteDTO;

public interface CustomizedClientService {
	List<ClientDTO> findAll(String search, TypeClient typeClient, Status status);

	Integer encoursClient(long clientId);

	ClientDTO save(ClientDTO client) throws Exception;

	ClientDTO update(ClientDTO client) throws Exception;

	CompteClientDTO save(CompteClientDTO compteClient) throws Exception;

	CompteClientDTO update(CompteClientDTO compteClient) throws Exception;

	AyantDroitDTO save(AyantDroitDTO ayantDroit) throws Exception;

	AyantDroitDTO update(AyantDroitDTO ayantDroit) throws Exception;

	ClientDTO findById(Long id);

	Page<VenteDTO> findVenteByClientId(long clientId, Pageable pageable);

	default Client buildClientFromClientDTO(ClientDTO dto) {
		Client client = new Client();
		client.setFirstName(dto.getFirstName());
		client.setLastName(dto.getLastName());
		client.setMail(dto.getMail());
		client.setMobile(dto.getMobile());
		client.setSexe(dto.getSexe());
		client.setDatNaiss(dto.getDatNaiss());
		client.setCreatedAt(LocalDate.now());
		client.setUpdatedAt(LocalDate.now());
		client.setStatus(Status.ACTIVE);
		return client;
	}

	default Client buildClientFromClientDTO(ClientDTO dto, Client client) {
		client.setFirstName(dto.getFirstName());
		client.setLastName(dto.getLastName());
		client.setMail(dto.getMail());
		client.setMobile(dto.getMobile());
		client.setSexe(dto.getSexe());
		client.setDatNaiss(dto.getDatNaiss());
		client.setUpdatedAt(LocalDate.now());
		client.setStatus(Status.ACTIVE);
		return client;
	}

	default AyantDroit buildAyantDroitFromAyantDroitDTO(AyantDroitDTO dto, AyantDroit ayantDroit) {
		ayantDroit.setFirstName(dto.getFirstName());
		ayantDroit.setLastName(dto.getLastName());
		ayantDroit.setMobile(dto.getMobile());
		ayantDroit.setSexe(dto.getSexe());
		ayantDroit.setNum(dto.getNum());
		ayantDroit.setDatNaiss(dto.getDatNaiss());
		ayantDroit.setUpdatedAt(LocalDate.now());
		ayantDroit.setStatus(Status.ACTIVE);
		return ayantDroit;
	}

	default AyantDroit buildAyantDroitFromAyantDroitDTO(AyantDroitDTO dto) {
		AyantDroit ayantDroit = new AyantDroit();
		ayantDroit.setFirstName(dto.getFirstName());
		ayantDroit.setLastName(dto.getLastName());
		ayantDroit.setMobile(dto.getMobile());
		ayantDroit.setSexe(dto.getSexe());
		ayantDroit.setDatNaiss(dto.getDatNaiss());
		ayantDroit.setCreatedAt(LocalDate.now());
		ayantDroit.setUpdatedAt(LocalDate.now());
		ayantDroit.setStatus(Status.ACTIVE);
		ayantDroit.setNum(dto.getNum());
		return ayantDroit;
	}

	default CompteClient buildCompteClientFromCompteClientDTO(CompteClientDTO dto) {
		CompteClient compteClient = new CompteClient();
		compteClient.setAbsolute(dto.isAbsolute());
		compteClient.setCategorie(dto.getCategorie());
		compteClient.setConsoJournaliere(dto.getConsoJournaliere());
		compteClient.setConsommation(dto.getConsommation());
		compteClient.setPlafondJournalier(dto.getPlafondJournalier());
		compteClient.setPlafondMensuel(dto.getPlafondMensuel());
		compteClient.setEncours(0);
		compteClient.setEnbale(true);
		compteClient.setTypeClient(dto.getTypeClient());
		compteClient.setCreatedAt(Instant.now());
		compteClient.setUpdatedAt(Instant.now());
		compteClient.setNumMaticule(dto.getNumMaticule());
		compteClient.setTaux(dto.getTaux());
		compteClient.setTierspayant(fromId(dto.getTierspayantId()));
		return compteClient;
	}

	default CompteClient buildCompteClientFromCompteClientDTO(CompteClientDTO dto, CompteClient compteClient) {
		compteClient.setAbsolute(dto.isAbsolute());
		compteClient.setCategorie(dto.getCategorie());
		compteClient.setConsoJournaliere(dto.getConsoJournaliere());
		compteClient.setPlafondJournalier(dto.getPlafondJournalier());
		compteClient.setPlafondMensuel(dto.getPlafondMensuel());
		compteClient.setEncours(dto.getEncours());
		compteClient.setTypeClient(dto.getTypeClient());
		compteClient.setUpdatedAt(Instant.now());
		compteClient.setNumMaticule(dto.getNumMaticule());
		compteClient.setTaux(dto.getTaux());
		compteClient.setTierspayant(fromId(dto.getTierspayantId()));
		return compteClient;
	}

	default Tierspayant fromId(Long id) {
		if (id == null) {
			return null;
		}
		Tierspayant tierspayant = new Tierspayant();
		tierspayant.setId(id);
		return tierspayant;
	}
}
