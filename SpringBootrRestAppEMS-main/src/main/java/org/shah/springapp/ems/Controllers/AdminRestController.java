package org.shah.springapp.ems.Controllers;

import org.shah.springapp.ems.Domains.Demand;
import org.shah.springapp.ems.Domains.Member;
import org.shah.springapp.ems.Exceptions.MemberNotFoundException;
import org.shah.springapp.ems.Repository.DemandRepository;
import org.shah.springapp.ems.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DemandRepository demandRepository;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable("id") long id) {
        Optional<Member> memberData = memberRepository.findById(id);

        if (memberData.isPresent()) {
            return new ResponseEntity<>(memberData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @PutMapping ("/members/{id}")
    public ResponseEntity<Member> updateTutorial(@PathVariable long id, @RequestBody Member updatedMember) {
        Optional<Member> memberData = memberRepository.findById(id);
        if (memberData.isPresent()) {
            Member existingMember= memberData.get();
            existingMember.setDateOfJoining(updatedMember.getDateOfJoining());
            existingMember.setExperience(updatedMember.getExperience());
//          existingMember.setId(updatedMember.getId());
            existingMember.setLocation(updatedMember.getLocation());
            existingMember.setEmployeeId(updatedMember.getEmployeeId());
            existingMember.setFirstName(updatedMember.getFirstName());
            existingMember.setLastName((updatedMember.getLastName()));
            existingMember.setPositionLevel(updatedMember.getPositionLevel());
            existingMember.setStatus(updatedMember.getStatus());
            existingMember.setSkills(updatedMember.getSkills());
            return new ResponseEntity<>(memberRepository.save(existingMember), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<HttpStatus> deleteMember(@PathVariable long id) {
        try {
            memberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/demands")
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    @GetMapping("/demands/{id}")
    public ResponseEntity<Demand> getDemandById(@PathVariable("id") long id) {
        Optional<Demand> demandData = demandRepository.findById(id);

        if (demandData.isPresent()) {
            return new ResponseEntity<>(demandData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
