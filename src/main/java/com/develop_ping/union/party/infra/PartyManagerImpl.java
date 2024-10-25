package com.develop_ping.union.party.infra;

import com.develop_ping.union.party.domain.PartyManager;
import com.develop_ping.union.party.domain.dto.PartyInfo;
import com.develop_ping.union.party.domain.entity.Party;
import com.develop_ping.union.party.domain.entity.PartyRole;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PartyManagerImpl implements PartyManager {

    private final PartyRepository partyRepository;

    @Override
    public PartyInfo createParty(Long gatheringId, Long userId) {

        Party savedParty = partyRepository.save(Party.builder()
                                                     .userId(userId)
                                                     .gatheringId(gatheringId)
                                                     .role(PartyRole.OWNER)
                                                     .build());
        return PartyInfo.of(savedParty);
    }
}
