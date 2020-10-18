package com.kobe.nucleus.repository;

import com.kobe.nucleus.domain.Client;
import com.kobe.nucleus.service.dto.ClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface Iclient {
    Page<ClientDTO> findAllByTiersPayant(@NotNull Integer tiersPayantId , String search, Pageable pageable);

}
