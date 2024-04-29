package ru.itmentor.spring.boot_security.demo.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class StartedTableData implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    @Autowired
    public StartedTableData(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminrole = new Role(1L, "ROLE_ADMIN");
        Role userrole = new Role(2L, "ROLE_USER");
        roleRepository.save(adminrole);
        roleRepository.save(userrole);

        Set<Role> admin_roles = new HashSet<>();
        admin_roles.add(adminrole);


        User admin = new User(1L, "admin", 48, "admin@ex.com",  "$2a$12$CkYDNhCIwhQ84mBG6aMUn.Gh5dvtot9oVE/KA2ECZGPMxXgV6B7WK", admin_roles);
        userRepository.save(admin);

        Set<Role> user_roles = new HashSet<>();
        user_roles.add(userrole);

        User user = new User(2L, "user", 22, "user@ex.com", "$2a$12$aIaaGcL34A1CzRWIhB2XSOcikogHhQ2zMiUPvZ8T.Bk7gmc8BZPyu", user_roles);
        userRepository.save(user);

    }
    // $2a$12$CkYDNhCIwhQ84mBG6aMUn.Gh5dvtot9oVE/KA2ECZGPMxXgV6B7WK
    // $2a$12$aIaaGcL34A1CzRWIhB2XSOcikogHhQ2zMiUPvZ8T.Bk7gmc8BZPyu
}
