package com.kobe.nucleus.repository;

import java.time.Instant;

import com.kobe.nucleus.config.Constants;
import com.kobe.nucleus.domain.Magasin;
import com.kobe.nucleus.domain.Rayon;
import com.kobe.nucleus.domain.User;
import com.kobe.nucleus.domain.enumeration.Status;
import com.kobe.nucleus.service.dto.MagasinDTO;
import com.kobe.nucleus.service.dto.MagasinInfos;

public interface CustomizedMagasinService {
	MagasinDTO save(MagasinDTO dto) throws Exception;

	MagasinDTO update(MagasinDTO dto) throws Exception;

	MagasinDTO update(MagasinInfos dto) throws Exception;

	MagasinDTO updateManager(Long id, Long managerId) throws Exception;
	
	MagasinDTO updateStockage(MagasinDTO dto) throws Exception;

	default User fromManagerId(Long id) {
		if (id != null) {
			User user = new User();
			user.setId(id);
			return user;
		}
		return null;
	}

	default Magasin fromId(Long id,boolean autonome) {
		if(!autonome) {
			
				Magasin magasin = new Magasin();
				magasin.setId(Constants.PRINCIPAL);
				return magasin;
			
		}
		
		return null;
	}

	default Magasin buildMagasinFromDTO(MagasinDTO dto) {
		Magasin magasin = new Magasin();
		magasin.setAddressePostal(dto.getAddressePostal());
		magasin.setAutonome(dto.isAutonome());
		magasin.setAutreCommentaire(dto.getAutreCommentaire());
		magasin.setCentreImposition(dto.getCentreImposition());
		magasin.setCommentaire(dto.getCommentaire());
		magasin.setCompteContribuable(dto.getCompteContribuable());
		magasin.setEntete(dto.getEntete());
		magasin.setPhone(dto.getPhone());
		magasin.setMobile(dto.getMobile());
		magasin.setMagasin(fromId(dto.getMagasinId(),dto.isAutonome()));
		magasin.setManager(fromManagerId(dto.getManagerId()));
		magasin.setNumComptable(dto.getNumComptable());
		magasin.setNomLong(dto.getNomLong());
		magasin.setNomCourt(dto.getNomLong());
		magasin.setTypeMagasin(dto.getTypeMagasin());
		magasin.setRegistreCommerce(dto.getRegistreCommerce());
		magasin.setRegistreImposition(dto.getRegistreImposition());
		return magasin;

	}

	default Magasin buildMagasinFromDTO(MagasinInfos dto, Magasin magasin) {
		magasin.setAutreCommentaire(dto.getAutreCommentaire());
		magasin.setCommentaire(dto.getCommentaire());
		magasin.setEntete(dto.getEntete());
		return magasin;

	}

	default Magasin buildStockageFromDTO(MagasinDTO dto, Magasin magasin) {
		magasin.setNomLong(dto.getNomLong());
		magasin.setNomCourt(dto.getNomLong());
		magasin.setTypeMagasin(dto.getTypeMagasin());
		magasin.setAutonome(false);
		return magasin;

	}

	default Magasin buildMagasinFromDTO(MagasinDTO dto, Magasin magasin) {

		magasin.setAddressePostal(dto.getAddressePostal());
		magasin.setAutonome(dto.isAutonome());
		magasin.setAutreCommentaire(dto.getAutreCommentaire());
		magasin.setCentreImposition(dto.getCentreImposition());
		magasin.setCommentaire(dto.getCommentaire());
		magasin.setCompteContribuable(dto.getCompteContribuable());
		magasin.setEntete(dto.getEntete());
		magasin.setPhone(dto.getPhone());
		magasin.setMobile(dto.getMobile());
		magasin.setMagasin(fromId(dto.getMagasinId(),dto.isAutonome()));
		magasin.setManager(fromManagerId(dto.getManagerId()));
		magasin.setNumComptable(dto.getNumComptable());
		magasin.setNomLong(dto.getNomLong());
		magasin.setNomCourt(dto.getNomLong());
		magasin.setTypeMagasin(dto.getTypeMagasin());
		magasin.setRegistreCommerce(dto.getRegistreCommerce());
		magasin.setRegistreImposition(dto.getRegistreImposition());
		return magasin;

	}
	
	default Rayon defaultRayon() {
		Rayon rayon=new Rayon();
		rayon.setCode("SANS");
		rayon.setLibelle("SANS EMPLACEMENT");
		rayon.setStatus(Status.ACTIVE);
		rayon.setCreatedAt(Instant.now());
		rayon.setUpdatedAt(Instant.now());
		return rayon;
		
	}
}
