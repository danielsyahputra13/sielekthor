package apap.tugas.sielekthor.service;

import apap.tugas.sielekthor.model.MemberModel;
import apap.tugas.sielekthor.repository.MemberDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.*;

@Service
@Transactional
public class MemberServiceImpl implements MemberService{
    @Autowired
    MemberDb memberDb;

    @Override
    public void addMember(MemberModel member) {
        memberDb.save(member);
    }

    @Override
    public List<MemberModel> getListMember() {
        return memberDb.findAll();
    }

    @Override
    public MemberModel getMemberByIdMember(Long idMember) {
        Optional<MemberModel> member = memberDb.findByIdMember(idMember);
        if (member.isPresent()) return member.get();
        return null;
    }

    @Override
    public MemberModel ubahMember(MemberModel member) {
        memberDb.save(member);
        return member;
    }

    @Override
    public List<MemberModel> getMemberPalingBanyakBeli() {
        List<MemberModel> listMember = memberDb.findAll();
        Collections.sort(listMember);
        return listMember;
    }

}
