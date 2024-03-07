package org.shah.springapp.ems.Services;

import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandMemberService {

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findMemberForDemand(long demandId){
        Demand demand = demandRepository.findById(demandId).orElse(null);
        if(demand != null){
            String positionLevel = String.valueOf(demand.getLevel());
            String city = demand.getCity();
            List<Member> allMembers = memberRepository.findAll();
            return allMembers.stream().filter(member -> {
                return member.getPositionLevel().equals(positionLevel) && member.getLocation().equalsIgnoreCase(city);
            }).collect(Collectors.toList());
        }else{
            return Collections.emptyList();
        }
    }
}
