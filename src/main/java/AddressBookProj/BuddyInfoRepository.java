package AddressBookProj;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Integer> {
    BuddyInfo findById(int id);
    List<BuddyInfo> findByName(String name);
    void deleteById(int id);
}
