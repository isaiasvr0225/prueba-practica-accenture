package com.sprayl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpraylBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpraylBackendApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner init(ClientRepository clientRepository) {
        return args -> {
            // Populating the database with data

            // Creating the permission
            var createPermission = PermissionEntity
                    .builder()
                    .name("CREATE")
                    .build();

            var readPermission = PermissionEntity
                    .builder()
                    .name("READ")
                    .build();

            var updatePermission = PermissionEntity
                    .builder()
                    .name("UPDATE")
                    .build();

            var deletePermission = PermissionEntity
                    .builder()
                    .name("DELETE")
                    .build();

            // Creating the roles
            var adminRole = RoleEntity
                    .builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissionSet(Set.of(createPermission,
                            readPermission,
                            updatePermission,
                            deletePermission))
                    .build();

            var userRole = RoleEntity
                    .builder()
                    .roleEnum(RoleEnum.USER)
                    .permissionSet(Set.of(createPermission,
                            readPermission))
                    .build();

            var invitedRole = RoleEntity
                    .builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissionSet(Set.of(readPermission))
                    .build();

            var developerRole = RoleEntity
                    .builder()
                    .roleEnum(RoleEnum.DEV)
                    .permissionSet(Set.of(createPermission,
                            readPermission,
                            updatePermission,
                            deletePermission))
                    .build();
        };
    } */
}
